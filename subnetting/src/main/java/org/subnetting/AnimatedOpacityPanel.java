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

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;
/**
 * Un pannello personalizzato che consente un'animazione graduale della sua opacità,
 * permettendo effetti di apparizione e scomparsa tramite transizioni fluide.
 */
public class AnimatedOpacityPanel extends JPanel {

    /**
     * Opacità corrente del pannello. Varia durante l'animazione.
     */
    private float currentOpacity = 0.0f;
    /**
     * Opacità target che il pannello deve raggiungere durante l'animazione.
     * Per impostazione predefinita è 0.5f.
     */
    private float targetOpacity = 0.5f; // Opacity target set to 0.4
    /**
     * Incremento per ogni passo dell'animazione dell'opacità.
     * Per impostazione predefinita è 0.05f.
     */
    private float opacityIncrement = 0.05f; // Default increment amount per step
    /**
     * Timer utilizzato per gestire i passi dell'animazione.
     */
    private Timer timer;

    /**
     * Costruttore della classe {@code AnimatedOpacityPanel}.
     * Imposta il pannello come non opaco per consentire la gestione della trasparenza.
     */
    public AnimatedOpacityPanel() {
        setOpaque(false);
    }

    /**
     * Avvia l'animazione dell'opacità basata sulla direzione specificata.
     *
     * @param appear {@code true} per animare l'opacità da 0 a 0.5, {@code false} per animare l'opacità da 0.5 a 0.
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

    /**
     * Disegna il pannello con l'opacità corrente, riempiendolo con un colore grigio.
     * L'opacità varia a seconda dello stato corrente dell'animazione.
     *
     * @param g L'oggetto {@code Graphics} utilizzato per disegnare il pannello.
     */
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
