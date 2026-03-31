//package dataStructure.graph;
//
//import java.util.ArrayList;
//import java.util.List;
//
//// 无权图的边列表表示
//class Edge {
//    int source;      // 边的起点
//    int destination; // 边的终点
//
//    public Edge(int source, int destination) {
//        this.source = source;
//        this.destination = destination;
//    }
//}
//
//// 有权图的边列表表示
//class WeightedEdge {
//    int source;      // 边的起点
//    int destination; // 边的终点
//    int weight;      // 边的权重
//
//    public WeightedEdge(int source, int destination, int weight) {
//        this.source = source;
//        this.destination = destination;
//        this.weight = weight;
//    }
//}
//
//// 使用边列表表示的图
//class Graph {
//    int V; // 顶点数
//    List<Edge> edges; // 边列表
//
//    public Graph(int V) {
//        this.V = V;
//        edges = new ArrayList<>();
//    }
//
//    // 添加边
//    public void addEdge(int source, int destination) {
//        edges.add(new Edge(source, destination));
//    }
//
//    // 打印所有边
//    public void printEdges() {
//        for (Edge edge : edges) {
//            System.out.println(edge.source + " -> " + edge.destination);
//        }
//    }
//}
