package dataStructure.graph;

import java.util.*;

/**
 * 有向无环图(DAG)的实现
 */
public class DirectedAcyclicGraph {
    private int V;                          // 顶点数量
    private Map<Integer, List<Integer>> adj;  // 邻接表
    private Map<Integer, List<Integer>> incoming;  // 反向邻接表（用于快速查找入边）
    
    /**
     * 创建一个具有指定顶点数的有向无环图
     * @param V 顶点数
     */
    public DirectedAcyclicGraph(int V) {
        this.V = V;
        adj = new HashMap<>();
        incoming = new HashMap<>();
        
        // 初始化顶点
        for (int i = 0; i < V; i++) {
            adj.put(i, new ArrayList<>());
            incoming.put(i, new ArrayList<>());
        }
    }
    
    /**
     * 添加有向边，在添加前会检查是否会形成环
     * @param source 边的起点
     * @param destination 边的终点
     * @return 是否成功添加边
     * @throws IllegalArgumentException 如果顶点索引无效或添加边会形成环
     */
    public boolean addEdge(int source, int destination) {
        // 确保顶点有效
        validateVertex(source);
        validateVertex(destination);
        
        // 检查是否会形成环
        if (hasPath(destination, source)) {
            throw new IllegalArgumentException("添加此边将形成环，DAG不允许有环");
        }
        
        // 添加边
        adj.get(source).add(destination);
        incoming.get(destination).add(source);
        
        return true;
    }
    
