package hot100.linkedList.singleLinkedList;

import base.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 回文链表
 */
public class LC_234_IsPalindrome {

    /**
     *
     * 给你一个单链表的头节点 head ，
     * 请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
     *
     * 示例 1：
     * 输入：head = [1,2,2,1]
     * 输出：true
     */
    public boolean isPalindrome(ListNode head) {
        //1. 把链表值存入数组
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while(cur!=null) {
            list.add(cur.val);
        }

        //2. 双指针查看是否回文
        int left =0;
        int right =list.size()-1;
        while(left<right) {
            //1️⃣如果不等，就返回false
            if(list.get(left)!=list.get(right)) {
                return false;
            }
            //2️⃣继续遍历
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {

        // 测试1：[1,2,2,1] → true
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));
        System.out.println(new LC_234_IsPalindrome().isPalindrome(head1));

        // 测试2：[1,2] → false
        ListNode head2 = new ListNode(1, new ListNode(2));
        System.out.println(new LC_234_IsPalindrome().isPalindrome(head2));

    }


}
