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

/**
 * La classe {@code CalcFLSM} fornisce metodi per calcolare le sottoreti in una rete IP basata su una maschera di sottorete iniziale
 * e un numero specificato di sottoreti. Calcola anche gli indirizzi IP associati a ciascuna sottorete, incluso l'indirizzo di rete,
 * il gateway, l'indirizzo di broadcast, il primo e l'ultimo host.
 */
public class CalcFLSM {

    /**
     * Metodo principale per calcolare e stampare le sottoreti.
     * @param args Argomenti della riga di comando (non utilizzati).
     * @throws UnknownHostException Se l'indirizzo IP o la maschera di sottorete non possono essere risolti.
     */
    public static void main(String[] args) throws UnknownHostException {
        // Initial IP address, default subnet mask, and number of subnets
        String ipAddress = "10.0.0.0";
        String initialSubnetMask = "255.255.255.0"; // Example initial subnet mask
        int numSubnets = 4;
        System.out.print(CalcManager.calculateNumberOfHostsPerSubnet(ipAddress,4));
        // Calculate and print subnet information
        ObjSM[] subnets = calculateSubnets(ipAddress, initialSubnetMask, numSubnets);
        for (ObjSM subnet : subnets) {
            System.out.println(subnet);
        }
    }

    /**
     * Calcola le sottoreti basate su un indirizzo IP iniziale, una maschera di sottorete e un numero specificato di sottoreti.
     * @param ipAddress Indirizzo IP iniziale.
     * @param initialSubnetMask Maschera di sottorete iniziale.
     * @param numSubnets Numero di sottoreti desiderate.
     * @return Un array di {@link ObjSM} contenente le informazioni di ciascuna sottorete calcolata.
     * @throws UnknownHostException Se l'indirizzo IP o la maschera di sottorete non possono essere risolti.
     */
    public static ObjSM[] calculateSubnets(String ipAddress, String initialSubnetMask, int numSubnets) throws UnknownHostException {
        // Convert IP address and initial subnet mask to byte arrays
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        byte[] ipBytes = inetAddress.getAddress();

        InetAddress initialSubnet = InetAddress.getByName(initialSubnetMask);
        byte[] initialMaskBytes = initialSubnet.getAddress();

        // Convert initial subnet mask to an integer
        int initialMask = 0;
        for (byte b : initialMaskBytes) {
            initialMask = (initialMask << 8) | (b & 0xFF);
        }

        // Calculate the number of bits for the initial subnet mask
        int initialMaskLength = Integer.bitCount(initialMask);

        // Calculate the number of bits to borrow for the new subnet mask
        int bitsToBorrow = (int) Math.ceil(Math.log(numSubnets) / Math.log(2));
        int newMaskLength = initialMaskLength + bitsToBorrow;

        // Calculate the total number of hosts per subnet with the new subnet mask
        int totalHosts = (int) Math.pow(2, 32 - newMaskLength);

        // Calculate the new subnet mask
        int newMask = initialMask | ((1 << (32 - initialMaskLength)) - 1) & ~((1 << (32 - newMaskLength)) - 1);
        String newSubnetMask = intToIpString(newMask);

        ObjSM[] subnets = new ObjSM[numSubnets];

        for (int i = 0; i < numSubnets; i++) {
            byte[] subnetIpBytes = ipBytes.clone();
            int subnetIncrement = i * totalHosts;

            // Calculate the Network ID for the i-th subnet
            for (int j = subnetIpBytes.length - 1; j >= 0; j--) {
                int part = subnetIpBytes[j] & 0xFF;
                part += subnetIncrement & 0xFF;
                subnetIpBytes[j] = (byte) part;
                subnetIncrement >>= 8;
            }

            String subnetIp = InetAddress.getByAddress(subnetIpBytes).getHostAddress();
            String networkId = subnetIp;
            String gateway = getNextIp(subnetIp, 1);
            String broadcast = getNextIp(subnetIp, totalHosts - 1);
            String firstHost = getNextIp(subnetIp, 2);
            String lastHost = getNextIp(subnetIp, totalHosts - 2);

            // Use the newly calculated subnet mask
            subnets[i] = new ObjSM(
                    i + 1,
                    networkId,
                    newSubnetMask,
                    gateway,
                    broadcast,
                    firstHost,
                    lastHost
            );
        }

        return subnets;
    }

    /**
     * Calcola il prossimo indirizzo IP basato su un offset dato.
     * @param ipAddress Indirizzo IP iniziale.
     * @param offset Offset da aggiungere all'indirizzo IP.
     * @return Il prossimo indirizzo IP calcolato.
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
     * Converte un intero rappresentante un indirizzo IP in una stringa formattata.
     * @param ip Indirizzo IP rappresentato come intero.
     * @return L'indirizzo IP come stringa.
     */
    public static String intToIpString(int ip) {
        return ((ip >> 24) & 0xFF) + "." + ((ip >> 16) & 0xFF) + "." + ((ip >> 8) & 0xFF) + "." + (ip & 0xFF);
    }
}