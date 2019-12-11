package graphalgorithms;

import model.Station;
import model.TransportGraph;

import java.util.List;

/**
 * @author Finn Bon
 */
public class DepthFirstPath extends AbstractPathSearch {

	public DepthFirstPath(TransportGraph graph, String start, String end) {
		super(graph, start, end);
	}

	@Override
	public void search() {
		depthFirstSearch(startIndex, -1);
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
		nodesVisited.add(graph.getStationList().get(vertex));

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
