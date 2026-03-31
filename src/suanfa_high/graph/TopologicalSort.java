package suanfa_high.graph;

import java.util.*;

public class TopologicalSort {
    // 图的邻接表表示
    private Map<Integer, List<Integer>> graph;
    // 顶点数
    private int numVertices;
    
    public TopologicalSort(int vertices) {
        this.numVertices = vertices;
        this.graph = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            graph.put(i, new ArrayList<>());
        }
    }
    
    // 添加有向边
    public void addEdge(int u, int v) {
        graph.get(u).add(v);
    }
    
    // 使用Kahn算法进行拓扑排序
    public List<Integer> topologicalSort() {
        // 计算所有顶点的入度
        int[] inDegree = new int[numVertices];
        for (int u = 0; u < numVertices; u++) {
            for (int v : graph.get(u)) {
                inDegree[v]++;
            }
        }
        
        // 将所有入度为0的顶点加入队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numVertices; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        
        // 存储拓扑排序结果
        List<Integer> result = new ArrayList<>();
        
        // 从队列中取出顶点，并更新其邻接顶点的入度
        while (!queue.isEmpty()) {
            int u = queue.poll();
            result.add(u);
            
            for (int v : graph.get(u)) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.add(v);
                }
            }
        }
        
        // 检查是否存在环
        if (result.size() != numVertices) {
            System.out.println("图中存在环，无法进行拓扑排序");
            return new ArrayList<>();
        }
        
        return result;
    }
    
    // 使用DFS实现拓扑排序
    public List<Integer> topologicalSortDFS() {
        boolean[] visited = new boolean[numVertices];
        Stack<Integer> stack = new Stack<>();
        
        // 对所有未访问的顶点进行DFS
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                dfs(i, visited, stack);
            }
        }
        
        // 将栈中的元素依次取出，即为拓扑排序结果
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        
        return result;
    }
    
    // DFS辅助函数
    private void dfs(int u, boolean[] visited, Stack<Integer> stack) {
        visited[u] = true;
        
        for (int v : graph.get(u)) {
            if (!visited[v]) {
                dfs(v, visited, stack);
            }
        }
        
        // 在回溯时将顶点加入栈中
        stack.push(u);
    }
    
    public static void main(String[] args) {
        // 创建图
        TopologicalSort g = new TopologicalSort(6);
        g.addEdge(5, 0);
        g.addEdge(5, 2);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        
        // 使用Kahn算法进行拓扑排序
        List<Integer> result = g.topologicalSort();
        System.out.println("Kahn算法拓扑排序结果：" + result);
        
        // 使用DFS进行拓扑排序
        List<Integer> resultDFS = g.topologicalSortDFS();
        System.out.println("DFS算法拓扑排序结果：" + resultDFS);
    }
}
