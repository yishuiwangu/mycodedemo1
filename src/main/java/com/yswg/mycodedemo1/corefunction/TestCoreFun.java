package com.yswg.mycodedemo1.corefunction;

import com.yswg.mycodedemo1.model.Employee;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 内置的四大核心函数式接口
 * Consumer<T>: 消费型接口 void accept(T t);
 * Supplier<T>: 供给型接口 T get();
 * Function<T,R>: 函数型接口  R apply(T t);
 * Predicate<T>: 断言型接口 boolean test(T t);
 */
public class TestCoreFun {


    //Consumer<T>: 消费型接口 void accept(T t);
    @Test
    public void test1(){
        shopping(1000,x-> System.out.println("这个购物花了"+x+"元"));
    }

    private void shopping(double money, Consumer<Double> con){
        con.accept(money);
    }

    //Supplier<T>: 供给型接口 T get();
    @Test
    public void test2(){
        List<String> strList = getStrList(10, () -> UUID.randomUUID().toString().substring(0, 20));
        strList.forEach(System.out::println);
    }

    //生产num个随机数
    private List<String> getStrList(int num, Supplier<String> sup){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(sup.get());
        }
        return list;
    }

    //Function<T,R>: 函数型接口  R apply(T t);
    @Test
    public void test3(){
        String str = "ZHE SHI YI GE JAVA8-LAMBDA XIAO DEMO";
        System.out.println(strHandle(str,x->x.toLowerCase().replaceAll(" ","")));
    }

    //对一个字符进行 一些操作 返回结果
    private String strHandle(String str, Function<String,String> fun){
        String apply = fun.apply(str);
        return apply;
    }

    //Predicate<T>: 断言型接口 boolean test(T t);
    @Test
    public void test4(){
        //薪水大于8000
        List<Employee> list = filterEmpByCondition(emps, x -> x.getSalary() > 8000);
        list.forEach(System.out::println);
        System.out.println("-------------------------------------------------------------------");
        //类似于filter
        emps.stream().filter(x -> x.getSalary() > 8000).forEach(System.out::println);
    }

    //根据条件进行对员工集进行筛选
    private List<Employee> filterEmpByCondition(List<Employee> emps, Predicate<Employee> pre){
        List<Employee> result = new ArrayList<>();
        for (Employee emp: emps) {
            if(pre.test(emp)){
                result.add(emp);
            }
        }
        return result;
    }
    List<Employee> emps = Arrays.asList(
            new Employee(103, "张三", 18, 9999.99),
            new Employee(104, "李四", 59, 6666.66),
            new Employee(105, "王五", 48, 3333.33),
            new Employee(106, "赵六", 18, 7777.77),
            new Employee(107, "孙七", 38, 1111.55),
            new Employee(108, "周八", 48, 2222.55),
            new Employee(109, "吴九", 68, 4444.55),
            new Employee(110, "郑十", 33, 99999.55)
    );


    /*******自定义排序***************/
    private static <T> void diySort(List<T> data,Comparator<T> comparator) {
        data.sort(comparator);
    }
    @Test
    public void diySort(){
        diySort(emps, (e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()));
        emps.forEach(x->{
            System.out.println(x.toString());
        });
    }

}
