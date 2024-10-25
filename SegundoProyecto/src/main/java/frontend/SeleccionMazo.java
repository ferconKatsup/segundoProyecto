/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

/**
 *
 * @author fer
 */
import backend.Elemento;
import backend.GestorCartas;
import backend.carta.Carta;
import backend.carta.CartaEnergia;
import backend.carta.CartaHechizo;
import backend.carta.CartaMonstruo;
import backend.listasEnlazadas.ListaEnlazadaCartas;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SeleccionMazo extends JFrame {
    private ListaEnlazadaCartas mazo;  // Lista enlazada para el mazo del jugador
    private DefaultListModel<String> cartasModel;  // Modelo para mostrar las cartas disponibles
    private DefaultListModel<String> mazoModel;  // Modelo para mostrar las cartas del mazo
    private JList<String> listaCartas;  // Lista para mostrar las cartas disponibles
    private JList<String> listaMazo;    // Lista para mostrar las cartas agregadas al mazo
    private JButton btnAgregarCarta;
    private JButton btnConfirmarMazo;

    // Contadores para limitar el número de cartas por tipo
    private int contadorMonstruos = 0;
    private int contadorEnergias = 0;
    private int contadorHechizos = 0;

    // Constructor de la ventana de selección de mazo
    public SeleccionMazo() {
        mazo = new ListaEnlazadaCartas();
        cartasModel = new DefaultListModel<>();
        mazoModel = new DefaultListModel<>();
        listaCartas = new JList<>(cartasModel);
        listaMazo = new JList<>(mazoModel);

        // Agregar algunas cartas de ejemplo (esto debería reemplazarse con la carga de cartas reales)
        cargarCartas();

        // Configuración de la interfaz gráfica
        setTitle("Seleccionar Cartas para el Mazo");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear botón para agregar carta seleccionada al mazo
        btnAgregarCarta = new JButton("Agregar Carta al Mazo");
        btnAgregarCarta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCartaAlMazo();
            }
        });

        // Botón para confirmar la construcción del mazo
        btnConfirmarMazo = new JButton("Confirmar Mazo");
        btnConfirmarMazo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmarMazo();
            }
        });

        // Layout de la ventana
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Cartas disponibles:"));
        add(new JScrollPane(listaCartas));
        add(btnAgregarCarta);
        add(new JLabel("Cartas agregadas al mazo:"));
        add(new JScrollPane(listaMazo));
        add(btnConfirmarMazo);

        pack();
        setVisible(true);
    }

    // Método para agregar una carta seleccionada al mazo del jugador
    private void agregarCartaAlMazo() {
        String cartaSeleccionada = listaCartas.getSelectedValue();
        if (cartaSeleccionada != null) {
            Carta carta = crearCartaDesdeNombre(cartaSeleccionada);

            // Verificamos el tipo de carta y aplicamos restricciones
            if (carta instanceof CartaMonstruo && contadorMonstruos >= 3) {
                JOptionPane.showMessageDialog(this, "Ya tienes 3 cartas de Monstruo en el mazo.");
                return;
            } else if (carta instanceof CartaEnergia && contadorEnergias >= 10) {
                JOptionPane.showMessageDialog(this, "Ya tienes 10 cartas de Energía en el mazo.");
                return;
            }

            // Agregar carta al mazo
            mazo.agregarCarta(carta);
            mazoModel.addElement(carta.getNombre());

            // Incrementar los contadores según el tipo de carta
            if (carta instanceof CartaMonstruo) {
                contadorMonstruos++;
            } else if (carta instanceof CartaEnergia) {
                contadorEnergias++;
            } else if (carta instanceof CartaHechizo) {
                contadorHechizos++;
            }

            JOptionPane.showMessageDialog(this, carta.getNombre() + " agregada al mazo.");
        }
    }

    // Método para confirmar el mazo (verifica que haya al menos 30 cartas)
    private void confirmarMazo() {
        if (mazo.getTamaño() < 30) {
            JOptionPane.showMessageDialog(this, "El mazo debe tener al menos 30 cartas.");
            return;
        }
        JOptionPane.showMessageDialog(this, "Mazo confirmado con " + mazo.getTamaño() + " cartas.");
        // Guardar el mazo en un archivo binario
        GestorCartas.guardarCarta(mazo, "mazoJugador.bin");
    }
     // Método para crear cartas desde el nombre seleccionado
      private Carta crearCartaDesdeNombre(String nombre) {
        switch (nombre) {
            case "Charizard - Monstruo":
                return new CartaMonstruo("Charizard", Elemento.FUEGO, 150, 100);
            case "Pikachu - Monstruo":
                return new CartaMonstruo("Pikachu", Elemento.ELECTRICIDAD, 60, 50);
            case "Lanzallamas - Hechizo":
                return new CartaHechizo("Lanzallamas", "Inflige 50 de daño");
            case "Energía Fuego - Energía":
                return new CartaEnergia("Energía Fuego", Elemento.FUEGO);
            default:
                return null;
        }
    }
// Método para cargar las cartas disponibles
    private void cargarCartas() {
       cartasModel.addElement("Charizard - Monstruo");
        cartasModel.addElement("Pikachu - Monstruo");
        cartasModel.addElement("Lanzallamas - Hechizo");
        cartasModel.addElement("Energía Fuego - Energía");  }

}

