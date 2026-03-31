package dataStructure.tree;

public class SegmentTree {
    private int[] tree;   // 存储线段树节点
    private int[] lazy;   // 懒惰标记
    private int[] nums;   // 原始数组的副本
    private int n;        // 原始数组长度
    
    public SegmentTree(int[] array) {
        n = array.length;
        // 线段树数组大小一般为原数组大小的4倍
        tree = new int[4 * n];
        lazy = new int[4 * n];
        nums = array.clone();
        build(0, 0, n - 1);
    }
    
    // 构建线段树
    private void build(int node, int start, int end) {
        if (start == end) {
            // 叶子节点，存储单个元素
            tree[node] = nums[start];
            return;
        }
        
        int mid = (start + end) / 2;
        int leftNode = 2 * node + 1;
        int rightNode = 2 * node + 2;
        
        // 递归构建左右子树
        build(leftNode, start, mid);
        build(rightNode, mid + 1, end);
        
        // 合并子节点的信息
        tree[node] = tree[leftNode] + tree[rightNode];
    }
    
    // 单点修改
    public void update(int index, int val) {
        // 计算与原值的差值
        int diff = val - nums[index];
        nums[index] = val;
        updateSingle(0, 0, n - 1, index, diff);
    }
    
    private void updateSingle(int node, int start, int end, int index, int diff) {
        // 检查索引是否在当前节点范围内
        if (index < start || index > end) {
            return;
        }
        
        // 更新当前节点的值
        tree[node] += diff;
        
        if (start != end) {
            int mid = (start + end) / 2;
            int leftNode = 2 * node + 1;
            int rightNode = 2 * node + 2;
            
            // 递归更新子节点
            updateSingle(leftNode, start, mid, index, diff);
            updateSingle(rightNode, mid + 1, end, index, diff);
        }
    }
    
    // 区间查询
    public int query(int left, int right) {
        return queryRange(0, 0, n - 1, left, right);
    }
    
    private int queryRange(int node, int start, int end, int left, int right) {
        // 如果当前节点的区间完全在查询区间外
        if (right < start || left > end) {
            return 0;
        }
        
        // 如果当前节点的区间完全在查询区间内
        if (left <= start && end <= right) {
            return tree[node];
        }
        
        // 处理懒惰标记
        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node];
            
            if (start != end) {
                lazy[2 * node + 1] += lazy[node];
                lazy[2 * node + 2] += lazy[node];
            }
            
            lazy[node] = 0;
        }
        
        // 查询范围部分覆盖当前节点的区间，需要分别查询左右子节点
        int mid = (start + end) / 2;
        int leftSum = queryRange(2 * node + 1, start, mid, left, right);
        int rightSum = queryRange(2 * node + 2, mid + 1, end, left, right);
        
        return leftSum + rightSum;
    }
    
    // 区间修改
    public void updateRange(int left, int right, int val) {
        updateRangeTree(0, 0, n - 1, left, right, val);
    }
    
    private void updateRangeTree(int node, int start, int end, int left, int right, int val) {
        // 处理当前节点的懒惰标记
        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node];
            
            if (start != end) {
                lazy[2 * node + 1] += lazy[node];
                lazy[2 * node + 2] += lazy[node];
            }
            
            lazy[node] = 0;
        }
        
        // 如果当前节点的区间完全在修改区间外
        if (right < start || left > end) {
            return;
        }
        
        // 如果当前节点的区间完全在修改区间内
        if (left <= start && end <= right) {
            tree[node] += (end - start + 1) * val;
            
            if (start != end) {
                lazy[2 * node + 1] += val;
                lazy[2 * node + 2] += val;
            }
            
            return;
        }
        
        // 修改范围部分覆盖当前节点的区间，需要分别修改左右子节点
        int mid = (start + end) / 2;
        updateRangeTree(2 * node + 1, start, mid, left, right, val);
        updateRangeTree(2 * node + 2, mid + 1, end, left, right, val);
        
        // 更新当前节点的值
        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }
}
