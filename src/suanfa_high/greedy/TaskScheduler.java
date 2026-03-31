package suanfa_high.greedy;

import java.util.*;

class Task {
    int id;
    int deadline;    // 截止时间
    int priority;    // 优先级（值越大优先级越高）
    int processingTime;  // 处理时间
    
    public Task(int id, int deadline, int priority, int processingTime) {
        this.id = id;
        this.deadline = deadline;
        this.priority = priority;
        this.processingTime = processingTime;
    }
    
    @Override
    public String toString() {
        return "Task " + id + " (deadline=" + deadline + ", priority=" + priority + 
               ", processing time=" + processingTime + ")";
    }
}

public class TaskScheduler {
    
    // 任务调度主方法
    public static List<Task> scheduleEDD(List<Task> tasks) {
        // 按截止时间升序，优先级降序，处理时间升序排序
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                if (t1.deadline != t2.deadline) {
                    return Integer.compare(t1.deadline, t2.deadline);
                }
                if (t1.priority != t2.priority) {
                    return Integer.compare(t2.priority, t1.priority);
                }
                return Integer.compare(t1.processingTime, t2.processingTime);
            }
        });
        
        return new ArrayList<>(tasks);
    }
    
    // 计算调度性能指标
    public static void evaluateSchedule(List<Task> scheduledTasks) {
        int currentTime = 0;
        int totalTardiness = 0;
        int tardyTaskCount = 0;
        
        System.out.println("执行顺序:");
        
        for (Task task : scheduledTasks) {
            // 更新当前时间
            currentTime += task.processingTime;
            
            // 计算延迟时间
            int tardiness = Math.max(0, currentTime - task.deadline);
            
            // 输出任务信息和延迟情况
            System.out.println(task + " - 完成时间: " + currentTime + 
                               ", 延迟: " + (tardiness > 0 ? tardiness : "无"));
            
            // 统计总延迟时间和延迟任务数
            totalTardiness += tardiness;
            if (tardiness > 0) {
                tardyTaskCount++;
            }
        }
        
        System.out.println(" 性能评估:");
        System.out.println("总延迟时间: " + totalTardiness);
        System.out.println("平均延迟时间: " + (float)totalTardiness / scheduledTasks.size());
        System.out.println("延迟任务数: " + tardyTaskCount + "/" + scheduledTasks.size());
    }
    
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        
        // 创建任务示例
        tasks.add(new Task(1, 4, 2, 1));  // 任务1: 截止时间4, 优先级2, 处理时间1
        tasks.add(new Task(2, 2, 1, 3));  // 任务2: 截止时间2, 优先级1, 处理时间3
        tasks.add(new Task(3, 3, 3, 2));  // 任务3: 截止时间3, 优先级3, 处理时间2
        tasks.add(new Task(4, 1, 4, 4));  // 任务4: 截止时间1, 优先级4, 处理时间4
        tasks.add(new Task(5, 3, 2, 1));  // 任务5: 截止时间3, 优先级2, 处理时间1
        
        // 调度任务
        List<Task> scheduledTasks = scheduleEDD(tasks);
        
        // 评估调度结果
        evaluateSchedule(scheduledTasks);
    }
}
