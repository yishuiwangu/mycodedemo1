package com.yswg.mycodedemo1.tlambda;

import com.yswg.mycodedemo1.MockData;
import com.yswg.mycodedemo1.model.Employee;
import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TestLambda1 {


    /**
     * 内部类和lambda的简单对比
     */
    @Test
    public void test1() {
        //1.声明一个匿名内部类
        Comparator<Integer> com = new Comparator<Integer>() {
            //比较方法
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        //2.传入排序方法
        TreeSet<Integer> ts = new TreeSet<>(com);

        //3.改写成lambda写法
        Comparator<Integer> lambdaCom = (x, y) -> Integer.compare(x, y);
        TreeSet lambdaTs = new TreeSet(lambdaCom);
    }

    /**
     * 老式过滤-foreach判断过滤
     */
    @Test
    public void test2() {
        List<Employee> emps = MockData.emps;

        System.out.println("-- 员工中年龄大于35的名单 --");
        ArrayList<Employee> emps2 = new ArrayList<>();
        for (Employee e : emps) {
            if (e.getAge() > 35) {
                emps2.add(e);
            }
        }
        for (Employee e : emps2) {
            System.out.println(e.toString());
        }
        System.out.println("--- ---");

        System.out.println("-- 员工中薪水大于6666.66的名单 --");
        ArrayList<Employee> emps3 = new ArrayList<>();
        for (Employee e : emps) {
            if (e.getSalary() >= 6666.66d) {
                emps3.add(e);
            }
        }
        for (Employee e : emps3) {
            System.out.println(e.toString());
        }
        System.out.println("--- ---");
    }


    /**
     * 优化方法一
     * 策略模式
     * 每一个策略都创建一个类
     *
     * @param datas
     * @param myFilterFun1
     * @return
     */
    private List<Employee> filterEmployee(List<Employee> datas, MyFilterFun1<Employee> myFilterFun1) {
        List<Employee> list = new ArrayList<>();
        for (Employee emp : datas) {
            if (myFilterFun1.test(emp)) {
                list.add(emp);
            }
        }
        return list;
    }

    @Test
    public void test3() {
        List<Employee> emps = MockData.emps;
        List<Employee> es1 = this.filterEmployee(emps, new FilterEmpByAge());
        List<Employee> es2 = this.filterEmployee(emps, new FilterEmpBySalary());
        System.out.println("--年龄大于35--");
        es1.forEach(System.out::println);
        System.out.println("--薪水高于6666.66--");
        es2.forEach(System.out::println);

    }

    /**
     * 优化方法二
     * 匿名内部类
     */
    public void test4() {
        List<Employee> emps = MockData.emps;
        List<Employee> es1 = filterEmployee(emps, new MyFilterFun1<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() >= 6666.66;
            }
        });
        List<Employee> es2 = filterEmployee(emps, new MyFilterFun1<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() > 35;
            }
        });
        System.out.println("--年龄大于35--");
        es2.forEach(System.out::println);
        System.out.println("--薪水高于6666.66--");
        es1.forEach(System.out::println);
    }

    /**
     * 优化方式三 lambda
     */
    @Test
    public void test5() {
        List<Employee> emps = MockData.emps;
        List<Employee> es1 = filterEmployee(emps, e -> e.getAge() > 35);
        List<Employee> es2 = filterEmployee(emps, e -> e.getAge() >= 6666.66);
        System.out.println("--年龄大于35--");
        es1.forEach(System.out::println);
        System.out.println("--薪水高于6666.66--");
        es2.forEach(System.out::println);
    }

    /**
     * 优化方法四 stream api + lambda
     */
    @Test
    public void test6() {
        List<Employee> employees = MockData.emps.stream()
                .filter(x -> x.getAge() > 35)
                .peek(System.out::println)
                .collect(Collectors.toList());
    }


}
