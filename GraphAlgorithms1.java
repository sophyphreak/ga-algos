import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Your implementation of various graph traversal algorithms.
 */
public class GraphAlgorithms1 {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * You may import/use java.util.Set, java.util.List, java.util.Queue, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you should use is the adjacency
     * list from graph. DO NOT create new instances of Map for BFS
     * (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin the bfs on.
     * @param graph The graph to search through.
     * @return List of vertices in visited order.
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        var adjList = graph.getAdjList();
        var visited = new HashSet<Vertex<T>>();
        var result = new ArrayList<Vertex<T>>();
        var queue = new ArrayDeque<Vertex<T>>();
        visited.add(start);
        result.add(start);
        queue.add(start);
        while (queue.size() > 0) {
            var current = queue.remove();
            var neighbors = adjList.get(current);
            for (var vertexDistance : neighbors) {
                var vertex = vertexDistance.getVertex();
                if (!visited.contains(vertex)) {
                    visited.add(vertex);
                    result.add(vertex);
                    queue.add(vertex);
                }
            }
        }
        return result;
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * NOTE: This method should be implemented recursively. You may need to
     * create a helper method.
     *
     * You may import/use java.util.Set, java.util.List, and any classes that
     * implement the aforementioned interfaces, as long as they are efficient.
     *
     * The only instance of java.util.Map that you may use is the adjacency list
     * from graph. DO NOT create new instances of Map for DFS
     * (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin the dfs on.
     * @param graph The graph to search through.
     * @return List of vertices in visited order.
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        var adjList = graph.getAdjList();
        var visited = new HashSet<Vertex<T>>();
        var result = new ArrayList<Vertex<T>>();
        return dfsHelper(start, visited, adjList, result);
    }

    private static <T> List<Vertex<T>> dfsHelper(Vertex<T> current, HashSet<Vertex<T>> visited,
            Map<Vertex<T>, List<VertexDistance<T>>> adjList, List<Vertex<T>> result) {
        visited.add(current);
        result.add(current);
        var neighbors = adjList.get(current);
        for (var vertexDistance : neighbors) {
            var vertex = vertexDistance.getVertex();
            if (!visited.contains(vertex)) {
                dfsHelper(vertex, visited, adjList, result);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        var a = new Vertex<String>("A");
        var b = new Vertex<String>("B");
        var c = new Vertex<String>("C");
        var d = new Vertex<String>("D");
        var e = new Vertex<String>("E");
        var f = new Vertex<String>("F");
        var g = new Vertex<String>("G");
        var h = new Vertex<String>("H");
        var verticies = new HashSet<Vertex<String>>();
        verticies.add(a);
        verticies.add(b);
        verticies.add(c);
        verticies.add(d);
        verticies.add(e);
        verticies.add(f);
        verticies.add(g);
        verticies.add(h);
        var edges = new LinkedHashSet<Edge<String>>();
        edges.add(new Edge<>(a, b, 1));
        edges.add(new Edge<>(a, c, 1));
        edges.add(new Edge<>(a, d, 1));
        edges.add(new Edge<>(a, e, 1));

        edges.add(new Edge<>(b, a, 1));
        edges.add(new Edge<>(b, g, 1));

        edges.add(new Edge<>(c, a, 1));

        edges.add(new Edge<>(d, a, 1));
        edges.add(new Edge<>(d, f, 1));

        edges.add(new Edge<>(e, a, 1));
        edges.add(new Edge<>(e, g, 1));

        edges.add(new Edge<>(f, d, 1));
        edges.add(new Edge<>(f, g, 1));

        edges.add(new Edge<>(g, b, 1));
        edges.add(new Edge<>(g, e, 1));
        edges.add(new Edge<>(g, f, 1));
        edges.add(new Edge<>(g, h, 1));

        edges.add(new Edge<>(h, g, 1));

        var graph = new Graph<>(verticies, edges);

        var result = dfs(h, graph);
        assert result.get(0).getData() == "H";
        assert result.get(1).getData() == "G";
        assert result.get(2).getData() == "B";
        assert result.get(3).getData() == "A";
        assert result.get(4).getData() == "C";
        assert result.get(5).getData() == "D";
        assert result.get(6).getData() == "F";
        assert result.get(7).getData() == "E";

        result = dfs(a, graph);
        assert result.get(0).getData() == "A";
        assert result.get(1).getData() == "B";
        assert result.get(2).getData() == "G";
        assert result.get(3).getData() == "E";
        assert result.get(4).getData() == "F";
        assert result.get(5).getData() == "D";
        assert result.get(6).getData() == "H";
        assert result.get(7).getData() == "C";

        result = bfs(h, graph);
        assert result.get(0).getData() == "H";
        assert result.get(1).getData() == "G";
        assert result.get(2).getData() == "B";
        assert result.get(3).getData() == "E";
        assert result.get(4).getData() == "F";
        assert result.get(5).getData() == "A";
        assert result.get(6).getData() == "D";
        assert result.get(7).getData() == "C";

        result = bfs(a, graph);
        assert result.get(0).getData() == "A";
        assert result.get(1).getData() == "B";
        assert result.get(2).getData() == "C";
        assert result.get(3).getData() == "D";
        assert result.get(4).getData() == "E";
        assert result.get(5).getData() == "G";
        assert result.get(6).getData() == "F";
        assert result.get(7).getData() == "H";
    }
}