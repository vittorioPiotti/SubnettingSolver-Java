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
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;



/**
 * La classe {@code AnimatedRoundedLabel} estende JLabel e rappresenta un'etichetta animata
 * con bordi arrotondati, che include effetti di animazione per l'espansione e la riduzione.
 * Questo componente può essere utilizzato per migliorare l'interfaccia utente con transizioni visive.
 */

public class AnimatedRoundedLabel extends JLabel {

    /**
     * Scala corrente utilizzata per l'animazione.
     */
    private float scale = 0.0f; // Initial scale for animation
    /**
     * Timer per gestire i passi dell'animazione.
     */
    private Timer animationTimer;
    /**
     * Parametro di compensazione verticale per la centratura del testo.
     */
    private int ch;
    /**
     * Raggio degli angoli arrotondati.
     */
    private int cornerRadius; // Radius for rounded corners

    // Padding variables
    private int topPadding = 0;
    private int leftPadding = 0;
    private int bottomPadding = 0;
    private int rightPadding =2 ;


    /**
     * Crea un'istanza di {@code AnimatedRoundedLabel} con il testo specificato.
     *
     * @param text Il testo dell'etichetta.
     * @param ch Compensazione verticale per la centratura del testo.
     */
    public AnimatedRoundedLabel(String text, int ch) {
        super(text);
        setOpaque(false); // Make the label transparent
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
        setBackground(new Color(93, 199, 236)); // Blue background color
        this.ch = ch;
        this.cornerRadius = 10; // Set the corner radius
        setFont(new Font("Arial", Font.PLAIN, 16));
        setForeground(Color.WHITE);
    }

    /**
     * Sovrascrive il metodo di pittura per disegnare l'etichetta con angoli arrotondati e applicare la trasformazione di scala.
     *
     * @param g L'oggetto {@code Graphics} utilizzato per disegnare il componente.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.scale(scale, scale);
        int width = getWidth();
        int height = getHeight();

        // Calculate the center of the JLabel for the transformation
        int centerX = width / 2 - ch;
        int centerY = height / 2 - ch;

        // Apply the scale transformation
        g2d.translate(centerX, centerY);
        g2d.scale(scale, scale);
        g2d.translate(-centerX, -centerY);

        // Draw the rounded rectangle with background color
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, width - 1, height - 1, cornerRadius, cornerRadius);

        // Set the composite to ensure text is rendered correctly with transparency
        g2d.setComposite(java.awt.AlphaComposite.SrcOver);
        
        // Draw the label text with foreground color and font
        g2d.setColor(getForeground());
        g2d.setFont(getFont());
        Font font = getFont();
        String text = getText();
        int textWidth = g2d.getFontMetrics(font).stringWidth(text);
        int textHeight = g2d.getFontMetrics(font).getAscent();
        int textX = leftPadding + (width - leftPadding - rightPadding - textWidth) / 2;
        int textY = topPadding + (height - topPadding - bottomPadding - textHeight) / 2 + g2d.getFontMetrics(font).getAscent() - g2d.getFontMetrics(font).getDescent() / 2;
        g2d.drawString(text, textX, textY);

        g2d.dispose();
    }

    /**
     * Sovrascrive il metodo per evitare di dipingere un bordo.
     *
     * @param g L'oggetto {@code Graphics} utilizzato per disegnare il bordo.
     */
    @Override
    protected void paintBorder(Graphics g) {
        // Do not paint the border
    }

    /**
     * Restituisce sempre {@code false} per indicare che l'etichetta è trasparente.
     *
     * @return {@code false}, indicando che l'etichetta è trasparente.
     */
    @Override
    public boolean isOpaque() {
        return false; // Make the label itself transparent
    }

    /**
     * Restituisce i margini di inserimento del contenuto dell'etichetta.
     *
     * @return Un oggetto {@code Insets} contenente i margini.
     */
    @Override
    public Insets getInsets() {
        return new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
    }

    /**
     * Avvia l'animazione in base alla direzione specificata.
     *
     * @param expand {@code true} per espandere l'animazione, {@code false} per ridurla.
     */
    public void startAnimation(boolean expand) {
        if (animationTimer != null && animationTimer.isRunning()) {
            animationTimer.stop(); // Stop the current animation if it exists
        }

        if (expand) {
            expandAnimation();
        } else {
            collapseAnimation();
        }
    }

    /**
     * Avvia l'animazione di espansione da 0 a 1.
     */
    private void expandAnimation() {
        // Expand animation from 0 to 1
        scale = 0.0f;
        animationTimer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scale += 0.02f;
                if (scale >= 1.0f) {
                    scale = 1.0f;
                    ((Timer) e.getSource()).stop(); // Stop the animation when it reaches 1.0
                }
                repaint();
            }
        });
        animationTimer.start();
    }

    /**
     * Avvia l'animazione di riduzione da 1 a 0.
     */
    private void collapseAnimation() {
        // Collapse animation from 1 to 0
        scale = 1.0f;
        animationTimer = new Timer(3, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scale -= 0.02f;
                if (scale <= 0.0f) {
                    scale = 0.0f;
                    ((Timer) e.getSource()).stop(); // Stop the animation when it reaches 0.0
                }
                repaint();
            }
        });
        animationTimer.start();
    }

    /**
     * Avvia un'animazione di click che riduce l'etichetta a 0 e la riporta a 1.
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
