package suanfa_high.greedy;

import java.util.*;

public class ActivitySelection {
    static class Activity {
        int start, finish;
        
        public Activity(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }
        
        @Override
        public String toString() {
            return "(" + start + ", " + finish + ")";
        }
    }
    
    public static List<Activity> selectActivities(Activity[] activities) {
        // 按照结束时间对活动进行排序
        Arrays.sort(activities, Comparator.comparingInt(a -> a.finish));
        
        List<Activity> selected = new ArrayList<>();
        
        // 选择第一个活动
        if (activities.length > 0) {
            selected.add(activities[0]);
        }
        
        // 当前选择的最后一个活动的索引
        int lastSelected = 0;
        
        // 贪心选择剩余活动
        for (int i = 1; i < activities.length; i++) {
            // 如果当前活动的开始时间大于等于上一个选择的活动的结束时间
            if (activities[i].start >= activities[lastSelected].finish) {
                selected.add(activities[i]);
                lastSelected = i;
            }
        }
        
        return selected;
    }
    
    public static void main(String[] args) {
        Activity[] activities = {
            new Activity(1, 4),
            new Activity(3, 5),
            new Activity(0, 6),
            new Activity(5, 7),
            new Activity(3, 9),
            new Activity(5, 9),
            new Activity(6, 10),
            new Activity(8, 11),
            new Activity(8, 12),
            new Activity(2, 14),
            new Activity(12, 16)
        };
        
        List<Activity> selected = selectActivities(activities);
        
        System.out.println("选择的活动数量: " + selected.size());
        System.out.println("选择的活动: " + selected);
    }
}
