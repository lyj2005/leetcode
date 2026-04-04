package hot100.linkedList.singleLinkedList;


import base.ListNode;

/**
 * 19.删除链表的倒数第N个结点
 *
 */
public class LC_19_RemoveNthFromEnd {

    /**
     * 给你一个链表，删除链表的倒数第 n 个结点，
     *
     * 并且返回链表的头结点。
     *
     * 示例 1：
     * 输入：head = [1,2,3,4,5], n = 2
     * 输出：[1,2,3,5]
     */

    public ListNode removeNthFromEnd(ListNode head, int n) {

        //1. 新建链表，快慢指针
        ListNode dummy = new ListNode(0,head);
        ListNode fast = dummy;
        ListNode slow = dummy;

        //2. 找到倒数第n位。使快指针与慢指针天然相差n位
        //1. 快指针先走n位
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        //2. 同时走到快指针到头
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }


        //3. 删除第n位
        slow.next = slow.next.next;

        //4. 返回结果
        return dummy.next;

    }


    // ================== 测试 ==================
    public static void main(String[] args) {
        // 示例 1：[1,2,3,4,5] n=2 → 删除 4，输出 [1,2,3,5]
        ListNode head = new ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(4,
                                        new ListNode(5)))));

        LC_19_RemoveNthFromEnd test = new LC_19_RemoveNthFromEnd();
        ListNode res = test.removeNthFromEnd(head, 2);

        // 打印结果
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }

}
