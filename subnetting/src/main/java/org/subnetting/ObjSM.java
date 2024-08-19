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
 * Rappresenta un oggetto che contiene le informazioni relative a una subnet, inclusi ID di rete, maschera di rete,
 * gateway, indirizzi di broadcast, primo host e ultimo host.
 */
public class ObjSM {
    private int subnetIndex;

    /**
     * Restituisce l'indice della subnet.
     *
     * @return l'indice della subnet
     */
    public int getSubnetIndex() {
        return subnetIndex;
    }

    /**
     * Imposta l'indice della subnet.
     *
     * @param subnetIndex l'indice della subnet da impostare
     */
    public void setSubnetIndex(int subnetIndex) {
        this.subnetIndex = subnetIndex;
    }

    private String networkId;
    private String subnetMask;
    private String gateway;
    private String broadcast;
    private String firstHost;
    private String lastHost;

    /**
     * Costruisce un oggetto {@code ObjSM} con i valori specificati.
     *
     * @param subnetIndex l'indice della subnet
     * @param networkId l'ID di rete
     * @param subnetMask la maschera di rete
     * @param gateway l'indirizzo del gateway
     * @param broadcast l'indirizzo di broadcast
     * @param firstHost l'indirizzo del primo host
     * @param lastHost l'indirizzo dell'ultimo host
     */
    public ObjSM(int subnetIndex, String networkId, String subnetMask, String gateway, String broadcast, String firstHost, String lastHost) {
        this.subnetIndex = subnetIndex;
        this.networkId = networkId;
        this.subnetMask = subnetMask;
        this.gateway = gateway;
        this.broadcast = broadcast;
        this.firstHost = firstHost;
        this.lastHost = lastHost;
    }

    /**
     * Restituisce una rappresentazione in formato stringa dell'oggetto {@code ObjSM}.
     * La stringa risultante include l'indice della subnet, l'ID di rete, la maschera di rete, l'indirizzo del gateway,
     * l'indirizzo di broadcast, il primo host e l'ultimo host.
     *
     * @return una stringa che rappresenta l'oggetto {@code ObjSM}
     */
    @Override
    public String toString() {
        return "Subnet " + subnetIndex + ":\n" +
                "  Network ID: " + networkId + "\n" +
                "  Subnet Mask: " + subnetMask + "\n" +
                "  Gateway: " + gateway + "\n" +
                "  Broadcast Address: " + broadcast + "\n" +
                "  First Host: " + firstHost + "\n" +
                "  Last Host: " + lastHost;
    }

    /**
     * Restituisce l'ID di rete.
     *
     * @return l'ID di rete
     */
    public String getNetworkId() {
        return networkId;
    }

    /**
     * Imposta l'ID di rete.
     *
     * @param networkId l'ID di rete da impostare
     */
    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    /**
     * Restituisce la maschera di rete.
     *
     * @return la maschera di rete
     */
    public String getSubnetMask() {
        return subnetMask;
    }

    /**
     * Imposta la maschera di rete.
     *
     * @param subnetMask la maschera di rete da impostare
     */
    public void setSubnetMask(String subnetMask) {
        this.subnetMask = subnetMask;
    }

    /**
     * Restituisce l'indirizzo del gateway.
     *
     * @return l'indirizzo del gateway
     */
    public String getGateway() {
        return gateway;
    }

    /**
     * Imposta l'indirizzo del gateway.
     *
     * @param gateway l'indirizzo del gateway da impostare
     */
    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    /**
     * Restituisce l'indirizzo di broadcast.
     *
     * @return l'indirizzo di broadcast
     */
    public String getBroadcast() {
        return broadcast;
    }

    /**
     * Imposta l'indirizzo di broadcast.
     *
     * @param broadcast l'indirizzo di broadcast da impostare
     */
    public void setBroadcast(String broadcast) {
        this.broadcast = broadcast;
    }

    /**
     * Restituisce l'indirizzo del primo host.
     *
     * @return l'indirizzo del primo host
     */
    public String getFirstHost() {
        return firstHost;
    }

    /**
     * Imposta l'indirizzo del primo host.
     *
     * @param firstHost l'indirizzo del primo host da impostare
     */
    public void setFirstHost(String firstHost) {
        this.firstHost = firstHost;
    }

    /**
     * Restituisce l'indirizzo dell'ultimo host.
     *
     * @return l'indirizzo dell'ultimo host
     */
    public String getLastHost() {
        return lastHost;
    }
    
    /**
     * Imposta l'indirizzo dell'ultimo host.
     *
     * @param lastHost l'indirizzo dell'ultimo host da impostare
     */
    public void setLastHost(String lastHost) {
        this.lastHost = lastHost;
    }
}