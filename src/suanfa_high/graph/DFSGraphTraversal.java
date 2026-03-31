package suanfa_high.graph;

import java.util.*;

public class DFSGraphTraversal {
    // 邻接表表示的图
    private Map<Integer, List<Integer>> graph;
    
    public DFSGraphTraversal() {
        graph = new HashMap<>();
    }
    
    // 添加顶点
    public void addVertex(int vertex) {
        if (!graph.containsKey(vertex)) {
            graph.put(vertex, new ArrayList<>());
        }
    }
    
    // 添加边
    public void addEdge(int v1, int v2) {
        if (!graph.containsKey(v1)) {
            addVertex(v1);
        }
        if (!graph.containsKey(v2)) {
            addVertex(v2);
        }
        graph.get(v1).add(v2);
        // 对于无向图，需要添加反向边
        // graph.get(v2).add(v1);
    }
    
    // DFS遍历（递归实现）
    public void dfs(int startVertex) {
        if (!graph.containsKey(startVertex)) {
            System.out.println("起始顶点不存在");
            return;
        }
        
        // 记录已访问的顶点
        Set<Integer> visited = new HashSet<>();
        
        // 调用递归DFS辅助函数
        dfsRecursive(startVertex, visited);
    }
    
    // 递归DFS辅助函数
    private void dfsRecursive(int vertex, Set<Integer> visited) {
        // 标记顶点为已访问
        visited.add(vertex);
        System.out.print(vertex + " ");
        
        // 获取当前顶点的所有邻接顶点
        List<Integer> neighbors = graph.getOrDefault(vertex, new ArrayList<>());
        
        // 对每个未访问的邻接顶点，递归执行DFS
        for (int neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited);
            }
        }
    }
    
    // DFS遍历（非递归实现，使用栈）
    public void dfsIterative(int startVertex) {
        if (!graph.containsKey(startVertex)) {
            System.out.println("起始顶点不存在");
            return;
        }
        
        // 记录已访问的顶点
        Set<Integer> visited = new HashSet<>();
        // 栈用于DFS
        Stack<Integer> stack = new Stack<>();
        
        // 将起始顶点入栈
        stack.push(startVertex);
        
        while (!stack.isEmpty()) {
            // 出栈
            int currentVertex = stack.pop();
            
            // 如果当前顶点未访问，则访问
            if (!visited.contains(currentVertex)) {
                visited.add(currentVertex);
                System.out.print(currentVertex + " ");
                
                // 获取当前顶点的所有邻接顶点
                List<Integer> neighbors = graph.getOrDefault(currentVertex, new ArrayList<>());
                
                // 将未访问的邻接顶点入栈（逆序，以保持与递归DFS相同的访问顺序）
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    int neighbor = neighbors.get(i);
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }
}
