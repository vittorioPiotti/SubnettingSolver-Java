/*
    Subnet Solver v1.0.0 (https://github.com/vittorioPiotti/Subnet-Solver-Java/releases/tag/1.0.0)
    Copyright 2024 Vittorio Piotti
    Licensed under GPL-3.0 (https://github.com/vittorioPiotti/Subnet-Solver-Java/blob/main/LICENSE.md)
*/

/*
    batik-all v1.17 (https://xmlgraphics.apache.org/batik/download.html)
    Copyright 2024 The Apache Software Foundation
    Licensed under Apache License 2.0 (https://xmlgraphics.apache.org/batik/license.html)
*/


/*
    FlatLaf v3.2.5 (https://github.com/JFormDesigner/FlatLaf/releases/tag/3.2.5)
    Copyright 2024 JFormDesigner GmbH
    Licensed under Apache License 2.0 (https://github.com/JFormDesigner/FlatLaf/blob/main/LICENSE)
*/

/*
    xml-apis-ext v1.3.04 (https://xmlgraphics.apache.org/batik/download.html)
    Part of Apache Batik (https://xmlgraphics.apache.org/batik/)
    Copyright 2024 The Apache Software Foundation
    Licensed under Apache License 2.0 (https://xmlgraphics.apache.org/batik/license.html)
*/

/*
    xmlgraphics-commons v2.9 (https://xmlgraphics.apache.org/batik/download.html)
    Part of Apache Batik (https://xmlgraphics.apache.org/batik/)
    Copyright 2024 The Apache Software Foundation
    Licensed under Apache License 2.0 (https://xmlgraphics.apache.org/batik/license.html)
*/

/*!
  * Bootstrap v5.3.0-alpha3 (https://getbootstrap.com/)
  * Copyright 2011-2023 The Bootstrap Authors (https://github.com/twbs/bootstrap/graphs/contributors)
  * Licensed under MIT (https://github.com/twbs/bootstrap/blob/main/LICENSE)
*/


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;

public class GUI {

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel row1;
    private JPanel row2;
    private JPanel row3;
    private JPanel columnsPanel;
    private GridBagConstraints gbc;
    private String lastSelectedOption = "Tipo e Classe di un IP";
    private JPanel contentRow1;
    private JPanel innerOutputForm;
    private JPanel contentRow2;
    private JPanel scrollPanePanel;
    private JLabel comboBoxLabelOutput; 
    public static final Color LIGHT = new Color(248, 249, 250); 
    public static final Color SECONDARY = new Color(208, 215, 222); 
    public static final Color THIRD = new Color(180, 180, 180); 
    public static final Color DARK_GRAY = new Color(173, 181, 189); 
    public static final Color PRIMARY = new Color(0, 123, 255); 
    private static int inputFormHeight = 455;
    private static int outputFormHeight = 140;
    private String preSelected = "";
    private int inputType = 0;
    private RoundedLabel  esercizioLabelOutput;
    private HashMap<String, JComponent> componentMap = new HashMap<>();
    private JPanel pn1;
    public static void main(String[] args) {
           try {
            UIManager.setLookAndFeel(new FlatLightLaf()); // Set FlatLaf light theme
            // UIManager.setLookAndFeel(new FlatDARK_GRAYLaf()); // Set FlatLaf DARK_GRAY theme
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            GUI example = new GUI();
            example.createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        // Creazione del frame principale
        frame = new JFrame("Subnetting Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1001, 600); // dimensioni iniziali del frame
        frame.setLayout(new BorderLayout()); // layout del frame
        frame.setBackground(LIGHT);
        frame.setMinimumSize(new Dimension(550, 400)); // Imposta la larghezza minima del frame

        // Inizializzazione dei pannelli
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(LIGHT);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(16, 32, 32, 32));

       

        row1 = new JPanel();
        row1.setBackground(LIGHT);
        row1.setPreferredSize(new Dimension(row1.getWidth(), inputFormHeight));

        row1.setLayout(new BorderLayout());

        // Creazione del pannello contentRow3
        contentRow1 = new JPanel();
        contentRow1.setPreferredSize(new Dimension(0, inputFormHeight));  // Impostazione dell'altezza a 80 pixel
        contentRow1.setBackground(LIGHT);  // Impostazione del colore di sfondo a blu
        contentRow1.setLayout(new BorderLayout());
        contentRow1.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));







        JPanel inputForm = new RoundedPanel(Color.WHITE, SECONDARY, 1);
        inputForm.setLayout(new BorderLayout());
        inputForm.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        JPanel innerInputForm = new JPanel();
        innerInputForm.setLayout(new BoxLayout(innerInputForm, BoxLayout.Y_AXIS));
        innerInputForm.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("Input");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Font titleFont = titleLabel.getFont().deriveFont(Font.BOLD, 24f);
        titleLabel.setFont(titleFont);
        innerInputForm.add(titleLabel);
        String[] comboBoxOptions = {"Tipo e Classe di un IP", "Stessa Rete tra due IP", "Subnetting a Maschera Fissa", "Subnetting a Maschera Variabile"};
        JComboBox<String> comboBox = new JComboBox<>(comboBoxOptions);
        comboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboBox.setMaximumSize(new Dimension(230, 30)); 
        JLabel comboBoxLabel = new JLabel("Scelta:");
        comboBoxLabel.setBorder(BorderFactory.createEmptyBorder(16, 0, 5, 0));
        Font labelFont = comboBoxLabel.getFont().deriveFont(Font.PLAIN, 14f);
        comboBoxLabel.setFont(labelFont);
        innerInputForm.add(comboBoxLabel);
        innerInputForm.add(comboBox);


        
            
        pn1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pn1.setBackground(Color.white);
        pn1.setLayout(null);  



        componentMap.put("lb1", new JLabel("Indirizzo IP:"));
        componentMap.get("lb1").setFont(componentMap.get("lb1").getFont().deriveFont(Font.PLAIN, 14f));
        componentMap.get("lb1").setBounds(0, 10, 230, 30);


        componentMap.put("in1", new RoundedTextField(5, THIRD, 1));
        componentMap.get("in1").addKeyListener(ipKeyListener);

        componentMap.get("in1").setBounds(0, 40, 130, 23);  
        pn1.add((JLabel) componentMap.get("lb1"));
        pn1.add((RoundedTextField) componentMap.get("in1"));


        componentMap.put("lb2", new JLabel("Indirizzo IP1:"));
        componentMap.get("lb2").setFont(componentMap.get("lb2").getFont().deriveFont(Font.PLAIN, 14f));
        componentMap.get("lb2").setBounds(0, 10, 230, 30);
        componentMap.put("in2", new RoundedTextField(5, THIRD, 1));
        componentMap.get("in2").setBounds(0, 40, 130, 23);  
        componentMap.get("in2").addKeyListener(ipKeyListener);

        pn1.add((JLabel) componentMap.get("lb2"));
        pn1.add((RoundedTextField) componentMap.get("in2"));


        componentMap.put("lb3", new JLabel("Subnetmask SM1:"));
        componentMap.get("lb3").setFont(componentMap.get("lb3").getFont().deriveFont(Font.PLAIN, 14f));
        componentMap.get("lb3").setBounds(0, 70, 230, 30);
        componentMap.put("in3", new RoundedTextField(5, THIRD, 1));
        componentMap.get("in3").setBounds(0, 100, 130, 23);  
        componentMap.get("in3").addKeyListener(ipKeyListener);
        pn1.add((JLabel) componentMap.get("lb3"));
        pn1.add((RoundedTextField) componentMap.get("in3"));


