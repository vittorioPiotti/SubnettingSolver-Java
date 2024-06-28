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
import java.awt.Component;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class LayoutTable extends JTable {
    private JFrame frame;

    // Costruttore della classe che estende JTable
    public LayoutTable(String[][] data, String[] columnNames) {
        // Costruttore di JTable
        super(data, columnNames);
        
        // Configura la tabella
        configureTable();
    }

    // Metodo per configurare la tabella
    private void configureTable() {
        // Imposta la dimensione delle righe e colonne
        this.setRowHeight(40);
        for (int i = 0; i < this.getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setPreferredWidth(100);
        }

        // Colore del bordo
        Color borderColor = new Color(208, 215, 222);

        // Imposta l'allineamento centrale e aggiungi bordi per tutte le celle
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((JLabel) comp).setHorizontalAlignment(SwingConstants.CENTER);
                ((JLabel) comp).setVerticalAlignment(SwingConstants.CENTER);
                ((JLabel) comp).setBorder(new LineBorder(borderColor, 1));
                return comp;
            }
        };

        for (int i = 0; i < this.getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Personalizza l'intestazione
        JTableHeader header = this.getTableHeader();
        header.setBackground(new Color(240, 240, 240));
        header.setFont(new Font("SansSerif", Font.BOLD, 12));

        // Imposta il renderer personalizzato per l'intestazione
        header.setDefaultRenderer(new CustomHeaderRenderer(this.getTableHeader().getDefaultRenderer(), borderColor));
    }

    // Renderer personalizzato per l'intestazione della tabella
    static class CustomHeaderRenderer implements TableCellRenderer {
        private final TableCellRenderer delegate;
        private final Color borderColor;

        public CustomHeaderRenderer(TableCellRenderer delegate, Color borderColor) {
            this.delegate = delegate;
            this.borderColor = borderColor;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            Component comp = delegate.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            comp.setBackground(new Color(240, 240, 240));
            comp.setFont(new Font("SansSerif", Font.BOLD, 12));
            ((DefaultTableCellRenderer) comp).setHorizontalAlignment(SwingConstants.CENTER);
            ((JLabel) comp).setBorder(new LineBorder(borderColor, 1));
            return comp;
        }
    }

    // Metodo per aggiornare i dati della tabella
    public void updateData(String[][] newData) {
        this.setModel(new javax.swing.table.DefaultTableModel(
            newData,
            new String[]{"IP", "Classe", "Tipo"}
        ));
    }

    // Metodo per mostrare la tabella in un frame
    public void showInFrame() {
        frame = new JFrame("Tabella IP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JScrollPane(this));
        frame.setSize(400, 150);
        frame.setVisible(true);
    }

   
}
