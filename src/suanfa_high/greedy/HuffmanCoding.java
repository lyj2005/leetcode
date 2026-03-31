package suanfa_high.greedy;

import java.util.*;

public class HuffmanCoding {
    // 定义Huffman树的节点
    static class Node implements Comparable<Node> {
        char data;
        int frequency;
        Node left, right;
        
        // 叶节点构造函数
        public Node(char data, int frequency) {
            this.data = data;
            this.frequency = frequency;
            this.left = this.right = null;
        }
        
        // 内部节点构造函数
        public Node(int frequency, Node left, Node right) {
            this.data = '�'; // 内部节点不存储字符
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }
        
        @Override
        public int compareTo(Node other) {
            return this.frequency - other.frequency;
        }
    }
    
    // 构建Huffman树
    public static Node buildHuffmanTree(Map<Character, Integer> frequencies) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        
        // 创建叶节点并加入优先队列
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            priorityQueue.add(new Node(entry.getKey(), entry.getValue()));
        }
        
        // 构建Huffman树
        while (priorityQueue.size() > 1) {
            // 取出两个权重最小的节点
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();
            
            // 创建新的内部节点，权重为两个节点的权重之和
            Node parent = new Node(left.frequency + right.frequency, left, right);
            
            // 将新节点加入优先队列
            priorityQueue.add(parent);
        }
        
        // 返回Huffman树的根节点
        return priorityQueue.poll();
    }
    
    // 生成Huffman编码
    public static Map<Character, String> generateCodes(Node root) {
        Map<Character, String> codes = new HashMap<>();
        generateCodesRecursive(root, "", codes);
        return codes;
    }
    
    private static void generateCodesRecursive(Node node, String code, Map<Character, String> codes) {
        if (node == null) {
            return;
        }
        
        // 如果是叶节点，存储字符和对应的编码
        if (node.left == null && node.right == null) {
            codes.put(node.data, code);
        }
        
        // 递归遍历左子树，编码追加0
        generateCodesRecursive(node.left, code + "0", codes);
        
        // 递归遍历右子树，编码追加1
        generateCodesRecursive(node.right, code + "1", codes);
    }
    
    // 编码字符串
    public static String encode(String text, Map<Character, String> codes) {
        StringBuilder encodedText = new StringBuilder();
        
        for (char c : text.toCharArray()) {
            encodedText.append(codes.get(c));
        }
        
        return encodedText.toString();
    }
    
    // 解码字符串
    public static String decode(String encodedText, Node root) {
        StringBuilder decodedText = new StringBuilder();
        Node current = root;
        
        for (char bit : encodedText.toCharArray()) {
            // 根据位选择左子树或右子树
            if (bit == '0') {
                current = current.left;
            } else {
                current = current.right;
            }
            
            // 如果到达叶节点，找到一个字符
            if (current.left == null && current.right == null) {
                decodedText.append(current.data);
                current = root; // 重置为根节点，开始下一个字符的解码
            }
        }
        
        return decodedText.toString();
    }
    
    public static void main(String[] args) {
        // 示例文本
        String text = "abcdefabcdeabcdabcaba";
        
        // 计算频率
        Map<Character, Integer> frequencies = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }
        
        // 构建Huffman树
        Node root = buildHuffmanTree(frequencies);
        
        // 生成编码
        Map<Character, String> codes = generateCodes(root);
        
        // 打印编码
        System.out.println("Huffman编码:");
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        // 编码文本
        String encodedText = encode(text, codes);
        System.out.println(" 编码后的文本: " + encodedText);
        
        // 解码文本
        String decodedText = decode(encodedText, root);
        System.out.println("解码后的文本: " + decodedText);
        
        // 计算压缩率
        int originalSize = text.length() * 8; // 假设每个字符用8位表示
        int compressedSize = encodedText.length();
        double compressionRatio = (double) (originalSize - compressedSize) / originalSize * 100;
        
        System.out.println(" 原始大小: " + originalSize + " 位");
        System.out.println("压缩后大小: " + compressedSize + " 位");
        System.out.println("压缩率: " + String.format("%.2f", compressionRatio) + "%");
    }
}
