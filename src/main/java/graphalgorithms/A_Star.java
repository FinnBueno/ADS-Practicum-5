package graphalgorithms;

import model.Connection;
import model.IndexMinPQ;
import model.Station;
import model.TransportGraph;

/**
 * @author Finn Bon
 */
public class A_Star extends AbstractPathSearch {

	private final Station endLocation;
	private final double startToEnd;
	private double[] distTo;
	private IndexMinPQ<Double> queue;

	public A_Star(TransportGraph graph, String start, String end) {
		super(graph, start, end);
		distTo = new double[graph.getNumberOfStations()];
		endLocation = graph.getStationList().get(endIndex);
		startToEnd = graph.getStationList().get(startIndex).getLocation().travelTime(endLocation.getLocation());
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

		for (int i = 0; i < distTo.length; i++) {
			queue.insert(i, distTo[i]);
		}
		while(!queue.isEmpty() && !hasPathTo(endIndex)){
			int outOfQueue = queue.delMin();
			relax(graph, outOfQueue);
		}

		// Not sure if this is da wei
		if(hasPathTo(endIndex))
			pathTo(endIndex);
	}

	private void relax(TransportGraph graph, int vector) {
		for (int adj : graph.getAdjacentVertices(vector)){
			Connection conn = graph.getConnection(vector, adj);
			double straightDistance = endLocation.getLocation().travelTime(conn.getTo().getLocation());
			if(distTo[adj] > distTo[vector] + conn.getWeight() + straightDistance) {
				distTo[adj] = distTo[vector] + conn.getWeight();
				edgeTo[adj] = vector;
				nodesVisited.add(graph.getStationList().get(adj));
				if(queue.contains(adj))
					queue.changeKey(adj, distTo[adj] + straightDistance);
				else
					queue.insert(adj, distTo[adj] + straightDistance);
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

	public double getTotalTimeTaken() {
		return getTotalWeight(endIndex);
	}

	@Override
	public String toString() {
		StringBuilder resultString = new StringBuilder(String.format("Path from %s to %s: ", graph.getStationList().get(startIndex), graph.getStationList().get(endIndex)));
		resultString.append(nodesInPath).append(" with " + transfers).append(" transfers").append(" and takes " + getTotalWeight(endIndex) + " minutes");
		return resultString.toString();
	}
}