        componentMap.put("lb4", new JLabel("Indirizzo IP2:"));
        componentMap.get("lb4").setFont(componentMap.get("lb4").getFont().deriveFont(Font.PLAIN, 14f));
        componentMap.get("lb4").setBounds(0, 130, 230, 30);
        componentMap.put("in4", new RoundedTextField(5, THIRD, 1));
        componentMap.get("in4").setBounds(0, 160, 130, 23);  
        componentMap.get("in4").addKeyListener(ipKeyListener);
        pn1.add((JLabel) componentMap.get("lb4"));
        pn1.add((RoundedTextField) componentMap.get("in4"));


        componentMap.put("lb5", new JLabel("Subnetmask SM2:"));
        componentMap.get("lb5").setFont(componentMap.get("lb5").getFont().deriveFont(Font.PLAIN, 14f));
        componentMap.get("lb5").setBounds(0, 190, 230, 30);
        componentMap.put("in5", new RoundedTextField(5, THIRD, 1));
        componentMap.get("in5").setBounds(0, 220, 130, 23);  
        componentMap.get("in5").addKeyListener(ipKeyListener);
        pn1.add((JLabel) componentMap.get("lb5"));
        pn1.add((RoundedTextField) componentMap.get("in5"));


        componentMap.put("lb6", new JLabel("Indirizzo IP:"));
        componentMap.get("lb6").setFont(componentMap.get("lb6").getFont().deriveFont(Font.PLAIN, 14f));
        componentMap.get("lb6").setBounds(0, 10, 230, 30);
        componentMap.put("in6", new RoundedTextField(5, THIRD, 1));
        componentMap.get("in6").setBounds(0, 40, 130, 23);  
        componentMap.get("in6").addKeyListener(ipKeyListener);
        pn1.add((JLabel) componentMap.get("lb6"));
        pn1.add((RoundedTextField) componentMap.get("in6"));


        componentMap.put("lb20", new JLabel("Subnet Mask SM:"));
        componentMap.get("lb20").setFont(componentMap.get("lb20").getFont().deriveFont(Font.PLAIN, 14f));
        componentMap.get("lb20").setBounds(0, 70, 230, 30);
        componentMap.put("in20", new RoundedTextField(5, THIRD, 1));
        componentMap.get("in20").setBounds(0, 100, 130, 23);  
        componentMap.get("in20").addKeyListener(ipKeyListener);
        pn1.add((JLabel) componentMap.get("lb20"));
        pn1.add((RoundedTextField) componentMap.get("in20"));

        componentMap.put("lb7", new JLabel("Inserisci Numero di:"));
        componentMap.get("lb7").setFont(componentMap.get("lb7").getFont().deriveFont(Font.PLAIN, 14f));
        componentMap.get("lb7").setBounds(0, 135, 230, 30);
        pn1.add((JLabel) componentMap.get("lb7"));
        
        componentMap.put("in8", new JRadioButton());
        componentMap.get("in8").setBounds(0, 160, 23, 23);
        ((JRadioButton) componentMap.get("in8")).setSelected(true);

        pn1.add((JRadioButton) componentMap.get("in8"));
        
        componentMap.put("lb8", new JLabel("Sottoreti"));
        componentMap.get("lb8").setFont(componentMap.get("lb8").getFont().deriveFont(Font.PLAIN, 14f));
        componentMap.get("lb8").setBounds(25, 157, 230, 30);
        pn1.add((JLabel) componentMap.get("lb8"));
        
        componentMap.put("in9", new JRadioButton());
        componentMap.get("in9").setBounds(0, 185, 23, 23);
        pn1.add((JRadioButton) componentMap.get("in9"));
        
        componentMap.put("lb9", new JLabel("Host"));
        componentMap.get("lb9").setFont(componentMap.get("lb9").getFont().deriveFont(Font.PLAIN, 14f));
        componentMap.get("lb9").setBounds(25, 182, 230, 30);
        pn1.add((JLabel) componentMap.get("lb9"));
        
        componentMap.put("lb10", new JLabel("Sottoreti:"));
        componentMap.get("lb10").setFont(componentMap.get("lb10").getFont().deriveFont(Font.PLAIN, 14f));
        componentMap.get("lb10").setBounds(0, 207, 100, 30);
        pn1.add((JLabel) componentMap.get("lb10"));
        
        componentMap.put("in10", new RoundedTextField(5, THIRD, 1));
        componentMap.get("in10").setBounds(65, 210, 45, 23);
        componentMap.get("in10").addKeyListener(retiKeyListener);
        pn1.add((RoundedTextField) componentMap.get("in10"));
        componentMap.put("lb11", new JLabel("Indirizzo IP:"));
        componentMap.get("lb11").setFont(componentMap.get("lb11").getFont().deriveFont(Font.PLAIN, 14f));
        componentMap.get("lb11").setBounds(0, 10, 230, 30);
        pn1.add((JLabel) componentMap.get("lb11"));


        
        
        componentMap.put("in11", new RoundedTextField(5, THIRD, 1));
        componentMap.get("in11").setBounds(0, 40, 130, 23);
        componentMap.get("in11").addKeyListener(ipKeyListener);

        pn1.add((RoundedTextField) componentMap.get("in11"));


        componentMap.put("lb30", new JLabel("Subnet Mask SM:"));
        componentMap.get("lb30").setFont(componentMap.get("lb30").getFont().deriveFont(Font.PLAIN, 14f));
        componentMap.get("lb30").setBounds(0, 70, 230, 30);
        componentMap.put("in30", new RoundedTextField(5, THIRD, 1));
        componentMap.get("in30").setBounds(0, 100, 130, 23);  
        componentMap.get("in30").addKeyListener(ipKeyListener);
        pn1.add((JLabel) componentMap.get("lb30"));
        pn1.add((RoundedTextField) componentMap.get("in30"));

        
 
        componentMap.put("lb12", new JLabel("Numero di Sottoreti:"));
        componentMap.get("lb12").setFont(componentMap.get("lb12").getFont().deriveFont(Font.PLAIN, 14f));
        componentMap.get("lb12").setBounds(0, 130, 230, 30);
        pn1.add((JLabel) componentMap.get("lb12"));
        
        componentMap.put("in12", new RoundedTextField(5, THIRD, 1));
        componentMap.get("in12").setBounds(0, 160, 45, 23);
        componentMap.get("in12").addKeyListener(retiKeyListener);

        pn1.add((RoundedTextField) componentMap.get("in12"));
        componentMap.put("lb13", new JLabel("Host per Sottorete:"));
        componentMap.get("lb13").setFont(componentMap.get("lb13").getFont().deriveFont(Font.PLAIN, 14f));
        componentMap.get("lb13").setBounds(0, 190, 230, 30);
        componentMap.get("lb13").setVisible(false);

        pn1.add((JLabel) componentMap.get("lb13"));
        

   
        componentMap.put("pn100", new JPanel());
        componentMap.get("pn100").setLayout(null);
        componentMap.get("pn100").setBackground(Color.WHITE);
        componentMap.get("pn100").setBorder(null);
        
        int numberOfFields = 0;
        ((RoundedTextField) componentMap.get("in12")).setText("");

        for (int i = 0; i < 1000; i++) {


       
            componentMap.put("lb100" + (i+ 1),  new JLabel("Host SR."+ (i + 1) +":"));
            componentMap.get("lb100" + (i+ 1)).setFont(componentMap.get("lb100" + (i+ 1)).getFont().deriveFont(Font.PLAIN, 14f));
            componentMap.get("lb100" + (i+ 1)).setBounds(0, i * 40 - 3, 100, 30);
            componentMap.get("pn100").add( (JLabel) componentMap.get("lb100" + (i+ 1)));


            componentMap.put("in100" + (i+ 1),  new RoundedTextField(5, THIRD, 1));
            componentMap.get("in100"+ (i+ 1)).addKeyListener(hostKeyListener);

            componentMap.get("in100" + (i+ 1)).setBounds(110, i * 40, 80, 23);
            componentMap.get("pn100").add( (RoundedTextField) componentMap.get("in100" + (i+ 1)));

        }

