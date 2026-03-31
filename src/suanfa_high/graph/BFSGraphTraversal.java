package suanfa_high.graph;

import java.util.*;

public class BFSGraphTraversal {
    // 邻接表表示的图
    private Map<Integer, List<Integer>> graph;
    
    public BFSGraphTraversal() {
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
    
    // BFS遍历
    public void bfs(int startVertex) {
        if (!graph.containsKey(startVertex)) {
            System.out.println("起始顶点不存在");
            return;
        }
        
        // 记录已访问的顶点
        Set<Integer> visited = new HashSet<>();
        // 队列用于BFS
        Queue<Integer> queue = new LinkedList<>();
        
        // 将起始顶点标记为已访问并入队
        visited.add(startVertex);
        queue.add(startVertex);
        
        while (!queue.isEmpty()) {
            // 出队并访问
            int currentVertex = queue.poll();
            System.out.print(currentVertex + " ");
            
            // 获取当前顶点的所有邻接顶点
            List<Integer> neighbors = graph.getOrDefault(currentVertex, new ArrayList<>());
            
            // 对每个未访问的邻接顶点，标记为已访问并入队
            for (int neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }
}
