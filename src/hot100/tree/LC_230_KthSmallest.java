package hot100.tree;

import base.TreeNode;

/**
 * 230.二叉搜索树中第K小的元素
 *
 */

public class LC_230_KthSmallest {

    /**
     *
     * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，
     *
     * 请你设计一个算法查找其中第 k 小的元素（k 从 1 开始计数）。
     *
     *
     *
     * 示例 1：
     * 输入：root = [3,1,4,null,2], k = 1
     * 输出：1
     * 示例 2：
     *
     *
     * 输入：root = [5,3,6,2,4,null,null,1], k = 3
     * 输出：3
     *
     *
     * 提示：
     * 树中的节点数为 n 。
     * 1 <= k <= n <= 104
     * 0 <= Node.val <= 104
     *
     *
     * 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
     */


    int count = 0;
    int res = 0;

    public int kthSmallest(TreeNode root, int k) {

        inOrder(root,k);
        return res;

    }

    /**
     * 中序遍历方法   ，利用BST的性质 ，    天然：左到右，从小到大
     * @param node
     * @param k
     */
    private void inOrder(TreeNode node, int k) {

        //0. 终止条件
        if (node == null) {
            return;
        }

        //1. 左子树
        inOrder(node.left,k);

        //2. 计数递归次数，如果k次了，赋值结果,并且要终止！！！！
        count++;
        if (count == k) {
            res = node.val;
            return;
        }

        //3. 右子树
        inOrder(node.right,k);

    }

    /**
     * 测试
     */

    public static void main(String[] args) {
        //输入：root = [3,1,4,null,2], k = 1
        int k = 1;
        TreeNode root = new TreeNode(3, new TreeNode(1, null, new TreeNode(2)), new TreeNode(4));
        System.out.println(new LC_230_KthSmallest().kthSmallest(root,k));
    }

}
