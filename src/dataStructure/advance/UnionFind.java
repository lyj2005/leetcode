package dataStructure.advance;

public class UnionFind {
    private int[] parent; // 记录每个节点的父节点
    private int[] rank;   // 记录每个根节点对应树的高度
    
    // 初始化
    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        
        // 初始时，每个元素自成一个集合，父节点指向自己
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }
    
    // 查找操作（带路径压缩）
    public int find(int x) {
        if (parent[x] != x) {
            // 路径压缩：将x的父节点直接设为根节点
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    // 合并操作（按秩合并）
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        
        // 如果已经在同一集合中，不需要合并
        if (rootX == rootY) {
            return;
        }
        
        // 按秩合并：将秩较小的树连接到秩较大的树下
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            // 秩相同时，随便选一个作为根，并将新根的秩+1
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
    
    // 判断两个元素是否在同一集合
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }
}
