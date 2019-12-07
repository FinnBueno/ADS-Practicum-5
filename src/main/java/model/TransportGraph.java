package model;

import java.util.*;
import java.util.stream.Collectors;

public class TransportGraph {

    private int numberOfStations;
    private int numberOfConnections;
    private List<Station> stationList; // vertices
    private Map<Station, Integer> stationIndices;
    private List<Integer>[] adjacencyLists;
    private Connection[][] connections; // edges

    public TransportGraph (int size) {
        this.numberOfStations = size;
        stationList = new ArrayList<>(size);
        stationIndices = new HashMap<>();
        connections = new Connection[size][size];
        adjacencyLists = (List<Integer>[]) new List[size];
        for (int vertex = 0; vertex < size; vertex++) {
            adjacencyLists[vertex] = new LinkedList<>();
        }
    }

    /**
     * @param vertex Station to be added to the stationList
     * The method also adds the station with it's index to the map stationIndices
     */
    public void addVertex(Station vertex) {
        stationList.add(vertex);
        stationIndices.put(vertex, stationList.size() - 1);
    }


    /**
     * Method to add an edge to a adjancencyList. The indexes of the vertices are used to define the edge.
     * Method also increments the number of edges, that is number of Connections.
     * The grap is bidirected, so edge(to, from) should also be added.
     * @param from
     * @param to
     */
    private void addEdge(int from, int to) {
        // TODO
        this.numberOfConnections++;
        this.adjacencyLists[from].add(to);
    }


    /**
     * Method to add an edge in the form of a connection between stations.
     * The method also adds the edge as an edge of indices by calling addEdge(int from, int to).
     * The method adds the connecion to the connections 2D-array.
     * The method also builds the reverse connection, Connection(To, From) and adds this to the connections 2D-array.
     * @param connection The edge as a connection between stations
     */
    public void addEdge(Connection connection) {
        int fromIndex = stationIndices.get(connection.getFrom());
        int toIndex = stationIndices.get(connection.getTo());

        addEdge(fromIndex, toIndex);
        addEdge(toIndex, fromIndex);

        connections[fromIndex][toIndex] = connection;
        connections[toIndex][fromIndex] = new Connection(connection.getTo(), connection.getFrom());
    }

    public List<Integer> getAdjacentVertices(int index) {
        return adjacencyLists[index];
    }

    public Connection getConnection(int from, int to) {
        return connections[from][to];
    }

    public Station getStation(int index) {
        return stationList.get(index);
    }

    public int getNumberOfStations() {
        return numberOfStations;
    }

    public List<Station> getStationList() {
        return stationList;
    }

    public int getNumberEdges() {
        return numberOfConnections;
    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();
        System.out.println(stationList.size());
        resultString.append(String.format("Graph with %d vertices and %d edges: \n", numberOfStations, numberOfConnections));
        for (int indexVertex = 0; indexVertex < numberOfStations; indexVertex++) {
            resultString.append(stationList.get(indexVertex) + ": ");
            int loopsize = adjacencyLists[indexVertex].size() - 1;
            for (int indexAdjacent = 0; indexAdjacent < loopsize; indexAdjacent++) {
                resultString.append(stationList.get(adjacencyLists[indexVertex].get(indexAdjacent)).getStationName() + "-" );
            }
            resultString.append(stationList.get(adjacencyLists[indexVertex].get(loopsize)).getStationName() + "\n");
        }
        return resultString.toString();
    }


    /**
     * A Builder helper class to build a TransportGraph by adding lines and building sets of stations and connections from these lines.
     * Then build the graph from these sets.
     */
    public static class Builder {

        private Set<Station> stationSet;
        private List<Line> lineList;
        private Set<Connection> connectionSet;

        public Builder() {
            lineList = new ArrayList<>();
            stationSet = new HashSet<>();
            connectionSet = new HashSet<>();
        }

        /**
         * Method to add a line to the list of lines and add stations to the line.
         * @param lineDefinition String array that defines the line. The array should start with the name of the line,
         *                       followed by the type of the line and the stations on the line in order.
         * @return
         */
        public Builder addLine(String[] lineDefinition) {
            Line line = new Line(lineDefinition[0], lineDefinition[1]);
            for (int i = 2; i < lineDefinition.length; i++) {
                Station station = new Station(lineDefinition[i]);
                line.addStation(station);
            }
            lineList.add(line);
            return this;
        }


        /**
         * Method that reads all the lines and their stations to build a set of stations.
         * Stations that are on more than one line will only appear once in the set.
         * @return
         */
        public Builder buildStationSet() {
            for (Line line : lineList) {
                // duplicates are handled by Set itself
                stationSet.addAll(line.getStationsOnLine());
            }
            return this;
        }

        /**
         * For every station on the set of station add the lines of that station to the lineList in the station
         * @return
         */
        public Builder addLinesToStations() {
            for (Station station : stationSet) {
                // find all lines passing through station
                lineList
                    .stream()
                    .filter(line -> line.getStationsOnLine().contains(station))
                    .forEach(station::addLine);
            }
            return this;
        }

        /**
         * Method that uses the list of Lines to build connections from the consecutive stations in the stationList of a line.
         * @return
         */
        public Builder buildConnections() {
            for (Line line : lineList) {
                List<Station> stationsOnLine = line.getStationsOnLine();
                for (int i = 0; i < stationsOnLine.size() - 1; i++) {
                    Station from = stationsOnLine.get(i);
                    Station to = stationsOnLine.get(i + 1);
                    connectionSet.add(new Connection(from, to));
                }
            }
            return this;
        }

        /**
         * Method that builds the graph.
         * All stations of the stationSet are addes as vertices to the graph.
         * All connections of the connectionSet are addes as edges to the graph.
         * @return
         */
        public TransportGraph build() {
            TransportGraph graph = new TransportGraph(stationSet.size());
            stationSet.forEach(graph::addVertex);
            connectionSet.forEach(graph::addEdge);
            return graph;
        }

    }
}
