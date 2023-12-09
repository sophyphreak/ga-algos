
// import java.util.ArrayList;
import java.util.HashSet;
// import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Your implementation of Prim's algorithm.
 */
public class GraphAlgorithms {

    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops or parallel edges in the MST.
     *
     * You may import/use java.util.PriorityQueue, java.util.Set, and any
     * class that implements the aforementioned interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * The only instance of java.util.Map that you may use is the adjacency
     * list from graph. DO NOT create new instances of Map for this method
     * (storing the adjacency list in a variable is fine).
     *
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin Prims on.
     * @param graph The graph we are applying Prims to.
     * @return The MST of the graph or null if there is no valid MST.
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        Set<Vertex<T>> visitedSet = new HashSet<Vertex<T>>();
        Set<Edge<T>> mst = new HashSet<Edge<T>>();
        // List<String> result = new ArrayList<>();
        // result.add(start.getData().toString());
        PriorityQueue<Edge<T>> pq = new PriorityQueue<Edge<T>>();
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();

        List<VertexDistance<T>> startNeighbors = adjList.get(start);
        for (VertexDistance<T> vertexDistance : startNeighbors) {
            Vertex<T> vertex = vertexDistance.getVertex();
            int weight = vertexDistance.getDistance();
            pq.add(new Edge<T>(start, vertex, weight));
        }

        visitedSet.add(start);
        while (!pq.isEmpty()) {
            Edge<T> edge = pq.remove();
            if (!visitedSet.contains(edge.getV())) {
                visitedSet.add(edge.getV());

                // result.add(edge.getV().getData().toString());
                mst.add(edge);
                mst.add(new Edge<>(edge.getV(), edge.getU(), edge.getWeight()));

                List<VertexDistance<T>> neighbors = adjList.get(edge.getV());
                for (VertexDistance<T> vertexDistance : neighbors) {
                    Vertex<T> vertex = vertexDistance.getVertex();
                    int weight = vertexDistance.getDistance();
                    if (!visitedSet.contains(vertex)) {
                        pq.add(new Edge<T>(edge.getV(), vertex, weight));
                    }
                }
            }
        }
        if (visitedSet.size() < graph.getVertices().size()) {
            return null;
        }
        return mst;
    }

    // public static void main(String[] args) {
    // var a = new Vertex<String>("A");
    // var b = new Vertex<String>("B");
    // var c = new Vertex<String>("C");
    // var d = new Vertex<String>("D");
    // var e = new Vertex<String>("E");
    // var f = new Vertex<String>("F");
    // var g = new Vertex<String>("G");
    // var h = new Vertex<String>("H");
    // var verticies = new HashSet<Vertex<String>>();
    // verticies.add(a);
    // verticies.add(b);
    // verticies.add(c);
    // verticies.add(d);
    // verticies.add(e);
    // verticies.add(f);
    // verticies.add(g);
    // verticies.add(h);
    // var edges = new LinkedHashSet<Edge<String>>();
    // edges.add(new Edge<>(a, c, 8));
    // edges.add(new Edge<>(a, d, 2));
    // edges.add(new Edge<>(a, e, 8));

    // edges.add(new Edge<>(b, c, 6));
    // edges.add(new Edge<>(b, f, 1));
    // edges.add(new Edge<>(b, g, 5));

    // edges.add(new Edge<>(c, a, 8));
    // edges.add(new Edge<>(c, b, 6));
    // edges.add(new Edge<>(c, g, 6));

    // edges.add(new Edge<>(d, a, 2));
    // edges.add(new Edge<>(d, h, 5));

    // edges.add(new Edge<>(e, a, 8));
    // edges.add(new Edge<>(e, g, 4));

    // edges.add(new Edge<>(f, b, 1));
    // edges.add(new Edge<>(f, h, 8));

    // edges.add(new Edge<>(g, b, 5));
    // edges.add(new Edge<>(g, c, 6));
    // edges.add(new Edge<>(g, e, 4));
    // edges.add(new Edge<>(g, h, 3));

    // edges.add(new Edge<>(h, d, 5));
    // edges.add(new Edge<>(h, f, 8));
    // edges.add(new Edge<>(h, g, 3));

    // var graph = new Graph<>(verticies, edges);
    // var results = prims(d, graph);

    // a = new Vertex<String>("A");
    // b = new Vertex<String>("B");
    // verticies = new HashSet<Vertex<String>>();
    // verticies.add(a);
    // verticies.add(b);
    // edges = new LinkedHashSet<Edge<String>>();
    // graph = new Graph<>(verticies, edges);
    // results = prims(a, graph);
    // }
}