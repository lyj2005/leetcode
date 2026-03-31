package suanfa_high.graph;

import java.util.*;

public class FordFulkerson {
    // 表示无穷大的流量
    private static final int INF = Integer.MAX_VALUE;
    
    // 使用邻接矩阵表示图
    private int[][] graph; // 原始容量图
    private int[][] residual; // 残余网络
    private int[] parent; // 用于存储增广路径
    private int V; // 顶点数
    
    public FordFulkerson(int vertices) {
        this.V = vertices;
        this.graph = new int[V][V];
        this.residual = new int[V][V];
        this.parent = new int[V];
    }
    
    // 添加从源点u到汇点v的边，容量为capacity
    public void addEdge(int u, int v, int capacity) {
        graph[u][v] = capacity;
    }
    
    // 使用BFS寻找增广路径（即实现Edmonds-Karp算法）
    private boolean bfs(int source, int sink) {
        boolean[] visited = new boolean[V];
        Arrays.fill(visited, false);
        Arrays.fill(parent, -1);
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;
        
        while (!queue.isEmpty()) {
            int u = queue.poll();
            
            for (int v = 0; v < V; v++) {
                if (!visited[v] && residual[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        
        // 如果汇点被访问到，说明找到了一条增广路径
        return visited[sink];
    }
    
    // 使用DFS寻找增广路径
    private boolean dfs(int u, int sink, boolean[] visited) {
        if (u == sink) {
            return true;
        }
        
        visited[u] = true;
        
        for (int v = 0; v < V; v++) {
            if (!visited[v] && residual[u][v] > 0) {
                parent[v] = u;
                
                if (dfs(v, sink, visited)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    // 计算从源点到汇点的最大流
    public int maxFlow(int source, int sink) {
        // 初始化残余网络
        for (int u = 0; u < V; u++) {
            for (int v = 0; v < V; v++) {
                residual[u][v] = graph[u][v];
            }
        }
        
        int maxFlow = 0;
        
        // 当仍然存在增广路径时
        while (bfs(source, sink)) {
            // 找到增广路径上的最小剩余容量
            int pathFlow = INF;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residual[u][v]);
            }
            
            // 更新残余网络的容量
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                residual[u][v] -= pathFlow;
                residual[v][u] += pathFlow; // 增加反向边的容量
            }
            
            maxFlow += pathFlow;
        }
        
        return maxFlow;
    }
    
    public static void main(String[] args) {
        // 创建图并添加边
        FordFulkerson maxFlow = new FordFulkerson(6);
        maxFlow.addEdge(0, 1, 16);
        maxFlow.addEdge(0, 2, 13);
        maxFlow.addEdge(1, 2, 10);
        maxFlow.addEdge(1, 3, 12);
        maxFlow.addEdge(2, 1, 4);
        maxFlow.addEdge(2, 4, 14);
        maxFlow.addEdge(3, 2, 9);
        maxFlow.addEdge(3, 5, 20);
        maxFlow.addEdge(4, 3, 7);
        maxFlow.addEdge(4, 5, 4);
        
        // 计算最大流量
        System.out.println("最大流量: " + maxFlow.maxFlow(0, 5));
    }
}
