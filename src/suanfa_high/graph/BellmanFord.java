package suanfa_high.graph;

import java.util.*;

public class BellmanFord {
    // 边类
    static class Edge {
        int src, dest, weight;
        
        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }
    
    // 图类
    static class Graph {
        int V, E; // 顶点数和边数
        List<Edge> edges; // 边列表
        
        public Graph(int V, int E) {
            this.V = V;
            this.E = E;
            edges = new ArrayList<>();
        }
        
        // 添加边
        public void addEdge(int src, int dest, int weight) {
            edges.add(new Edge(src, dest, weight));
        }
        
        // Bellman-Ford算法实现
        public void bellmanFord(int src) {
            // 初始化距离数组和前驱数组
            int[] dist = new int[V];
            int[] prev = new int[V];
            
            // 初始化
            for (int i = 0; i < V; i++) {
                dist[i] = Integer.MAX_VALUE;
                prev[i] = -1;
            }
            dist[src] = 0;
            
            // 松弛操作，V-1次
            for (int i = 1; i < V; i++) {
                for (Edge edge : edges) {
                    int u = edge.src;
                    int v = edge.dest;
                    int weight = edge.weight;
                    
                    // 如果u可达且可以通过u到v的路径更新v的距离
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                        prev[v] = u;
                    }
                }
            }
            
            // 检测负权环
            boolean hasNegativeCycle = false;
            for (Edge edge : edges) {
                int u = edge.src;
                int v = edge.dest;
                int weight = edge.weight;
                
                // 如果仍然可以更新，说明有负权环
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    hasNegativeCycle = true;
                    System.out.println("图中存在负权环，不存在最短路径。");
                    break;
                }
            }
            
            // 如果没有负权环，打印结果
            if (!hasNegativeCycle) {
                printSolution(dist, prev, src);
            }
        }
        
        // 打印解决方案
        private void printSolution(int[] dist, int[] prev, int src) {
            System.out.println("从顶点 " + src + " 到其他顶点的最短距离：");
            for (int i = 0; i < V; i++) {
                if (dist[i] == Integer.MAX_VALUE) {
                    System.out.println(i + " : 不可达");
                } else {
                    System.out.println(i + " : " + dist[i]);
                    
                    // 打印路径
                    System.out.print("  路径: ");
                    printPath(prev, i);
                    System.out.println();
                }
            }
        }
        
        // 递归打印路径
        private void printPath(int[] prev, int vertex) {
            if (vertex == -1) {
                return;
            }
            
            printPath(prev, prev[vertex]);
            System.out.print(vertex + " ");
        }
    }
    
    public static void main(String[] args) {
        // 创建图实例
        int V = 5; // 顶点数
        int E = 8; // 边数
        Graph graph = new Graph(V, E);
        
        // 添加边 (从0开始编号，对应A-E)
        graph.addEdge(0, 1, 6);  // A-B
        graph.addEdge(0, 2, 4);  // A-C
        graph.addEdge(0, 3, 5);  // A-D
        graph.addEdge(1, 4, -1); // B-E
        graph.addEdge(2, 1, -2); // C-B
        graph.addEdge(2, 4, 3);  // C-E
        graph.addEdge(3, 2, -2); // D-C
        graph.addEdge(4, 3, -2); // E-D
        
        // 执行Bellman-Ford算法，从顶点0(A)开始
        graph.bellmanFord(0);
    }
}
