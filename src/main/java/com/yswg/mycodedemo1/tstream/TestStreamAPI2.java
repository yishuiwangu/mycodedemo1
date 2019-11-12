package com.yswg.mycodedemo1.tstream;

import com.yswg.mycodedemo1.model.Employee;
import com.yswg.mycodedemo1.model.Status;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
public class TestStreamAPI2 {

    List<Employee> emps = Arrays.asList(
            new Employee(103, "张三", 18, 9999.99, Status.DASHUAIGE),
            new Employee(104, "李四", 19, 6666.66, Status.PINGPINGWUQI),
            new Employee(105, "王五", 28, 3333.33, Status.YOUDIANXIAOSHUAI),
            new Employee(106, "赵六", 28, 7777.77, Status.PINGPINGWUQI),
            new Employee(106, "赵六", 28, 7777.77, Status.DASHUAIGE),
            new Employee(111, "孙六六", 28, 7777.77, Status.DASHUAIGE),
            new Employee(107, "孙七", 38, 1111.55, Status.DASHUAIGE),
            new Employee(108, "周八", 48, 2222.55, Status.PINGPINGWUQI),
            new Employee(109, "吴九", 68, 4444.55, Status.DASHUAIGE),
            new Employee(110, "郑十", 33, 99999.55, Status.PINGPINGWUQI)
    );

     /*
        查

        allMatch——检查是否匹配所有元素
		anyMatch——检查是否至少匹配一个元素
		noneMatch——检查是否没有匹配的元素
		findFirst——返回第一个元素
		findAny——返回当前流中的任意元素
		count——返回流中元素的总个数
		max——返回流中最大值
		min——返回流中最小值
	 */
     @Test
     public void test1(){
         System.out.println("是否都是大帅哥"+emps.stream().allMatch(e -> e.getStatus().equals(Status.DASHUAIGE)));
         System.out.println("是否含有有点小帅"+emps.stream().anyMatch(e -> e.getStatus().equals(Status.YOUDIANXIAOSHUAI)));
         System.out.println("是否不存在丑逼"+emps.stream().noneMatch(e -> e.getStatus().equals(Status.CHOUBI)));
         System.out.println("----------------------------------------");
         System.out.println("第一个员工:"+emps.stream().findFirst().get().toString());
         System.out.println("随机一个员工:"+emps.stream().findAny().get().toString());
         System.out.println("员工数:"+emps.stream().count());
         System.out.println("----------------------------------------");
         System.out.println("1薪水最高的员工:"+emps.stream().sorted((x,y)->Double.compare(y.getSalary(),x.getSalary())).findFirst().get().toString());
         System.out.println("2薪水最高的员工:"+emps.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).findFirst().get().toString());
         System.out.println("3薪水最高的员工:"+emps.stream().max((x, y) -> Double.compare(x.getSalary(), y.getSalary())).get().toString());
         System.out.println("4薪水最高的员工:"+emps.stream().max(Comparator.comparing(Employee::getSalary)).get().toString());


         System.out.println("薪水最低的员工:"+emps.stream().min((x, y) -> Double.compare(x.getSalary(), y.getSalary())).get());
         System.out.println("最低薪水:"+emps.stream().map(Employee::getSalary).min(Double::compare).get());
     }


    /**
     * 规约:reduce
     * 将流中的元素反复结合,得到一个值
     */
    @Test
    public void test2() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //求和
        Integer reduce = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(reduce);
        System.out.println("---------------------");
         Optional<Double> reduce1 = emps.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println("总薪水:" + reduce1.get());
    }


    /**
     * 统计名员工名字里六的个数
     */
    @Test
    public void test3(){
        Optional<Integer> r1 = emps.stream()
                .map(Employee::getName)
                .flatMap(TestStreamAPI::splitStr)
                .map(x -> {
                    if (x.equals('六')) {
                        return 1;
                    } else {
                        return 0;
                    }
                }).reduce(Integer::sum);
        System.out.println("员工名字里六的个数:"+r1.get());
    }


    /**
     * Collectors收集器
     */
    @Test
    public void test4() {
        List<Double> collect = emps.stream().map(Employee::getSalary).collect(Collectors.toList());
        Set<String> collect1 = emps.stream().map(Employee::getName).collect(Collectors.toSet());
        ArrayList<String> collect2 = emps.stream().map(Employee::getName).collect(Collectors.toCollection(ArrayList::new));
        HashSet<String> collect3 = emps.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
    }

    /**
     * 拼接
     */
    @Test
    public void test9(){
        String collect = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.joining("###"));
        System.out.println(collect);
    }

    /**
     * 常用统计
     */
    @Test
    public void test5() {

        //和
        Double sum = emps.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);

        //平均值
        Double avg = emps.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avg);

        //计数
        Long count = emps.stream()
                .collect(Collectors.counting());
        System.out.println(count);

        //最值
        Optional<Employee> collect = emps.stream().collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        Optional<Employee> max = emps.stream().max(Comparator.comparing(Employee::getSalary));
        System.out.println(collect.get());

        System.out.println("--------------------------------------------");

        DoubleSummaryStatistics dss = emps.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(dss);//DoubleSummaryStatistics{count=10, sum=151111.490000, min=1111.550000, average=15111.149000, max=99999.550000}
    }


    /**
     * 分组
     */
    @Test
    public void test6() {
        //按照状态分组
        Map<Status, List<Employee>> collect = emps.stream().collect(Collectors.groupingBy(Employee::getStatus));
        collect.entrySet().forEach(x->{
            System.out.println(x);
        });
    }



    /**
     * 多级分组
     * 根据状态分组员工 -> 再进行低收入高收入普通收入分组
     */
    @Test
    public void test7(){
        Map<Status, Map<String, List<Employee>>> collect = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(x -> {
                    if (x.getSalary() < 8000) {
                        return "低收入";
                    } else if (x.getSalary() > 30000) {
                        return "高收入";
                    } else {
                        return "普通收入";
                    }
                })));
        collect.entrySet().forEach(x->{
            System.out.println(x);
        });
    }


    /**
     * 分区(条件分组)
     */
    @Test
    public void test8(){
        Map<Boolean, List<Employee>> collect = emps.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 6000));
        collect.entrySet().forEach(x->{
            System.out.println(x);
        });
    }
}
