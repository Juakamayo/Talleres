import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Crear usuario
class Usuario {
    private String username;
    private String password;
    private String nombre;

    public Usuario(String username, String password, String nombre) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
    }

    public boolean validarCredenciales(String u, String p) {
        return this.username.equals(u) && this.password.equals(p);
    }

    public String getNombre() {
        return nombre;
    }
}

// Ventana registro
class VentanaRegistro extends JFrame {
    private final JTextField txtUsuario = new JTextField();
    private final JPasswordField txtClave = new JPasswordField();
    private final JTextField txtNombre = new JTextField();
    private final JButton btnRegistrar = new JButton("Registrar");

    public VentanaRegistro() {
        super("Registro - Casino Black Cat");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));
        setLocationRelativeTo(null);

        add(new JLabel("Usuario:"));
        add(txtUsuario);
        add(new JLabel("Clave:"));
        add(txtClave);
        add(new JLabel("Nombre completo:"));
        add(txtNombre);
        add(btnRegistrar);

        btnRegistrar.addActionListener(e -> registrarUsuario());
    }

    public void mostrarVentana() {
        setVisible(true);
    }

    private void registrarUsuario() {
        String usuario = txtUsuario.getText().trim();
        String clave = new String(txtClave.getPassword());
        String nombre = txtNombre.getText().trim();

        if (usuario.isEmpty() || clave.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Todos los campos son obligatorios",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        VentanaLogin.USUARIOS.add(new Usuario(usuario, clave, nombre));

        JOptionPane.showMessageDialog(this,
                "Usuario registrado correctamente",
                "Registro Exitoso",
                JOptionPane.INFORMATION_MESSAGE);

        dispose();
        new VentanaLogin().mostrarVentana();
    }
}

// Ventana login
class VentanaLogin extends JFrame {

    public static final List<Usuario> USUARIOS = new ArrayList<>();

    private final JTextField txtUsuario = new JTextField();
    private final JPasswordField txtClave = new JPasswordField();

    public VentanaLogin() {
        super("Login - Casino Black Cat");

        USUARIOS.add(new Usuario("admin", "1234", "Administrador"));
        USUARIOS.add(new Usuario("Juakamayo", "abcd", "Joaquin Pizarro"));
        USUARIOS.add(new Usuario("FTgrimm", "pass", "Fernando Torres"));

        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));
        setLocationRelativeTo(null);

        JButton btnIngresar = new JButton("Ingresar");
        JButton btnRegistrar = new JButton("Registrar");

        add(new JLabel("Usuario:"));
        add(txtUsuario);
        add(new JLabel("Clave:"));
        add(txtClave);
        add(btnIngresar);
        add(btnRegistrar);

        btnIngresar.addActionListener(e -> login());
        btnRegistrar.addActionListener(e -> abrirRegistro());
    }

    public void mostrarVentana() {
        setVisible(true);
    }

    private void login() {
        String usuario = txtUsuario.getText().trim();
        String clave = new String(txtClave.getPassword());

        String nombre = validarCredenciales(usuario, clave);

        if (!nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Bienvenido " + nombre + "!",
                    "Login Exitoso",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();

            new RuletaGrafica().setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this,
                    "Usuario o clave incorrectos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private String validarCredenciales(String u, String p) {
        for (Usuario usuario : USUARIOS) {
            if (usuario.validarCredenciales(u, p)) {
                return usuario.getNombre();
            }
        }
        return "";
    }

    private void abrirRegistro() {
        dispose();
        new VentanaRegistro().mostrarVentana();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaLogin().mostrarVentana());
    }
}

// Clase del juego de la ruleta
class RuletaGrafica extends JFrame {

