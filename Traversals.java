import java.io.*;
import java.util.*;

public class Traversals {

    static class Edge {
        int src, dest, wt;

        public Edge(int s, int d, int w) {
            src = s;
            dest = d;
            wt = w;
        }
    }

    public static void createGraph(ArrayList<Edge>[] graph, int[][] adjacencyMatrix) {
        int n = graph.length;
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                    graph[i].add(new Edge(i, j, adjacencyMatrix[i][j]));
                }
            }
        }
    }

    public static void BFS(ArrayList<Edge>[] graph) {
        boolean[] visited = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) 
            	bfsUtil(graph, visited, i);
        }
    }

    private static void bfsUtil(ArrayList<Edge>[] graph, boolean[] visited, int vertex) {
    
        Queue<Integer> q = new LinkedList<>();
        System.out.print("BFS : ");
        
        q.add(vertex);
        visited[vertex] = true;
        
        while (!q.isEmpty()) {
        
            int current = q.remove();
            System.out.print(current + " ");
            
            for (Edge edge : graph[current]) {
                if (!visited[edge.dest]) {
                
                    q.add(edge.dest);
                    visited[edge.dest] = true;
                }
            }
        }
        
        System.out.println();
    }

    public static void DFS(ArrayList<Edge>[] graph) {
        boolean[] visited = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) dfsUtil(graph, visited, i);
        }
    }

    private static void dfsUtil(ArrayList<Edge>[] graph, boolean[] visited, int vertex) {
    
        Stack<Integer> s = new Stack<>();
        s.push(vertex);
        
        System.out.print("DFS : ");
        visited[vertex] = true;
        
        while (!s.isEmpty()) {
        
            int curr = s.pop();
            System.out.print(curr + " ");
            
            for (Edge edge : graph[curr]) {
            
                if (!visited[edge.dest]) {
                
                    s.push(edge.dest);
                    visited[edge.dest] = true;
                }
            }
        }
        
        System.out.println();
    }

    public static void main(String[] args) {
    
        try {
            Scanner scanner = new Scanner(new File("graph.txt"));
            
            int V = Integer.parseInt(scanner.nextLine().trim());
            
            @SuppressWarnings("unchecked")
            ArrayList<Edge>[] graph = new ArrayList[V];
            
            int[][] adjacencyMatrix = new int[V][V];
            
            for (int i = 0; i < V; i++) {
            
                String[] data = scanner.nextLine().split(" ");
                
                for (int j = 0; j < V; j++) {
                    adjacencyMatrix[i][j] = Integer.parseInt(data[j]);
                }
            }

            createGraph(graph, adjacencyMatrix);
            
            BFS(graph);
            DFS(graph);

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
