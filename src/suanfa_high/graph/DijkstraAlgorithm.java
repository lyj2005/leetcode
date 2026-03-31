package suanfa_high.graph;

import java.util.*;

public class DijkstraAlgorithm {
    // 图的邻接表表示，Map<顶点, Map<邻接顶点, 边权重>>
    private Map<Integer, Map<Integer, Integer>> graph;
    
    public DijkstraAlgorithm() {
        graph = new HashMap<>();
    }
    
    // 添加顶点
    public void addVertex(int vertex) {
        if (!graph.containsKey(vertex)) {
            graph.put(vertex, new HashMap<>());
        }
    }
    
    // 添加边
    public void addEdge(int source, int destination, int weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Dijkstra算法不支持负权边");
        }
        
        if (!graph.containsKey(source)) {
            addVertex(source);
        }
        if (!graph.containsKey(destination)) {
            addVertex(destination);
        }
        
        graph.get(source).put(destination, weight);
    }
    
    // Dijkstra算法实现
    public Map<Integer, Integer> dijkstra(int startVertex) {
        if (!graph.containsKey(startVertex)) {
            throw new IllegalArgumentException("起始顶点不存在");
        }
        
        // 存储到各顶点的最短距离
        Map<Integer, Integer> distances = new HashMap<>();
        // 存储到各顶点的前驱顶点（用于路径重建）
        Map<Integer, Integer> previousVertices = new HashMap<>();
        // 优先队列，用于选择距离最小的顶点
        PriorityQueue<VertexDistance> priorityQueue = new PriorityQueue<>();
        // 已处理的顶点集合
        Set<Integer> settled = new HashSet<>();
        
        // 初始化距离
        for (int vertex : graph.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
            previousVertices.put(vertex, null);
        }
        
        // 起始顶点到自身的距离为0
        distances.put(startVertex, 0);
        priorityQueue.add(new VertexDistance(startVertex, 0));
        
        while (!priorityQueue.isEmpty() && settled.size() < graph.size()) {
            // 获取距离最小的顶点
            int currentVertex = priorityQueue.poll().vertex;
            
            // 如果该顶点已经处理过，则跳过
            if (settled.contains(currentVertex)) {
                continue;
            }
            
            // 标记该顶点为已处理
            settled.add(currentVertex);
            
            // 更新相邻顶点的距离
            updateNeighborsDistances(currentVertex, distances, previousVertices, priorityQueue, settled);
        }
        
        return distances;
    }
    
    // 更新相邻顶点的距离
    private void updateNeighborsDistances(int currentVertex, 
                                         Map<Integer, Integer> distances,
                                         Map<Integer, Integer> previousVertices, 
                                         PriorityQueue<VertexDistance> priorityQueue,
                                         Set<Integer> settled) {
        int currentDistance = distances.get(currentVertex);
        Map<Integer, Integer> neighbors = graph.get(currentVertex);
        
        for (Map.Entry<Integer, Integer> neighborEntry : neighbors.entrySet()) {
            int neighbor = neighborEntry.getKey();
            int edgeWeight = neighborEntry.getValue();
            
            // 如果邻居已经处理过，则跳过
            if (settled.contains(neighbor)) {
                continue;
            }
            
            // 计算通过当前顶点到达邻居的新距离
            int newDistance = currentDistance + edgeWeight;
            
            // 如果新距离更短，则更新
            if (newDistance < distances.get(neighbor)) {
                distances.put(neighbor, newDistance);
                previousVertices.put(neighbor, currentVertex);
                priorityQueue.add(new VertexDistance(neighbor, newDistance));
            }
        }
    }
    
    // 重建从起始顶点到目标顶点的最短路径
    public List<Integer> getPath(int startVertex, int endVertex, Map<Integer, Integer> previousVertices) {
        List<Integer> path = new ArrayList<>();
        
        // 如果没有路径到达终点，返回空列表
        if (previousVertices.get(endVertex) == null && startVertex != endVertex) {
            return path;
        }
        
        // 从终点回溯到起点
        for (Integer vertex = endVertex; vertex != null; vertex = previousVertices.get(vertex)) {
            path.add(vertex);
        }
        
        // 反转路径，使其从起点到终点
        Collections.reverse(path);
        return path;
    }
    
    // 用于优先队列的顶点距离类
    private static class VertexDistance implements Comparable<VertexDistance> {
        int vertex;
        int distance;
        
        public VertexDistance(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
        
        @Override
        public int compareTo(VertexDistance other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
    
    public static void main(String[] args) {
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm();
        
        // 构建示例图
        dijkstra.addEdge(0, 1, 5);
        dijkstra.addEdge(0, 2, 2);
        dijkstra.addEdge(1, 3, 4);
        dijkstra.addEdge(1, 4, 2);
        dijkstra.addEdge(2, 1, 8);
        dijkstra.addEdge(2, 3, 7);
        dijkstra.addEdge(3, 4, 6);
        dijkstra.addEdge(3, 5, 1);
        dijkstra.addEdge(4, 5, 3);
        
        int startVertex = 0;
        Map<Integer, Integer> distances = dijkstra.dijkstra(startVertex);
        
        System.out.println("从顶点" + startVertex + "到各顶点的最短距离：");
        for (Map.Entry<Integer, Integer> entry : distances.entrySet()) {
            System.out.println("到顶点" + entry.getKey() + "的距离：" + entry.getValue());
        }
    }
}
