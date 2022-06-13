package com.aram.calculadora.vista;

import com.aram.calculadora.controlador.StateBasal;
import com.aram.calculadora.modelo.CalculadoraState;
import com.aram.calculadora.modelo.Simbolos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static java.util.Arrays.stream;
import static javax.swing.KeyStroke.getKeyStroke;
import static javax.swing.SwingConstants.RIGHT;

public final class VistaCalculadora extends JFrame {

    private static final String NO_SE_PUEDE_DIVIDIR_ENTRE_CERO = "No se puede dividir entre cero";

    private final JTextField campoTextoSuperior;
    private final JTextField campoTextoInferior;
    private final Map<String, AbstractButton> botones;

    // Patrón de diseño 'State'.
    // Manejaremos un estado 'basal' y un estado 'operacion activa'
    private CalculadoraState estado;

    public VistaCalculadora() {
        this.botones = new HashMap<>();
        this.campoTextoSuperior = new JTextField();
        this.campoTextoInferior = new JTextField();
        configurarEsteFrame();
        agregarTodosLosComponentes();
        this.estado = new StateBasal(this);
    }

    private void agregarTodosLosComponentes() {
        agregarCamposTextoAlFrame();
        agregarBotonesAlFrame();
    }

    private boolean esBotonOperacionMatematica(AbstractButton boton) {
        return switch (boton.getText()) {
            case Simbolos.SUMA,
                    Simbolos.RESTA,
                    Simbolos.MULTIPLICACION,
                    Simbolos.DIVISION          ->   true;
            default -> false;
        };
    }

    private void porCadaBoton(Predicate<AbstractButton> filtro, Consumer<AbstractButton> consumidorBoton) {
        botones.values().stream()
                .filter(filtro)
                .forEach(consumidorBoton);
    }

    private void colorearBoton(AbstractButton boton) {
        boton.setForeground(VistaCalculadoraPreferencias.COLOR_FUENTE_BOTONES);
        boton.setBackground(VistaCalculadoraPreferencias.COLOR_FONDO_BOTONES);
        boton.setBorder(VistaCalculadoraPreferencias.BORDE_BOTONES);
        boton.setBorderPainted(true);
    }

    private void configurarCamposTexto() {
        configurarCampoTexto(campoTextoSuperior,
                VistaCalculadoraPreferencias.FUENTE_CAMPO_TEXTO_SUPERIOR,
                VistaCalculadoraPreferencias.TEXTO_DEFAULT_SUPERIOR,
                VistaCalculadoraPreferencias.COLOR_FONDO_CAMPO_SUPERIOR);
        campoTextoSuperior.setForeground(VistaCalculadoraPreferencias.COLOR_FUENTE_CAMPO_SUPERIOR);

        configurarCampoTexto(campoTextoInferior,
                VistaCalculadoraPreferencias.FUENTE_CAMPO_TEXTO_INFERIOR,
                VistaCalculadoraPreferencias.TEXTO_DEFAULT_INFERIOR,
                VistaCalculadoraPreferencias.COLOR_FONDO_CAMPO_INFERIOR);
    }

    private void configurarCampoTexto(JTextField campoTexto, int fuente, String textoDefault, Color fondo) {
        campoTexto.setFont(fuente(fuente));
        campoTexto.setBackground(fondo);
        campoTexto.setColumns(VistaCalculadoraPreferencias.COLUMNAS_CAMPOS_TEXTO);
        campoTexto.setEditable(VistaCalculadoraPreferencias.CAMPOS_TEXTO_EDITABLES);
        campoTexto.setText(textoDefault);
        campoTexto.setHorizontalAlignment(RIGHT);
    }

    private void asignarComportamientoBotones() {
        activarBotonesEspecificos();
        porCadaBoton(this::esBotonNumerico, this::activarBotonNumerico);
        porCadaBoton(this::esBotonOperacionMatematica, this::activarBotonOperacionMatematica);
        enlazarBotonesConTeclado();
    }