        changeInputVisibility(numberOfFields);

  


        componentMap.put("sp1", new JScrollPane(componentMap.get("pn100")));
        componentMap.get("sp1").setBounds(0, 225, 230, 75);
        componentMap.get("sp1").setBorder(null);


        pn1.add(componentMap.get("sp1"));




        innerInputForm.add(pn1);
        
        
        changeInputType(0,280);


    


        inputFormHeight = 280;
        contentRow1.setPreferredSize(new Dimension(0, inputFormHeight));
        row1.setPreferredSize(new Dimension(0, inputFormHeight));
        row1.revalidate();
        row1.repaint();
        contentRow1.revalidate();
        contentRow1.repaint();
     

        

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBox.getSelectedItem();
                if (!selectedOption.equals(lastSelectedOption)) {
                switch (selectedOption) {
                        case "Tipo e Classe di un IP":

                            changeInputType(0,280);
                            break;
                        case "Stessa Rete tra due IP":

                        changeInputType(1,460);
                            
                    
                            break;
                        case "Subnetting a Maschera Fissa":
                        changeInputType(2,450);
                       
                            break;
                        case "Subnetting a Maschera Variabile":
                       changeInputType(3,455);
                            
                            break;
                        default:
                            // Azione di default
                            break;
                    }
                    lastSelectedOption = selectedOption;
                }
            }
        });

        ActionListener radioListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton selectedRadioButton = (JRadioButton) e.getSource();
                if (selectedRadioButton == componentMap.get("in8")) {
                    ((JRadioButton) componentMap.get("in8")).setSelected(true); 
                    if("in8" != preSelected){
                        ((JRadioButton) componentMap.get("in9")).setSelected(false); 
                        ((JLabel) componentMap.get("lb10")).setText("Sottoreti:");
                        ((RoundedTextField) componentMap.get("in10")).setBounds(65, 211, 45, 23);  
                        componentMap.get("in10").removeKeyListener(hostKeyListener);
                        componentMap.get("in10").addKeyListener(retiKeyListener);
                        ((RoundedTextField) componentMap.get("in10")).setText("");
                        preSelected = "in8";
                    }

                
                } else if (selectedRadioButton == componentMap.get("in9")) {
                    ((JRadioButton) componentMap.get("in9")).setSelected(true); 

                    if("in9" != preSelected){

                        ((JRadioButton) componentMap.get("in8")).setSelected(false); 
                        ((JLabel) componentMap.get("lb10")).setText("Host:");
                        ((RoundedTextField) componentMap.get("in10")).setBounds(40, 211, 95, 23); 
                        componentMap.get("in10").removeKeyListener(retiKeyListener);
                        componentMap.get("in10").addKeyListener(hostKeyListener);
                        ((RoundedTextField) componentMap.get("in10")).setText("");
                        preSelected = "in9";
                    }
                }
            }
        };

        ((JRadioButton) componentMap.get("in8")).addActionListener(radioListener);
        ((JRadioButton) componentMap.get("in9")).addActionListener(radioListener);


        JButton primaryButton = new RoundedButton("Risolvi",10);
        primaryButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        primaryButton.setBackground(new Color(0, 123, 255)); 
        primaryButton.setMaximumSize(new Dimension(75, 30)); 
        primaryButton.setForeground(Color.WHITE); 
        primaryButton.setFocusPainted(false);
        primaryButton.setFont(new Font("Arial", Font.PLAIN, 14)); 
        innerInputForm.add(Box.createRigidArea(new Dimension(0, 20))); 
        innerInputForm.add(primaryButton);
      
  
        
        inputForm.add(innerInputForm);

        
        contentRow1.add(inputForm, BorderLayout.CENTER);




        
        row1.add(contentRow1, BorderLayout.NORTH);

        row2 = new JPanel();
        row2.setBackground(LIGHT);
        row2.setPreferredSize(new Dimension(row2.getWidth(), outputFormHeight));


        row2.setLayout(new BorderLayout());
        contentRow2 = new JPanel();
        contentRow2.setPreferredSize(new Dimension(0, outputFormHeight));  
        contentRow2.setBackground(LIGHT);  
        contentRow2.setLayout(new BorderLayout());
        contentRow2.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));







        JPanel outputForm = new RoundedPanel(Color.WHITE, SECONDARY, 1);
        outputForm.setLayout(new BorderLayout());
        outputForm.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        innerOutputForm = new JPanel();
        innerOutputForm.setLayout(new BoxLayout(innerOutputForm, BoxLayout.Y_AXIS));
        innerOutputForm.setBackground(Color.WHITE);
        innerOutputForm.setBorder(null);
        JPanel containerinnerOutputForm = new JPanel();
        containerinnerOutputForm.setLayout(new BoxLayout(containerinnerOutputForm, BoxLayout.X_AXIS)); // Layout orizzontale
        containerinnerOutputForm.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); // Imposta la massima larghezza disponibile
        containerinnerOutputForm.setBackground(Color.WHITE);
        JLabel titleLabelOutput = new JLabel("Output");
        titleLabelOutput.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabelOutput.setAlignmentX(Component.LEFT_ALIGNMENT);
        Font titleFontOutput = titleLabelOutput.getFont().deriveFont(Font.BOLD, 24f);
        titleLabelOutput.setFont(titleFontOutput);

         esercizioLabelOutput = new RoundedLabel("VLSM",10);
        esercizioLabelOutput.setForeground(new Color(50, 54, 53));
        esercizioLabelOutput.setOpaque(false); // Rende l'etichetta opaca
        esercizioLabelOutput.setBackground(new Color(225, 255, 227));
        esercizioLabelOutput.setAlignmentX(Component.CENTER_ALIGNMENT);
        esercizioLabelOutput.setHorizontalAlignment(SwingConstants.CENTER);
        esercizioLabelOutput.setVisible(false);

        int topPadding = 3;
        int leftPadding = 7;
        int bottomPadding = 3;
        int rightPadding = 7;
        esercizioLabelOutput.setBorder(new EmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));

        Font esercizioFontOutput = esercizioLabelOutput.getFont().deriveFont(Font.PLAIN, 16f);
        esercizioLabelOutput.setFont(esercizioFontOutput);
        containerinnerOutputForm.add(titleLabelOutput);
        containerinnerOutputForm.add(Box.createHorizontalGlue());
        containerinnerOutputForm.add(esercizioLabelOutput);
        containerinnerOutputForm.setAlignmentX(Component.LEFT_ALIGNMENT);

        innerOutputForm.add(containerinnerOutputForm);
        //esercizioLabelOutput.setVisible(true);
        //esercizioLabelOutput.startAnimation(false);
    
        comboBoxLabelOutput = new JLabel("Visualizza qui la soluzione dell'esercizio");
        comboBoxLabelOutput.setBorder(BorderFactory.createEmptyBorder(16, 0, 5, 0));
        Font labelFontOutput = comboBoxLabelOutput.getFont().deriveFont(Font.PLAIN, 14f);
        comboBoxLabelOutput.setFont(labelFontOutput);
        comboBoxLabelOutput.setAlignmentX(Component.LEFT_ALIGNMENT);

        innerOutputForm.add(comboBoxLabelOutput);

        outputForm.add(innerOutputForm, BorderLayout.CENTER);




      

        // Creazione del JSpinner con un modello di lista di opzioni
      
       

        // Aggiunta di contentRow3 a row3 con allineamento al nord
        contentRow2.add(outputForm, BorderLayout.CENTER);
        row2.add(contentRow2, BorderLayout.NORTH);



       
        row3 = new JPanel();
        row3.setBackground(LIGHT);
        row3.setPreferredSize(new Dimension(row3.getWidth(), 100));

        




        row3.setLayout(new BorderLayout());
        row3.setBorder(BorderFactory.createEmptyBorder(16, 0, 0, 0));

        JPanel contentRow3 = new JPanel();
        contentRow3.setPreferredSize(new Dimension(0, 50));  // Impostazione dell'altezza a 80 pixel
        contentRow3.setBackground(LIGHT);  // Impostazione del colore di sfondo a blu
        contentRow3.setLayout(new BorderLayout());  // Impostazione del layout BorderLayout
        contentRow3.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 0));

        JPanel  outerContentRow3 = new JPanel();
        outerContentRow3.setBackground(Color.WHITE);  // Impostazione del colore di sfondo a grigio
        outerContentRow3.setPreferredSize(new Dimension(300, 50));  // Impostazione dell'altezza a 80 pixel
        outerContentRow3.setLayout(new BorderLayout());  // Impostazione del layout BorderLayout


        JPanel innerContent1Row3 = new JPanel();
        innerContent1Row3.setBackground(Color.WHITE);
        innerContent1Row3.setPreferredSize(new Dimension(110, 50));
        innerContent1Row3.setLayout(new BoxLayout(innerContent1Row3, BoxLayout.Y_AXIS));
        innerContent1Row3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
      

        AnimatedSVG svgCanvas = new AnimatedSVG("github.svg",0);
        svgCanvas.setMaximumSize(new Dimension(45, 45));  
        svgCanvas.setMinimumSize(new Dimension(45, 45));  
        svgCanvas.setPreferredSize(new Dimension(45, 45));  
    


        AnimatedLabel label = new AnimatedLabel("GitHub",Font.PLAIN,16,Color.BLACK,0);
        label.setLayout(new BorderLayout());  // Impostazione del layout BorderLayout
        label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        label.setAlignmentX(Component.LEFT_ALIGNMENT); 
        label.setHorizontalAlignment(SwingConstants.LEFT); 
        label.setVerticalAlignment(SwingConstants.CENTER);
        
        
        // Aggiungi un listener per aprire il link al click
        label.addMouseListener(listenerOnLink(svgCanvas,label));
        svgCanvas.svgCanvas.addMouseListener(listenerOnLink(svgCanvas,label));
        




        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Allineamento a sinistra
        contentPanel.add(svgCanvas);
        contentPanel.add(label);
        contentPanel.setBackground(LIGHT);



        JPanel outerContentPanel = new JPanel();
        outerContentPanel.setLayout(new BorderLayout());  // Impostazione del layout BorderLayout
        outerContentPanel.add(contentPanel);

        innerContent1Row3.add(outerContentPanel,BorderLayout.WEST);

