package dataStructure.graph;

import java.util.*;

// 无向图的边表示
class UndirectedEdge {
    int vertex1;  // 边的一个顶点
    int vertex2;  // 边的另一个顶点
    
    public UndirectedEdge(int vertex1, int vertex2) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }
    
    @Override
    public String toString() {
        return vertex1 + " -- " + vertex2;
    }
}

// 带权重的无向边
class WeightedUndirectedEdge {
    int vertex1;  // 边的一个顶点
    int vertex2;  // 边的另一个顶点
    int weight;   // 边的权重
    
    public WeightedUndirectedEdge(int vertex1, int vertex2, int weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }
    
    @Override
    public String toString() {
        return vertex1 + " -- " + vertex2 + " (" + weight + ")";
    }
}

// 使用边列表实现的无向图
class UndirectedGraph {
    private int V;                      // 顶点数量
    private List<UndirectedEdge> edges; // 边列表
    private Map<Integer, List<Integer>> adjacencyList; // 邻接表
    
    public UndirectedGraph(int V) {
        this.V = V;
        this.edges = new ArrayList<>();
        this.adjacencyList = new HashMap<>();
        
        // 初始化顶点
        for (int i = 0; i < V; i++) {
            adjacencyList.put(i, new ArrayList<>());
        }
    }
    
    // 添加无向边
    public void addEdge(int vertex1, int vertex2) {
        // 确保顶点有效
        if (vertex1 < 0 || vertex1 >= V || vertex2 < 0 || vertex2 >= V) {
            throw new IllegalArgumentException("顶点索引无效");
        }
        
        // 添加到边列表
        edges.add(new UndirectedEdge(vertex1, vertex2));
        
        // 更新邻接表（无向图需要双向添加）
        adjacencyList.get(vertex1).add(vertex2);
        adjacencyList.get(vertex2).add(vertex1);
    }
    
    // 获取图中的顶点数
    public int getVertexCount() {
        return V;
    }
    
    // 获取图中的边数
    public int getEdgeCount() {
        return edges.size();
    }
    
    // 获取顶点的度
    public int getDegree(int vertex) {
        validateVertex(vertex);
        return adjacencyList.get(vertex).size();
    }
    
    // 获取与指定顶点相邻的所有顶点
    public List<Integer> getNeighbors(int vertex) {
        validateVertex(vertex);
        return new ArrayList<>(adjacencyList.get(vertex));
    }
    
    // 判断两个顶点之间是否存在边
    public boolean hasEdge(int v1, int v2) {
        validateVertex(v1);
        validateVertex(v2);
        return adjacencyList.get(v1).contains(v2);
    }
    
    // 检查顶点是否有效
    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("顶点 " + v + " 不在有效范围内");
        }
    }
    
    // 打印图的所有边
    public void printEdges() {
        System.out.println("图中的所有无向边：");
        for (UndirectedEdge edge : edges) {
            System.out.println(edge);
        }
    }
    
    // 使用BFS判断两个顶点之间是否存在路径（检查连通性）
    public boolean isConnected(int start, int end) {
        validateVertex(start);
        validateVertex(end);
        
        if (start == end) return true;
        
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        
        visited[start] = true;
        queue.offer(start);
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            for (int neighbor : adjacencyList.get(current)) {
                if (neighbor == end) {
                    return true;
                }
                
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
        
        return false;
    }
    
    // 使用DFS遍历图（从指定顶点开始）
    public void dfs(int start) {
        validateVertex(start);
        
        boolean[] visited = new boolean[V];
        System.out.print("DFS遍历（从顶点" + start + "开始）：");
        
        dfsUtil(start, visited);
        System.out.println();
    }
    
    private void dfsUtil(int vertex, boolean[] visited) {
        // 标记当前顶点为已访问并打印
        visited[vertex] = true;
        System.out.print(vertex + " ");
        
        // 递归处理所有未访问的邻居
        for (int neighbor : adjacencyList.get(vertex)) {
            if (!visited[neighbor]) {
                dfsUtil(neighbor, visited);
            }
        }
    }
    
    // 使用BFS遍历图（从指定顶点开始）
    public void bfs(int start) {
        validateVertex(start);
        
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        
        visited[start] = true;
        queue.offer(start);
        
        System.out.print("BFS遍历（从顶点" + start + "开始）：");
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");
            
            for (int neighbor : adjacencyList.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
        
        System.out.println();
    }
    
    // 计算图中的连通分量数量
    public int countConnectedComponents() {
        boolean[] visited = new boolean[V];
        int count = 0;
        
        for (int v = 0; v < V; v++) {
            if (!visited[v]) {
                // 对未访问的顶点执行DFS
                dfsForComponents(v, visited);
                count++;
            }
        }
        
        return count;
    }
    
    private void dfsForComponents(int vertex, boolean[] visited) {
        visited[vertex] = true;
        
        for (int neighbor : adjacencyList.get(vertex)) {
            if (!visited[neighbor]) {
                dfsForComponents(neighbor, visited);
            }
        }
    }
}
