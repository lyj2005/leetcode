package dataStructure.tree;

/**
* 红黑树的Java实现
*/
public class RedBlackTree {
// 颜色常量
private static final boolean RED = true;
private static final boolean BLACK = false;

// 树节点定义
class Node {
int key; // 节点值
Node left; // 左子节点
Node right; // 右子节点
Node parent; // 父节点
boolean color; // 节点颜色（true为红，false为黑）

Node(int key) {
this.key = key;
this.color = RED; // 新节点默认为红色
}
}

private Node root; // 根节点
private Node NIL; // 哨兵节点（表示叶子节点）

// 构造函数
public RedBlackTree() {
NIL = new Node(0);
NIL.color = BLACK;
NIL.left = null;
NIL.right = null;
root = NIL;
}

// 获取节点颜色，空节点视为黑色
private boolean colorOf(Node node) {
return node == NIL ? BLACK : node.color;
}

// 判断节点是否为红色
private boolean isRed(Node node) {
return colorOf(node) == RED;
}

// 判断节点是否为黑色
private boolean isBlack(Node node) {
return colorOf(node) == BLACK;
}

// 设置节点颜色
private void setColor(Node node, boolean color) {
if (node != NIL) {
node.color = color;
}
}

// 左旋操作
private void leftRotate(Node x) {
Node y = x.right; // 设置y为x的右子节点

// 将y的左子树设为x的右子树
x.right = y.left;
if (y.left != NIL) {
y.left.parent = x;
}

// 设置y的父节点
y.parent = x.parent;

// 设置x的父节点关系
if (x.parent == null) {
root = y;
} else if (x == x.parent.left) {
x.parent.left = y;
} else {
x.parent.right = y;
}

// 完成旋转
y.left = x;
x.parent = y;
}

// 右旋操作
private void rightRotate(Node y) {
Node x = y.left; // 设置x为y的左子节点

// 将x的右子树设为y的左子树
y.left = x.right;
if (x.right != NIL) {
x.right.parent = y;
}

// 设置x的父节点
x.parent = y.parent;

// 设置y的父节点关系
if (y.parent == null) {
root = x;
} else if (y == y.parent.left) {
y.parent.left = x;
} else {
y.parent.right = x;
}

// 完成旋转
x.right = y;
y.parent = x;
}

// 插入节点
public void insert(int key) {
Node node = new Node(key);
node.left = NIL;
node.right = NIL;

Node y = null;
Node x = this.root;

// 找到插入位置
while (x != NIL) {
y = x;
if (node.key < x.key) {
x = x.left;
} else {
x = x.right;
}
}

// 设置node的父节点
node.parent = y;

// 设置node为根节点或者是父节点的左/右孩子
if (y == null) {
root = node;
} else if (node.key < y.key) {
y.left = node;
} else {
y.right = node;
}

// 如果新节点是根节点，则将其设为黑色
if (node.parent == null) {
node.color = BLACK;
return;
}

// 如果新节点的祖父节点为空，则不需要修复
if (node.parent.parent == null) {
return;
}

// 修复红黑树性质
fixInsert(node);
}

// 修复插入后的红黑树性质
private void fixInsert(Node k) {
Node u;
while (isRed(k.parent)) {
// 父节点是祖父节点的右子节点
if (k.parent == k.parent.parent.right) {
u = k.parent.parent.left; // 叔叔节点
// 情况1：叔叔节点是红色
if (isRed(u)) {
u.color = BLACK;
k.parent.color = BLACK;
k.parent.parent.color = RED;
k = k.parent.parent;
} else {
// 情况2：叔叔节点是黑色，当前节点是左子节点
if (k == k.parent.left) {
k = k.parent;
rightRotate(k);
}
// 情况3：叔叔节点是黑色，当前节点是右子节点
k.parent.color = BLACK;
k.parent.parent.color = RED;
leftRotate(k.parent.parent);
}
} else { // 父节点是祖父节点的左子节点
u = k.parent.parent.right; // 叔叔节点
// 情况1：叔叔节点是红色
if (isRed(u)) {
u.color = BLACK;
k.parent.color = BLACK;
k.parent.parent.color = RED;
k = k.parent.parent;
} else {
// 情况2：叔叔节点是黑色，当前节点是右子节点
if (k == k.parent.right) {
k = k.parent;
leftRotate(k);
}
// 情况3：叔叔节点是黑色，当前节点是左子节点
k.parent.color = BLACK;
k.parent.parent.color = RED;
rightRotate(k.parent.parent);
}
}

// 如果k是根节点，退出循环
if (k == root) {
break;
}
}
// 确保根节点是黑色
root.color = BLACK;
}

// 查找最小值节点
private Node minimum(Node node) {
while (node.left != NIL) {
node = node.left;
}
return node;
}

// 用v替换u
private void transplant(Node u, Node v) {
if (u.parent == null) {
root = v;
} else if (u == u.parent.left) {
u.parent.left = v;
} else {
u.parent.right = v;
}
v.parent = u.parent;
}

// 删除节点
public void delete(int key) {
Node z = search(root, key);
if (z == NIL) {
System.out.println("Key " + key + " not found in the tree");
return;
}

Node y = z;
Node x;
boolean yOriginalColor = y.color;

if (z.left == NIL) {
x = z.right;
transplant(z, z.right);
} else if (z.right == NIL) {
x = z.left;
transplant(z, z.left);
} else {
y = minimum(z.right);
yOriginalColor = y.color;
x = y.right;

if (y.parent == z) {
x.parent = y;
} else {
transplant(y, y.right);
y.right = z.right;
y.right.parent = y;
}

transplant(z, y);
y.left = z.left;
y.left.parent = y;
y.color = z.color;
}

// 如果原始颜色是黑色，需要修复
if (yOriginalColor == BLACK) {
fixDelete(x);
}
}

// 修复删除后的红黑树性质
private void fixDelete(Node x) {
while (x != root && isBlack(x)) {
if (x == x.parent.left) {
Node w = x.parent.right;

// 情况1：兄弟节点是红色
if (isRed(w)) {
w.color = BLACK;
x.parent.color = RED;
leftRotate(x.parent);
w = x.parent.right;
}

// 情况2：兄弟节点是黑色，且两个子节点都是黑色
if (isBlack(w.left) && isBlack(w.right)) {
w.color = RED;
x = x.parent;
} else {
// 情况3：兄弟节点是黑色，左子节点是红色，右子节点是黑色
if (isBlack(w.right)) {
w.left.color = BLACK;
w.color = RED;
rightRotate(w);
w = x.parent.right;
}

// 情况4：兄弟节点是黑色，右子节点是红色
w.color = x.parent.color;
x.parent.color = BLACK;
w.right.color = BLACK;
leftRotate(x.parent);
x = root;
}
} else { // x是右子节点
Node w = x.parent.left;

// 情况1：兄弟节点是红色
if (isRed(w)) {
w.color = BLACK;
x.parent.color = RED;
rightRotate(x.parent);
w = x.parent.left;
}

// 情况2：兄弟节点是黑色，且两个子节点都是黑色
if (isBlack(w.right) && isBlack(w.left)) {
w.color = RED;
x = x.parent;
} else {
// 情况3：兄弟节点是黑色，右子节点是红色，左子节点是黑色
if (isBlack(w.left)) {
w.right.color = BLACK;
w.color = RED;
leftRotate(w);
w = x.parent.left;
}

// 情况4：兄弟节点是黑色，左子节点是红色
w.color = x.parent.color;
x.parent.color = BLACK;
w.left.color = BLACK;
rightRotate(x.parent);
x = root;
}
}
}
// 将x设为黑色
x.color = BLACK;
}

// 查找节点
private Node search(Node root, int key) {
if (root == NIL || key == root.key) {
return root;
}

if (key < root.key) {
return search(root.left, key);
} else {
return search(root.right, key);
}
}

// 公开的查找方法
public boolean contains(int key) {
return search(root, key) != NIL;
}

// 中序遍历
public void inorderTraversal() {
inorder(root);
System.out.println();
}

private void inorder(Node node) {
if (node != NIL) {
inorder(node.left);
System.out.print(node.key +
(node.color == RED ? "(R) " : "(B) "));
inorder(node.right);
}
}

// 获取树的黑高
public int blackHeight() {
return calculateBlackHeight(root);
}

private int calculateBlackHeight(Node node) {
if (node == NIL) {
return 0;
}

int leftHeight = calculateBlackHeight(node.left);
int rightHeight = calculateBlackHeight(node.right);

// 验证左右子树黑高是否相同
if (leftHeight != rightHeight) {
throw new RuntimeException("Red-Black Tree property violation: Unequal black heights");
}

// 计算当前节点到叶子节点的黑色节点数
return isBlack(node) ? leftHeight + 1 : leftHeight;
}
}
