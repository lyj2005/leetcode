package dataStructure.tree.bst;

/**
 *
 * 编程导航
 * • BinarySearchTree (递归范式) 更符合函数式编程思想，代码优雅，
 * 对于理解递归和树结构自我相似的特性很有帮助。
 */


public class BinarySearchTree {


    /**
     * 定义节点类
     */
    class Node {
        int value;      // 节点值
        Node left;      // 左子节点
        Node right;     // 右子节点
        
        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
    


    private Node root;  // 根节点
    
    public BinarySearchTree() {
        root = null;
    }


    /**
     * 插入操作
     * @param value
     */
    public void insert(int value) {
        root = insertRecursive(root, value);
    }



    private Node insertRecursive(Node current, int value) {
        // 如果当前节点为空，创建新节点
        if (current == null) {
            return new Node(value);
        }
        
        // 递归插入到合适位置
        if (value < current.value) {
            current.left = insertRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = insertRecursive(current.right, value);
        }
        
        // 值已存在，不做任何操作
        return current;
    }





    /**
     * 查找操作
     * @param value
     * @return
     */

    public boolean search(int value) {
        return searchRecursive(root, value);
    }
    
    private boolean searchRecursive(Node current, int value) {
        // 空树或到达叶节点
        if (current == null) {
            return false;
        }
        
        // 找到目标值
        if (value == current.value) {
            return true;
        }
        
        // 在左子树或右子树中查找
        if (value < current.value) {
            return searchRecursive(current.left, value);
        } else {
            return searchRecursive(current.right, value);
        }
    }



    /**
     * 删除操作
     * @param value
     */

    public void delete(int value) {
        root = deleteRecursive(root, value);
    }
    
    private Node deleteRecursive(Node current, int value) {
        // 树为空或未找到要删除的值
        if (current == null) {
            return null;
        }
        
        // 找到要删除的节点
        if (value == current.value) {
            // 情况1：叶节点
            if (current.left == null && current.right == null) {
                return null;
            }
            
            // 情况2：只有一个子节点
            if (current.left == null) {
                return current.right;
            }
            if (current.right == null) {
                return current.left;
            }
            
            // 情况3：有两个子节点
            // 查找右子树中的最小值
            int smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            // 删除右子树中的最小值节点
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }
        
        // 继续在左子树或右子树中查找
        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
        } else {
            current.right = deleteRecursive(current.right, value);
        }
        return current;
    }


    /**
     * 查找子树中的最小值（最左侧节点）
     * @param root
     * @return
     */
    private int findSmallestValue(Node root) {
        if (root.left == null) {
            return root.value;
        }
        return findSmallestValue(root.left);
    }



}
