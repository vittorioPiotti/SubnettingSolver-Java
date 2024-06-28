
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

import java.net.InetAddress;
import java.net.UnknownHostException;

public class CalcNetIDs {
    public static void main(String[] args) throws UnknownHostException {
        // Indirizzi IP e subnet mask
        String ip1 = "192.168.1.10";
        String sm1 = "255.255.0.0";
        String ip2 = "192.168.1.20";
        String sm2 = "255.255.255.0";

        // Calcola i Network ID e stampa i risultati
        ObjNetIDs result = calculateNetworkIDs(ip1, sm1, ip2, sm2);
        System.out.println(result);
    }

    public static ObjNetIDs calculateNetworkIDs(String ip1, String sm1, String ip2, String sm2) throws UnknownHostException {
        // Calcola il Network ID per il primo indirizzo IP
        String netId1 = calculateNetworkID(ip1, sm1);

        // Calcola il Network ID per il secondo indirizzo IP
        String netId2 = calculateNetworkID(ip2, sm2);

        // Ritorna un oggetto contenente entrambi i Network ID
        return new ObjNetIDs(netId1, netId2);
    }

    public static String calculateNetworkID(String ipAddress, String subnetMask) throws UnknownHostException {
        // Ottiene i byte dell'indirizzo IP e della subnet mask
        byte[] ipBytes = InetAddress.getByName(ipAddress).getAddress();
        byte[] maskBytes = InetAddress.getByName(subnetMask).getAddress();

        // Calcola il Network ID byte per byte usando l'operatore AND bit a bit
        byte[] networkBytes = new byte[ipBytes.length];
        for (int i = 0; i < ipBytes.length; i++) {
            networkBytes[i] = (byte) (ipBytes[i] & maskBytes[i]);
        }

        // Converte i byte del Network ID in un formato stringa
        return InetAddress.getByAddress(networkBytes).getHostAddress();
    }


    
}
