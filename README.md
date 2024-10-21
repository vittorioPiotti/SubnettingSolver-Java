# Subnetting Java

Soluzione software per la risoluzione degli esercizi sulle reti.

> [!NOTE]
> Software responsive con schermata Small e Large



> [!WARNING]
> Correttezza VLSM non garantita

#### Support Me


[![ko-fi](https://ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/P5P012BC8U)


## Indice

 - [Funzioni](#funzioni)
 - [Ispirazione](#ispirazione)
 - [Javadoc](#javadoc)
 - [Preview](#preview)
 - [Screenshots](#screenshots)
 - [Distribuzione](#distribuzione)
 - [Albero di Path](#albero-di-path)
 - [Crediti](#crediti)
 - [Licenze](#licenze)

## Funzioni

 - Identifica IP
 - Verifica Stessa Rete
 - Subnet a Maschera Fissa - FLSM
 - Subnet a Maschera Variabile - VLSM

## Ispirazione

La grafica del software è stata ispirata e **sviluppata indipendentemente** riproponendo l'interfaccia utente offerta da [Scheduling Solver](https://process-scheduling-solver.boonsuen.com/)

## Javadoc

Link al javadoc [(link)](https://vittoriopiotti.altervista.org/SubnettingJava/org/subnetting/package-summary.html)

## Preview

Video di Test [(link)](https://drive.google.com/file/d/17XctjTlS7LYgrnKikc7BHBkw4H3vvMtA/view?usp=sharing)


---

<img src="https://github.com/vittorioPiotti/Subnet-Solver-Java/blob/main/screenshots/vlsm.png" />


## Screenshots


|<img src="https://github.com/vittorioPiotti/Subnet-Solver-Java/blob/main/screenshots/ip.png" /> | <img src="https://github.com/vittorioPiotti/Subnet-Solver-Java/blob/main/screenshots/net.png" />|
|-|-|
|<img src="https://github.com/vittorioPiotti/Subnet-Solver-Java/blob/main/screenshots/flsm.png" />|<img src="https://github.com/vittorioPiotti/Subnet-Solver-Java/blob/main/screenshots/vlsm.png" />|


## Distribuzione

 - Mac o Windows
 - openjdk `v.21.0.3`
 - Avviare il file `.jar`


## Albero di Path


```bash
$ tree
.
├── assets * contiene file.svg
├── bin * contiene i file binari
├── lib
│   ├── batik-all-1.17.jar 
│   ├── flatlaf-3.2.5.jar 
│   ├── xml-apis-ext-1.3.04.jar
│   └── xmlgraphics-commons-2.9.jar
├── src
│   ├── AnimatedButton.java 
│   ├── AnimatedLabel.java 
│   ├── AnimatedModal.java 
│   ├── AnimatedOpacityPanel.java 
│   ├── AnimatedRoundedLabel.java
│   ├── AnimatedSVG.java
│   ├── CalcFLSM.java
│   ├── CalcIP.java 
│   ├── CalcManager.java 
│   ├── CalcNetIDs.java 
│   ├── CalcVLSM.java 
│   ├── GUI.java * main del progetto
│   ├── LayoutTable.java
│   ├── ObjIP.java
│   ├── ObjNetIDs.java
│   ├── ObjSM.java
│   ├── RoundedButton.java
│   ├── RoundedLabel.java
│   ├── RoundedPanel.java
│   ├── RoundedPanelApp.java
│   └── RoundedTextField.java
└── SubnetSolver.jar * eseguibile

```

## Crediti

[Reda Karimi](https://github.com/RedaKarimi):
 - Correzzione errore caricamento SVG su Windows
 - Caricamento delle dipendenze da pom.xml anzichè da import diretto

## Licenze


> [!NOTE]
> Di **Bootstrap** sono state utilizzate solo le icone



---

### Subnet Solver

**Copyright** 2024 Vittorio Piotti [(GitHub page)](https://github.com/vittorioPiotti) [(Personal page)](https://vittoriopiotti.altervista.org/)

**Version** [v1.0.0](https://github.com/vittorioPiotti/SubnettingSolver-Java/releases/tag/1.0.0)

**License** [GPL-3.0 License](https://github.com/vittorioPiotti/Subnet-Solver-Java/blob/main/LICENSE.md)

---

### batik-all

**Copyright** 2024 The Apache Software Foundation

**Version** [v1.17](https://mvnrepository.com/artifact/org.apache.xmlgraphics/batik-all/1.17)

**License** [Apache License 2.0](https://xmlgraphics.apache.org/batik/license.html)

---

### FlatLaf

**Copyright** 2024 JFormDesigner GmbH

**Version** [v3.2.5](https://mvnrepository.com/artifact/com.formdev/flatlaf/3.2.5)

**License** [Apache License 2.0](https://github.com/JFormDesigner/FlatLaf/blob/main/LICENSE)

---

### xml-apis-ext

**Copyright** 2024 The Apache Software Foundation

**Version** [v1.3.04](https://mvnrepository.com/artifact/xml-apis/xml-apis-ext/1.3.04)

**License** [Apache License 2.0](https://xmlgraphics.apache.org/batik/license.html)

---

### xmlgraphics-commons

**Copyright** 2024 The Apache Software Foundation

**Version** [v2.9](https://mvnrepository.com/artifact/org.apache.xmlgraphics/xmlgraphics-commons/2.9)

**License** [Apache License 2.0](https://xmlgraphics.apache.org/batik/license.html)

---

### Bootstrap Icons

**Copyright** 2011-2018 The Bootstrap Authors 

**Version** [v1.11.0](https://blog.getbootstrap.com/2023/09/12/bootstrap-icons-1-11-0/)

**License** [MIT](https://github.com/twbs/icons/blob/main/LICENSE)





