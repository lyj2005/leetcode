package dataStructure.tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    private TreeNode root;  // 根节点
    
    // 构造空二叉树
    public BinaryTree() {
        root = null;
    }
    
    // 构造带根节点的二叉树
    public BinaryTree(int rootValue) {
        root = new TreeNode(rootValue);
    }
    
    // 获取根节点
    public TreeNode getRoot() {
        return root;
    }
    
    // 判断树是否为空
    public boolean isEmpty() {
        return root == null;
    }
    
    // 插入节点（简单实现：若树为空则作为根节点，否则作为左子节点，若左子节点存在则作为右子节点）
    public void insert(int value) {
        TreeNode newNode = new TreeNode(value);
        if (isEmpty()) {
            root = newNode;
            return;
        }
        
        // 使用队列进行层序遍历，找到第一个没有左子节点或右子节点的节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            
            // 如果没有左子节点，将新节点作为左子节点
            if (current.left == null) {
                current.left = newNode;
                return;
            } 
            // 如果没有右子节点，将新节点作为右子节点
            else if (current.right == null) {
                current.right = newNode;
                return;
            }
            // 将子节点加入队列
            else {
                queue.add(current.left);
                queue.add(current.right);
            }
        }
    }
    
    // 前序遍历
    public void preorderTraversal(TreeNode node) {
        if (node == null) return;
        
        // 访问根节点
        System.out.print(node.val + " ");
        // 遍历左子树
        preorderTraversal(node.left);
        // 遍历右子树
        preorderTraversal(node.right);
    }
    
    // 中序遍历
    public void inorderTraversal(TreeNode node) {
        if (node == null) return;
        
        // 遍历左子树
        inorderTraversal(node.left);
        // 访问根节点
        System.out.print(node.val + " ");
        // 遍历右子树
        inorderTraversal(node.right);
    }
    
    // 后序遍历
    public void postorderTraversal(TreeNode node) {
        if (node == null) return;
        
        // 遍历左子树
        postorderTraversal(node.left);
        // 遍历右子树
        postorderTraversal(node.right);
        // 访问根节点
        System.out.print(node.val + " ");
    }
    
    // 层序遍历
    public void levelOrderTraversal() {
        if (isEmpty()) return;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.print(current.val + " ");
            
            if (current.left != null) {
                queue.add(current.left);
            }
            
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }
    
    // 计算树的高度
    public int height(TreeNode node) {
        if (node == null) return 0;
        
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    // 获取节点数量
    public int countNodes(TreeNode node) {
        if (node == null) return 0;
        
        return 1 + countNodes(node.left) + countNodes(node.right);
    }
    
    // 查找值为value的节点
    public boolean search(TreeNode node, int value) {
        if (node == null) return false;
        
        if (node.val == value) return true;
        
        // 在左右子树中查找
        return search(node.left, value) || search(node.right, value);
    }
    
    // 使用示例
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        
        // 插入节点
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        
        System.out.println("前序遍历:");
        tree.preorderTraversal(tree.getRoot());  // 输出: 1 2 4 5 3
        
        System.out.println(" 中序遍历:");
        tree.inorderTraversal(tree.getRoot());   // 输出: 4 2 5 1 3
        
        System.out.println(" 后序遍历:");
        tree.postorderTraversal(tree.getRoot()); // 输出: 4 5 2 3 1
        
        System.out.println(" 层序遍历:");
        tree.levelOrderTraversal();              // 输出: 1 2 3 4 5
        
        System.out.println(" 树的高度: " + tree.height(tree.getRoot()));
        System.out.println("节点数量: " + tree.countNodes(tree.getRoot()));
        System.out.println("是否包含值为3的节点: " + tree.search(tree.getRoot(), 3));
        System.out.println("是否包含值为6的节点: " + tree.search(tree.getRoot(), 6));
    }
}
class TreeNode {
    int val;           // 节点值
    TreeNode left;     // 左子节点
    TreeNode right;    // 右子节点
    
    // 构造函数
    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