    private void enlazarBotonesConTeclado() {
        enlazarBotonesNumericosConTeclado();
        enlazarBotonesOperacionesConTeclado();
        enlazarBotonesEspecificosConTeclado();
    }

    private void enlazarBotonesNumericosConTeclado() {

        enlazarConTeclado("0", KeyEvent.VK_0);
        enlazarConTeclado("0", KeyEvent.VK_NUMPAD0);

        enlazarConTeclado("1", KeyEvent.VK_1);
        enlazarConTeclado("1", KeyEvent.VK_NUMPAD1);

        enlazarConTeclado("2", KeyEvent.VK_2);
        enlazarConTeclado("2", KeyEvent.VK_NUMPAD2);

        enlazarConTeclado("3", KeyEvent.VK_3);
        enlazarConTeclado("3", KeyEvent.VK_NUMPAD3);

        enlazarConTeclado("4", KeyEvent.VK_4);
        enlazarConTeclado("4", KeyEvent.VK_NUMPAD4);

        enlazarConTeclado("5", KeyEvent.VK_5);
        enlazarConTeclado("5", KeyEvent.VK_NUMPAD5);

        enlazarConTeclado("6", KeyEvent.VK_6);
        enlazarConTeclado("6", KeyEvent.VK_NUMPAD6);

        enlazarConTeclado("7", KeyEvent.VK_7);
        enlazarConTeclado("7", KeyEvent.VK_NUMPAD7);

        enlazarConTeclado("8", KeyEvent.VK_8);
        enlazarConTeclado("8", KeyEvent.VK_NUMPAD8);

        enlazarConTeclado("9", KeyEvent.VK_9);
        enlazarConTeclado("9", KeyEvent.VK_NUMPAD9);

    }

    private void enlazarBotonesOperacionesConTeclado() {

        enlazarConTeclado(Simbolos.SUMA, KeyEvent.VK_ADD);
        enlazarConTeclado(Simbolos.SUMA, KeyEvent.VK_PLUS);

        enlazarConTeclado(Simbolos.RESTA, KeyEvent.VK_MINUS);
        enlazarConTeclado(Simbolos.RESTA, KeyEvent.VK_SUBTRACT);

        enlazarConTeclado(Simbolos.MULTIPLICACION, KeyEvent.VK_MULTIPLY);
        enlazarConTeclado(Simbolos.MULTIPLICACION, KeyEvent.VK_ASTERISK);

        enlazarConTeclado(Simbolos.DIVISION, KeyEvent.VK_DIVIDE);

    }

    private void enlazarBotonesEspecificosConTeclado() {

        enlazarConTeclado(Simbolos.IGUAL, KeyEvent.VK_ENTER);

        enlazarConTeclado(Simbolos.PUNTO, KeyEvent.VK_PERIOD);
        enlazarConTeclado(Simbolos.PUNTO, KeyEvent.VK_DECIMAL);

        enlazarConTeclado(Simbolos.CE, KeyEvent.VK_DELETE);
        enlazarConTeclado(Simbolos.CE, KeyEvent.VK_BACK_SPACE);
        enlazarConTeclado(Simbolos.CE, KeyEvent.VK_SPACE);
    }

    private void activarBotonesEspecificos() {
        activarBoton(Simbolos.PUNTO, evento -> estado.manejarBotonSimboloPunto());
        activarBoton(Simbolos.IGUAL, evento -> estado.manejarBotonSimboloIgual());
        activarBoton(Simbolos.CE, this::funcionBotonCE);
    }

    private void activarBotonOperacionMatematica(AbstractButton boton) {
        boton.addActionListener(evento -> estado.manejarBotonOperacion(boton.getText()));
    }

