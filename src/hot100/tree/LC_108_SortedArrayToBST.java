package hot100.tree;


import base.TreeNode;

/**
 *
 * 108.将有序数组转换为二叉搜索树
 *
 */


public class LC_108_SortedArrayToBST {

    /**
     *给你一个整数数组 nums ，其中元素已经按 升序 排列，
     *
     * 请你将其转换为一棵 平衡 二叉搜索树。
     *
     *
     *
     * 示例 1：
     *
     *
     * 输入：nums = [-10,-3,0,5,9]
     * 输出：[0,-3,9,-10,null,5]
     * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
     *
     * 示例 2：
     *
     *
     * 输入：nums = [1,3]
     * 输出：[3,1]
     * 解释：[1,null,3] 和 [3,1] 都是高度平衡二叉搜索树。
     *
     *
     * 提示：
     *
     * 1 <= nums.length <= 104
     * -104 <= nums[i] <= 104
     * nums 按 严格递增 顺序排列
     *
     *
     */


    public TreeNode sortedArrayToBST(int[] nums) {

        //递归返回
        return build(nums,0,nums.length-1);

    }


    /**
     * 构建函数
     * @param nums
     * @param left
     * @param right
     * @return
     */

    public TreeNode build(int[] nums, int left, int right) {

        //1. 终止条件
        if(left>right) return null;

        //2. 取中间为根节点
        int mid = (left+right)/2;
        TreeNode root = new TreeNode(nums[mid]);


        //3. 递归构建左右节点
        root.left = build(nums, left, mid-1);
        root.right = build(nums, mid+1, right);

        //4. 返回结果
        return root;

    }


    public static void main(String[] args) {
        int[] nums = {-10,-3,0,5,9};
        TreeNode root = new LC_108_SortedArrayToBST().sortedArrayToBST(nums);
        print(root);

    }

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
