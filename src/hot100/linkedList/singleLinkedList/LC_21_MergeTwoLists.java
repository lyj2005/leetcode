package hot100.linkedList.singleLinkedList;


import base.ListNode;

/**
 *
 *21.合并两个有序链表
 */
public class LC_21_MergeTwoLists {

    /**
     *将两个升序链表合并为一个新的 升序 链表并返回。
     * 新链表是通过拼接给定的两个链表的所有节点组成的。
     *
     * 示例 1：
     * 输入：l1 = [1,2,4], l2 = [1,3,4]
     * 输出：[1,1,2,3,4,4]
     *
     */

    public ListNode mergeTwoLists(ListNode l1,ListNode l2) {

        //1. 新建链表（虚拟头节点）
        ListNode dummy = new ListNode();
        ListNode curr = dummy;


        //2. 遍历拼接，谁小谁先接
        while(l1!=null && l2!=null) {

            //1. l1小
            if(l1.val<= l2.val) {
                curr.next =l1;
                l1 = l1.next;
            }

            //2. l2小
            else {
                curr.next =l2;
                l2 = l2.next;
            }

            //3. curr移动
            curr = curr.next;

        }

        //3. 拼接剩余节点
        curr.next = l1==null?l2:l1;

        //4. 返回结果
        return dummy.next;

    }


    public static void main(String[] args) {
        //1. 构造l1,l2
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode l2 = new ListNode(2, new ListNode(3, new ListNode(4)));

        //2. 调用测试方法
        LC_21_MergeTwoLists test = new LC_21_MergeTwoLists();
        ListNode res = test.mergeTwoLists(l1, l2);

        //3. 打印结果
        while(res !=null) {
            System.out.println(res.val+" ");
            res = res.next;
        }

    }


}
