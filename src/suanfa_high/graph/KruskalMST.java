package suanfa_high.graph;

import java.util.*;

public class KruskalMST {
    // 表示图中的一条边
    static class Edge implements Comparable<Edge> {
        int source, destination, weight;
        
        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
        
        // 按照权重比较边
        @Override
        public int compareTo(Edge otherEdge) {
            return this.weight - otherEdge.weight;
        }
    }
    
    // 并查集实现
    static class DisjointSet {
        int[] parent, rank;
        
        // 初始化并查集，每个顶点各为一个集合
        public DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            
            for (int i = 0; i < n; i++) {
                parent[i] = i;  // 初始时每个元素的父节点是自身
                rank[i] = 0;    // 初始时每个集合的秩为0
            }
        }
        
        // 查找元素x所属的集合（带路径压缩）
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);  // 路径压缩
            }
            return parent[x];
        }
        
        // 合并包含元素x和y的两个集合（按秩合并）
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            if (rootX == rootY) {
                return;  // x和y已经在同一个集合中
            }
            
            // 按秩合并
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }
    
    // 存储图的边集
    private List<Edge> edges;
    // 顶点数量
    private int vertexCount;
    
    public KruskalMST(int vertexCount) {
        this.vertexCount = vertexCount;
        edges = new ArrayList<>();
    }
    
    // 添加边
    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        edges.add(edge);
    }
    
    // Kruskal算法实现
    public List<Edge> kruskalMST() {
        List<Edge> mst = new ArrayList<>();
        
        // 按权重升序排序所有边
        Collections.sort(edges);
        
        // 创建并查集
        DisjointSet disjointSet = new DisjointSet(vertexCount);
        
        // 按权重从小到大遍历所有边
        for (Edge edge : edges) {
            int sourceRoot = disjointSet.find(edge.source);
            int destinationRoot = disjointSet.find(edge.destination);
            
            // 如果当前边的两个顶点不在同一个集合中（即不会形成环）
            if (sourceRoot != destinationRoot) {
                // 将这条边加入MST
                mst.add(edge);
                
                // 合并包含这两个顶点的集合
                disjointSet.union(sourceRoot, destinationRoot);
                
                // 如果MST已经有V-1条边，则结束
                if (mst.size() == vertexCount - 1) {
                    break;
                }
            }
        }
        
        return mst;
    }
    
    // 计算MST的总权重
    public int getTotalWeight(List<Edge> mst) {
        int totalWeight = 0;
        for (Edge edge : mst) {
            totalWeight += edge.weight;
        }
        return totalWeight;
    }
}
