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

public class ObjSM {
    private int subnetIndex;
    public int getSubnetIndex() {
        return subnetIndex;
    }

    public void setSubnetIndex(int subnetIndex) {
        this.subnetIndex = subnetIndex;
    }

    private String networkId;
    private String subnetMask;
    private String gateway;
    private String broadcast;
    private String firstHost;
    private String lastHost;

    public ObjSM(int subnetIndex, String networkId, String subnetMask, String gateway, String broadcast, String firstHost, String lastHost) {
        this.subnetIndex = subnetIndex;
        this.networkId = networkId;
        this.subnetMask = subnetMask;
        this.gateway = gateway;
        this.broadcast = broadcast;
        this.firstHost = firstHost;
        this.lastHost = lastHost;
    }

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

    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public String getSubnetMask() {
        return subnetMask;
    }

    public void setSubnetMask(String subnetMask) {
        this.subnetMask = subnetMask;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(String broadcast) {
        this.broadcast = broadcast;
    }

    public String getFirstHost() {
        return firstHost;
    }

    public void setFirstHost(String firstHost) {
        this.firstHost = firstHost;
    }

    public String getLastHost() {
        return lastHost;
    }

    public void setLastHost(String lastHost) {
        this.lastHost = lastHost;
    }
}