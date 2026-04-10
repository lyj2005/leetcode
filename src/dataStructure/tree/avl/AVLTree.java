package dataStructure.tree.avl;

/**
 * AVL树的Java实现
 */
public class AVLTree {


    // 树节点定义
    class Node {
        int key;        // 节点值
        Node left;      // 左子节点
        Node right;     // 右子节点
        int height;     // 节点高度

        Node(int key) {
            this.key = key;
            this.height = 1; // 新节点高度初始为1
        }
    }

    Node root; // 根节点







    /**
     * 获取节点高度，空节点高度为0
     * @param node
     * @return
     */
    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }


    /**
     * 获取节点的平衡因子
     * @param node
     * @return
     */
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }


    /**
     *  更新节点高度
     * @param node
     */
    private void updateHeight(Node node) {
        if (node == null) {
            return;
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }


    /**
     * 右旋转（处理左左情况）
     * @param y
     * @return
     */
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // 执行旋转
        x.right = y;
        y.left = T2;

        // 更新高度
        updateHeight(y);
        updateHeight(x);

        return x; // 返回新的根节点
    }


    /**
     *  左旋转（处理右右情况）
     * @param x
     * @return
     */
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // 执行旋转
        y.left = x;
        x.right = T2;

        // 更新高度
        updateHeight(x);
        updateHeight(y);

        return y; // 返回新的根节点
    }

    /**
     * 插入节点
     * @param key
     */
    public void insert(int key) {
        root = insertNode(root, key);
    }

    private Node insertNode(Node node, int key) {
        // 1. 执行标准BST插入
        if (node == null) {
            return new Node(key);
        }

        if (key < node.key) {
            node.left = insertNode(node.left, key);
        } else if (key > node.key) {
            node.right = insertNode(node.right, key);
        } else {
            // 相同键值不做处理，或根据需求更新节点
            return node;
        }

        // 2. 更新节点高度
        updateHeight(node);

        // 3. 获取平衡因子
        int balance = getBalanceFactor(node);

        // 4. 如果节点失衡，进行旋转调整

        // 左左情况 - 右旋
        if (balance > 1 && getBalanceFactor(node.left) >= 0) {
            return rotateRight(node);
        }

        // 右右情况 - 左旋
        if (balance < -1 && getBalanceFactor(node.right) <= 0) {
            return rotateLeft(node);
        }

        // 左右情况 - 左右双旋
        if (balance > 1 && getBalanceFactor(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // 右左情况 - 右左双旋
        if (balance < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        // 返回未变化的节点引用
        return node;
    }


    /**
     * 查找最小值节点
     * @param node
     * @return
     */
    private Node findMinNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // 删除节点
    public void delete(int key) {
        root = deleteNode(root, key);
    }

    private Node deleteNode(Node root, int key) {
        // 1. 执行标准BST删除
        if (root == null) {
            return root;
        }

        if (key < root.key) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.key) {
            root.right = deleteNode(root.right, key);
        } else {
            // 找到要删除的节点

            // 情况1：叶子节点或只有一个子节点
            if (root.left == null || root.right == null) {
                Node temp = (root.left != null) ? root.left : root.right;

                // 没有子节点
                if (temp == null) {
                    root = null;
                } else {
                    // 一个子节点
                    root = temp;
                }
            } else {
                // 情况2：有两个子节点
                // 找到右子树的最小节点（中序后继）
                Node temp = findMinNode(root.right);

                // 复制中序后继的值到当前节点
                root.key = temp.key;

                // 删除中序后继
                root.right = deleteNode(root.right, temp.key);
            }
        }

        // 如果树只有一个节点，删除后直接返回
        if (root == null) {
            return root;
        }

        // 2. 更新高度
        updateHeight(root);

        // 3. 获取平衡因子
        int balance = getBalanceFactor(root);

        // 4. 进行旋转操作保持平衡

        // 左左情况
        if (balance > 1 && getBalanceFactor(root.left) >= 0) {
            return rotateRight(root);
        }

        // 左右情况
        if (balance > 1 && getBalanceFactor(root.left) < 0) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }

        // 右右情况
        if (balance < -1 && getBalanceFactor(root.right) <= 0) {
            return rotateLeft(root);
        }

        // 右左情况
        if (balance < -1 && getBalanceFactor(root.right) > 0) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;
    }

    /**
     * 查找节点
     * @param key
     * @return
     */
    public boolean search(int key) {
        return searchNode(root, key);
    }

    private boolean searchNode(Node root, int key) {
        if (root == null) {
            return false;
        }

        if (key == root.key) {
            return true;
        }

        if (key < root.key) {
            return searchNode(root.left, key);
        } else {
            return searchNode(root.right, key);
        }
    }




}
