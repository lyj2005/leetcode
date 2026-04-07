package hot100.tree;


import base.TreeNode;

/**
 * 226.翻转二叉树
 */
public class LC_226_InvertTree {

    /**
     *
     * 给你一棵二叉树的根节点 root ，
     * 翻转这棵二叉树，并返回其根节点。
     *
     * 示例 1：
     *
     * 输入：root = [4,2,7,1,3,6,9]
     * 输出：[4,7,2,9,6,3,1]
     *
     * 4
     * 2 7
     * 1,3,6,9
     *
     *
     *
     * 4
     * 7 2
     * 9,6,3,1
     *
     */


    public TreeNode invertTree(TreeNode root) {

        //1. 递归终止条件
        if (root == null) {
            return null;
        }




        //2. 交换节点
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;


        //3. 左右递归
        invertTree(root.left);
        invertTree(root.right);


        //4. 返回结果
        return root;


    }


    public static void main(String[] args) {
        LC_226_InvertTree s = new LC_226_InvertTree();

        // 示例1
        TreeNode root1 = new TreeNode(4,
                new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(7, new TreeNode(6), new TreeNode(9))
        );
        System.out.println("示例1翻转前：");
        print(root1);
        System.out.println();
        System.out.println("示例1翻转后：");
        print(s.invertTree(root1));

    }


    // 超级简单打印二叉树（前序遍历）
//    public static void print(TreeNode root) {
//        if (root == null) return;
//        System.out.print(root.val + " ");
//        print(root.left);
//        print(root.right);
//    }

    // 层序遍历打印二叉树（顺序和LeetCode示例完全一致）
    public static void print(TreeNode root) {

        if (root == null) {
            System.out.println("[]");
            return;
        }

        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        System.out.println();
    }






}
