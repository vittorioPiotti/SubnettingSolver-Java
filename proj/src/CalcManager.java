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
import java.util.regex.Pattern;

public class CalcManager {

    public static void main(String[] args) {
        // Esempi di utilizzo dei metodi di controllo
        System.out.println(isValidIPAddress("192.168.1.1")); // true
        System.out.println(isValidIPAddress("999.999.999.999")); // false
        System.out.println(isValidSubnetMask("255.255.255.0")); // true
        System.out.println(isValidSubnetMask("255.0.0.255")); // false
        System.out.println(isValidNumberOfSubnets(4)); // true
        System.out.println(isValidNumberOfSubnets(-1)); // false
        System.out.println(isValidNumberOfHosts(256)); // true
        System.out.println(isValidNumberOfHosts(-10)); // false
    }

    public static boolean isValidIPAddress(String ipAddress) {
        // Pattern regex per un indirizzo IP valido
        String ipPattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        return Pattern.matches(ipPattern, ipAddress);
    }

    public static boolean isValidSubnetMask(String subnetMask) {
        try {
            // Convertire l'indirizzo in byte
            InetAddress inetAddress = InetAddress.getByName(subnetMask);
            byte[] addressBytes = inetAddress.getAddress();

            // Convertire i byte in un intero
            int mask = 0;
            for (byte b : addressBytes) {
                mask = (mask << 8) | (b & 0xFF);
            }

            // Verifica che la maschera di sottorete sia contigua
            boolean foundZero = false;
            for (int i = 31; i >= 0; i--) {
                if ((mask & (1 << i)) == 0) {
                    foundZero = true;
                } else if (foundZero) {
                    return false;
                }
            }
            return true;
        } catch (UnknownHostException e) {
            return false;
        }
    }

    public static boolean isValidNumberOfSubnets(int numSubnets) {
        // Il numero di sottoreti deve essere un intero positivo
        return numSubnets > 0;
    }

    public static boolean isValidNumberOfSubnets(String numHosts) {
        // Controllo se la stringa è vuota
        if (numHosts.isEmpty()) {
            return false;
        }
    
        // Controllo se la stringa contiene un numero decimale valido
        try {
            double number = Double.parseDouble(numHosts);
            // Verifica che il numero sia maggiore di 0
            return number > 0;
        } catch (NumberFormatException e) {
            // Se non è possibile convertire la stringa in un numero, ritorna false
            return false;
        }
    }
    public static boolean isValidNumberOfHosts(int numHosts) {
        // Il numero di host deve essere un intero positivo
        return numHosts > 0;
    }
    public static boolean isValidNumberOfHosts(String numHosts) {
        // Controllo se la stringa è vuota
        if (numHosts.isEmpty()) {
            return false;
        }
    
        // Controllo se la stringa contiene un numero decimale valido
        try {
            double number = Double.parseDouble(numHosts);
            // Verifica che il numero sia maggiore di 0
            return number > 0;
        } catch (NumberFormatException e) {
            // Se non è possibile convertire la stringa in un numero, ritorna false
            return false;
        }
    }
    

    public static int calculateNumberOfSubnets(String ipAddress, int numHosts) {
        // Numero di bit necessari per rappresentare il numero di host
        int hostBits = (int) Math.ceil(Math.log(numHosts + 2) / Math.log(2)); // +2 per network e broadcast

        // Numero di bit disponibili per le sottoreti
        int subnetBits = 32 - hostBits;

        // Numero massimo di sottoreti
        int maxSubnets = 1 << subnetBits;
        
        return maxSubnets;
    }

    public static int calculateNumberOfHostsPerSubnet(String ipAddress, int numSubnets) {
        // Numero di bit richiesti per il numero di sottoreti
        int subnetBits = (int) Math.ceil(Math.log(numSubnets) / Math.log(2));

        // Numero di bit disponibili per gli host
        int hostBits = 32 - subnetBits;

        // Numero massimo di host per sottorete
        int numHosts = (1 << hostBits) - 2; // -2 per network e broadcast
        
        return numHosts;
    }
    
}
