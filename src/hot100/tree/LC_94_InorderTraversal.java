package hot100.tree;

import base.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LC_94_InorderTraversal {

    /**
     *
     *
     * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
     *
     * 示例 1：
     * 输入：root = [1,null,2,3]
     * 输出：[1,3,2]
     */
    public List<Integer> inorderTraversal(TreeNode root) {

        //1. 调用dfs函数
        List<Integer> res = new ArrayList<>();
        dfs(root,res);

        //2. 返回结果
        return res;

    }

    /**
     * 中序遍历函数
     * @param root
     * @param res
     */
    private void dfs(TreeNode root, List<Integer> res) {

        //1. 递归终止条件
        if(root == null) {
            return;
        }

        //2. 左中右遍历
        dfs(root.left,res);
        res.add(root.val);
        dfs(root.right,res);

    }


    public static void main(String[] args) {
        // 构建示例1：[1,null,2,3]
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        LC_94_InorderTraversal s = new LC_94_InorderTraversal();
        System.out.println(s.inorderTraversal(root)); // 输出 [1,3,2]
    }





}
