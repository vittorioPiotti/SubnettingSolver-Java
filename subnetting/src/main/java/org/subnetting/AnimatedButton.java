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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/**
 * Un componente JButton personalizzato che supporta transizioni di animazione tramite scaling.
 * Il pulsante si espande o si contrae quando viene attivato, creando un effetto visivo accattivante.
 */
public class AnimatedButton extends JButton {
  
    private float scale = 0.0f; // Initial scale for animation
    private Timer animationTimer;
    private int ch;
    private int cornerRadius; // Radius for rounded corners

    /**
     * Crea un'istanza di {@code AnimatedButton} con il testo specificato e l'offset orizzontale.
     *
     * @param text Il testo da visualizzare sul pulsante.
     * @param ch L'offset orizzontale per regolare il posizionamento del testo sul pulsante.
     */
    public AnimatedButton(String text, int ch) {
        super(text);
        setOpaque(false); // Make the button transparent
        setHorizontalAlignment(JButton.CENTER);
        setVerticalAlignment(JButton.CENTER);
        setBackground(new Color(93, 199, 236)); // Blue background color
        setBorder(new EmptyBorder(5, 10, 5, 10)); // Set internal margins for the text
        this.ch = ch;
        this.cornerRadius = 10; // Set the corner radius
        // Set the font with specified style and size
        setFont(new Font("Arial", Font.PLAIN, 16));
        setForeground(Color.WHITE);
        setPreferredSize(new Dimension(50, 30));
        setMaximumSize(new Dimension(50, 30));
        setMinimumSize(new Dimension(50, 30));
    }

    /**
     * Ridisegna il pulsante con grafiche personalizzate, tra cui angoli arrotondati e animazione di scaling.
     * Questo metodo viene chiamato automaticamente durante il processo di repaint.
     *
     * @param g L'oggetto {@code Graphics} utilizzato per disegnare il componente.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Calculate the center of the JLabel for the transformation
        int centerX = width / 2 - ch;
        int centerY = height / 2 - ch;

        // Apply the scale transformation
        g2d.translate(centerX, centerY);
        g2d.scale(scale, scale);
        g2d.translate(-centerX, -centerY);

        // Draw the rounded rectangle
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, width - 1, height - 1, cornerRadius, cornerRadius);

        // Set the composite to ensure text is rendered correctly with transparency
        g2d.setComposite(java.awt.AlphaComposite.SrcOver);

        // Draw the button text
        g2d.setColor(getForeground());
        g2d.setFont(getFont());
        Font font = getFont();
        String text = getText();
        int textWidth = g2d.getFontMetrics(font).stringWidth(text);
        int textHeight = g2d.getFontMetrics(font).getAscent();
        int textX = (width - textWidth) / 2;
        int textY = (height - textHeight) / 2 + g2d.getFontMetrics(font).getAscent() - g2d.getFontMetrics(font).getDescent() / 2;
        g2d.drawString(text, textX, textY);

        g2d.dispose();
    }

    /**
     * Sovrascrive il comportamento predefinito di disegno del bordo, disabilitandolo completamente.
     *
     * @param g L'oggetto {@code Graphics} utilizzato per disegnare il bordo.
     */
    @Override
    protected void paintBorder(Graphics g) {
        // Do not paint the border
    }

    /**
     * Indica se questo componente è opaco.
     *
     * @return Restituisce sempre {@code false} per indicare trasparenza.
     */
    @Override
    public boolean isOpaque() {
        return false; // Make the JButton itself transparent
    }

    /**
     * Avvia l'animazione, espandendo o contraendo il pulsante in base al parametro specificato.
     * Se un'animazione è già in corso, viene interrotta prima di avviare la nuova.
     *
     * @param expand Se {@code true}, il pulsante si espande; se {@code false}, si contrae.
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
     * Gestisce l'animazione di espansione, scalando gradualmente il pulsante da 0.0 a 1.0.
     * L'animazione viene eseguita utilizzando un {@code Timer} che aggiorna la scala e ridisegna il componente.
     *
     * @throws IllegalStateException Se l'animazione non può essere avviata.
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
     * Gestisce l'animazione di contrazione, scalando gradualmente il pulsante da 1.0 a 0.0.
     * L'animazione viene eseguita utilizzando un {@code Timer} che aggiorna la scala e ridisegna il componente.
     *
     * @throws IllegalStateException Se l'animazione non può essere avviata.
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
}
