package graphalgorithms;

import model.Connection;
import model.IndexMinPQ;
import model.TransportGraph;

public class DijkstraShortesPath extends AbstractPathSearch {

    private double[] distTo;
    private IndexMinPQ<Double> queue;

    public DijkstraShortesPath(TransportGraph graph, String start, String end) {
        super(graph, start, end);
        distTo = new double[graph.getNumberOfStations()];
    }

    @Override
    public void search() {
        queue = new IndexMinPQ<>(graph.getNumberOfStations());
        edgeTo[startIndex] = -1;
        nodesVisited.add(graph.getStationList().get(startIndex));

        for (int vector = 0; vector < graph.getNumberOfStations(); vector++){
            distTo[vector] = Double.POSITIVE_INFINITY;
        }
        distTo[startIndex] = 0.0;

        queue.insert(startIndex, 0.0);
        while(!queue.isEmpty()){
            relax(graph, queue.delMin());
        }

        // Not sure if this is da wei
        if(hasPathTo(endIndex))
            pathTo(endIndex);
    }

    private void relax(TransportGraph graph, int vector) {
        for (int adj : graph.getAdjacentVertices(vector)){
            Connection conn = graph.getConnection(vector, adj);
            if(distTo[adj] > distTo[vector] + conn.getWeight()){
                distTo[adj] = distTo[vector] + conn.getWeight();
                edgeTo[adj] = vector;
                nodesVisited.add(graph.getStationList().get(adj));
                if(queue.contains(adj))
                    queue.changeKey(adj, distTo[adj]);
                else
                    queue.insert(adj, distTo[adj]);
            }
        }
    }

    @Override
    public boolean hasPathTo(int vertex) {
        return distTo[vertex] < Double.POSITIVE_INFINITY;
    }

    public double getTotalWeight(int vertex){
        return distTo[vertex];
    }
}
