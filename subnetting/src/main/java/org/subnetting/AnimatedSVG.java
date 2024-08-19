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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.net.URL;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.apache.batik.swing.JSVGCanvas;


/**
 * La classe {@code AnimatedSVG} rappresenta un pannello che visualizza e anima un'immagine SVG
 * con transizioni fluide di espansione e riduzione. Utilizza Batik per il rendering delle immagini SVG.
 */
public class AnimatedSVG extends JPanel {
    public JSVGCanvas svgCanvas;
    private Timer animationTimer;
    private float scale = 0.0f;
    private static final float targetScale = 1.0f;
    private static final float delta = 0.02f;
    private int ch;

    /**
     * Crea un'istanza di {@code AnimatedSVG} che carica e visualizza un file SVG specificato.
     *
     * @param svgFilePath Il percorso del file SVG da caricare.
     * @param ch La compensazione verticale per centrare l'immagine durante la trasformazione.
     */
    public AnimatedSVG(String svgFilePath, int ch) {
        setPreferredSize(new Dimension(72, 72)); // Dimensioni più grandi per una visualizzazione migliore
        setLayout(new BorderLayout());
        setOpaque(false);
        this.ch = ch;
        svgCanvas = new JSVGCanvas();

        // Load the SVG file from the resources/assets directory
        URL svgFileUrl = getClass().getResource("/assets/" + svgFilePath);
        if (svgFileUrl != null) {
            svgCanvas.setURI(svgFileUrl.toString());
        } else {
            throw new RuntimeException("SVG file not found: " + svgFilePath);
        }

        svgCanvas.setBackground(new Color(0, 0, 0, 0));
        svgCanvas.setOpaque(false);

        add(svgCanvas, BorderLayout.CENTER);
    }
    /**
     * Avvia l'animazione in base alla direzione specificata.
     *
     * @param expand {@code true} per espandere l'immagine, {@code false} per ridurla.
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
     * Avvia l'animazione di espansione da 0 a 1.0.
     */
    private void expandAnimation() {
         // Metodo per l'animazione di espansione da 0 a 1.0
        scale = 0.0f;
        animationTimer = new Timer(5, new ActionListener() { // Intervallo di 5 ms per un'animazione più fluida
            @Override
            public void actionPerformed(ActionEvent e) {
                scale += delta; // Incrementa la scala per l'espansione
                if (scale >= targetScale) {
                    scale = targetScale;
                    ((Timer) e.getSource()).stop(); // Ferma il timer quando raggiunge la scala finale
                }

                applyTransform(scale);
            }
        });

        animationTimer.start(); // Avvia l'animazione
    }

    /**
     * Avvia l'animazione di riduzione da 1.0 a 0.
     */
    private void collapseAnimation() {
        // Metodo per l'animazione di collasso da 1.0 a 0
        scale = 1.0f;
        animationTimer = new Timer(3, new ActionListener() { // Intervallo di 5 ms per un'animazione più fluida
            @Override
            public void actionPerformed(ActionEvent e) {
                scale -= delta; // Decrementa la scala per il collasso
                if (scale <= 0.02f) {
                    scale = 0.001f;
                    ((Timer) e.getSource()).stop(); // Ferma il timer quando raggiunge la scala finale
                }

                applyTransform(scale);
            }
        });

        animationTimer.start(); // Avvia l'animazione
    }

    /**
     * Applica la trasformazione di scala centrata al canvas SVG.
     *
     * @param scaleFactor Il fattore di scala da applicare.
     */
    private void applyTransform(float scaleFactor) {
        // Applica la trasformazione di scala centrata al canvas SVG
        Dimension size = svgCanvas.getSize();
        double centerX = size.getWidth() / 2;
        double centerY = size.getHeight() / 2 + ch;

        AffineTransform transform = new AffineTransform();
        transform.translate(centerX, centerY); // Trasla al centro del pannello
        transform.scale(scaleFactor, scaleFactor);
        transform.translate(-centerX, -centerY); // Trasla indietro per centrare correttamente

        svgCanvas.setRenderingTransform(transform);
        svgCanvas.revalidate();
        svgCanvas.repaint();
    }
    /**
     * Avvia un'animazione di riduzione parziale da 1.0 a 0.9.
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
                applyTransform(scale); 
            }
        });
        animationTimer.start();
    }
    /**
     * Avvia un'animazione di ingrandimento parziale da 0.9 a 1.0.
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
                applyTransform(scale);
            }
        });
        animationTimer.start();
    }
    /**
     * Avvia un'animazione di click che riduce l'immagine a 0.01 e poi la riporta a 1.0.
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
                    if (scale <= 0.01f) {
                        scale = 0.01f;
                        decreasing = false; // Passa al modo di aumento
                    }
                } else {
                    scale += 0.02f;
                    if (scale >= 1.0f) {
                        scale = 1.0f;
                        ((Timer) e.getSource()).stop(); // Ferma l'animazione quando raggiunge 1.0
                    }
                }
                applyTransform(scale);            }
        });
        animationTimer.start();
    }
}
