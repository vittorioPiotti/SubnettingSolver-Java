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

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimatedOpacityPanel extends JPanel {

    private float currentOpacity = 0.0f;
    private float targetOpacity = 0.5f; // Opacity target set to 0.4
    private float opacityIncrement = 0.05f; // Default increment amount per step
    private Timer timer;

    public AnimatedOpacityPanel() {
        setOpaque(false);
    }

    /**
     * Starts the animation based on the direction.
     * @param appear true to animate opacity from 0 to 0.4, false to animate from 0.4 to 0
     */
    public void startAnimation(boolean appear) {
        // Adjust increment and target based on direction
        if (appear) {
            currentOpacity = 0.0f;
            targetOpacity = 0.5f;
            opacityIncrement = 0.05f;
        } else {
            currentOpacity = 0.4f;
            targetOpacity = 0.0f;
            opacityIncrement = -0.05f; // Decrease opacity
        }

        // Ensure the timer is not null and running before starting a new animation
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Increment the opacity gradually
                currentOpacity += opacityIncrement;
                if ((appear && currentOpacity >= targetOpacity) || (!appear && currentOpacity <= targetOpacity)) {
                    currentOpacity = targetOpacity;
                    timer.stop(); // Stop the timer when opacity reaches the target
                }
                repaint(); // Request repaint to animate
            }
        });
        timer.start(); // Start the timer
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        // Set transparency based on currentOpacity
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, currentOpacity));

        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.dispose();
    }

    // Test the animation
  
}
