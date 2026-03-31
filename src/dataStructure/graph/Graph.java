package dataStructure.graph;

import java.util.ArrayList;
import java.util.List;

// 邻接矩阵表示的图
class Graph {
    private int V;           // 顶点数
    private int[][] adjMatrix; // 邻接矩阵
    
    // 构造函数
    public Graph(int v) {
        this.V = v;
        adjMatrix = new int[v][v];
        // 初始化矩阵，所有边的值都为0
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                adjMatrix[i][j] = 0;
            }
        }
    }
    
    // 添加边（无向图）
    public void addEdge(int source, int destination) {
        // 确保顶点有效
        if (source >= 0 && source < V && destination >= 0 && destination < V) {
            adjMatrix[source][destination] = 1;
            adjMatrix[destination][source] = 1; // 对于无向图，两个方向都要设置
        }
    }
    
    // 添加带权重的边（有权图）
    public void addEdge(int source, int destination, int weight) {
        if (source >= 0 && source < V && destination >= 0 && destination < V) {
            adjMatrix[source][destination] = weight;
            adjMatrix[destination][source] = weight; // 对于无向图，两个方向都要设置
        }
    }
    
    // 删除边
    public void removeEdge(int source, int destination) {
        if (source >= 0 && source < V && destination >= 0 && destination < V) {
            adjMatrix[source][destination] = 0;
            adjMatrix[destination][source] = 0; // 对于无向图，两个方向都要重置
        }
    }
    
    // 判断两个顶点之间是否有边
    public boolean hasEdge(int source, int destination) {
        if (source >= 0 && source < V && destination >= 0 && destination < V) {
            return adjMatrix[source][destination] != 0;
        }
        return false;
    }
    
    // 获取边的权重
    public int getEdgeWeight(int source, int destination) {
        if (source >= 0 && source < V && destination >= 0 && destination < V) {
            return adjMatrix[source][destination];
        }
        return -1; // 表示没有边或顶点无效
    }
    
    // 打印邻接矩阵
    public void printMatrix() {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    // 获取顶点的所有邻接点
    public List<Integer> getAdjacentVertices(int vertex) {
        List<Integer> adjacentVertices = new ArrayList<>();
        if (vertex >= 0 && vertex < V) {
            for (int j = 0; j < V; j++) {
                if (adjMatrix[vertex][j] != 0) {
                    adjacentVertices.add(j);
                }
            }
        }
        return adjacentVertices;
    }
}
