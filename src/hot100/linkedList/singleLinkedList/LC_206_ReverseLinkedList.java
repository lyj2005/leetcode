package hot100.linkedList.singleLinkedList;

import base.ListNode;

/**
 * 206. 反转链表
 * 方法1：迭代（推荐面试写）
 * 方法2：递归
 */
public class LC_206_ReverseLinkedList {

    /**
     * 迭代写法：双指针
     * 最稳、最好理解、面试首选
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            // 先保存下一个节点
            ListNode next = curr.next;
            // 反转当前节点指向
            curr.next = prev;
            // 指针后移
            prev = curr;
            curr = next;
        }
        // 最终 prev 就是新头
        return prev;
    }



    /**
     * 递归写法
     * 思路：把后面先反转，再处理当前节点
     */
    public ListNode reverseListRecursive(ListNode head) {
        // 递归终止条件
        if (head == null || head.next == null) {
            return head;
        }

        // 递归去反转后面的链表
        ListNode newHead = reverseListRecursive(head.next);

        // 反转当前节点和下一个节点的指向
        head.next.next = head;
        head.next = null;

        return newHead;
    }


    // 测试方法
    public static void test() {
        // 1. 构建测试链表 1->2->3->4->5
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        LC_206_ReverseLinkedList solution = new LC_206_ReverseLinkedList();

        // 2. 执行算法
        ListNode newHead = solution.reverseList(head);

        // 3. 打印结果
        System.out.println("=== 反转链表测试结果 ===");
        printList(newHead);
    }


    // 【新增】通用打印工具（也可以抽去base包）
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println("\n========================");
    }


}