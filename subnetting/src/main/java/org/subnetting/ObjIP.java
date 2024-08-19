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

/**
 * Rappresenta un oggetto IP con un indirizzo IP, un tipo e una classe.
 */
class ObjIP {
    private String ipAddress;

    /**
     * Restituisce l'indirizzo IP dell'oggetto.
     *
     * @return l'indirizzo IP dell'oggetto
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Imposta l'indirizzo IP dell'oggetto.
     *
     * @param ipAddress l'indirizzo IP da impostare
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * Restituisce il tipo dell'oggetto IP.
     *
     * @return il tipo dell'oggetto IP
     */
    public String getType() {
        return type;
    }

    /**
     * Imposta il tipo dell'oggetto IP.
     *
     * @param type il tipo da impostare
     */
    public void setType(String type) {
        this.type = type;
    }

    private String type;
    private String ipClass;

    /**
     * Restituisce la classe dell'oggetto IP.
     *
     * @return la classe dell'oggetto IP
     */
    public String getIpClass() {
        return ipClass;
    }

    /**
     * Imposta la classe dell'oggetto IP.
     *
     * @param ipClass la classe da impostare
     */
    public void setIpClass(String ipClass) {
        this.ipClass = ipClass;
    }

    /**
     * Costruisce un oggetto {@code ObjIP} con i valori specificati.
     *
     * @param ipAddress l'indirizzo IP dell'oggetto
     * @param type il tipo dell'oggetto IP
     * @param ipClass la classe dell'oggetto IP
     */
    public ObjIP(String ipAddress, String type, String ipClass) {
        this.ipAddress = ipAddress;
        this.type = type;
        this.ipClass = ipClass;
    }

    /**
     * Restituisce una rappresentazione in formato stringa dell'oggetto {@code ObjIP}.
     * La stringa risultante include l'indirizzo IP, il tipo e la classe.
     *
     * @return una stringa che rappresenta l'oggetto {@code ObjIP}
     */
    @Override
    public String toString() {
        return "IP Address: " + ipAddress + "\n" +
               "  Type: " + type + "\n" +
               "  Class: " + ipClass;
    }
}
