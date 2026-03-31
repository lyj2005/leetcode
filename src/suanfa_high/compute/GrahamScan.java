package suanfa_high.compute;

import java.util.*;

class Point {
    int x, y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    // 计算两点间的平方距离
    public int squareDistance(Point p) {
        return (this.x - p.x) * (this.x - p.x) + (this.y - p.y) * (this.y - p.y);
    }
    
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

public class GrahamScan {
    
    // 计算叉积(OA×OB)，用于判断转向方向
    private static int crossProduct(Point O, Point A, Point B) {
        return (A.x - O.x) * (B.y - O.y) - (A.y - O.y) * (B.x - O.x);
    }
    
    // 计算凸包
    public static List<Point> convexHull(Point[] points) {
        int n = points.length;
        if (n <= 3) {
            // 点数少于等于3时，所有点都在凸包上
            List<Point> result = new ArrayList<>();
            for (Point p : points) {
                result.add(p);
            }
            return result;
        }
        
        // 找到y坐标最小的点（若有多个，取x坐标最小的）
        int lowestPoint = 0;
        for (int i = 1; i < n; i++) {
            if (points[i].y < points[lowestPoint].y || 
                (points[i].y == points[lowestPoint].y && points[i].x < points[lowestPoint].x)) {
                lowestPoint = i;
            }
        }
        
        // 将锚点放到数组首位
        Point temp = points[0];
        points[0] = points[lowestPoint];
        points[lowestPoint] = temp;
        
        // 按极角排序其余点
        final Point anchor = points[0];
        Arrays.sort(points, 1, n, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                int orientation = crossProduct(anchor, p1, p2);
                if (orientation == 0) {
                    // 共线时，按距离排序
                    return anchor.squareDistance(p1) - anchor.squareDistance(p2);
                }
                return -orientation; // 逆时针排序
            }
        });
        
        // 构建凸包
        Stack<Point> stack = new Stack<>();
        stack.push(points[0]);
        stack.push(points[1]);
        
        for (int i = 2; i < n; i++) {
            while (stack.size() > 1) {
                Point top = stack.pop();
                Point nextTop = stack.peek();
                
                // 如果构成逆时针转向，则保留栈顶点，否则弹出
                if (crossProduct(nextTop, top, points[i]) > 0) {
                    stack.push(top);
                    break;
                }
            }
            stack.push(points[i]);
        }
        
        return new ArrayList<>(stack);
    }
    
    public static void main(String[] args) {
        Point[] points = {
            new Point(0, 3),
            new Point(1, 1),
            new Point(2, 2),
            new Point(4, 4),
            new Point(0, 0),
            new Point(1, 2),
            new Point(3, 1),
            new Point(3, 3)
        };
        
        List<Point> hull = convexHull(points);
        
        System.out.println("凸包顶点：");
        for (Point p : hull) {
            System.out.println(p);
        }
    }
}
