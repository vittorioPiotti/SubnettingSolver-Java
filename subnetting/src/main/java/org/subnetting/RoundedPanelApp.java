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

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;


/**
 * Un {@code JPanel} con uno sfondo arrotondato.
 * Questa classe estende {@link JPanel} e disegna un pannello con uno sfondo arrotondato
 * utilizzando il raggio degli angoli specificato.
 */
public class RoundedPanelApp extends JPanel {
    private int cornerRadius;

    /**
     * Costruisce un pannello con angoli arrotondati.
     *
     * @param radius il raggio degli angoli arrotondati
     */
    public RoundedPanelApp(int radius) {
        super();
        this.cornerRadius = radius;
        setOpaque(false); // Make the panel background transparent
    }

    /**
     * Disegna il componente del pannello. Questo metodo sovrascritto disegna uno sfondo arrotondato
     * utilizzando il raggio degli angoli specificato.
     *
     * @param g il contesto grafico da utilizzare per il disegno
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw rounded rectangle background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

        g2.dispose();
    }

    /**
     * Restituisce la dimensione preferita del pannello. Questo metodo utilizza la dimensione
     * preferita della classe {@link JPanel} e non modifica le dimensioni.
     *
     * @return la dimensione preferita del pannello
     */
    @Override
    public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }
}
