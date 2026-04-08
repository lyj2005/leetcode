package hot100.tree;


import base.TreeNode;

/**
 * 101.对称二叉树
 */


public class LC_101_IsSymmetric {

    /**
     * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
     *
     * 示例 1：
     *
     * 输入：root = [1,2,2,3,4,4,3]
     * 输出：true
     *
     * 1
     * 2 2
     * 3 4 4 3
     */

    public boolean isSymmetric(TreeNode root) {

        //1. 根节点为空，对称
        if(root == null) return true;

        //2. 再考虑左右节点情况
        return isMirror(root.left, root.right);


    }

    /**
     * 镜像递归方法
     * @param left
     * @param right
     * @return
     */

    public boolean isMirror(TreeNode left, TreeNode right) {

        //1. 都为空，对称
        if(left == null && right == null) return true;

        //2. 一个为空，不对称
        if(left == null || right == null) return false;

        //3. 值相等，继续镜像递归
        return left.val == right.val &&
                isMirror(left.left, right.right) &&
                isMirror(left.right, right.left);

    }


    public static void main(String[] args) {
        // 示例1：对称 → true
        /**
         * 示例 1：
         *
         *
         * 输入：root = [1,2,2,3,4,4,3]
         * 输出：true
         */
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4))
                , new TreeNode(2, new TreeNode(4), new TreeNode(3)));
        System.out.println(new LC_101_IsSymmetric().isSymmetric(root));

        // 示例2：不对称 → false
        TreeNode root2 = new TreeNode(1,
                new TreeNode(2, null, new TreeNode(3)),
                new TreeNode(2, null, new TreeNode(3))
        );
        System.out.println(new LC_101_IsSymmetric().isSymmetric(root2)); // false
    }



}
