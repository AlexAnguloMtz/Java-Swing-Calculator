package com.aram.calculadora;

import com.aram.calculadora.vista.VistaCalculadora;

import javax.swing.*;
import java.awt.*;

public class CalculadoraMain extends JFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(CalculadoraMain::iniciar);
    }

    private static void iniciar() {
        VistaCalculadora gui = new VistaCalculadora();
        gui.mostrar();
    }


 }