    // --- LÓGICA DEL JUEGO ---
    private static final int MAX_HISTORIAL = 100;
    private int[] historialNumeros = new int[MAX_HISTORIAL];
    private int[] historialApuestas = new int[MAX_HISTORIAL];
    private boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    private int historialSize = 0;
    private Random rng = new Random();
    private int[] numerosRojos = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};

    // --- COMPONENTES DE LA GUI ---
    private final JTextArea resultadoArea;
    private final JTextArea estadisticasArea;
    private final JTextField montoField;
    private final JComboBox<String> estiloApuesta;

    public RuletaGrafica() {
        super("Juego de la Ruleta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 450);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JPanel inputPanel = new JPanel(new GridLayout(4, 1, 5, 5));

        JLabel tituloLabel = new JLabel("Ruleta - ¡Haz tu Apuesta!");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 18));
        montoField = new JTextField(10);
        estiloApuesta = new JComboBox<>(new String[]{"Rojo", "Negro", "Par", "Impar"});
        JButton botonIniciarRonda = new JButton("Girar Ruleta");
        JButton botonEstadisticas = new JButton("Ver Estadísticas");

        resultadoArea = new JTextArea(10, 20);
        resultadoArea.setEditable(false);
        resultadoArea.setBorder(BorderFactory.createTitledBorder("Resultado de la Ronda"));

        estadisticasArea = new JTextArea(10, 20);
        estadisticasArea.setEditable(false);
        estadisticasArea.setBorder(BorderFactory.createTitledBorder("Estadísticas del Juego"));

        topPanel.add(tituloLabel);
        inputPanel.add(new JLabel("Monto a apostar:"));
        inputPanel.add(montoField);
        inputPanel.add(new JLabel("Estilo de Apuesta:"));
        inputPanel.add(estiloApuesta);
        inputPanel.add(botonIniciarRonda);
        inputPanel.add(botonEstadisticas);

        centerPanel.add(inputPanel);
        centerPanel.add(new JScrollPane(resultadoArea));

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(new JScrollPane(estadisticasArea), BorderLayout.SOUTH);

        add(mainPanel);

        botonIniciarRonda.addActionListener(e -> iniciarRonda());
        botonEstadisticas.addActionListener(e -> mostrarEstadisticas());
    }

    private void iniciarRonda() {
        try {
            int monto = Integer.parseInt(montoField.getText());
            String estiloStr = (String) estiloApuesta.getSelectedItem();
            char estilo = estiloStr.charAt(0);

            int numero = girarRuleta();
            boolean acierto = evaluarResultado(numero, Character.toUpperCase(estilo));

            registrarResultado(numero, monto, acierto);
            mostrarResultado(numero, estilo, monto, acierto);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarEstadisticas() {
        int totalRondas = historialSize;
        int totalPlata = 0;
        int aciertos = 0;
        int ganancia = 0;

        for (int i = 0; i < historialSize; i++) {
            totalPlata += historialApuestas[i];
            if (historialAciertos[i]) {
                aciertos++;
                ganancia += historialApuestas[i];
            } else {
                ganancia -= historialApuestas[i];
            }
        }

        StringBuilder stats = new StringBuilder();
        stats.append("Estadisticas: \n");
        stats.append("Cantidad de rondas jugadas: ").append(totalRondas).append("\n");
        stats.append("Total apostado: $").append(totalPlata).append("\n");
        stats.append("Total aciertos: ").append(aciertos).append("\n");
        if (totalRondas > 0) {
            stats.append(String.format("%% de acierto: %.2f%%\n", (aciertos * 100.0 / totalRondas)));
        }
        stats.append("Ganancia/Perdida neta: $").append(ganancia);

        estadisticasArea.setText(stats.toString());
    }

    private int girarRuleta() {
        return rng.nextInt(37);
    }

    private boolean evaluarResultado(int numero, char tipo) {
        if (numero == 0) return false;
        switch (tipo) {
            case 'R':
                return esRojo(numero);
            case 'N':
                return !esRojo(numero);
            case 'P':
                return numero % 2 == 0;
            case 'I':
                return numero % 2 != 0;
            default:
                return false;
        }
    }

    private boolean esRojo(int n) {
        for (int rojo : numerosRojos) {
            if (rojo == n) return true;
        }
        return false;
    }

    private void registrarResultado(int numero, int apuesta, boolean acierto) {
        if (historialSize < MAX_HISTORIAL) {
            historialNumeros[historialSize] = numero;
            historialApuestas[historialSize] = apuesta;
            historialAciertos[historialSize] = acierto;
            historialSize++;
        }
    }

    private void mostrarResultado(int numero, char tipo, int monto, boolean acierto) {
        StringBuilder resultado = new StringBuilder();
        resultado.append("Resultado \n\n");
        resultado.append("Número conseguido: ").append(numero).append("\n");
        String apuestaStr = "";
        if (tipo == 'R') apuestaStr = "Rojo";
        if (tipo == 'N') apuestaStr = "Negro";
        if (tipo == 'P') apuestaStr = "Par";
        if (tipo == 'I') apuestaStr = "Impar";

        resultado.append("Tu apuesta: ").append(apuestaStr).append(" por $").append(monto).append("\n");
        if (acierto) {
            resultado.append("¡Has ganado +$").append(monto).append("!");
        } else {
            resultado.append("Acabas de perder -$").append(monto).append(".");
        }
        resultadoArea.setText(resultado.toString());
    }

}