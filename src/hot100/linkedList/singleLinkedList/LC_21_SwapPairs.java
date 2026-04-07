package hot100.linkedList.singleLinkedList;


import base.ListNode;

/**
 *
 * 24.两两交换链表中的节点
 */

public class LC_21_SwapPairs {

    /**
     *
     * 给你一个链表，两两交换其中相邻的节点，
     * 并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
     *
     * 示例 1：
     * 输入：head = [1,2,3,4]
     * 输出：[2,1,4,3]
     */

    public ListNode swapPairs(ListNode head) {

        //1. 定义新链表（虚拟头节点）
        ListNode dummy = new ListNode();
        dummy.next = head;  //在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）
        ListNode curr = dummy;


        //2. 只要后面有两个数，就交换
        while (curr.next != null && curr.next.next != null) {
            // 1. 两个数
            ListNode first = curr.next;
            ListNode second = curr.next.next;

            //2. 交换
            first.next = second.next; //1 --》  3
            second.next = first; // 2 -- 》  1
            curr.next = second; //curr  -- 》  2


            //3. 准备交换下一批
            curr = first;

        }

        //3. 返回结果
        return dummy.next;

    }


    public static void main(String[] args) {
        // 构建链表 1->2->3->4
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        LC_21_SwapPairs solution = new LC_21_SwapPairs();
        ListNode newHead = solution.swapPairs(head);

        // 打印结果 2->1->4->3
        while (newHead != null) {
            System.out.print(newHead.val + " ");
            newHead = newHead.next;
        }
    }


}
