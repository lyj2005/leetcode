package suanfa_high.graph;

import java.util.*;

public class Kosaraju {
    private int V;  // 顶点数量
    private List<List<Integer>> adj;  // 邻接表
    private List<List<Integer>> transpose;  // 转置图的邻接表
    
    public Kosaraju(int V) {
        this.V = V;
        adj = new ArrayList<>();
        transpose = new ArrayList<>();
        
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
            transpose.add(new ArrayList<>());
        }
    }
    
    // 添加边
    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        transpose.get(w).add(v);  // 同时构建转置图
    }
    
    // 第一次DFS，填充顶点完成时间栈
    private void fillOrder(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        
        for (int adj : adj.get(v)) {
            if (!visited[adj]) {
                fillOrder(adj, visited, stack);
            }
        }
        
        // 当顶点的所有邻接点都处理完后，将其加入栈
        stack.push(v);
    }
    
    // 标准DFS算法
    private void dfs(int v, boolean[] visited, List<Integer> component) {
        visited[v] = true;
        component.add(v);
        
        for (int adj : transpose.get(v)) {
            if (!visited[adj]) {
                dfs(adj, visited, component);
            }
        }
    }
    
    // 打印所有强连通分量
    public List<List<Integer>> getSCCs() {
        Stack<Integer> stack = new Stack<>();
        
        // 第一步：标记所有顶点为未访问
        boolean[] visited = new boolean[V];
        
        // 第二步：填充顶点栈
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                fillOrder(i, visited, stack);
            }
        }
        
        // 第三步：重置访问状态
        Arrays.fill(visited, false);
        
        // 第四步：按栈顺序处理顶点
        List<List<Integer>> sccs = new ArrayList<>();
        while (!stack.isEmpty()) {
            int v = stack.pop();
            
            if (!visited[v]) {
                List<Integer> component = new ArrayList<>();
                dfs(v, visited, component);
                sccs.add(component);
            }
        }
        
        return sccs;
    }
}
