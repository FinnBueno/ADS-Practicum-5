package graphalgorithms;

import model.TransportGraph;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstPath extends AbstractPathSearch {

    public BreadthFirstPath(TransportGraph graph, String start, String end) {
        super(graph, start, end);
    }

    @Override
    public void search() {
        Queue<Integer> queue = new LinkedList<>();
        marked[startIndex] = true;
        edgeTo[startIndex] = -1;
        queue.add(startIndex);
        nodesVisited.add(graph.getStationList().get(startIndex));

        while(!queue.isEmpty()){
            int vertex = queue.poll();
            if(vertex == endIndex){
                pathTo(vertex);
                break;
            }
            for(int connection : graph.getAdjacentVertices(vertex)){
                if(!marked[connection]){
                    edgeTo[connection] = vertex;
                    nodesVisited.add(graph.getStationList().get(connection));
                    queue.add(connection);
                    marked[connection] = true;
                }
            }
        }
    }
}
