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
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

// Classe per un JPanel con bordi arrotondati
public class RoundedPanel extends JPanel {
    private Color borderColor;
    private int borderThickness;
    private Color backgroundColor;

    public RoundedPanel(Color backgroundColor, Color borderColor, int borderThickness) {
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.borderThickness = borderThickness;
        setOpaque(false);  // Permette al background di mostrarsi
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g.create();

        // Imposta il rendering per anti-aliasing
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Crea la forma del pannello con i bordi arrotondati
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(
                0, 0, width - 1, height - 1, 24, 24);  // 10 è il raggio degli angoli, puoi cambiarlo

        // Disegna il background del pannello
        graphics.setColor(backgroundColor);
        graphics.fill(roundedRectangle);

        // Disegna il bordo del pannello
        graphics.setColor(borderColor);
        graphics.setStroke(new BasicStroke(borderThickness));
        graphics.draw(roundedRectangle);

        graphics.dispose();
    }

    public void startAnimation() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startAnimation'");
    }
}
