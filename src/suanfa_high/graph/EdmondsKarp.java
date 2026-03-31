package suanfa_high.graph;

import java.util.*;

public class EdmondsKarp {
    // 边的定义
    static class Edge {
        int to;       // 边的终点
        int capacity; // 边的容量
        int flow;     // 当前流量
        int rev;      // 反向边在邻接表中的索引

        public Edge(int to, int capacity, int rev) {
            this.to = to;
            this.capacity = capacity;
            this.flow = 0;
            this.rev = rev;
        }
    }

    private int V; // 顶点数量
    private List<List<Edge>> adj;

    public EdmondsKarp(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // 添加边（包含正向和反向边）
    public void addEdge(int from, int to, int capacity) {
        Edge forward = new Edge(to, capacity, adj.get(to).size());
        Edge backward = new Edge(from, 0, adj.get(from).size());
        adj.get(from).add(forward);
        adj.get(to).add(backward);
    }

    // BFS用于寻找增广路径，记录每个点的前驱节点和通过哪条边到达
    private boolean bfs(int source, int sink, Edge[] parentEdge, int[] parentNode) {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        visited[source] = true;

        Arrays.fill(parentEdge, null);
        Arrays.fill(parentNode, -1);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (Edge edge : adj.get(u)) {
                int v = edge.to;
                if (!visited[v] && edge.capacity > edge.flow) {
                    visited[v] = true;
                    parentNode[v] = u;
                    parentEdge[v] = edge;
                    queue.offer(v);
                    if (v == sink) return true; // 提前结束
                }
            }
        }
        return false;
    }

    public int maxFlow(int source, int sink) {
        int flow = 0;
        Edge[] parentEdge = new Edge[V]; // 记录增广路径中每个点通过的边
        int[] parentNode = new int[V];   // 记录每个点的前驱节点

        while (bfs(source, sink, parentEdge, parentNode)) {
            // 找到增广路径上的最小残余容量
            int pathFlow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parentNode[v]) {
                Edge edge = parentEdge[v];
                pathFlow = Math.min(pathFlow, edge.capacity - edge.flow);
            }

            // 更新路径上的边和反向边的流量
            for (int v = sink; v != source; v = parentNode[v]) {
                Edge edge = parentEdge[v];
                edge.flow += pathFlow;
                adj.get(edge.to).get(edge.rev).flow -= pathFlow;
            }

            flow += pathFlow;
        }

        return flow;
    }

    // 打印每条边的流量分配
    public void printFlowDistribution() {
        System.out.println("流量分配：");
        for (int u = 0; u < V; u++) {
            for (Edge edge : adj.get(u)) {
                // 打印正向边（即初始容量 > 0 的边）
                if (edge.capacity > 0) {
                    System.out.println(u + " -> " + edge.to + ": " + edge.flow + "/" + edge.capacity);
                }
            }
        }
    }

    public static void main(String[] args) {
        // 节点编号：S=0, A=1, B=2, C=3, D=4, T=5
        EdmondsKarp graph = new EdmondsKarp(6);

        // 添加边
        graph.addEdge(0, 1, 10); // S -> A
        graph.addEdge(0, 3, 10); // S -> C
        graph.addEdge(1, 2, 4);  // A -> B
        graph.addEdge(1, 3, 2);  // A -> C
        graph.addEdge(1, 4, 8);  // A -> D
        graph.addEdge(2, 5, 10); // B -> T
        graph.addEdge(3, 4, 9);  // C -> D
        graph.addEdge(4, 2, 6);  // D -> B
        graph.addEdge(4, 5, 10); // D -> T

        // 计算最大流
        int maxFlow = graph.maxFlow(0, 5);
        System.out.println("最大流量: " + maxFlow);

        // 打印流量分配
        graph.printFlowDistribution();
    }
}

