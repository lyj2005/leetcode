package dataStructure.hash;

import java.util.BitSet;
import java.util.function.Function;

public class BloomFilter<T> {
    private BitSet bitSet;
    private int size;
    private int numberOfHashFunctions;
    
    // 哈希函数数组
    private Function<T, Integer>[] hashFunctions;
    
    @SuppressWarnings("unchecked")
    public BloomFilter(int size, int numberOfHashFunctions) {
        this.size = size;
        this.numberOfHashFunctions = numberOfHashFunctions;
        this.bitSet = new BitSet(size);
        this.hashFunctions = new Function[numberOfHashFunctions];
        
        // 创建多个哈希函数（这里用简单方式模拟多个哈希函数）
        for (int i = 0; i < numberOfHashFunctions; i++) {
            final int seed = i + 1;
            hashFunctions[i] = item -> Math.abs((item.hashCode() * seed) % size);
        }
    }
    
    // 添加元素
    public void add(T item) {
        for (Function<T, Integer> hashFunction : hashFunctions) {
            int hash = hashFunction.apply(item);
            bitSet.set(hash);
        }
    }
    
    // 查询元素
    public boolean mightContain(T item) {
        for (Function<T, Integer> hashFunction : hashFunctions) {
            int hash = hashFunction.apply(item);
            if (!bitSet.get(hash)) {
                return false; // 如果有一个位是0，则元素一定不存在
            }
        }
        return true; // 所有位都是1，元素可能存在
    }
    
    // 获取当前设置为1的位的数量
    public int getBitCount() {
        return bitSet.cardinality();
    }
    
    // 清空过滤器
    public void clear() {
        bitSet.clear();
    }
    
    // 示例使用
    public static void main(String[] args) {
        // 创建一个大小为1000，使用3个哈希函数的布隆过滤器
        BloomFilter<String> filter = new BloomFilter<>(1000, 3);
        
        // 添加元素
        filter.add("apple");
        filter.add("banana");
        filter.add("orange");
        
        // 测试元素是否存在
        System.out.println("apple 可能存在: " + filter.mightContain("apple"));    // 应该返回 true
        System.out.println("banana 可能存在: " + filter.mightContain("banana"));  // 应该返回 true
        System.out.println("grape 可能存在: " + filter.mightContain("grape"));    // 很可能返回 false
    }
}
