package suanfa_low.search;

public class HashSearch {
    // 哈希表节点类
    static class Node {
        String key;
        int value;
        Node next;
        
        public Node(String key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
    
    // 哈希表类
    static class HashTable {
        private Node[] buckets;
        private int capacity;
        private int size;
        private final float LOAD_FACTOR = 0.75f; // 负载因子阈值
        
        public HashTable(int capacity) {
            this.capacity = capacity;
            this.buckets = new Node[capacity];
            this.size = 0;
        }
        
        // 哈希函数
        private int hash(String key) {
            int hash = 0;
            for (char c : key.toCharArray()) {
                hash = (hash * 31 + c) % capacity;
            }
            return Math.abs(hash);
        }
        
        // 插入键值对
        public void put(String key, int value) {
            if ((float)size / capacity >= LOAD_FACTOR) {
                resize(2 * capacity);
            }
            
            int index = hash(key);
            Node newNode = new Node(key, value);
            
            // 如果桶为空，直接插入
            if (buckets[index] == null) {
                buckets[index] = newNode;
                size++;
                return;
            }
            
            // 处理哈希冲突，使用链地址法
            Node current = buckets[index];
            
            // 检查是否已存在相同的键
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value; // 更新值
                    return;
                }
                if (current.next == null) {
                    break;
                }
                current = current.next;
            }
            
            // 在链表末尾添加新节点
            current.next = newNode;
            size++;
        }
        
        // 查找键对应的值
        public Integer get(String key) {
            int index = hash(key);
            Node current = buckets[index];
            
            // 遍历链表查找匹配的键
            while (current != null) {
                if (current.key.equals(key)) {
                    return current.value;
                }
                current = current.next;
            }
            
            // 未找到
            return null;
        }
        
        // 删除键值对
        public boolean remove(String key) {
            int index = hash(key);
            Node current = buckets[index];
            Node prev = null;
            
            // 查找目标节点
            while (current != null) {
                if (current.key.equals(key)) {
                    break;
                }
                prev = current;
                current = current.next;
            }
            
            // 未找到目标节点
            if (current == null) {
                return false;
            }
            
            // 删除节点
            if (prev == null) {
                buckets[index] = current.next;
            } else {
                prev.next = current.next;
            }
            
            size--;
            return true;
        }
        
        // 扩容并重新哈希
        private void resize(int newCapacity) {
            Node[] oldBuckets = buckets;
            
            // 创建新的哈希表
            buckets = new Node[newCapacity];
            capacity = newCapacity;
            size = 0;
            
            // 重新哈希所有元素
            for (Node bucket : oldBuckets) {
                Node current = bucket;
                while (current != null) {
                    put(current.key, current.value);
                    current = current.next;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(10);
        
        // 插入数据
        hashTable.put("apple", 5);
        hashTable.put("banana", 10);
        hashTable.put("orange", 15);
        hashTable.put("grape", 20);
        
        // 查找数据
        System.out.println("apple: " + hashTable.get("apple"));
        System.out.println("banana: " + hashTable.get("banana"));
        System.out.println("orange: " + hashTable.get("orange"));
        System.out.println("grape: " + hashTable.get("grape"));
        System.out.println("watermelon: " + hashTable.get("watermelon"));
        
        // 删除数据
        hashTable.remove("orange");
        System.out.println("After removing orange: " + hashTable.get("orange"));
    }
}
