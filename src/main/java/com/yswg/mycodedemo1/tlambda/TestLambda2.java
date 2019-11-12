package com.yswg.mycodedemo1.tlambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * lamdbd的基础语法: "->"箭头操作符
 * 箭头左侧: Lambda表达式的参数列表
 * <p>
 * 箭头右侧: Lambda表达式中所需要执行的功能
 * #语法一:无参数 无返回值
 * () -> System.out.println("Hello");
 * <p>
 * #语法二:有一个参数,且无返回值
 * x -> sout(x) 或者 (x) -> sout(x)
 * #语法三: 有2个以上的参数,有返回值,且Lambda语句有多条
 * (x,y) -> {
 * aaa;
 * bbb;
 * return ccc;
 * }
 * <p>
 * #语法四:有2个以上的参数,有返回值,但只有一条lambda语句 {}和return可以省略
 * Comparator<Integer> com  = (x,y)->Integer.compare(x,y)-->类型推断(lambda表达式的参数列表的数据类型可以不用写,JVM通过上下文自动推断出数据类型)
 * <p>
 * #Lambda表达式需要函数式接口的支持
 * 函数式接口: 只有一个抽象方法的接口,称为函数式接口,可以使用@FunctionInterface修饰 用来检查是否是函数式接口
 */
public class TestLambda2 {

    /**
     * 语法一
     */
    @Test
    public void test1() {
        int num = 0;//jdk1.7前必须是final;jdk1.7后.如果内部类用到了该参数,则默认加上final,其他地方出现值变动会报错
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("冲冲冲!!!" + num);
            }
        };
        r.run();
        /*语法1*/
        Runnable r2 = () -> System.out.println("Lambda 冲冲冲!!!" + num);
        r2.run();
    }

    /**
     * 语法2
     */
    @Test
    public void test2() {
        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("就这?就这?就这?");
    }

    /**
     * 语法三,四
     */
    @Test
    public void test3() {
        Comparator<Integer> comparator = (x, y) -> {
            return Integer.compare(x, y);
        };
        Comparator<Integer> comparator2 = (Integer x, Integer y) -> Integer.compare(x, y);
        Comparator<Integer> comparator3 = (x, y) -> Integer.compare(x, y);//类型推断
    }


    //例子:对一个值进行操作
    @Test
    public void test4() {
        System.out.println(operation(3, x -> x + x));
        System.out.println(operation(3, x -> x * x));
    }

    /**
     * 对一个值进行操作
     *
     * @param num
     * @param myFun
     * @return
     */
    private Integer operation(Integer num, MyFun<Integer> myFun) {
        return myFun.getValue(num);
    }

}
