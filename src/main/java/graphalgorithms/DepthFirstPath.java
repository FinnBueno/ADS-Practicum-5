package graphalgorithms;

import model.Station;
import model.TransportGraph;

import java.util.List;

/**
 * @author Finn Bon
 */
public class DepthFirstPath extends AbstractPathSearch {

	public DepthFirstPath(TransportGraph graph, int start, int end) {
		super(graph, start, end);
		depthFirstSearch(start);
	}

	public void depthFirstSearch(int vertex) {
		depthFirstSearch(vertex, -1);
	}

	private void depthFirstSearch(int vertex, int parent) {
		// don't revisit
		if (hasPathTo(vertex)) {
			return;
		}

		// mark as visited
		marked[vertex] = true;
		// build an array to find a node's parent
		edgeTo[vertex] = parent;

		// add station to visited list for printing later
		nodesVisited.add(graph.getStation(vertex));

		// see if we found our end station
		if (vertex == endIndex) {
			pathTo(vertex);
			return;
		}

		// search neighbours
		List<Integer> neighbours = graph.getAdjacentVertices(vertex);
		for (Integer neighbour : neighbours) {
			depthFirstSearch(neighbour, vertex);
		}
	}
}
