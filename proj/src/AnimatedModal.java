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

public class AnimatedModal extends JPanel {
    private Color borderColor;
    private int borderThickness;
    private Color backgroundColor;
    private float scale = 0.0f; // Initial scale to 0 for proper animation start
    private Timer animationTimer;

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

    public void startAnimation(boolean expand) {
        if (expand) {
            expandAnimation();
        } else {
            collapseAnimation();
        }
    }

    // Animation method for expanding from 0 to 1.2 and then to 1
    private void expandAnimation() {
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
    

    // Animation method for collapsing from 1 to 0
    private void collapseAnimation() {
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
    

    // Getters and setters for properties
    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }

    public int getBorderThickness() {
        return borderThickness;
    }

    public void setBorderThickness(int borderThickness) {
        this.borderThickness = borderThickness;
        repaint();
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        repaint();
    }
}
