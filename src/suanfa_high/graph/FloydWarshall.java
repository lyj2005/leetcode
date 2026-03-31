package suanfa_high.graph;

import java.util.Arrays;

public class FloydWarshall {
    final static int INF = Integer.MAX_VALUE / 2; // 防止溢出
    
    public static void floydWarshall(int[][] graph, int V) {
        // 初始化距离矩阵
        int[][] dist = new int[V][V];
        // 初始化路径矩阵
        int[][] next = new int[V][V];
        
        // 初始化
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
                if (graph[i][j] != INF && i != j) {
                    next[i][j] = j;
                } else {
                    next[i][j] = -1;
                }
            }
        }
        
        // Floyd-Warshall算法核心
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k]; // 更新路径
                    }
                }
            }
        }
        
        // 检测负权环
        boolean hasNegativeCycle = false;
        for (int i = 0; i < V; i++) {
            if (dist[i][i] < 0) {
                hasNegativeCycle = true;
                System.out.println("图中存在负权环");
                break;
            }
        }
        
        if (!hasNegativeCycle) {
            // 打印距离矩阵
            printDistanceMatrix(dist, V);
            
            // 打印所有顶点对的最短路径
            printAllPaths(dist, next, V);
        }
    }
    
    // 打印距离矩阵
    private static void printDistanceMatrix(int[][] dist, int V) {
        System.out.println("最短距离矩阵：");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    
    // 构建并打印最短路径
    private static void printPath(int[][] next, int u, int v) {
        if (next[u][v] == -1) {
            System.out.print("没有路径");
            return;
        }
        
        System.out.print(u + " ");
        while (u != v) {
            u = next[u][v];
            System.out.print(u + " ");
        }
    }
    
    // 打印所有顶点对的最短路径
    private static void printAllPaths(int[][] dist, int[][] next, int V) {
        System.out.println(" 所有顶点对的最短路径：");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i != j) {
                    System.out.print("从 " + i + " 到 " + j + " 的最短路径 (距离: " + dist[i][j] + "): ");
                    printPath(next, i, j);
                    System.out.println();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        int V = 4; // 顶点数
        
        // 创建邻接矩阵表示的图 (INF表示不直接相连)
        int[][] graph = {
            {0, 3, 6, INF},
            {INF, 0, 2, 4},
            {INF, INF, 0, 1},
            {5, INF, INF, 0}
        };
        
        // 执行Floyd-Warshall算法
        floydWarshall(graph, V);
    }
}
