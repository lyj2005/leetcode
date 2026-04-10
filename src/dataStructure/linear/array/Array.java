package dataStructure.linear.array;

public class Array {


    public void ans() {
        // 声明和初始化
        int[] numbers = new int[5]; // 创建一个长度为5的int数组，默认值都是0
        int[] primes = {2, 3, 5, 7, 11}; // 直接使用初始值创建数组

        // 访问元素
        int firstPrime = primes[0]; // 得到2

        // 更新元素
        numbers[0] = 10;



        // 获取数组长度
        int length = numbers.length;


        // 遍历数组
        for (int i = 0; i < primes.length; i++) {
            System.out.println(primes[i]);
        }

        // 使用增强for循环遍历
        for (int prime : primes) {
            System.out.println(prime);
        }



    }

}
