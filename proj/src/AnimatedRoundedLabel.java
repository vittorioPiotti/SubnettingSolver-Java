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

public class AnimatedRoundedLabel extends JLabel {
    private float scale = 0.0f; // Initial scale for animation
    private Timer animationTimer;
    private int ch;
    private int cornerRadius; // Radius for rounded corners

    // Padding variables
    private int topPadding = 0;
    private int leftPadding = 0;
    private int bottomPadding = 0;
    private int rightPadding =2 ;

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

    @Override
    protected void paintBorder(Graphics g) {
        // Do not paint the border
    }

    @Override
    public boolean isOpaque() {
        return false; // Make the label itself transparent
    }

    @Override
    public Insets getInsets() {
        return new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
    }

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

    // Expand animation from 0 to 1
    private void expandAnimation() {
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

    // Collapse animation from 1 to 0
    private void collapseAnimation() {
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
