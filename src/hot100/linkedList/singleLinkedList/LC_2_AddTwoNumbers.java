package hot100.linkedList.singleLinkedList;


import base.ListNode;

/**
 *2.两数相加
 *
 */

public class LC_2_AddTwoNumbers {

    /**
     *
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     *
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * 示例 1：
     *
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.

     */

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        //1.新建链表（虚拟头节点）
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        int carry = 0;//进位0/1

        //2. 遍历求解,只要还有东西，就继续运算
        while(l1!=null || l2!=null || carry!=0) {

            //1. 取当前位的值，没有即是0
            int val1 = l1==null?0:l1.val;
            int val2 = l2==null?0:l2.val;

            //2.求和
            int sum = val1 + val2 +carry;
            carry = sum/10;
            int num = sum%10;//当前位结果

            //3.拼接
            cur.next =new ListNode(num);

            //4. 后移
            cur = cur.next;
            if(l1!=null) l1= l1.next;
            if(l2!=null) l2 = l2.next;


        }


        //3. 返回结果
        return dummy.next;

    }


    // ===================== 测试 main =====================
    public static void main(String[] args) {
        // 示例 1：l1 = [2,4,3] 代表 342
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        // l2 = [5,6,4] 代表 465
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

        LC_2_AddTwoNumbers test = new LC_2_AddTwoNumbers();
        ListNode res = test.addTwoNumbers(l1, l2);

        // 打印结果：7 0 8
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }

}
