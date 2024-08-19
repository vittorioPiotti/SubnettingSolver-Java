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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Una classe personalizzata di {@code JPanel} che fornisce un'interfaccia grafica animata con
 * una scala dinamica e un design arrotondato. Questo pannello supporta animazioni di espansione
 * e contrazione quando viene mostrato o nascosto.
 */
public class AnimatedModal extends JPanel {
     /**
     * Colore del bordo del pannello.
     */
    private Color borderColor;
    /**
     * Spessore del bordo del pannello.
     */
    private int borderThickness;
    /**
     * Colore di sfondo del pannello.
     */
    private Color backgroundColor;
    /**
     * Scala attuale dell'animazione; inizialmente impostata a 0 per garantire un avvio corretto dell'animazione.
     */
    private float scale = 0.0f; // Initial scale to 0 for proper animation start
    /**
     * Timer utilizzato per gestire le animazioni del pannello.
     */
    private Timer animationTimer;
    /**
     * Costruisce un {@code AnimatedModal} con il colore di sfondo, il colore del bordo e lo spessore specificati.
     *
     * @param backgroundColor Il colore di sfondo del pannello.
     * @param borderColor Il colore del bordo del pannello.
     * @param borderThickness Lo spessore del bordo del pannello.
     */
    public AnimatedModal(Color backgroundColor, Color borderColor, int borderThickness) {
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.borderThickness = borderThickness;
        setOpaque(false);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                expandAnimation(); // Expand animation
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                collapseAnimation(); // Collapse animation
            }
        });
    }
    /**
     * Disegna il componente con grafica anti-aliasing, applicando una trasformazione di scala
     * e un rettangolo arrotondato che rappresenta il pannello.
     *
     * @param g L'oggetto {@code Graphics} utilizzato per disegnare il componente.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g.create();

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.translate(width / 2, height / 2);
        graphics.scale(scale, scale);
        graphics.translate(-width / 2, -height / 2);

        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(
                0, 0, width - 1, height - 1, 24, 24);

        graphics.setColor(backgroundColor);
        graphics.fill(roundedRectangle);

        graphics.setColor(borderColor);
        graphics.setStroke(new BasicStroke(borderThickness));
        graphics.draw(roundedRectangle);

        graphics.dispose();
    }
    /**
     * Avvia l'animazione di espansione o contrazione del pannello in base al parametro specificato.
     * 
     * @param expand Se {@code true}, viene avviata l'animazione di espansione; se {@code false}, quella di contrazione.
     */
    public void startAnimation(boolean expand) {
        if (expand) {
            expandAnimation();
        } else {
            collapseAnimation();
        }
    }

    /**
     * Metodo di animazione per espandere il pannello, scalando da 0 a 1.2 e poi stabilizzando a 1.
     * L'animazione è gestita da un {@code Timer} che aggiorna la scala del pannello e lo ridisegna.
     */
    private void expandAnimation() {
        // Animation method for expanding from 0 to 1.2 and then to 1

        if (animationTimer != null && animationTimer.isRunning()) {
            animationTimer.stop();
        }
    
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
     * Metodo di animazione per contrarre il pannello, scalando da 1 a 0.
     * L'animazione è gestita da un {@code Timer} che riduce gradualmente la scala e ridisegna il pannello.
     */
    private void collapseAnimation() {
        // Animation method for collapsing from 1 to 0

        if (animationTimer != null && animationTimer.isRunning()) {
            animationTimer.stop();
        }
    
        scale = 1.0f;
        animationTimer = new Timer(5, new ActionListener() {
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
     * Restituisce il colore del bordo del pannello.
     *
     * @return Il colore del bordo del pannello.
     */
    public Color getBorderColor() {

        return borderColor;
    }

    /**
     * Imposta il colore del bordo del pannello e richiede il ridisegno.
     *
     * @param borderColor Il nuovo colore del bordo.
     */
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }

    /**
     * Restituisce lo spessore del bordo del pannello.
     *
     * @return Lo spessore del bordo.
     */
    public int getBorderThickness() {
        return borderThickness;
    }

    /**
     * Imposta lo spessore del bordo del pannello e richiede il ridisegno.
     *
     * @param borderThickness Il nuovo spessore del bordo.
     */
    public void setBorderThickness(int borderThickness) {
        this.borderThickness = borderThickness;
        repaint();
    }

    /**
     * Restituisce il colore di sfondo del pannello.
     *
     * @return Il colore di sfondo.
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Imposta il colore di sfondo del pannello e richiede il ridisegno.
     *
     * @param backgroundColor Il nuovo colore di sfondo.
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        repaint();
    }
}
