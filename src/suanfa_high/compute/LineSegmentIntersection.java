//package suanfa_high.compute;
//
//
//class Point {
//    double x, y;
//
//    public Point(double x, double y) {
//        this.x = x;
//        this.y = y;
//    }
//}
//
//
//public class LineSegmentIntersection {
//
//    // 计算向量叉积 (p1-p0) × (p2-p0)
//    private static double crossProduct(Point p0, Point p1, Point p2) {
//        return (p1.x - p0.x) * (p2.y - p0.y) - (p1.y - p0.y) * (p2.x - p0.x);
//    }
//
//    // 判断点p是否在线段p1p2上（假设三点共线）
//    private static boolean isPointOnSegment(Point p, Point p1, Point p2) {
//        return p.x >= Math.min(p1.x, p2.x) && p.x <= Math.max(p1.x, p2.x) &&
//               p.y >= Math.min(p1.y, p2.y) && p.y <= Math.max(p1.y, p2.y);
//    }
//
//    // 检测两线段是否相交
//    public static boolean doIntersect(Point p1, Point p2, Point p3, Point p4) {
//        // 计算四个叉积
//        double d1 = crossProduct(p3, p4, p1);
//        double d2 = crossProduct(p3, p4, p2);
//        double d3 = crossProduct(p1, p2, p3);
//        double d4 = crossProduct(p1, p2, p4);
//
//        // 一般情况：两线段相交
//        if (((d1 > 0 && d2 < 0) || (d1 < 0 && d2 > 0)) &&
//            ((d3 > 0 && d4 < 0) || (d3 < 0 && d4 > 0))) {
//            return true;
//        }
//
//        // 特殊情况：线段端点在另一线段上
//        if (d1 == 0 && isPointOnSegment(p1, p3, p4)) return true;
//        if (d2 == 0 && isPointOnSegment(p2, p3, p4)) return true;
//        if (d3 == 0 && isPointOnSegment(p3, p1, p2)) return true;
//        if (d4 == 0 && isPointOnSegment(p4, p1, p2)) return true;
//
//        return false;
//    }
//
//
//
//
//    // 计算两线段交点（假设已知两线段相交）
//    public static Point getIntersectionPoint(Point p1, Point p2, Point p3, Point p4) {
//        // 线段1参数方程: p1 + t1 * (p2 - p1), t1 ∈ [0,1]
//        // 线段2参数方程: p3 + t2 * (p4 - p3), t2 ∈ [0,1]
//
//        double denominator = (p4.y - p3.y) * (p2.x - p1.x) - (p4.x - p3.x) * (p2.y - p1.y);
//
//        // 处理平行或共线情况
//        if (Math.abs(denominator) < 1e-10) {
//            // 如果共线且重叠，返回其中一个重叠点
//            if (isPointOnSegment(p3, p1, p2)) return p3;
//            if (isPointOnSegment(p4, p1, p2)) return p4;
//            if (isPointOnSegment(p1, p3, p4)) return p1;
//            if (isPointOnSegment(p2, p3, p4)) return p2;
//            return null;
//        }
//
//        double t1 = ((p4.x - p3.x) * (p1.y - p3.y) - (p4.y - p3.y) * (p1.x - p3.x)) / denominator;
//
//        // 计算交点
//        double x = p1.x + t1 * (p2.x - p1.x);
//        double y = p1.y + t1 * (p2.y - p1.y);
//
//        return new Point(x, y);
//    }
//
//    public static void main(String[] args) {
//        // 测试用例
//        Point p1 = new Point(1, 1);
//        Point p2 = new Point(5, 5);
//        Point p3 = new Point(1, 5);
//        Point p4 = new Point(5, 1);
//
//        boolean intersect = doIntersect(p1, p2, p3, p4);
//        System.out.println("线段是否相交: " + intersect);
//
//        if (intersect) {
//            Point intersection = getIntersectionPoint(p1, p2, p3, p4);
//            System.out.println("交点坐标: (" + intersection.x + ", " + intersection.y + ")");
//        }
//    }
//}