    /**
     * 判断从source到destination是否存在路径
     * @param source 起点
     * @param destination 终点
     * @return 是否存在路径
     */
    public boolean hasPath(int source, int destination) {
        validateVertex(source);
        validateVertex(destination);
        
        // 特殊情况：起点等于终点
        if (source == destination) {
            return true;
        }
        
        // 使用BFS检查是否存在路径
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        
        visited[source] = true;
        queue.offer(source);
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            for (int neighbor : adj.get(current)) {
                if (neighbor == destination) {
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
    
    /**
     * 使用Kahn算法进行拓扑排序
     * @return 拓扑排序结果（如果存在环，则返回null）
     */
    public List<Integer> topologicalSort() {
        // 计算每个顶点的入度
        int[] inDegree = new int[V];
        for (int v = 0; v < V; v++) {
            for (int neighbor : adj.get(v)) {
                inDegree[neighbor]++;
            }
        }
        
        // 将所有入度为0的顶点加入队列
        Queue<Integer> queue = new LinkedList<>();
        for (int v = 0; v < V; v++) {
            if (inDegree[v] == 0) {
                queue.offer(v);
            }
        }
        
        // 拓扑排序结果
        List<Integer> topologicalOrder = new ArrayList<>();
        
        // 处理队列
        while (!queue.isEmpty()) {
            int current = queue.poll();
            topologicalOrder.add(current);
            
            // 删除所有从current出发的边
            for (int neighbor : adj.get(current)) {
                // 减少neighbor的入度
                inDegree[neighbor]--;
                
                // 如果入度变为0，则加入队列
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        // 如果不是所有顶点都加入了拓扑排序，则存在环
        if (topologicalOrder.size() != V) {
            return null;  // 图中存在环
        }
        
        return topologicalOrder;
    }
    
    /**
     * DFS方法的拓扑排序
     * @return 拓扑排序结果
     */
    public List<Integer> topologicalSortDFS() {
        boolean[] visited = new boolean[V];
        LinkedList<Integer> result = new LinkedList<>();
        Set<Integer> recursionStack = new HashSet<>();
        
        // 对每个未访问的顶点进行DFS
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (!topologicalSortDFSUtil(i, visited, recursionStack, result)) {
                    return null;  // 检测到环
                }
            }
        }
        
        return result;
    }
    
    private boolean topologicalSortDFSUtil(int v, boolean[] visited, Set<Integer> recursionStack, LinkedList<Integer> result) {
        // 标记当前节点为已访问
        visited[v] = true;
        recursionStack.add(v);
        
        // 递归处理所有邻接顶点
        for (int neighbor : adj.get(v)) {
            // 如果邻居在递归栈中，则存在环
            if (recursionStack.contains(neighbor)) {
                return false;
            }
            
            // 如果邻居尚未访问，则递归访问
            if (!visited[neighbor]) {
                if (!topologicalSortDFSUtil(neighbor, visited, recursionStack, result)) {
                    return false;
                }
            }
        }
        
        // 将当前顶点添加到结果的前面（这就是拓扑排序的关键）
        recursionStack.remove(v);
        result.addFirst(v);
        return true;
    }
    
    /**
     * 获取图中的所有源点（入度为0的顶点）
     * @return 源点列表
     */
    public List<Integer> getSources() {
        List<Integer> sources = new ArrayList<>();
        
        for (int v = 0; v < V; v++) {
            if (incoming.get(v).isEmpty()) {
                sources.add(v);
            }
        }
        
        return sources;
    }
    
    /**
     * 获取图中的所有汇点（出度为0的顶点）
     * @return 汇点列表
     */
    public List<Integer> getSinks() {
        List<Integer> sinks = new ArrayList<>();
        
        for (int v = 0; v < V; v++) {
            if (adj.get(v).isEmpty()) {
                sinks.add(v);
            }
        }
        
        return sinks;
    }
    
    /**
     * 获取顶点的出度
     * @param vertex 顶点
     * @return 出度
     */
    public int getOutDegree(int vertex) {
        validateVertex(vertex);
        return adj.get(vertex).size();
    }
    
    /**
     * 获取顶点的入度
     * @param vertex 顶点
     * @return 入度
     */
    public int getInDegree(int vertex) {
        validateVertex(vertex);
        return incoming.get(vertex).size();
    }
    
    /**
     * 获取顶点的所有后继（能够到达的顶点）
     * @param vertex 顶点
     * @return 后继列表
     */
    public List<Integer> getSuccessors(int vertex) {
        validateVertex(vertex);
        
        // 使用BFS找出所有可达顶点
        Set<Integer> successors = new HashSet<>();
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(vertex);
        visited[vertex] = true;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            for (int neighbor : adj.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                    successors.add(neighbor);
                }
            }
        }
        
        return new ArrayList<>(successors);
    }
    
    /**
     * 获取顶点的所有前驱（能够到达该顶点的顶点）
     * @param vertex 顶点
     * @return 前驱列表
     */
    public List<Integer> getPredecessors(int vertex) {
        validateVertex(vertex);
        
        // 使用BFS找出所有能到达此顶点的顶点
        Set<Integer> predecessors = new HashSet<>();
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        
        // 反向搜索，从incoming开始
        for (int v : incoming.get(vertex)) {
            queue.offer(v);
            visited[v] = true;
            predecessors.add(v);
        }
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            for (int prev : incoming.get(current)) {
                if (!visited[prev]) {
                    visited[prev] = true;
                    queue.offer(prev);
                    predecessors.add(prev);
                }
            }
        }
        
        return new ArrayList<>(predecessors);
    }
    
    /**
     * 检查顶点是否有效
     * @param vertex 顶点
     * @throws IllegalArgumentException 如果顶点无效
     */
    private void validateVertex(int vertex) {
        if (vertex < 0 || vertex >= V) {
            throw new IllegalArgumentException("顶点 " + vertex + " 不在有效范围内");
        }
    }
    
    /**
     * 获取图中的顶点数
     * @return 顶点数
     */
    public int getVertexCount() {
        return V;
    }
    
    /**
     * 打印图的结构
     */
    public void printGraph() {
        System.out.println("有向无环图结构：");
        for (int v = 0; v < V; v++) {
            System.out.print(v + " -> ");
            if (adj.get(v).isEmpty()) {
                System.out.println("(无出边)");
            } else {
                System.out.println(adj.get(v));
            }
        }
    }
}
