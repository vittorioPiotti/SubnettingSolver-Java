package org.subnetting;/*
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

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * Una classe personalizzata di JLabel che supporta animazioni fluide basate su trasformazioni di scala.
 * Il testo all'interno del JLabel può espandersi, contrarsi o eseguire altre animazioni fluide tramite un timer.
 */
public class AnimatedLabel extends JLabel {
    private float scale = 0.0f; // Scala iniziale a 0 per un corretto avvio dell'animazione
    private Timer animationTimer;
    private int ch;

    /**
     * Crea un'istanza di {@code AnimatedLabel} con il testo, lo stile, la dimensione, il colore e l'offset specificati.
     *
     * @param text  Il testo da visualizzare all'interno del JLabel.
     * @param style Lo stile del font (ad esempio {@code Font.PLAIN}, {@code Font.BOLD}).
     * @param size  La dimensione del font.
     * @param color Il colore del testo.
     * @param ch    L'offset verticale per il posizionamento del testo.
     */
    public AnimatedLabel(String text, int style, int size,Color color,int ch) {
        super(text);
        setOpaque(false); // Rende il JLabel trasparente
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
        this.ch = ch;
        // Imposta il font con stile e dimensione specificati
        setFont(new Font("Arial", style, size));
        setForeground(color);
        setVisible(true);
        scale = 1.0f;
    }
    /**
     * Ridisegna il JLabel con una trasformazione di scala e grafica anti-aliasing per un rendering fluido.
     * Viene chiamato automaticamente durante il processo di repaint.
     *
     * @param g L'oggetto {@code Graphics} utilizzato per disegnare il componente.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Calcola il centro del JLabel per la trasformazione
        int centerX = width / 2;
        int centerY = height / 2 - ch;

        // Applica la trasformazione di scala
        g2d.translate(centerX, centerY);
        g2d.scale(scale, scale);
        g2d.translate(-centerX, -centerY);

        // Disegna il testo del JLabel
        super.paintComponent(g2d);
        g2d.dispose();
    }

    /**
     * Avvia un'animazione di espansione o contrazione in base al parametro specificato.
     * Se un'animazione è già in corso, viene interrotta prima di avviare la nuova.
     *
     * @param expand Se {@code true}, il JLabel si espande; se {@code false}, si contrae.
     */
    public void startAnimation(boolean expand) {
        if (animationTimer != null && animationTimer.isRunning()) {
            animationTimer.stop(); // Ferma l'animazione corrente se esiste
        }

        if (expand) {
            expandAnimation();
        } else {
            collapseAnimation();
        }
    }

    /**
     * Gestisce l'animazione di espansione, scalando gradualmente il JLabel da 0.0 a 1.0.
     * L'animazione viene eseguita tramite un {@code Timer} che aggiorna il valore di scala e ridisegna il componente.
     *
     * @throws IllegalStateException Se l'animazione non può essere avviata.
     */
 
    private void expandAnimation() {
        // Animazione di espansione da 0 a 1
        scale = 0.0f;
        animationTimer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scale += 0.02f;
                if (scale >= 1.0f) {
                    scale = 1.0f;
                    ((Timer) e.getSource()).stop(); // Ferma l'animazione quando raggiunge 1.0
                }
                repaint();
            }
        });
        animationTimer.start();
    }

    /**
     * Gestisce un'animazione di riduzione della scala da 1.0 a 0.9, riducendo leggermente il JLabel.
     * Viene utilizzata per simulare un leggero ridimensionamento verso il basso.
     */
    public void reduxAnimation() {
        scale = 1.0f;
        animationTimer = new Timer(3, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scale -= 0.005f;
                if (scale <= 0.9f) {
                    scale = 0.9f;
                    ((Timer) e.getSource()).stop(); // Ferma l'animazione quando raggiunge 0.8
                }
                repaint();
            }
        });
        animationTimer.start();
    }
    /**
     * Gestisce un'animazione di zoom che aumenta la scala del JLabel da 0.9 a 1.0.
     * Viene utilizzata per simulare un leggero ingrandimento del testo.
     */
    public void zoomAnimation() {
        scale = 0.9f;
        animationTimer = new Timer(3, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scale += 0.005f;
                if (scale >= 1.0f) {
                    scale = 1.0f;
                    ((Timer) e.getSource()).stop(); // Ferma l'animazione quando raggiunge 1.0
                }
                repaint();
            }
        });
        animationTimer.start();
    }
    /**
     * Gestisce l'animazione di collasso, scalando gradualmente il JLabel da 1.0 a 0.0.
     * L'animazione viene eseguita tramite un {@code Timer} che aggiorna la scala e ridisegna il componente.
     *
     * @throws IllegalStateException Se l'animazione non può essere avviata.
     */
    
    private void collapseAnimation() {
        // Animazione di collasso da 1 a 0
        scale = 1.0f;
        animationTimer = new Timer(3, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scale -= 0.02f;
                if (scale <= 0.0f) {
                    scale = 0.0f;
                    ((Timer) e.getSource()).stop(); // Ferma l'animazione quando raggiunge 0.0
                }
                repaint();
            }
        });
        animationTimer.start();
    }
    /**
     * Gestisce un'animazione che riduce la scala del JLabel a 0.0 e poi la riporta a 1.0.
     * Questa animazione può essere utilizzata per simulare un "click" o un rapido movimento di rimbalzo.
     */
    public void clickAnimation() {
        // Ferma l'animazione corrente se esiste
        if (animationTimer != null && animationTimer.isRunning()) {
            animationTimer.stop();
        }
    
        // Avvia l'animazione da 1 a 0 e poi da 0 a 1
        scale = 1.0f;
        animationTimer = new Timer(5, new ActionListener() {
            private boolean decreasing = true; // Indica se stiamo diminuendo scale
    
            @Override
            public void actionPerformed(ActionEvent e) {
                if (decreasing) {
                    scale -= 0.02f;
                    if (scale <= 0.0f) {
                        scale = 0.0f;
                        decreasing = false; // Passa al modo di aumento
                    }
                } else {
                    scale += 0.02f;
                    if (scale >= 1.0f) {
                        scale = 1.0f;
                        ((Timer) e.getSource()).stop(); // Ferma l'animazione quando raggiunge 1.0
                    }
                }
                repaint(); // Ridisegna il componente con il nuovo valore di scale
            }
        });
        animationTimer.start();
    }
    
    
}
