package hot100.tree;


import base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 114.二叉树展开为链表
 */

public class LC_114_Flatten {

    /**
     *
     *
     * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     *
     *
     * 展开后的单链表应该同样使用 TreeNode ，
     *
     * 其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     *
     *
     * 示例 1：
     * 输入：root = [1,2,5,3,4,null,6]
     * 输出：[1,null,2,null,3,null,4,null,5,null,6]
     *
     *
     * 示例 2：
     * 输入：root = []
     * 输出：[]
     * 示例 3：
     *
     * 输入：root = [0]
     * 输出：[0]
     *
     *
     * 提示：
     * 树中结点数在范围 [0, 2000] 内
     * -100 <= Node.val <= 100
     *
     * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
     *
     */


    /**
     * 给你二叉树的根结点 root ，
     *
     * 请你将它展开为一个单链表：
     *
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     */

    public void flatten(TreeNode root) {

       //1. 前序遍历放入集合
        List<TreeNode> list = new ArrayList<>();
        preOrder(root,list);

        //2. 遍历成单链表。展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
        for(int i = 0 ; i < list.size() -1; i++){

            //展开后的单链表应该同样使用 TreeNode
            TreeNode curr = list.get(i);
            TreeNode next = list.get(i+1);

            //其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
            curr.left = null;
            curr.right = next;


        }




    }


    /**
     * 前序遍历
     * @param root
     * @param list
     */
    private void preOrder(TreeNode root,List<TreeNode> list){

        //1. 终止条件
        if(root == null){
            return;
        }

        //2. 根左右
        list.add(root);
        preOrder(root.left,list);
        preOrder(root.right,list);



    }


    /**
     * 测试
     * 输入：root = [1,2,5,3,4,null,6]
     */

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(5, null, new TreeNode(6)));

        new LC_114_Flatten().flatten(root);

        while(root != null){

            System.out.print(root.val+" ");
            root = root.right;

        }

    }


}
