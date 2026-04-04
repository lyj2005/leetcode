package hot100.linkedList.singleLinkedList;


import base.ListNode;

/**
 * 环形链表||
 */
public class LC_142_DetectCycle {


    /**
     * 给定一个链表的头节点  head ，
     * 返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     *
     * 示例 1：
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：返回索引为 1 的链表节点
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     */

    public ListNode detectCycle(ListNode head) {

        //1. 定义快慢指针
        ListNode slow =head;
        ListNode fast =head;


        //2. 遍历循环（检查是否有环，找到环点）
        while(fast!=null && fast.next !=null) {
            //1. 移动双指针
            slow  = slow.next;
            fast = fast.next.next;

            //2. 检查是否有环
            if(slow == fast) {//如果有环，根据数学关系：头节点和另外一个节点必定相遇
                ListNode ptr = head;

                while(ptr != slow) {//每次一步
                    ptr = ptr.next;
                    slow = slow.next;
                }

                return ptr;
            }


        }

        //3. 返回结果
        return null;

    }

    // ====================== 测试 main 方法 ======================
    public static void main(String[] args) {
        // 1. 创建示例链表：3 -> 2 -> 0 -> -4
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);

        // 2. 连接成链表
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        // 3. 【造环】让 -4 指向 node2（索引 1），形成环
        node4.next = node2;

        // 4. 调用你的算法
        LC_142_DetectCycle test = new LC_142_DetectCycle();
        ListNode result = test.detectCycle(node1);

        // 5. 输出结果
        if (result != null) {
            System.out.println("入环点的值是：" + result.val);
            System.out.println("符合预期：应该返回 2");
        } else {
            System.out.println("无环");
        }
    }


}
