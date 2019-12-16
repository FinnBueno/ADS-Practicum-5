package controller;

import graphalgorithms.BreadthFirstPath;
import graphalgorithms.DepthFirstPath;
import graphalgorithms.DijkstraShortesPath;
import model.Location;
import model.TransportGraph;

public class TransportGraphLauncher {

    /*public static void main(String[] args) {
        String[] redLine = {"metro", "red", "A", "B", "C", "D"};
        String[] blueLine = {"metro", "blue", "E", "B", "F", "G"};
        String[] greenLine = {"metro", "green", "H", "I", "C", "G", "J"};
        String[] yellowLine = {"bus", "yellow", "A", "E", "H", "D", "G", "A"};

        TransportGraph.Builder tgBuilder = new TransportGraph.Builder();
        tgBuilder.addLine(redLine);
        tgBuilder.addLine(blueLine);
        tgBuilder.addLine(greenLine);
        tgBuilder.addLine(yellowLine);

        TransportGraph transportGraph = tgBuilder
            .buildStationSet()
            .addLinesToStations()
            .buildConnections()
            .build();

        System.out.println(transportGraph);

//        Uncommented to test the DepthFirstPath algorithm
        DepthFirstPath dfpTest = new DepthFirstPath(transportGraph, "E", "J");
        dfpTest.search();
        System.out.println(dfpTest);
        dfpTest.printNodesInVisitedOrder();
        System.out.println();

//        Uncommented to test the BreadthFirstPath algorithm
        BreadthFirstPath bfsTest = new BreadthFirstPath(transportGraph, "E", "J");
        bfsTest.search();
        System.out.println(bfsTest);
        bfsTest.printNodesInVisitedOrder();
    }*/
/*
    public static void main(String[] args){
        String[] redLine = {"metro", "red", "Haven", "Marken", "Steigerplein", "Centrum", "Meridiaan", "Dukdalf", "Oostvaarders"};
        String[] blueLine = {"metro", "blue", "Trojelaan", "Coltrane Cirkel", "Meridiaan", "Robijnpark", "Violetplantsoen"};
        String[] purpleLine = {"metro", "purple", "Grote Sluis", "Grootzeil", "Coltrane Cirkel", "Centrum", "Swingstraat"};
        String[] greenLine = {"metro", "green", "Ymeerdijk", "Trojelaan", "Steigerplein", "Swingstraat", "Bachgracht", "Nobelplein"};
        String[] yellowLine = {"bus", "yellow", "Grote Sluis", "Ymeerdijk", "Haven", "Nobelplein", "Violetplantsoen", "Oostvaarders", "Grote Sluis"};

        Double[] redWeight = {4.5, 4.7, 6.1, 3.5, 5.4, 5.6};
        Double[] blueWeight = {6.0, 5.3, 5.1, 3.3};
        Double[] purpleWeight = {6.2, 5.2, 3.8, 3.6};
        Double[] greenWeight = {5.0, 3.7, 5.9, 3.9, 3.4};
        Double[] yellowWeight = {26.0, 19.0, 37.0, 25.0, 22.0, 28.0};
        //Double[] lineWeights = {3.7, 3.4, 37.0, 5.6, 25.0, 3.6, 5.0, 28.0, 5.3, 5.4, 22.0, 6.1, 3.3, 5.1, 26.0, 4.5, 6.0, 6.9, 3.5, 3.8, 6.2, 19.0, 5.2, 3.9, 4.7};


        TransportGraph.Builder tgBuilder = new TransportGraph.Builder();
        tgBuilder.addLine(redLine);
        tgBuilder.addLine(blueLine);
        tgBuilder.addLine(purpleLine);
        tgBuilder.addLine(greenLine);
        tgBuilder.addLine(yellowLine);
        //tgBuilder.addWeights(lineWeights);
        tgBuilder.addWeights(redWeight);
        tgBuilder.addWeights(blueWeight);
        tgBuilder.addWeights(purpleWeight);
        tgBuilder.addWeights(greenWeight);
        tgBuilder.addWeights(yellowWeight);

        TransportGraph transportGraph = tgBuilder
                .buildStationSet()
                .addLinesToStations()
                .buildConnections()
                .build();

        System.out.println(transportGraph);

        //        Uncommented to test the DepthFirstPath algorithm
        DepthFirstPath dfpTest = new DepthFirstPath(transportGraph, "Oostvaarders", "Nobelplein");
        dfpTest.search();
        System.out.println(dfpTest);
        dfpTest.printNodesInVisitedOrder();
        System.out.println();

//        Uncommented to test the BreadthFirstPath algorithm
        BreadthFirstPath bfsTest = new BreadthFirstPath(transportGraph, "Oostvaarders", "Haven");
        bfsTest.search();
        System.out.println(bfsTest);
        bfsTest.printNodesInVisitedOrder();
        System.out.println();

        // Dijkstra test...
        DijkstraShortesPath dspTest = new DijkstraShortesPath(transportGraph, "Oostvaarders", "Nobelplein");
        dspTest.search();
        System.out.println(dspTest);
        dspTest.printNodesInVisitedOrder();
    }
*/
    public static void main(String[] args) {
        String[] redLine = {"metro", "red", "Haven", "Marken", "Steigerplein", "Centrum", "Meridiaan", "Dukdalf", "Oostvaarders"};
        String[] blueLine = {"metro", "blue", "Trojelaan", "Coltrane Cirkel", "Meridiaan", "Robijnpark", "Violetplantsoen"};
        String[] purpleLine = {"metro", "purple", "Grote Sluis", "Grootzeil", "Coltrane Cirkel", "Centrum", "Swingstraat"};
        String[] greenLine = {"metro", "green", "Ymeerdijk", "Trojelaan", "Steigerplein", "Swingstraat", "Bachgracht", "Nobelplein"};
        String[] yellowLine = {"bus", "yellow", "Grote Sluis", "Ymeerdijk", "Haven", "Nobelplein", "Violetplantsoen", "Oostvaarders", "Grote Sluis"};

        Double[] redWeight = {4.5, 4.7, 6.1, 3.5, 5.4, 5.6};
        Double[] blueWeight = {6.0, 5.3, 5.1, 3.3};
        Double[] purpleWeight = {6.2, 5.2, 3.8, 3.6};
        Double[] greenWeight = {5.0, 3.7, 5.9, 3.9, 3.4};
        Double[] yellowWeight = {26.0, 19.0, 37.0, 25.0, 22.0, 28.0};

        Location[] redLocations = {new Location(14, 1), new Location(12, 3), new Location(10, 5), new Location(8, 8), new Location(6, 9), new Location(3, 10), new Location(0, 11)};
        Location[] blueLocations = {new Location(9, 3), new Location(7, 6), new Location(6, 9), new Location(6, 12), new Location(5, 14)};
        Location[] purpleLocations = {new Location(2, 3), new Location(4, 6), new Location(7, 6), new Location(8, 8), new Location(10, 9)};
        Location[] greenLocations = {new Location(9, 0), new Location(9, 3), new Location(10, 5), new Location(10, 9), new Location(11, 11), new Location(12, 13)};
        Location[] yellowLocations = {new Location(2, 3), new Location(9, 0), new Location(14, 1), new Location(12, 13), new Location(5, 14), new Location(0, 11), new Location(2, 3)};


        TransportGraph.Builder tgBuilder = new TransportGraph.Builder();
        tgBuilder.addLineLocation(redLine, redLocations);
        tgBuilder.addLineLocation(blueLine, blueLocations);
        tgBuilder.addLineLocation(purpleLine, purpleLocations);
        tgBuilder.addLineLocation(greenLine, greenLocations);
        tgBuilder.addLineLocation(yellowLine, yellowLocations);
        tgBuilder.addWeights(redWeight);
        tgBuilder.addWeights(blueWeight);
        tgBuilder.addWeights(purpleWeight);
        tgBuilder.addWeights(greenWeight);
        tgBuilder.addWeights(yellowWeight);

        TransportGraph transportGraph = tgBuilder
                .buildStationSet()
                .addLinesToStations()
                .buildConnections()
                .build();

        System.out.println(transportGraph);

        //        Uncommented to test the DepthFirstPath algorithm
        DepthFirstPath dfpTest = new DepthFirstPath(transportGraph, "Oostvaarders", "Nobelplein");
        dfpTest.search();
        System.out.println(dfpTest);
        dfpTest.printNodesInVisitedOrder();
        System.out.println();

//        Uncommented to test the BreadthFirstPath algorithm
        BreadthFirstPath bfsTest = new BreadthFirstPath(transportGraph, "Oostvaarders", "Haven");
        bfsTest.search();
        System.out.println(bfsTest);
        bfsTest.printNodesInVisitedOrder();
        System.out.println();

        // Dijkstra test...
        DijkstraShortesPath dspTest = new DijkstraShortesPath(transportGraph, "Oostvaarders", "Nobelplein");
        dspTest.search();
        System.out.println(dspTest);
        dspTest.printNodesInVisitedOrder();
    }
}



