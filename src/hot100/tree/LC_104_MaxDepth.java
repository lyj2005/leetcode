package hot100.tree;


import base.TreeNode;

/**
 * 104.二叉树的最大深度
 */


public class LC_104_MaxDepth {

    /**
     *
     * 给定一个二叉树 root ，返回其最大深度。
     * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
     *
     *
     *
     * 示例 1：
     *
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：3
     *
     * 3
     * 9  20
     *null,null,15,7
     */

    public int maxDepth(TreeNode root) {

        //1. 递归终止条件
        if(root == null) {
            return 0;
        }

        //2. 左右递归
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        //3. 返回最大深度
        return Math.max(left, right) + 1;

    }


    public static void main(String[] args) {
        // 构造树 [3,9,20,null,null,15,7]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        LC_104_MaxDepth s = new LC_104_MaxDepth();
        System.out.println(s.maxDepth(root)); // 输出 3
    }
    }

