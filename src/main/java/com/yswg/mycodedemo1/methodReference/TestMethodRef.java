package com.yswg.mycodedemo1.methodReference;

import com.yswg.mycodedemo1.model.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 *  Lambda简化写法
 *  方法引用->如果Lambda体中内容有方法已经实现,可以使用方法引用
 *  语法:   1. 对象::实例方法名
 *          2. 类:: 静态方法名
 *          3. 类:: 实例方法名
 *
 *  构造器引用
 *   ClassName::new
 */
public class TestMethodRef {


    //对象::实例方法名
    @Test
    public void test1() {
        //方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致
        Consumer<String> con = x -> System.out.println(x);

        PrintStream out = System.out;
        out.println("00");
        Consumer<String> con2 = System.out::println;
        con.accept("zzz");
        con2.accept("ccc");
    }


    //对象::实例方法名
    @Test
    public void test2() {
        Employee emp = Employee.builder().age(1).name("方法引用").salary(2000).build();
        Supplier<String> sup1 = () -> emp.getName();
        Supplier<String> sup2 = emp::getName;
        System.out.println(sup1.get());
        System.out.println(sup2.get());
    }


    /**
     * 类::静态方法名
     */
    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        Comparator<Integer> com2 = Integer::compare;

        BiFunction<Double,Double,Double> fun =(x, y)->Math.max(x,y);
        BiFunction<Double,Double,Double> fun2 =Math::max;
    }


    /**
     * 类:: 实例方法名
     * 特殊:若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
     */
    @Test
    public void test4(){
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);
        BiPredicate<String, String> bp2 = String::equals;
        Function<Employee,String> fun = e->e.getName();
        Function<Employee,String> fun2 = Employee::getName;
        String apply = fun2.apply(new Employee());
        System.out.println(apply);
    }


    /**
     * 构造器引用
     */
    @Test
    public void test5(){
        Supplier<Employee> sup =()-> new Employee();
        Supplier<Employee> sup2 =Employee::new;

        Function<Integer,Employee> fun1 = x -> new Employee(x);

        //需要调用的构造器的参数列表要和函数式接口中抽象方法的参数列表一致
        Function<Integer,Employee> fun2 = Employee::new;
        Employee apply2 = fun2.apply(1);

        BiFunction<Integer,Integer,Employee> bigFun = Employee::new;
        Employee apply = bigFun.apply(1, 2);
        System.out.println(apply.toString());

    }

    /**
     * 数组引用
     */
    @Test
    public void test6(){
        Function<Integer, String[]> fun = (args) -> new String[args];
        String[] strs = fun.apply(10);
        System.out.println(strs.length);

        Function<Integer, String[]> fun2 = String[]::new;
        Function<Integer, String[]> fun3 = x -> new String[x];
        System.out.println(fun2.apply(30).length);
        System.out.println(fun3.apply(30).length);


    }
}
