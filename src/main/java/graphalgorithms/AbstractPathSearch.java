package graphalgorithms;

import model.Connection;
import model.Line;
import model.Station;
import model.TransportGraph;

import java.util.*;

/**
 * Abstract class that contains methods and attributes shared by the DepthFirstPath en BreadthFirstPath classes
 */
public abstract class AbstractPathSearch {

    protected boolean[] marked;
    protected int[] edgeTo;
    protected int transfers = 0;
    protected List<Station> nodesVisited;
    // made this a linked list to prevent having to inverse it
    protected LinkedList<Station> nodesInPath;
    protected LinkedList<Integer> verticesInPath;
    protected TransportGraph graph;
    protected final int startIndex;
    protected final int endIndex;


    public AbstractPathSearch(TransportGraph graph, String start, String end) {
        startIndex = graph.getIndexOfStationByName(start);
        endIndex = graph.getIndexOfStationByName(end);
        this.graph = graph;
        nodesVisited = new ArrayList<>();
        marked = new boolean[graph.getNumberOfStations()];
        edgeTo = new int[graph.getNumberOfStations()];
    }

    public abstract void search();

    /**
     * @param vertex Determines whether a path exists to the station as an index from the start station
     * @return
     */
    public boolean hasPathTo(int vertex) {
        return marked[vertex];
    }


    /**
     * Method to build the path to the vertex, index of a Station.
     * First the LinkedList verticesInPath, containing the indexes of the stations, should be build, used as a stack
     * Then the list nodesInPath containing the actual stations is build.
     * Also the number of transfers is counted.
     * @param vertex The station (vertex) as an index
     */
    public void pathTo(int vertex) {
        verticesInPath = new LinkedList<>();
        nodesInPath = new LinkedList<>();
        int current = vertex;
        do {
            verticesInPath.addFirst(current);
            nodesInPath.addFirst(graph.getStationList().get(current));
            current = edgeTo[current];
        } while (current >= 0);
        countTransfers();
    }

    /**
     * Method to count the number of transfers in a path of vertices.
     * Uses the line information of the connections between stations.
     * If to consecutive connections are on different lines there was a transfer.
     */
    public void countTransfers() {
        Iterator<Integer> path = verticesInPath.iterator();
        Line previousLine = null;
        int from = path.next();
        while (path.hasNext()) {
            int to = path.next();
            Connection connection = graph.getConnection(from, to);
            if (previousLine != null && previousLine != connection.getLine()) {
                transfers++;
            }
            previousLine = connection.getLine();
            from = to;
        }
    }


    /**
     * Method to print all the nodes that are visited by the search algorithm implemented in one of the subclasses.
     */
    public void printNodesInVisitedOrder() {
        System.out.printf("Nodes in visited order (%d): ", nodesVisited.size());
        for (Station vertex : nodesVisited) {
            System.out.print(vertex.getStationName() + ", ");
        }
        System.out.println();
    }

    public int amountOfNodesVisited() {
        return nodesVisited.size();
    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder(String.format("Path from %s to %s: ", graph.getStationList().get(startIndex), graph.getStationList().get(endIndex)));
        resultString.append(nodesInPath).append(" with " + transfers).append(" transfers");
        return resultString.toString();
    }

}
