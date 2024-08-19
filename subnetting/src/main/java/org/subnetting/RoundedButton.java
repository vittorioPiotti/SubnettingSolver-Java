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

import javax.swing.JButton;
/**
 * Un pulsante con angoli arrotondati personalizzato.
 * La classe estende {@link JButton} e disegna un pulsante con angoli arrotondati.
 */
public class RoundedButton extends JButton {

    private int cornerRadius;

    /**
     * Costruisce un pulsante con angoli arrotondati.
     *
     * @param text il testo da visualizzare sul pulsante
     * @param radius il raggio degli angoli arrotondati
     */
    public RoundedButton(String text, int radius) {
        super(text);
        this.cornerRadius = radius;
        setOpaque(false); // Make the button background transparent
    }

    /**
     * Disegna il componente del pulsante. Il metodo sovrascritto disegna lo sfondo arrotondato e il testo del pulsante.
     *
     * @param g il contesto grafico da utilizzare per il disegno
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw rounded background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        
        // Draw the button text
        super.paintComponent(g);
        
        g2.dispose();
    }

    /**
     * Disegna il bordo del pulsante. Questo metodo è lasciato vuoto poiché non è richiesto alcun bordo.
     *
     * @param g il contesto grafico da utilizzare per il disegno del bordo
     */

    @Override
    protected void paintBorder(Graphics g) {
        // If you want to draw a border, you can do it here
        // For now, leaving it empty as no border is required
    }

    /**
     * Restituisce la dimensione preferita del pulsante. Il metodo sovrascritto aumenta le dimensioni per tenere conto
     * del raggio degli angoli arrotondati.
     *
     * @return la dimensione preferita del pulsante
     */
    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width += cornerRadius;
        size.height += cornerRadius;
        return size;
    }

    /**
     * Restituisce {@code false} poiché il pulsante è trasparente.
     *
     * @return {@code false}, rendendo il {@code JButton} stesso trasparente
     */
    @Override
    public boolean isOpaque() {
        return false; // Make the JButton itself transparent
    }
}
