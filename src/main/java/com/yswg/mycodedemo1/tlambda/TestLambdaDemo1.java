package com.yswg.mycodedemo1.tlambda;

import com.yswg.mycodedemo1.model.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.LongBinaryOperator;

/**
 * 举例子
 */
public class TestLambdaDemo1 {

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

    /**
     * 1.调用Collection.sort()方法 通过定制排序比较俩个employee(比较顺序:年龄(ASC),薪水(DESC))
     */
    @Test
    public void test1(){
        Collections.sort(emps,(e1, e2)->{
            if(e1.getAge() == e2.getAge() ){
                return Double.compare(e2.getSalary(),e1.getSalary());
            }else {
                return Integer.compare(e1.getAge(),e2.getAge());
            }
        });
        emps.forEach(System.out::println);
    }

    /**
     * 2.声明函数式接口声明一个类 编写方法使用接口作为参数,将字符串转换成大写并返回
     */
    @Test
    public void test2(){
        String str = "Wo Shi Dai Ming Xing";
        System.out.println(handleStr(str,x->x.toUpperCase()));
    }

    /**
     * 声明一个处理字符串的方法
     * @return
     */
    private String handleStr(String strValue, Function<String,String> function){
            return function.apply(strValue);
    }

    /**
     *  a.声明一个带2个泛型的函数式接口<T,R> T为参数,R为返回值
     *  b.接口中声明对应的抽象方法
     *  c.在xxx类中声明方法,使用接口作为参数,计算2个long型参数的和
     *  d.再计算2个long型参数的乘积
     */
    @Test
    public void test3(){
        calculate(1L,2L,(x,y)->  x+y);
        calculate(1L,2L,(x,y)->  x*y);
        System.out.println(calculate2(1L,2L,(x,y)->x+y+3));
    }

    /**
     * 操作2个Long值
     * @param l1
     * @param l2
     * @param myDemoFun2
     */
    private void calculate(Long l1,Long l2,MyDemoFun2<Long,Long> myDemoFun2){
        Object value = myDemoFun2.getValue(l1, l2);
        System.out.println(value);
    }

    private Long calculate2(Long x, Long y, LongBinaryOperator longBinaryOperator){
        return longBinaryOperator.applyAsLong(x, y);
    }
}