// Ora innerContent1Row3 contiene prima svgCanvas e poi label, entrambi allineati a sinistra e centrati in verticale


        JPanel  innerContent2Row3 = new JPanel();
        innerContent2Row3.setBackground(LIGHT);  // Impostazione del colore di sfondo a grigio
        innerContent2Row3.setPreferredSize(new Dimension(160, 50));
        innerContent2Row3.setLayout(new BorderLayout());  // Impostazione del layout BorderLayout
        JLabel labelContent2Row3 = new JLabel("© 2024 · Vittorio Piotti");
        Font labelFont3 = label.getFont();
        labelContent2Row3.setFont(new Font(labelFont3.getName(), labelFont3.getStyle(), 16)); // Set font size 18

        innerContent2Row3.add(labelContent2Row3,BorderLayout.WEST);  // Aggiunta del JLabel al pannello


         JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // Usiamo BoxLayout per l'allineamento verticale
        centerPanel.setBackground(LIGHT);
        centerPanel.setPreferredSize(new Dimension(0, 50));

        JPanel innerContent3Row3 = new JPanel();
        innerContent3Row3.setBackground(LIGHT); // Usa il colore SECONDARY o un altro colore desiderato
        innerContent3Row3.setPreferredSize(new Dimension(0, 25));
        JPanel innerContRow3 = new JPanel();
        innerContRow3.setBackground(DARK_GRAY); // Usa il colore SECONDARY o un altro colore desiderato

        innerContRow3.setPreferredSize(new Dimension(1, 25));
        innerContent3Row3.add(innerContRow3);
        centerPanel.add(Box.createVerticalGlue()); // Aggiunge spazio verticale sopra innerContent3Row3
        centerPanel.add(innerContent3Row3); // Aggiunge innerContent3Row3 al centerPanel
        centerPanel.add(Box.createVerticalGlue()); // Aggiunge spazio verticale sopra innerContent3Row3


        outerContentRow3.add( centerPanel, BorderLayout.CENTER);

        outerContentRow3.add( innerContent1Row3, BorderLayout.WEST);
        outerContentRow3.add( innerContent2Row3, BorderLayout.EAST);
       
        contentRow3.add( outerContentRow3, BorderLayout.WEST);


        
        // Aggiunta di contentRow3 a row3 con allineamento al nord
        row3.add(contentRow3, BorderLayout.NORTH);
        // Creazione del pannello con layout a due colonne
        columnsPanel = new JPanel(new GridBagLayout());
        columnsPanel.setBackground(LIGHT);
        columnsPanel.setBorder(BorderFactory.createEmptyBorder(16,0,0,0));

        gbc = new GridBagConstraints();
        
        // Aggiornamento iniziale dei vincoli
        updateGridConstraints();
        
        // Pannello contenitore per le prime due righe
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(columnsPanel, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        
        // Aggiunta della terza riga al mainPanel (occupa lo spazio rimanente)
        mainPanel.add(row3, BorderLayout.CENTER); // row3 nel centro, occupa lo spazio rimanente
        
        // Creazione della barra di scorrimento verticale
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        // Creazione del container per il pannello scrollabile con margine
        JPanel container = new JPanel(new BorderLayout());
        container.add(scrollPane, BorderLayout.CENTER);
        container.setBorder(null);


        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        frame.setContentPane(layeredPane);


        // Creation of the white panel with transparency
        AnimatedOpacityPanel whitePanel = new AnimatedOpacityPanel();
       


        // Add the panels to the JLayeredPane
        layeredPane.add(container, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(whitePanel, JLayeredPane.PALETTE_LAYER);

        AnimatedModal modalPanel = new AnimatedModal(Color.WHITE,SECONDARY,1);
        modalPanel.setBackground(Color.WHITE);
        modalPanel.setPreferredSize(new Dimension(500, 270));
        modalPanel.setLayout(new BoxLayout(modalPanel, BoxLayout.Y_AXIS));

 // Creazione delle tre scritte e aggiunta al JPanel

        AnimatedSVG SVGpanel = new AnimatedSVG("x-circle.svg",70);
        SVGpanel.setMaximumSize(new Dimension(72, 72));          
      




        AnimatedButton modalButton = new AnimatedButton("OK",50);
        // Apply custom styling
       
        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(Box.createHorizontalGlue()); // Aggiunge spazio elastico a sinistra
        buttonBox.add(modalButton); // Aggiunge il pulsante
        buttonBox.add(Box.createHorizontalStrut(20)); // Aggiunge spazio fisso a destra (opzionale)
        
        // Aggiungi il Box contenente il pulsante al modalPanel
        AnimatedLabel titleModal = new AnimatedLabel("Invalid Input",Font.BOLD,24,Color.BLACK,0);
        AnimatedLabel textModal = new AnimatedLabel("Please enter only integers",Font.PLAIN,18,new Color(89, 92, 94),40);

      
        SVGpanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleModal.setAlignmentX(Component.CENTER_ALIGNMENT);
        textModal.setAlignmentX(Component.CENTER_ALIGNMENT);
        modalPanel.add(Box.createVerticalStrut(28)); 
        modalPanel.add(SVGpanel);
        modalPanel.add(Box.createVerticalStrut(24)); 
        modalPanel.add(titleModal);
        modalPanel.add(Box.createVerticalStrut(16)); 
        modalPanel.add(textModal);
        modalPanel.add(Box.createVerticalStrut(16)); 

        modalPanel.add(buttonBox);
        modalPanel.setVisible(false);
        whitePanel.setVisible(false);

        int x = (layeredPane.getWidth() - modalPanel.getPreferredSize().width) / 2;
        int y = (layeredPane.getHeight() - modalPanel.getPreferredSize().height) / 2;
        modalPanel.setBounds(x, y, modalPanel.getPreferredSize().width, modalPanel.getPreferredSize().height);
        
              
        // Aggiungi il modalPanel al MODAL_LAYER
        layeredPane.add(modalPanel, JLayeredPane.MODAL_LAYER);
        layeredPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Calcoliamo la posizione centrale rispetto alle dimensioni correnti del layeredPane
                int x = (layeredPane.getWidth() - modalPanel.getWidth()) / 2;
                int y = (layeredPane.getHeight() - modalPanel.getHeight()) / 2;
                modalPanel.setBounds(x, y, modalPanel.getPreferredSize().width, modalPanel.getPreferredSize().height);
            }
        });

        // Add a listener to resize the panels when the frame size changes
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension size = frame.getSize();
                container.setSize(size);
                whitePanel.setSize(size);

                // Update layout constraints and repaint main panel
                updateGridConstraints();
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        // Set the initial sizes of the panels
        container.setSize(frame.getSize());
        whitePanel.setSize(frame.getSize());
        
        whitePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                whitePanel.startAnimation(false);

                SVGpanel.startAnimation(false);
                modalPanel.startAnimation(false);  
                titleModal.startAnimation(false);
                textModal.startAnimation(false);    
                modalButton.startAnimation(false);
          
                // Creazione di un Timer con ritardo di 5 secondi
                Timer timer = new Timer(500, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SVGpanel.setVisible(false);
                        whitePanel.setVisible(false);
                        modalPanel.setVisible(false);
                        titleModal.setVisible(false);
                        textModal.setVisible(false);
                        modalButton.setVisible(false);

                    }
                });
                
                // Avvio del Timer
                timer.setRepeats(false); // Imposta il timer per non ripetersi
                timer.start();
            }
        });
        
        primaryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                List<Boolean> checkCalc = new ArrayList<>();
                List<String> inputs = new ArrayList<>();

                switch(inputType){
                    case 0:
                    checkCalc.clear();
                    inputs.clear();
                    inputs.add(((RoundedTextField) componentMap.get("in1")).getText());//0  ip   
                    checkCalc.add(CalcManager.isValidIPAddress(inputs.get(0)));//0  ip  
                    if(checkInputs(checkCalc)){
                        esercizioLabelOutput.setText("IP Info");
                        esercizioLabelOutput.setVisible(true);
                        try {

                            if (comboBoxLabelOutput != null && innerOutputForm.isAncestorOf(comboBoxLabelOutput)) {
                                innerOutputForm.remove(comboBoxLabelOutput);
                            }

                            // Verifica se ci sono figli in scrollPanePanel e non è null
                            if (scrollPanePanel != null && scrollPanePanel.getComponentCount() > 0) {
                                // Rimuovi tutti i figli
                                scrollPanePanel.removeAll();
                            }
                            if (scrollPanePanel != null && innerOutputForm.isAncestorOf(scrollPanePanel)) {
                                innerOutputForm.remove(scrollPanePanel);
                            }
                            ObjIP objIP = CalcIP.calculateIPInfo(inputs.get(0));
                            System.out.print(objIP);
                            String[][] data = {
                                { objIP.getIpAddress(), objIP.getIpClass(), objIP.getType() }
                            };
                            
                            String[] columnNames = { "IP", "Classe", "Tipo" };
                            LayoutTable layoutTable = new LayoutTable(data, columnNames);

                            // Creazione di uno JScrollPane e aggiunta della tabella al suo interno
                            JScrollPane scrollPaneTable = new JScrollPane(layoutTable);
                            scrollPaneTable.setBorder(BorderFactory.createEmptyBorder(16, 0, 5, 0)); // Imposta il bordo a vuoto per rimuovere i bordi visibili
                            scrollPaneTable.setBackground(Color.WHITE);

                            // Aggiungi lo JscrollPaneTable al pannello
                            scrollPanePanel = new JPanel(new BorderLayout());
                            scrollPanePanel.add(scrollPaneTable, BorderLayout.CENTER);
                            scrollPanePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                            scrollPanePanel.setBackground(Color.WHITE);

                            // Aggiungi il pannello al contenitore principale
                            innerOutputForm.add(scrollPanePanel);
                         
                            redesignOutputForm(185);

                            // Aggiorna il layout
                            frame.revalidate();
                            frame.repaint();
                            




                            
                        } catch (UnknownHostException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                 
                    }else{
                        activeModal(whitePanel, SVGpanel, titleModal, textModal, modalButton, modalPanel);
                    }


                    break;
                    case 1:
                    
                        checkCalc.clear();
                        inputs.clear();
                        inputs.add(((RoundedTextField) componentMap.get("in2")).getText());//0 ip1        
                        inputs.add(((RoundedTextField) componentMap.get("in3")).getText());//1 sm1        
                        inputs.add(((RoundedTextField) componentMap.get("in4")).getText());//2 ip2       
                        inputs.add(((RoundedTextField) componentMap.get("in5")).getText());//3 sm2    
                        checkCalc.add(CalcManager.isValidIPAddress(inputs.get(0)));//0  ip1    
                        checkCalc.add(CalcManager.isValidSubnetMask(inputs.get(1)));//1  sm1    
                        checkCalc.add(CalcManager.isValidIPAddress(inputs.get(2)));//2  ip2    
                        checkCalc.add(CalcManager.isValidSubnetMask(inputs.get(3)));//3  sm2
                        if(checkInputs(checkCalc)){
                            // Calcola i risultati utilizzando CalcNetIDs
                            esercizioLabelOutput.setText("NET Info");
                            esercizioLabelOutput.setVisible(true);

                            try {
                                if (comboBoxLabelOutput != null && innerOutputForm.isAncestorOf(comboBoxLabelOutput)) {
                                    innerOutputForm.remove(comboBoxLabelOutput);
                                }
    
                                // Verifica se ci sono figli in scrollPanePanel e non è null
                                if (scrollPanePanel != null && scrollPanePanel.getComponentCount() > 0) {
                                    // Rimuovi tutti i figli
                                    scrollPanePanel.removeAll();
                                }
                                if (scrollPanePanel != null && innerOutputForm.isAncestorOf(scrollPanePanel)) {
                                    innerOutputForm.remove(scrollPanePanel);
                                }
                                ObjNetIDs result = CalcNetIDs.calculateNetworkIDs(inputs.get(0), inputs.get(1), inputs.get(2), inputs.get(3));
                            // Costruisci i dati per la tabella
                            String[][] data = {
                                { inputs.get(0), inputs.get(1), result.getNetId1(), result.getNetId1().equals(result.getNetId2())? "Stessa rete" : "Diverse reti" },
                                { inputs.get(2), inputs.get(3), result.getNetId2(), result.getNetId1().equals(result.getNetId2() )? "Stessa rete" : "Diverse reti" }
                            };

                            // Nomi delle colonne della tabella
                            String[] columnNames = { "IP", "Subnet Mask", "Net ID", "Rete" };

                            // Crea la tabella con i dati e i nomi delle colonne
                            LayoutTable layoutTable = new LayoutTable(data, columnNames);

                            // Crea uno JScrollPane per la tabella
                            JScrollPane scrollPaneTable = new JScrollPane(layoutTable);
                            scrollPaneTable.setBorder(BorderFactory.createEmptyBorder(16, 0, 5, 0)); // Imposta il bordo a vuoto per rimuovere i bordi visibili
                            scrollPaneTable.setBackground(Color.WHITE);

                            // Aggiungi la JScrollPane al pannello
                            scrollPanePanel = new JPanel(new BorderLayout());
                            scrollPanePanel.add(scrollPaneTable, BorderLayout.CENTER);
                            scrollPanePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                            scrollPanePanel.setBackground(Color.WHITE);

                      

                            // Aggiungi il pannello al contenitore principale
                            innerOutputForm.add(scrollPanePanel);

                            // Ridisegna l'output form con l'altezza specificata
                            redesignOutputForm(225);

                            // Aggiorna il layout del frame
                            frame.revalidate();
                            frame.repaint();
                            } catch (UnknownHostException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }

                           

                        }else{
                            activeModal(whitePanel, SVGpanel, titleModal, textModal, modalButton, modalPanel);
                        }                
                         break;
                    case 2:
                        checkCalc.clear();
                        inputs.clear();
                        inputs.add(((RoundedTextField) componentMap.get("in6")).getText());//0 ip 
                        inputs.add(((RoundedTextField) componentMap.get("in20")).getText());//1 ip        
       
                        if (!((JRadioButton) componentMap.get("in9")).isSelected()){
                            inputs.add(((RoundedTextField) componentMap.get("in10")).getText());//2 numero di reti        
                            inputs.add("");//3 numero di host 
                            checkCalc.add(CalcManager.isValidIPAddress(inputs.get(0)));//0  ip    
                            checkCalc.add(CalcManager.isValidSubnetMask(inputs.get(1)));//1  sm    

                            checkCalc.add(CalcManager.isValidNumberOfSubnets(inputs.get(2)));//2  numero di reti        
                            checkCalc.add(true);//3  numero di host        


                        }else{
                            inputs.add("");//2 numero di host 
                            inputs.add(((RoundedTextField) componentMap.get("in10")).getText());//3 numero di host        
                            checkCalc.add(CalcManager.isValidIPAddress(inputs.get(0)));//0  ip    
                            checkCalc.add(CalcManager.isValidSubnetMask(inputs.get(1)));//1  sm    
                            checkCalc.add(true);//2  numero di reti        
                            checkCalc.add(CalcManager.isValidNumberOfHosts(inputs.get(3)));//3  numero di host        
                        }
                        if(checkInputs(checkCalc)){
                            esercizioLabelOutput.setText("FLSM");
                            esercizioLabelOutput.setVisible(true);
                           
                            try {
                                if (comboBoxLabelOutput != null && innerOutputForm.isAncestorOf(comboBoxLabelOutput)) {
                                    innerOutputForm.remove(comboBoxLabelOutput);
                                }
    
                                // Verifica se ci sono figli in scrollPanePanel e non è null
                                if (scrollPanePanel != null && scrollPanePanel.getComponentCount() > 0) {
                                    // Rimuovi tutti i figli
                                    scrollPanePanel.removeAll();
                                }
                                if (scrollPanePanel != null && innerOutputForm.isAncestorOf(scrollPanePanel)) {
                                    innerOutputForm.remove(scrollPanePanel);
                                }
                        
                                
                                System.out.println("RETI : " + inputs.get(2));
                                System.out.println("HOST : " + inputs.get(3));

                                ObjSM[] subnets = CalcFLSM.calculateSubnets(inputs.get(0),inputs.get(1), inputs.get(2) != "" ? Integer.parseInt(inputs.get(2)) : CalcManager.calculateNumberOfSubnets(inputs.get(3), Integer.parseInt(inputs.get(3)))  );
                                String[][] data = new String[subnets.length][7]; // 6 colonne per ogni subnet

                                // Ciclo per popolare l'array data con i valori delle subnet
                                for (int i = 0; i < subnets.length; i++) {
                                    ObjSM subnet = subnets[i];
                                    data[i] = new String[]{
                                        String.valueOf(i + 1), // Convert integer to string
                                        subnet.getNetworkId(),
                                        subnet.getSubnetMask(),
                                        subnet.getGateway(),
                                        subnet.getBroadcast(),
                                        subnet.getFirstHost(),
                                        subnet.getLastHost()
                                    };
                                }
                        

                            // Nomi delle colonne della tabella
                            String[] columnNames = { "#","Net ID", "Subnet Mask", "Gateay", "Broadcast","First Host","Last Host" };

                            // Crea la tabella con i dati e i nomi delle colonne
                            LayoutTable layoutTable = new LayoutTable(data, columnNames);

                            // Crea uno JScrollPane per la tabella
                            JScrollPane scrollPaneTable = new JScrollPane(layoutTable);
                            scrollPaneTable.setBorder(BorderFactory.createEmptyBorder(16, 0, 5, 0)); // Imposta il bordo a vuoto per rimuovere i bordi visibili
                            scrollPaneTable.setBackground(Color.WHITE);

                            // Aggiungi la JScrollPane al pannello
                            scrollPanePanel = new JPanel(new BorderLayout());
                            scrollPanePanel.add(scrollPaneTable, BorderLayout.CENTER);
                            scrollPanePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                            scrollPanePanel.setBackground(Color.WHITE);

                      
                            // Aggiungi il pannello al contenitore principale
                            innerOutputForm.add(scrollPanePanel);

                            // Ridisegna l'output form con l'altezza specificata
                            redesignOutputForm(subnets.length > 14 ? 710 : 150 + (40 * (subnets.length)) );

                            // Aggiorna il layout del frame
                            innerOutputForm.revalidate();
                            innerOutputForm.repaint();
                            frame.revalidate();
                            frame.repaint();
                            } catch (UnknownHostException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }

                        }else{
                            activeModal(whitePanel, SVGpanel, titleModal, textModal, modalButton, modalPanel);
                        }

                        break;
                    case 3:
                    
                        checkCalc.clear();
                        inputs.clear();
                        inputs.add(((RoundedTextField) componentMap.get("in11")).getText());//0 ip       
                        inputs.add(((RoundedTextField) componentMap.get("in30")).getText());//1 sm        
 
                        inputs.add(((RoundedTextField) componentMap.get("in12")).getText());//2 numero di reti 
                        System.out.println(inputs.get(0));
                        System.out.println(inputs.get(1));
                        System.out.println(inputs.get(2));
                        int num =  inputs.get(2).isEmpty() ? -1 : Integer.parseInt(inputs.get(2));
                        
                        for(int i = 0; i < num; i ++){
                            inputs.add(((RoundedTextField) componentMap.get("in100"+ (i+ 1))).getText());//3...N host per sottorete
                            System.out.println(inputs.get(i + 3));
                        }
                       
                        checkCalc.add(CalcManager.isValidIPAddress(inputs.get(0)));
                        checkCalc.add(CalcManager.isValidSubnetMask(inputs.get(1)));
                        checkCalc.add(CalcManager.isValidNumberOfSubnets(inputs.get(2)));
                        for (int i = 3; i < inputs.size(); i++) {
                            try {
                                checkCalc.add(CalcManager.isValidNumberOfHosts(inputs.get(i)));
                            } catch (IndexOutOfBoundsException ex) {
                                // Handle the exception (e.g., print an error message or take appropriate action)
                                System.err.println("IndexOutOfBoundsException: " );
                                // Optionally, you can break out of the loop or add a default value to checkCalc
                                // break; // Uncomment this if you want to exit the loop on exception
                                // checkCalc.add(someDefaultValue); // Uncomment and replace someDefaultValue with your logic
                            }
                        }

                        /*
                            checkCalc.add(CalcManager.isValidIPAddress(inputs.get(0)));//0  ip    
                            System.out.println(checkCalc.get(1));
                            checkCalc.add(CalcManager.isValidNumberOfSubnets(inputs.get(1).isEmpty() ? -1 : Integer.parseInt(inputs.get(1))));//1  numero di reti 
                            for(int i = 2; i < checkCalc.size(); i ++){
                            checkCalc.add(CalcManager.isValidNumberOfHosts(inputs.get(i).isEmpty() ? -1 : Integer.parseInt(inputs.get(i)))) ;
                        }
                         */

                        if(checkInputs(checkCalc)){



                            esercizioLabelOutput.setText("VLSM");
                            esercizioLabelOutput.setVisible(true);

                            try {
                                if (comboBoxLabelOutput != null && innerOutputForm.isAncestorOf(comboBoxLabelOutput)) {
                                    innerOutputForm.remove(comboBoxLabelOutput);
                                }
    
                                // Verifica se ci sono figli in scrollPanePanel e non è null
                                if (scrollPanePanel != null && scrollPanePanel.getComponentCount() > 0) {
                                    // Rimuovi tutti i figli
                                    scrollPanePanel.removeAll();
                                }
                                if (scrollPanePanel != null && innerOutputForm.isAncestorOf(scrollPanePanel)) {
                                    innerOutputForm.remove(scrollPanePanel);
                                }

                                int[][] subnetsData = new int[inputs.size() - 3][2];

                                // Popolare l'array subnetsData con i valori provenienti da inputs
                                for (int i = 3; i < inputs.size(); i++) {
                                    // Parse int delle parti e assegnare i valori all'array subnetsData
                                    subnetsData[i - 3][0] = i - 3; // Indice di subnet
                                    subnetsData[i - 3][1] = Integer.parseInt(inputs.get(i)); // Numero di host richiesti
                                }
                                
                                for (int i = 3; i < inputs.size(); i++) {
                                    System.out.print(subnetsData[i -3 ][0] + " ");
                                    System.out.println(subnetsData[i -3 ][1]);
                                }
                                ObjSM[] subnets = CalcVLSM.calculateCalcVLSM(inputs.get(0),inputs.get(1), subnetsData);
                        
                               
                                String[][] data = new String[subnets.length][7]; // 6 colonne per ogni subnet
                  
                                // Ciclo per popolare l'array data con i valori delle subnet
                                for (int i = 0; i < subnets.length; i++) {
                                    ObjSM subnet = subnets[i];
                                    data[i] = new String[]{
                                        String.valueOf(i + 1), // Convert integer to string
                                        subnet.getNetworkId(),
                                        subnet.getSubnetMask(),
                                        subnet.getGateway(),
                                        subnet.getBroadcast(),
                                        subnet.getFirstHost(),
                                        subnet.getLastHost()
                                     
                                    };
                                    System.out.println(
                                        "\n" +
                                        String.valueOf(i + 1) + "\n" +
                                        subnet.getNetworkId() + "\n" +
                                        subnet.getSubnetMask() + "\n" +
                                        subnet.getGateway() + "\n" +
                                        subnet.getBroadcast() + "\n" +
                                        subnet.getFirstHost() + "\n" +
                                        subnet.getLastHost()
                                    );
                                  
                                }
                              

                            // Nomi delle colonne della tabella
                            String[] columnNames = { "#","Net ID", "Subnet Mask", "Gateay", "Broadcast","First Host","Last Host" };

                            // Crea la tabella con i dati e i nomi delle colonne
                            LayoutTable layoutTable = new LayoutTable(data, columnNames);

                            // Crea uno JScrollPane per la tabella
                            JScrollPane scrollPaneTable = new JScrollPane(layoutTable);
                            scrollPaneTable.setBorder(BorderFactory.createEmptyBorder(16, 0, 5, 0)); // Imposta il bordo a vuoto per rimuovere i bordi visibili
                            scrollPaneTable.setBackground(Color.WHITE);

                            // Aggiungi la JScrollPane al pannello
                            scrollPanePanel = new JPanel(new BorderLayout());
                            scrollPanePanel.add(scrollPaneTable, BorderLayout.CENTER);
                            scrollPanePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                            scrollPanePanel.setBackground(Color.WHITE);

                      
                            // Aggiungi il pannello al contenitore principale
                            innerOutputForm.add(scrollPanePanel);

                            // Ridisegna l'output form con l'altezza specificata
                            redesignOutputForm(subnets.length > 14 ? 710 : 150 + (40 * (subnets.length)) );

                            // Aggiorna il layout del frame
                            frame.revalidate();
                            frame.repaint();
                            } catch (UnknownHostException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }






                        }else{
                            activeModal(whitePanel, SVGpanel, titleModal, textModal, modalButton, modalPanel);
                        }
                    
                        break;
                }

          

                
            }
        });
        modalButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                whitePanel.startAnimation(false);

                SVGpanel.startAnimation(false);
                modalPanel.startAnimation(false); 
                titleModal.startAnimation(false);
                textModal.startAnimation(false);
                modalButton.startAnimation(false);

                // Creazione di un Timer con ritardo di 5 secondi
                Timer timer = new Timer(500, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SVGpanel.setVisible(false);
                        whitePanel.setVisible(false);
                        modalPanel.setVisible(false);
                        titleModal.setVisible(false);
                        textModal.setVisible(false);
                        modalButton.setVisible(false);

                    }
                });
                
                // Avvio del Timer
                timer.setRepeats(false); // Imposta il timer per non ripetersi
                timer.start();
            }
        });
        modalPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
              


                
            }
        });

        frame.setVisible(true);
        
    }
    private boolean checkInputs(List<Boolean> checkCalc){
        for(int i = 0; i < checkCalc.size(); i ++){
            if (checkCalc.get(i) == false) {
                return false;
            } 
        }    
        return true;
    }
    private void activeModal(AnimatedOpacityPanel whitePanel,AnimatedSVG SVGpanel, AnimatedLabel titleModal,AnimatedLabel textModal,AnimatedButton modalButton,AnimatedModal modalPanel){
        whitePanel.startAnimation(true);
        SVGpanel.startAnimation(true);
        titleModal.startAnimation(true);
        textModal.startAnimation(true);
        modalButton.startAnimation(true);

        whitePanel.setVisible(true);
        modalPanel.setVisible(true);
        SVGpanel.setVisible(true);
        titleModal.setVisible(true);
        textModal.setVisible(true);
        modalButton.setVisible(true);
    }
    private void changeInputVisibility(int numberOfFields){
        if(numberOfFields < 1000)
        for(int i = 0; i < numberOfFields; i ++){
            componentMap.get("in100"+ (i+ 1)).setVisible(true);
            componentMap.get("lb100"+ (i+ 1)).setVisible(true);
        }
        for(int i = numberOfFields; i < 1000; i ++){
            componentMap.get("in100"+ (i+ 1)).setVisible(false);
            componentMap.get("lb100"+ (i+ 1)).setVisible(false);
        }

        int preferredHeight = numberOfFields * 40;
        componentMap.get("pn100").setPreferredSize(new Dimension(210, preferredHeight)); 
        
    }
    private KeyListener ipKeyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
            RoundedTextField textField = (RoundedTextField) e.getComponent();
            char c = e.getKeyChar();
            
            // Controllo se il carattere è un numero o un punto
            if (Character.isDigit(c) || c == '.') {
                // Controllo se il testo non supera il limite di 12 caratteri
                if (textField.getText().length() >= 15) {
                    e.consume();
                }
            } else {
                // Consuma il carattere se non è un numero o un punto
                e.consume();
            }
            
            // Visualizza il contenuto totale del textfield su console
        }
    
        @Override
        public void keyPressed(KeyEvent e) {
            // Non utilizzato
        }
    
        @Override
        public void keyReleased(KeyEvent e) {
            // Non utilizzato
        }
    };
    
    
    
    
    private KeyListener hostKeyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
            RoundedTextField textField = (RoundedTextField) e.getComponent();
            char c = e.getKeyChar();
    
            // Controllo se il carattere è un numero e se non supera il limite di 5 cifre
            if (!(Character.isDigit(c) && textField.getText().length() < 10)) {
                e.consume(); // Consuma l'evento, impedendo l'inserimento del carattere non numerico o oltre il limite
            }

            // Visualizza il contenuto totale del textfield su console
        }
    
        @Override
        public void keyPressed(KeyEvent e) {
            // Non utilizzato
        }
    
        @Override
        public void keyReleased(KeyEvent e) {
            // Non utilizzato
        }
    };
    
    private KeyListener retiKeyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
            RoundedTextField textField = (RoundedTextField) e.getComponent();
            char c = e.getKeyChar();
    
            // Controllo se il carattere è un numero e se non supera il limite di 5 cifre
            if (!(Character.isDigit(c) && textField.getText().length() < 3)) {
                e.consume(); // Consuma l'evento, impedendo l'inserimento del carattere non numerico o oltre il limite
            }
            try {
                String newText = textField.getText() + (Character.isDigit(c) && textField.getText().length() < 3 ? c : "");
                if (newText.isEmpty() || Integer.parseInt(newText) == 0) {
                    if(inputType == 3){          
                        componentMap.get("lb13").setVisible(false);
                        changeInputVisibility(0);
                        redesignInputForm(396);//impoprtant

                }
                }else{
                    changeInputVisibility(Integer.parseInt(newText));

                    if(inputType == 3){ 
                        componentMap.get("lb13").setVisible(true);
                        componentMap.get("sp1").setBounds(0, 225, 230, Integer.parseInt(newText) > 6 ? 250 : (40 * Integer.parseInt(newText) ));

                        redesignInputForm(Integer.parseInt(newText) > 6 ? 685 : 425 + (40 * Integer.parseInt(newText) ));
                        
                    }

                }
            } catch (NumberFormatException ex) {
                if(inputType == 3){         
                    componentMap.get("lb13").setVisible(false);
                    changeInputVisibility(0);
                    redesignInputForm(396);


            }
            }
            // Visualizza il contenuto totale del textfield su console
        }
    
        @Override
        public void keyPressed(KeyEvent e) {
            // Non utilizzato
        }
    
        @Override
        public void keyReleased(KeyEvent e) {
            // Non utilizzato
        }
    };
    




    private MouseListener listenerOnLink(final AnimatedSVG svgCanvas, final AnimatedLabel label) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    label.clickAnimation();
                    svgCanvas.clickAnimation();
                    Desktop.getDesktop().browse(new URI("https://github.com"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setForeground(PRIMARY);
                label.setCursor(new Cursor(Cursor.HAND_CURSOR));
                svgCanvas.setCursor(new Cursor(Cursor.HAND_CURSOR));
                String svgFilePrimary = "github-primary.svg";
                svgCanvas.svgCanvas.setURI(svgFilePrimary);
                label.reduxAnimation();
                svgCanvas.reduxAnimation();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                label.setForeground(Color.BLACK);
                label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                svgCanvas.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                String svgFile = "github.svg";
                svgCanvas.svgCanvas.setURI(svgFile);
                label.zoomAnimation();
                svgCanvas.zoomAnimation();
            }
        };
    }
    // Metodo per aggiornare componenti di input
    private void changeInputType(int type, int inputFormH){
        inputType = type;
        componentMap.get("lb1").setVisible(type == 0 ? true : false);//0
        componentMap.get("in1").setVisible(type == 0 ? true : false);
        componentMap.get("in2").setVisible(type == 1 ? true : false);//1
        componentMap.get("lb2").setVisible(type == 1 ? true : false);//1
        componentMap.get("lb3").setVisible(type == 1 ? true : false);
        componentMap.get("in3").setVisible(type == 1 ? true : false);
        componentMap.get("lb4").setVisible(type == 1 ? true : false);
        componentMap.get("in4").setVisible(type == 1 ? true : false);
        componentMap.get("lb5").setVisible(type == 1 ? true : false);
        componentMap.get("in5").setVisible(type == 1 ? true : false);
        componentMap.get("lb7").setVisible(type == 2 ? true : false);//2
        componentMap.get("lb6").setVisible(type == 2 ? true : false);
        componentMap.get("in6").setVisible(type == 2 ? true : false);
        componentMap.get("in8").setVisible(type == 2 ? true : false);
        componentMap.get("in20").setVisible(type == 2 ? true : false);
        componentMap.get("lb20").setVisible(type == 2 ? true : false);
        componentMap.get("lb8").setVisible(type == 2 ? true : false);
        componentMap.get("in9").setVisible(type == 2 ? true : false);
        componentMap.get("lb9").setVisible(type == 2 ? true : false);
        componentMap.get("in10").setVisible(type == 2 ? true : false);
        componentMap.get("lb10").setVisible(type == 2 ? true : false);
        componentMap.get("lb11").setVisible(type == 3 ? true : false);//3
        componentMap.get("in30").setVisible(type == 3 ? true : false);
        componentMap.get("lb30").setVisible(type == 3 ? true : false);
        componentMap.get("in11").setVisible(type == 3 ? true : false);
        componentMap.get("lb12").setVisible(type == 3 ? true : false);
        componentMap.get("in12").setVisible(type == 3 ? true : false);
        componentMap.get("lb13").setVisible(type == 3 
        && (!((RoundedTextField) componentMap.get("in12")).getText().isEmpty() 
            && !((RoundedTextField) componentMap.get("in12")).getText().equals("0")));
    
    
        componentMap.get("sp1").setVisible(type == 3 ? true : false);

        redesignInputForm(type == 3 
        ? (!((RoundedTextField) componentMap.get("in12")).getText().isEmpty() 
            && !((RoundedTextField) componentMap.get("in12")).getText().equals("0"))
            ? inputFormH
            : 396
        : inputFormH);

    }

    
    private void redesignInputForm(int inputFormH){
        contentRow1.setPreferredSize(new Dimension(0, inputFormH));
        row1.setPreferredSize(new Dimension(0, inputFormH));
        row1.revalidate();
        row1.repaint();
        contentRow1.revalidate();
        contentRow1.repaint();
    }

    private void redesignOutputForm(int outputFormH){
        contentRow2.setPreferredSize(new Dimension(0, outputFormH));
        row2.setPreferredSize(new Dimension(0, outputFormH));
        row2.revalidate();
        row2.repaint();
        contentRow2.revalidate();
        contentRow2.repaint();
    }
    // Metodo per aggiornare i vincoli del GridBagLayout in base alla larghezza del frame
    private void updateGridConstraints() {
        columnsPanel.removeAll(); // Rimuovi tutti i componenti prima di aggiungerli di nuovo
        
        if (frame.getWidth() < 1000) {
            gbc.gridx = 0;  // Prima colonna (row1)
            gbc.gridy = 0;
            gbc.weightx = 1.0;  // Peso relativo per row1 se larghezza < 1000
        } else {
            gbc.gridx = 0;  // Prima colonna (row1)
            gbc.gridy = 0;
            gbc.weightx = 2.0 / 6.0;  // Peso relativo per row1 se larghezza >= 1000
        }
        gbc.fill = GridBagConstraints.BOTH;
        columnsPanel.add(row1, gbc);
        
        if (frame.getWidth() < 1000) {
            gbc.gridx = 0;  // Prima colonna (row2)
            gbc.gridy = 1;
            gbc.weightx = 1.0;  // Peso relativo per row2 se larghezza < 1000
        } else {
            gbc.gridx = 1;  // Seconda colonna (row2)
            gbc.gridy = 0;
            gbc.weightx = 4.0 / 6.0;  // Peso relativo per row2 se larghezza >= 1000
        }
        gbc.fill = GridBagConstraints.BOTH;
        columnsPanel.add(row2, gbc);
        
        columnsPanel.revalidate();
        columnsPanel.repaint();
    
        frame.revalidate();
        frame.repaint();
    }
}
