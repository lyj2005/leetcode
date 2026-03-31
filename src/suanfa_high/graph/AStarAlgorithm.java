package suanfa_high.graph;

import java.util.*;

public class AStarAlgorithm {
    // 表示图中的一个顶点
    static class Node implements Comparable<Node> {
        int x, y;        // 顶点坐标
        int g;           // 从起点到当前顶点的实际距离
        int h;           // 从当前顶点到终点的估计距离
        int f;           // f = g + h
        Node parent;     // 父顶点，用于路径重建
        
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.g = 0;
            this.h = 0;
            this.f = 0;
            this.parent = null;
        }
        
        // 顶点相等判断
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Node node = (Node) obj;
            return x == node.x && y == node.y;
        }
        
        // 顶点哈希函数
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
        
        // 按f值比较顶点，用于优先队列
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.f, other.f);
        }
    }
    
    // 四个方向：上、右、下、左
    private static final int[][] DIRECTIONS = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    
    // 网格地图
    private int[][] grid;
    // 网格尺寸
    private int rows, cols;
    
    public AStarAlgorithm(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
    }
    
    // 使用曼哈顿距离作为启发式函数
    private int heuristic(Node node, Node goal) {
        return Math.abs(node.x - goal.x) + Math.abs(node.y - goal.y);
    }
    
    // 检查坐标是否在网格范围内且不是障碍物
    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < cols && y >= 0 && y < rows && grid[y][x] == 0;
    }
    
    // A*算法实现
    public List<Node> findPath(int startX, int startY, int goalX, int goalY) {
        // 创建起点和终点
        Node start = new Node(startX, startY);
        Node goal = new Node(goalX, goalY);
        
        // 检查起点和终点是否有效
        if (!isValidPosition(startX, startY) || !isValidPosition(goalX, goalY)) {
            return new ArrayList<>();  // 无效起点或终点，返回空路径
        }
        
        // 开放集合，存储待探索的顶点
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        // 已探索集合
        Set<Node> closedSet = new HashSet<>();
        // 用于快速查找openSet中的顶点
        Map<String, Node> openSetMap = new HashMap<>();
        
        // 初始化起点
        start.h = heuristic(start, goal);
        start.f = start.h;  // g = 0
        openSet.add(start);
        openSetMap.put(startX + "," + startY, start);
        
        while (!openSet.isEmpty()) {
            // 获取f值最小的顶点
            Node current = openSet.poll();
            openSetMap.remove(current.x + "," + current.y);
            
            // 检查是否到达终点
            if (current.x == goalX && current.y == goalY) {
                return reconstructPath(current);  // 重建路径并返回
            }
            
            // 将当前顶点加入已探索集合
            closedSet.add(current);
            
            // 探索所有邻居
            for (int[] dir : DIRECTIONS) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];
                
                // 检查邻居是否有效
                if (!isValidPosition(newX, newY)) {
                    continue;
                }
                
                // 创建邻居顶点
                Node neighbor = new Node(newX, newY);
                
                // 如果邻居已经探索过，则跳过
                if (closedSet.contains(neighbor)) {
                    continue;
                }
                
                // 计算通过当前顶点到达邻居的新路径长度
                int tentativeG = current.g + 1;  // 假设每步距离为1
                
                // 获取openSet中的邻居（如果存在）
                Node openNeighbor = openSetMap.get(newX + "," + newY);
                
                // 如果邻居不在openSet中或新路径更短
                if (openNeighbor == null || tentativeG < openNeighbor.g) {
                    // 如果邻居已在openSet中，先移除
                    if (openNeighbor != null) {
                        openSet.remove(openNeighbor);
                        openSetMap.remove(newX + "," + newY);
                    }
                    
                    // 更新邻居信息
                    neighbor.parent = current;
                    neighbor.g = tentativeG;
                    neighbor.h = heuristic(neighbor, goal);
                    neighbor.f = neighbor.g + neighbor.h;
                    
                    // 将邻居加入openSet
                    openSet.add(neighbor);
                    openSetMap.put(newX + "," + newY, neighbor);
                }
            }
        }
        
        // 如果算法结束但未找到路径，返回空列表
        return new ArrayList<>();
    }
    
    // 重建从起点到终点的路径
    private List<Node> reconstructPath(Node goalNode) {
        List<Node> path = new ArrayList<>();
        Node current = goalNode;
        
        while (current != null) {
            path.add(current);
            current = current.parent;
        }
        
        Collections.reverse(path);  // 反转路径，从起点到终点
        return path;
    }
    
    public static void main(String[] args) {
        // 创建示例网格，0表示可通行，1表示障碍物
        int[][] grid = {
            {0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        
        AStarAlgorithm aStar = new AStarAlgorithm(grid);
        
        // 寻找从(0,0)到(4,4)的路径
        List<Node> path = aStar.findPath(0, 0, 4, 4);
        
        if (path.isEmpty()) {
            System.out.println("没有找到路径！");
        } else {
            System.out.println("找到路径：");
            for (Node node : path) {
                System.out.println("(" + node.x + ", " + node.y + ")");
            }
            System.out.println("路径长度：" + (path.size() - 1));
        }
    }
}
