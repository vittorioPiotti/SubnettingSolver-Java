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

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * La classe {@code CalcVLSM} fornisce metodi per calcolare le sottoreti in un'allocazione di subnet 
 * utilizzando il metodo VLSM (Variable Length Subnet Mask). VLSM consente di ottimizzare l'uso 
 * degli indirizzi IP suddividendo un singolo blocco di indirizzi IP in sottoreti di diverse dimensioni.
 */
public class CalcVLSM {
    /**
     * Metodo principale per dimostrare il calcolo delle sottoreti con VLSM.
     * @param args Argomenti della riga di comando (non utilizzati).
     * @throws UnknownHostException Se l'indirizzo IP non può essere risolto.
     */
    public static void main(String[] args) throws UnknownHostException {
        // Indirizzo IP di base e configurazione delle sottoreti
        String ipAddress = "192.168.1.0";
        String subnetMask = "255.255.255.0"; // Sottorete iniziale
        int[][] subnets = {
                {0, 50},  // Subnet 0 richiede 50 host
                {1, 20},  // Subnet 1 richiede 20 host
                {2, 10},  // Subnet 2 richiede 10 host
                {3, 5}    // Subnet 3 richiede 5 host
        };

        ObjSM[] subnetInfos = calculateCalcVLSM(ipAddress, subnetMask, subnets);
        for (ObjSM subnetInfo : subnetInfos) {
            System.out.println(subnetInfo);
        }
    }

    /**
     * Calcola le sottoreti utilizzando VLSM (Variable Length Subnet Mask).
     * @param ipAddress L'indirizzo IP di base da cui iniziare il calcolo delle sottoreti.
     * @param subnetMask La maschera di sottorete iniziale da utilizzare come riferimento.
     * @param subnets Un array bidimensionale che contiene gli indici delle sottoreti e il numero di host richiesti per ciascuna sottorete.
     * @return Un array di oggetti {@code ObjSM} contenente le informazioni sulle sottoreti calcolate.
     * @throws UnknownHostException Se l'indirizzo IP o la maschera di sottorete non può essere risolto.
     */
    public static ObjSM[] calculateCalcVLSM(String ipAddress, String subnetMask, int[][] subnets) throws UnknownHostException {
        // Ordina le sottoreti in base al numero di host richiesti, in ordine decrescente
        Arrays.sort(subnets, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(b[1], a[1]);
            }
        });

        ObjSM[] subnetInfos = new ObjSM[subnets.length];

        // Indirizzo di partenza
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        byte[] ipBytes = inetAddress.getAddress();

        for (int i = 0; i < subnets.length; i++) {
            int[] subnet = subnets[i];
            int subnetIndex = subnet[0];
            int requiredHosts = subnet[1];

            // Calcola il numero di bit per gli host richiesti
            int numHostBits = (int) Math.ceil(Math.log(requiredHosts + 2) / Math.log(2));  // +2 per Network ID e Broadcast
            int subnetMaskBits = 32 - numHostBits;
            int totalHosts = 1 << numHostBits;

            // Calcola la subnet mask
            String subnetMaskStr = calculateSubnetMask(subnetMaskBits);

            // Calcola l'indirizzo di rete
            String networkId = InetAddress.getByAddress(ipBytes).getHostAddress();
            String broadcast = getNextIp(networkId, totalHosts - 1);
            String gateway = getNextIp(networkId, 1);
            String firstHost = getNextIp(networkId, 2);
            String lastHost = getNextIp(networkId, totalHosts - 2);

            subnetInfos[i] = new ObjSM(
                    subnetIndex,
                    networkId,
                    subnetMaskStr,
                    gateway,
                    broadcast,
                    firstHost,
                    lastHost
            );

            // Incrementa l'indirizzo IP di partenza per la prossima sottorete
            ipBytes = getNextIpBytes(networkId, totalHosts);
        }

        return subnetInfos;
    }

    /**
     * Restituisce il prossimo indirizzo IP aggiungendo un offset all'indirizzo IP dato.
     * @param ipAddress L'indirizzo IP di partenza.
     * @param offset Il valore da aggiungere all'indirizzo IP.
     * @return Il prossimo indirizzo IP come stringa.
     * @throws UnknownHostException Se l'indirizzo IP non può essere risolto.
     */
    public static String getNextIp(String ipAddress, int offset) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        byte[] ipBytes = inetAddress.getAddress();

        for (int i = ipBytes.length - 1; i >= 0; i--) {
            int part = ipBytes[i] & 0xFF;
            part += offset;
            ipBytes[i] = (byte) (part & 0xFF);
            offset = (part >> 8) & 0xFF;
        }

        return InetAddress.getByAddress(ipBytes).getHostAddress();
    }

    /**
     * Restituisce i byte del prossimo indirizzo IP aggiungendo un offset all'indirizzo IP dato.
     * @param ipAddress L'indirizzo IP di partenza.
     * @param offset Il valore da aggiungere all'indirizzo IP.
     * @return I byte del prossimo indirizzo IP.
     * @throws UnknownHostException Se l'indirizzo IP non può essere risolto.
     */
    public static byte[] getNextIpBytes(String ipAddress, int offset) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        byte[] ipBytes = inetAddress.getAddress();

        for (int i = ipBytes.length - 1; i >= 0; i--) {
            int part = ipBytes[i] & 0xFF;
            part += offset;
            ipBytes[i] = (byte) (part & 0xFF);
            offset = (part >> 8) & 0xFF;
        }

        return ipBytes;
    }

    /**
     * Calcola la subnet mask in formato stringa basata sul numero di bit della maschera di sottorete.
     * @param subnetMaskBits Il numero di bit nella maschera di sottorete.
     * @return La maschera di sottorete come stringa.
     */
    public static String calculateSubnetMask(int subnetMaskBits) {
        int mask = 0xffffffff << (32 - subnetMaskBits);
        return intToIpString(mask);
    }

    /**
     * Calcola il numero di bit della maschera di sottorete basato sulla maschera di sottorete in formato stringa.
     * @param subnetMask La maschera di sottorete come stringa.
     * @return Il numero di bit nella maschera di sottorete.
     */
    public static int calculateSubnetMaskBits(String subnetMask) {
        String[] parts = subnetMask.split("\\.");
        int maskBits = 0;
        for (String part : parts) {
            int value = Integer.parseInt(part);
            maskBits += Integer.bitCount(value);
        }
        return maskBits;
    }

    /**
     * Converte un intero in una stringa di indirizzo IP in formato "xxx.xxx.xxx.xxx".
     * @param ip L'indirizzo IP come intero.
     * @return L'indirizzo IP come stringa.
     */
    public static String intToIpString(int ip) {
        return ((ip >> 24) & 0xFF) + "." + ((ip >> 16) & 0xFF) + "." + ((ip >> 8) & 0xFF) + "." + (ip & 0xFF);
    }
}
