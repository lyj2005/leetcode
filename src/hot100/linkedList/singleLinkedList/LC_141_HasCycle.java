package hot100.linkedList.singleLinkedList;

import base.ListNode;

/**
 * 环形链表
 */
public class LC_141_HasCycle {

    /**
     *
     *
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     *
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
     * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
     *
     * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
     *
     * 示例 1：
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：true
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     *
     *
     * 如何判断环
     */

    public boolean hasCycle(ListNode head) {


        //1. 定义双指针
        ListNode slow =head;
        ListNode fast = head;

        //2. 遍历循环，慢指针走一步，快指针走两步，如果相遇，则有环
        while(fast!=null&&fast.next !=null) {//条件：指针和指针下一个不能为空
            //1️⃣走
            slow = slow.next;
            fast =fast.next.next;

            //2️⃣判断相遇
            if(slow ==fast) {
                return true;
            }

        }

        //3. 返回结果,如果不相遇
        return false;

    }

    public static void main(String[] args) {
        LC_141_HasCycle solution =  new LC_141_HasCycle();

        // 测试用例1：无环链表 1->2->3->4
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        System.out.println("测试用例1（无环）：" + solution.hasCycle(head1)); // 预期 false

        // 测试用例2：有环链表 1->2->3->4->2
        ListNode head2 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        head2.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2; // 构成环
        System.out.println("测试用例2（有环）：" + solution.hasCycle(head2)); // 预期 true

        // 测试用例3：单个节点，无环
        ListNode head3 = new ListNode(1);
        System.out.println("测试用例3（单节点）：" + solution.hasCycle(head3)); // 预期 false
    }





}
