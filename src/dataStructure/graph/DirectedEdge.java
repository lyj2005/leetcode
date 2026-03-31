package dataStructure.graph;

import java.util.*;

// 有向图的边表示
class DirectedEdge {
    int source;      // 边的起点
    int destination; // 边的终点
    
    public DirectedEdge(int source, int destination) {
        this.source = source;
        this.destination = destination;
    }
    
    @Override
    public String toString() {
        return source + " -> " + destination;
    }
}

// 带权重的有向边
class WeightedDirectedEdge {
    int source;      // 边的起点
    int destination; // 边的终点
    int weight;      // 边的权重
    
    public WeightedDirectedEdge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    
    @Override
    public String toString() {
        return source + " -> " + destination + " (" + weight + ")";
    }
}

// 使用边列表实现的有向图
class DirectedGraph {
    private int V;                      // 顶点数量
    private List<DirectedEdge> edges;   // 边列表
    private Map<Integer, List<Integer>> outEdges;  // 每个顶点的出边列表
    private Map<Integer, List<Integer>> inEdges;   // 每个顶点的入边列表
    
    public DirectedGraph(int V) {
        this.V = V;
        this.edges = new ArrayList<>();
        this.outEdges = new HashMap<>();
        this.inEdges = new HashMap<>();
        
        // 初始化顶点
        for (int i = 0; i < V; i++) {
            outEdges.put(i, new ArrayList<>());
            inEdges.put(i, new ArrayList<>());
        }
    }
    
    // 添加有向边
    public void addEdge(int source, int destination) {
        // 确保顶点有效
        if (source < 0 || source >= V || destination < 0 || destination >= V) {
            throw new IllegalArgumentException("顶点索引无效");
        }
        
        // 添加到边列表
        edges.add(new DirectedEdge(source, destination));
        
        // 更新出边和入边列表
        outEdges.get(source).add(destination);
        inEdges.get(destination).add(source);
    }
    
    // 获取图中的顶点数
    public int getVertexCount() {
        return V;
    }
    
    // 获取图中的边数
    public int getEdgeCount() {
        return edges.size();
    }
    
    // 获取顶点的出度
    public int getOutDegree(int vertex) {
        validateVertex(vertex);
        return outEdges.get(vertex).size();
    }
    
    // 获取顶点的入度
    public int getInDegree(int vertex) {
        validateVertex(vertex);
        return inEdges.get(vertex).size();
    }
    
    // 获取从指定顶点出发的所有边
    public List<Integer> getOutEdges(int vertex) {
        validateVertex(vertex);
        return new ArrayList<>(outEdges.get(vertex));
    }
    
    // 获取指向指定顶点的所有边
    public List<Integer> getInEdges(int vertex) {
        validateVertex(vertex);
        return new ArrayList<>(inEdges.get(vertex));
    }
    
    // 判断从顶点u到顶点v是否存在有向边
    public boolean hasEdge(int u, int v) {
        validateVertex(u);
        validateVertex(v);
        return outEdges.get(u).contains(v);
    }
    
    // 检查顶点是否有效
    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("顶点 " + v + " 不在有效范围内");
        }
    }
    
    // 打印图的所有边
    public void printEdges() {
        System.out.println("图中的所有有向边：");
        for (DirectedEdge edge : edges) {
            System.out.println(edge);
        }
    }
    
    // 使用DFS判断从顶点source到顶点target是否可达
    public boolean isReachable(int source, int target) {
        validateVertex(source);
        validateVertex(target);
        
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        
        stack.push(source);
        visited[source] = true;
        
        while (!stack.isEmpty()) {
            int current = stack.pop();
            
            if (current == target) {
                return true;
            }
            
            for (int neighbor : outEdges.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    stack.push(neighbor);
                }
            }
        }
        
        return false;
    }
}