    private void enlazarConTeclado(String simbolo, int codigoTecla) {
        String accion = "accion";
        obtenerBoton(simbolo).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(getKeyStroke(codigoTecla, 0), accion);

        obtenerBoton(simbolo).getActionMap().put(accion, accionTeclado(obtenerBoton(simbolo)));
    }

    private Action accionTeclado(AbstractButton boton) {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boton.doClick();
            }
        };
    }

    public String textoSuperior() {
        return campoTextoSuperior.getText();
    }

    private void funcionBotonCE(ActionEvent actionEvent) {
        resetearCaluladora();
    }

    private void resetearCaluladora() {
        limpiarCamposTexto();
        establecerBotonesOperacionesActivados(true);
        cambiarEstado(new StateBasal(this));
    }

    public void cambiarEstado(CalculadoraState estado) {
        this.estado = estado;
    }

    private void limpiarCamposTexto() {
        establecerTextoSuperior(null);
        establecerTextoInferior(Simbolos.CERO);
    }

    public void establecerTextoSuperior(String texto) {
        campoTextoSuperior.setText(texto);
    }

    private void activarBoton(String textoBoton, ActionListener accion) {
        AbstractButton boton = obtenerBoton(textoBoton);
        boton.addActionListener(accion);
    }

    private AbstractButton obtenerBoton(String textoBoton) {
        return botones.get(textoBoton);
    }

    private boolean esBotonNumerico(AbstractButton boton) {
        return esNumero(boton.getText());
    }

    private boolean esNumero(String texto) {
        return texto.matches("\\d");
    }

    private void activarBotonNumerico(AbstractButton boton) {
        boton.addActionListener(evento -> funcionBotonNumerico(boton));
    }

    private void funcionBotonNumerico(AbstractButton boton) {
        estado.manejarBotonNumerico(boton.getText());
    }

    public void establecerTextoInferior(String texto) {
        campoTextoInferior.setText(texto);
    }

    public String textoInferior() {
        return campoTextoInferior.getText();
    }

    private void agregarBotonesAlFrame() {
        maquetarBotones();
        asignarComportamientoBotones();
    }

    private void maquetarBotones() {
        agregarPrimeraFila();
        agregarSegundaFila();
        agregarTerceraFila();
        agregarCuartaFila();
        agregarQuintaFila();
        porCadaBoton(boton -> true, this::colorearBoton);
    }

    private void agregarPrimeraFila() {
        var botonCE = crearBoton(Simbolos.CE);
        var botonBarra = crearBoton(Simbolos.DIVISION);
        var botonAsterisco = crearBoton(Simbolos.MULTIPLICACION);
        var botonMenos = crearBoton(Simbolos.RESTA);
        agregarComponente(this, botonCE,        0, 2, 1, 1);
        agregarComponente(this, botonBarra,     1, 2, 1, 1);
        agregarComponente(this, botonAsterisco, 2, 2, 1, 1);
        agregarComponente(this, botonMenos,     3, 2, 1, 1);
        agregarAlMapaDeBotones(botonCE, botonBarra, botonAsterisco, botonMenos);
    }

    private void agregarSegundaFila() {
        var boton7 = crearBoton("7");
        var boton8 = crearBoton("8");
        var boton9 = crearBoton("9");
        var botonMas = crearBoton(Simbolos.SUMA);
        agregarComponente(this, boton7,    0, 3, 1, 1);
        agregarComponente(this, boton8,    1, 3, 1, 1);
        agregarComponente(this, boton9,    2, 3, 1, 1);
        agregarComponente(this, botonMas,  3, 3, 2, 1);
        agregarAlMapaDeBotones(boton7, boton8, boton9, botonMas);
    }

    private void agregarTerceraFila() {
        var boton4 = crearBoton("4");
        var boton5 = crearBoton("5");
        var boton6 = crearBoton("6");
        agregarComponente(this, boton4, 0, 4, 1, 1);
        agregarComponente(this, boton5, 1, 4, 1, 1);
        agregarComponente(this, boton6, 2, 4, 1, 1);
        agregarAlMapaDeBotones(boton4, boton5, boton6);
    }

    private void agregarCuartaFila() {
        var boton1 = crearBoton("1");
        var boton2 = crearBoton("2");
        var boton3 = crearBoton("3");
        var botonIgual = crearBoton(Simbolos.IGUAL);
        agregarComponente(this, boton1,     0, 5, 1, 1);
        agregarComponente(this, boton2,     1, 5, 1, 1);
        agregarComponente(this, boton3,     2, 5, 1, 1);
        agregarComponente(this, botonIgual, 3, 5, 2, 1);
        agregarAlMapaDeBotones(boton1, boton2, boton3, botonIgual);
    }

    private void agregarQuintaFila() {
        var botonCero = crearBoton(Simbolos.CERO);
        var botonPunto = crearBoton(Simbolos.PUNTO);
        agregarComponente(this, botonCero,  0, 6, 1, 2);
        agregarComponente(this, botonPunto, 2, 6, 1, 1);
        agregarAlMapaDeBotones(botonCero, botonPunto);
    }

    private JButton crearBoton(String texto) {
        var boton = new JButton(texto);
        boton.setFont(fuente(VistaCalculadoraPreferencias.FUENTE_BOTONES));
        return boton;
    }

    private void agregarAlMapaDeBotones(AbstractButton...unosBotones) {
        stream(unosBotones)
                .forEach(boton -> botones.put(boton.getText(), boton));
    }

    private void agregarCamposTextoAlFrame() {
        configurarCamposTexto();
        agregarComponente(this, campoTextoSuperior, 0, 0, 1, 4);
        agregarComponente(this, campoTextoInferior, 0, 1, 1, 4);
    }

    public void mostrar() {
        setVisible(true);
    }

    private void configurarEsteFrame() {
        setTitle(VistaCalculadoraPreferencias.TITULO_FRAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new JPanel(new GridBagLayout()));
        setSize(VistaCalculadoraPreferencias.ANCHO, VistaCalculadoraPreferencias.ALTO);
        setMinimumSize(VistaCalculadoraPreferencias.DIMENSION_MINIMA);
    }

    private Font fuente(int dimension) {
        return new Font("Arial", Font.BOLD, dimension);
    }

    private void agregarComponente(Container contenedor,
                                   Component componente,
                                   int x, int y, int espaciosX,
                                   int espaciosY) {
        var coordenadas = new GridBagConstraints();
        coordenadas.gridx = x;
        coordenadas.gridy = y;
        coordenadas.gridheight = espaciosX;
        coordenadas.gridwidth = espaciosY;
        coordenadas.weightx = 1.0;
        coordenadas.weighty = 1.0;
        coordenadas.anchor = GridBagConstraints.CENTER;
        coordenadas.fill = GridBagConstraints.BOTH;
        coordenadas.insets = new Insets(0, 0, 0, 0);
        contenedor.add(componente, coordenadas);
    }

    public void establecerBotonesOperacionesActivados(boolean botonesActivos) {
        obtenerBoton(Simbolos.SUMA).setEnabled(botonesActivos);
        obtenerBoton(Simbolos.RESTA).setEnabled(botonesActivos);
        obtenerBoton(Simbolos.MULTIPLICACION).setEnabled(botonesActivos);
        obtenerBoton(Simbolos.DIVISION).setEnabled(botonesActivos);
    }

    public void cambiarAEstadoBasal() {
        cambiarEstado(new StateBasal(this));
        establecerTextoSuperior(null);
        establecerBotonesOperacionesActivados(true);
    }

    public void informarDivisionEntreCero() {
        JOptionPane.showMessageDialog(this, NO_SE_PUEDE_DIVIDIR_ENTRE_CERO);
    }

    public void establecerEstadoActivoBoton(String simbolo, boolean activo) {
        obtenerBoton(simbolo).setEnabled(activo);
    }



}
