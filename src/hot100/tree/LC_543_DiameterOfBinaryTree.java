package hot100.tree;


import base.TreeNode;

/**
 * 543.二叉树的直径
 */


public class LC_543_DiameterOfBinaryTree {

    /**
     *
     *给你一棵二叉树的根节点，
     * 返回该树的 直径 。
     *二叉树的 直径 是指树中任意两个节点之间  **最长路径**   的 长度 。
     *
     *
     * 这条路径可能经过也可能不经过根节点 root 。
     * 两节点之间路径的 长度 由它们之间边数表示。
     *
     *
     *
     * 示例 1：
     *
     *
     * 输入：root = [1,2,3,4,5]
     * 1
     * 2 3
     * 4  5  null  null
     * 输出：3
     * 解释：3 ，取路径 [4,2,1,3] 或 [5,2,1,3] 的长度。
     *
     */


    int max =0 ;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return max;
    }


    private int dfs(TreeNode node) {

        //1. 终止条件，node==null
        if(node == null) return 0;

        //2. *****左右节点递归求高度******
        int left = dfs(node.left);
        int right = dfs(node.right);

        //3. 更新直径最大值
        max = Math.max(max,left+right);

        //4. 返回当前节点的高度
        return Math.max(left,right) + 1;



    }


    public static void main(String[] args) {

        /**
         * 输入：root = [1,2,3,4,5]
         * 返回3
         */

        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3));
        System.out.println(new LC_543_DiameterOfBinaryTree().diameterOfBinaryTree(root));

    }



}
