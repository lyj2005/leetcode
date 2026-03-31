package dataStructure.graph;

import java.util.*;

// 带权边表示
class WeightedEdge {
    int source;      // 边的起点
    int destination; // 边的终点
    double weight;   // 边的权重
    
    public WeightedEdge(int source, int destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    
    @Override
    public String toString() {
        return source + " -> " + destination + " (" + weight + ")";
    }
}

// 使用邻接表实现的加权图
class WeightedGraph {
    private int V;                                  // 顶点数量
    private List<WeightedEdge> edges;               // 边列表
    private Map<Integer, List<WeightedEdge>> adj;   // 邻接表
    private boolean directed;                       // 是否为有向图
    
    public WeightedGraph(int V, boolean directed) {
        this.V = V;
        this.directed = directed;
        this.edges = new ArrayList<>();
        this.adj = new HashMap<>();
        
        // 初始化顶点
        for (int i = 0; i < V; i++) {
            adj.put(i, new ArrayList<>());
        }
    }
    
    // 添加带权边
    public void addEdge(int source, int destination, double weight) {
        // 确保顶点有效
        if (source < 0 || source >= V || destination < 0 || destination >= V) {
            throw new IllegalArgumentException("顶点索引无效");
        }
        
        WeightedEdge edge = new WeightedEdge(source, destination, weight);
        edges.add(edge);
        adj.get(source).add(edge);
        
        // 如果是无向图，还需要添加反方向的边
        if (!directed) {
            WeightedEdge reverseEdge = new WeightedEdge(destination, source, weight);
            adj.get(destination).add(reverseEdge);
            // 注意：不需要将反向边添加到边列表中，否则会重复计数
        }
    }
    
    // 获取图中的顶点数
    public int getVertexCount() {
        return V;
    }
    
    // 获取图中的边数
    public int getEdgeCount() {
        return edges.size();
    }
    
    // 获取从指定顶点出发的所有边
    public List<WeightedEdge> getEdges(int vertex) {
        validateVertex(vertex);
        return new ArrayList<>(adj.get(vertex));
    }
    
    // 获取图中的所有边
    public List<WeightedEdge> getAllEdges() {
        return new ArrayList<>(edges);
    }
    
    // 获取两个顶点之间边的权重，如果不存在则返回Double.POSITIVE_INFINITY
    public double getWeight(int source, int destination) {
        validateVertex(source);
        validateVertex(destination);
        
        for (WeightedEdge edge : adj.get(source)) {
            if (edge.destination == destination) {
                return edge.weight;
            }
        }
        
        return Double.POSITIVE_INFINITY;  // 表示不存在这条边
    }
    
    // 判断从顶点source到顶点destination是否存在边
    public boolean hasEdge(int source, int destination) {
        validateVertex(source);
        validateVertex(destination);
        
        for (WeightedEdge edge : adj.get(source)) {
            if (edge.destination == destination) {
                return true;
            }
        }
        
        return false;
    }
    
    // 更新边的权重，如果边不存在则返回false
    public boolean updateEdgeWeight(int source, int destination, double newWeight) {
        validateVertex(source);
        validateVertex(destination);
        
        // 更新邻接表中的权重
        boolean found = false;
        for (WeightedEdge edge : adj.get(source)) {
            if (edge.destination == destination) {
                edge.weight = newWeight;
                found = true;
                break;
            }
        }
        
        if (!found) {
            return false;
        }
        
        // 如果是无向图，还需要更新反方向的边
        if (!directed) {
            for (WeightedEdge edge : adj.get(destination)) {
                if (edge.destination == source) {
                    edge.weight = newWeight;
                    break;
                }
            }
        }
        
        // 更新边列表中的权重
        for (WeightedEdge edge : edges) {
            if (edge.source == source && edge.destination == destination) {
                edge.weight = newWeight;
                return true;
            }
        }
        
        return false;
    }
    
    // 检查顶点是否有效
    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("顶点 " + v + " 不在有效范围内");
        }
    }
    
    // 打印图的所有边
    public void printEdges() {
        System.out.println("图中的所有" + (directed ? "有向" : "无向") + "边：");
        for (WeightedEdge edge : edges) {
            System.out.println(edge);
        }
    }
}
