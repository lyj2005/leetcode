package dataStructure.tree;

public class MinHeap {
    private int[] heap;
    private int size;
    private int capacity;

    // 构造函数
    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    // 获取父节点索引
    private int parent(int i) {
        return (i - 1) / 2;
    }

    // 获取左子节点索引
    private int leftChild(int i) {
        return 2 * i + 1;
    }

    // 获取右子节点索引
    private int rightChild(int i) {
        return 2 * i + 2;
    }

    // 交换两个节点
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // 插入元素
    public void insert(int key) {
        if (size == capacity) {
            System.out.println("堆已满，无法插入");
            return;
        }

        // 先将新元素插入到堆的末尾
        heap[size] = key;
        int current = size;
        size++;

        // 上浮操作：将元素向上移动到合适位置
        while (current > 0 && heap[current] < heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    // 获取最小元素（不删除）
    public int peek() {
        if (size <= 0) {
            System.out.println("堆为空");
            return -1;
        }
        return heap[0];
    }

    // 删除并返回最小元素
    public int extractMin() {
        if (size <= 0) {
            System.out.println("堆为空");
            return -1;
        }
        if (size == 1) {
            size--;
            return heap[0];
        }

        // 存储根节点（最小值）
        int root = heap[0];
        
        // 将最后一个元素放到根位置
        heap[0] = heap[size - 1];
        size--;
        
        // 下沉操作：将根元素向下移动到合适位置
        heapifyDown(0);

        return root;
    }

    // 下沉操作
    private void heapifyDown(int i) {
        int smallest = i;
        int left = leftChild(i);
        int right = rightChild(i);

        // 找出当前节点、左子节点和右子节点中的最小值
        if (left < size && heap[left] < heap[smallest]) {
            smallest = left;
        }

        if (right < size && heap[right] < heap[smallest]) {
            smallest = right;
        }

        // 如果最小值不是当前节点，则交换并继续下沉
        if (smallest != i) {
            swap(i, smallest);
            heapifyDown(smallest);
        }
    }

    // 打印堆
    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(10);
        
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(8);
        minHeap.insert(1);
        minHeap.insert(10);
        
        System.out.println("构建的堆：");
        minHeap.printHeap();
        
        System.out.println("最小元素：" + minHeap.peek());
        
        System.out.println("提取最小元素：" + minHeap.extractMin());
        System.out.println("提取后的堆：");
        minHeap.printHeap();
    }
}
