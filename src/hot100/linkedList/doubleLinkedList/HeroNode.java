package hot100.linkedList.doubleLinkedList;

// 定义HeroNode ， 每个HeroNode 对象就是一个节点
class HeroNode {
public int no;
public String name;
public String nickname;
public HeroNode next; // 指向下一个节点, 默认为null
public HeroNode pre; // 指向前一个节点, 默认为null
// 构造器

public HeroNode(int no, String name, String nickname) {
this.no = no;
this.name = name;
this.nickname = nickname;
}

// 为了显示方法，我们重新toString
@Override
public String toString() {
return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
}

}