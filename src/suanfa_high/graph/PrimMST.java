package suanfa_high.graph;

import java.util.*;

public class PrimMST {
    // 图的边类
    static class Edge {
        int src, dest, weight;
        
        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }
    
    // 顶点类，用于优先队列
    static class Vertex implements Comparable<Vertex> {
        int id;
        int key; // 最小权重
        
        public Vertex(int id, int key) {
            this.id = id;
            this.key = key;
        }
        
        @Override
        public int compareTo(Vertex other) {
            return Integer.compare(this.key, other.key);
        }
    }
    
    // 图类
    static class Graph {
        int V; // 顶点数
        List<List<Edge>> adj; // 邻接表
        
        public Graph(int V) {
            this.V = V;
            adj = new ArrayList<>(V);
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
        }
        
        // 添加边（无向图）
        public void addEdge(int src, int dest, int weight) {
            Edge edge1 = new Edge(src, dest, weight);
            Edge edge2 = new Edge(dest, src, weight);
            adj.get(src).add(edge1);
            adj.get(dest).add(edge2);
        }
        
        // Prim算法实现
        public void primMST() {
            // 记录每个顶点的父节点（用于构建MST）
            int[] parent = new int[V];
            // 记录每个顶点的最小权重边
            int[] key = new int[V];
            // 记录顶点是否已加入MST
            boolean[] inMST = new boolean[V];
            
            // 初始化
            for (int i = 0; i < V; i++) {
                key[i] = Integer.MAX_VALUE;
                inMST[i] = false;
            }
            
            // 选择顶点0作为起始点
            key[0] = 0;
            parent[0] = -1; // 0是根节点
            
            // 创建优先队列
            PriorityQueue<Vertex> pq = new PriorityQueue<>();
            pq.add(new Vertex(0, 0));
            
            // 主循环，构建MST
            while (!pq.isEmpty()) {
                // 从优先队列中取出权重最小的顶点
                int u = pq.poll().id;
                
                // 如果已经处理过，则跳过
                if (inMST[u])
                    continue;
                
                // 将顶点加入MST
                inMST[u] = true;
                
                // 更新相邻顶点的最小权重
                for (Edge e : adj.get(u)) {
                    int v = e.dest;
                    int weight = e.weight;
                    
                    // 如果v未加入MST，且权重更小
                    if (!inMST[v] && weight < key[v]) {
                        // 更新v的最小权重和父节点
                        key[v] = weight;
                        parent[v] = u;
                        pq.add(new Vertex(v, key[v]));
                    }
                }
            }
            
            // 打印最小生成树
            System.out.println("最小生成树的边：");
            int totalWeight = 0;
            for (int i = 1; i < V; i++) {
                System.out.println(parent[i] + " - " + i + "	权重: " + key[i]);
                totalWeight += key[i];
            }
            System.out.println("最小生成树总权重: " + totalWeight);
        }
    }
    
    public static void main(String[] args) {
        // 创建图实例
        Graph graph = new Graph(5);
        
        // 添加边 (从0开始编号，对应A-E)
        graph.addEdge(0, 1, 2);  // A-B
        graph.addEdge(0, 2, 3);  // A-C
        graph.addEdge(1, 2, 1);  // B-C
        graph.addEdge(1, 3, 4);  // B-D
        graph.addEdge(2, 3, 5);  // C-D
        graph.addEdge(2, 4, 6);  // C-E
        graph.addEdge(3, 4, 7);  // D-E
        
        // 执行Prim算法
        graph.primMST();
    }
}
