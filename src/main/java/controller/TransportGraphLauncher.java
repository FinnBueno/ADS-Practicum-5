package controller;

import graphalgorithms.DepthFirstPath;
import model.TransportGraph;

public class TransportGraphLauncher {

    public static void main(String[] args) {
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
        DepthFirstPath dfpTest = new DepthFirstPath(transportGraph, 4, 9);
        System.out.println(dfpTest);
        dfpTest.printNodesInVisitedOrder();
        System.out.println();

//        Uncommented to test the BreadthFirstPath algorithm
        /*BreadthFirstPath bfsTest = new BreadthFirstPath(transportGraph, 4, 9);
        System.out.println(bfsTest);
        bfsTest.printNodesInVisitedOrder();*/

    }
}
