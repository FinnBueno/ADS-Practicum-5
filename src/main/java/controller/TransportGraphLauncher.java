package controller;

import graphalgorithms.A_Star;
import graphalgorithms.BreadthFirstPath;
import graphalgorithms.DepthFirstPath;
import graphalgorithms.DijkstraShortesPath;
import model.Location;
import model.TransportGraph;

import java.util.Arrays;
import java.util.LinkedList;

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

    public static void main(String[] args){
        String[] redLine = {"metro", "red", "Haven", "Marken", "Steigerplein", "Centrum", "Meridiaan", "Dukdalf", "Oostvaarders"};
        String[] blueLine = {"metro", "blue", "Trojelaan", "Coltrane Cirkel", "Meridiaan", "Robijnpark", "Violetplantsoen"};
        String[] purpleLine = {"metro", "purple", "Grote Sluis", "Grootzeil", "Coltrane Cirkel", "Centrum", "Swingstraat"};
        String[] greenLine = {"metro", "green", "Ymeerdijk", "Trojelaan", "Steigerplein", "Swingstraat", "Bachgracht", "Nobelplein"};
        String[] yellowLine = {"bus", "yellow", "Grote Sluis", "Ymeerdijk", "Haven", "Nobelplein", "Violetplantsoen", "Oostvaarders", "Grote Sluis"};

        int[][] redLocations = {
            {14, 1},
            {12, 3},
            {10, 5},
            {8, 8},
            {6, 9},
            {3, 10},
            {0, 11}
        };
        int[][] blueLocations = {
            {9, 3},
            {7, 6},
            {6, 9},
            {6, 12},
            {5, 14}
        };
        int[][] purpleLocations = {
            {2, 3},
            {4, 6},
            {7, 6},
            {8, 8},
            {10, 9}
        };
        int[][] greenLocations = {
            {9, 0},
            {9, 3},
            {10, 5},
            {10, 9},
            {11, 11},
            {12, 13},
        };
        int[][] yellowLocations = {
            {2, 3},
            {9, 0},
            {14, 1},
            {12, 13},
            {5, 14},
            {0, 11},
            {2, 3}
        };

        Double[] redWeight = {4.5, 4.7, 6.1, 3.5, 5.4, 5.6};
        Double[] blueWeight = {6.0, 5.3, 5.1, 3.3};
        Double[] purpleWeight = {6.2, 5.2, 3.8, 3.6};
        Double[] greenWeight = {5.0, 3.7, 5.9, 3.9, 3.4};
        Double[] yellowWeight = {26.0, 19.0, 37.0, 25.0, 22.0, 28.0};
        //Double[] lineWeights = {3.7, 3.4, 37.0, 5.6, 25.0, 3.6, 5.0, 28.0, 5.3, 5.4, 22.0, 6.1, 3.3, 5.1, 26.0, 4.5, 6.0, 6.9, 3.5, 3.8, 6.2, 19.0, 5.2, 3.9, 4.7};

        TransportGraph.Builder tgBuilder = new TransportGraph.Builder();
        tgBuilder.addLine(redLine, redLocations);
        tgBuilder.addLine(blueLine, blueLocations);
        tgBuilder.addLine(purpleLine, purpleLocations);
        tgBuilder.addLine(greenLine, greenLocations);
        tgBuilder.addLine(yellowLine, yellowLocations);
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

        // Dijkstra test
        DijkstraShortesPath dspTest = new DijkstraShortesPath(transportGraph, "Oostvaarders", "Nobelplein");
        dspTest.search();
        System.out.println(dspTest);
        dspTest.printNodesInVisitedOrder();
        System.out.println();

        // A* test
        A_Star a_StarTest = new A_Star(transportGraph, "Oostvaarders", "Nobelplein");
        a_StarTest.search();
        System.out.println(a_StarTest);
        a_StarTest.printNodesInVisitedOrder();

        // Quantity test
        String[] stations = new String[] { "Oostvaarders", "Haven", "Marken", "Steigerplein", "Centrum", "Meridiaan", "Dukdalf", "Oostvaarders", "Trojelaan", "Coltrane Cirkel", "Robijnpark", "Violetplantsoen", "Grote Sluis", "Grootzeil", "Swingstraat", "Ymeerdijk", "Bachgracht", "Nobelplein", };

        DijkstraShortesPath dsp;
        int nodesVisitedDijkstra = 0;
        double timeTakenDijkstra = 0;
        for (String start : stations) {
            for (String end : stations) {
                if (start.equals(end)) {
                    continue;
                }
                dsp = new DijkstraShortesPath(transportGraph, start, end);
                dsp.search();
                nodesVisitedDijkstra += dsp.amountOfNodesVisited();
                timeTakenDijkstra += dsp.getTotalTimeTaken();
            }
        }
        System.out.println();


        A_Star aStar;
        int nodesVisitedAStar = 0;
        double timeTakenAStar = 0;
        for (String start : stations) {
            for (String end : stations) {
                aStar = new A_Star(transportGraph, start, end);
                aStar.search();
                nodesVisitedAStar += aStar.amountOfNodesVisited();
                timeTakenAStar += aStar.getTotalTimeTaken();
            }
        }
        System.out.println();

        System.out.println("Ran every station to station for Dijkstra and A*");
        System.out.println();

        System.out.printf("Dijkstra visited a total of %d nodes, total time of routes is %.2f minutes%n", nodesVisitedDijkstra, timeTakenDijkstra);
        System.out.printf("A* visited a total of %d nodes, total time of routes is %.2f minutes%n", nodesVisitedAStar, timeTakenAStar);
        System.out.println();

        int notVisited = nodesVisitedDijkstra - nodesVisitedAStar;
        double averageTimeDijkstra = timeTakenDijkstra / Math.pow(stations.length, 2);
        double averageTimeAStar = timeTakenAStar / Math.pow(stations.length, 2);
        System.out.printf("A* visited %d less nodes than Dijkstra, making for an improvement of %.2f%%%n", notVisited, notVisited / (float) nodesVisitedDijkstra * 100.0);
        System.out.printf("Dijkstra has an average travel time of %.2f minutes%n", averageTimeDijkstra);
        System.out.printf("A* has an average travel time of %.2f minutes%n", averageTimeAStar);
    }
}
