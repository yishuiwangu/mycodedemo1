package com.yswg.mycodedemo1.tstream;

import com.yswg.mycodedemo1.model.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  1.stram不会改变源.会得到一个新源
 *  2.stream只有在需要得到结果的时候才会执行
 *
 * 三步走
 *  1.创建stram
 *  2.操作一波 filter map distinct
 *  3.终止一下
 */
public class TestStreamAPI {

    List<Employee> emps = Arrays.asList(
            new Employee(103, "张三", 18, 9999.99),
            new Employee(104, "李四", 19, 6666.66),
            new Employee(105, "王五", 28, 3333.33),
            new Employee(106, "赵六", 28, 7777.77),
            new Employee(106, "赵六", 28, 7777.77),
            new Employee(107, "孙七", 38, 1111.55),
            new Employee(108, "周八", 48, 2222.55),
            new Employee(109, "吴九", 68, 4444.55),
            new Employee(110, "郑十", 33, 99999.55)
    );

    /**
     * 创建流的方式
     */
    @Test
    public void createStream() {
        //1.xxx.stream()
        Stream<String> stream1 = new ArrayList<String>().stream();
        //2.
        Stream<String> stream2 = Arrays.stream(new String[10]);
        //3.
        Stream<String> stream3 = Stream.of("A", "B", "C");
        //4.无限流
        Stream<Integer> limit1 = Stream.iterate(0, x -> x + 1).limit(100).skip(0).peek(System.out::println);
        System.out.println(limit1.count());
        Stream<Double> limit2 = Stream.generate(Math::random).limit(10);
        System.out.println(limit2.count());
    }

    /**
     * filter-接受lambda表达式过滤规则
     * limit-截断流,限定元素数量
     * skip(n) 跳过元素,返回的时候会扔掉前n个元素,如果n>元素总数,返回空流
     * distinct-筛选,去重(根据hashCode equals)
     */
    @Test
    public void test1() {
        //中间操作:没有终止操作不会执行中间操作
        Stream<Employee> peek = emps.stream()
                .filter(x -> x.getAge() >= 28)
                //  .distinct()
                .limit(3)
                .distinct()
                .peek(System.out::println);
//        //终止操作
        System.out.println("--------------------");
        System.out.println(peek.count());
    }


    /**
     * map: 接受lambda表达式,将元素进行转换或者提取信息,并将结果映射成一个新的元素
     * flatMap 接收一个函数作为参数,将流中的每个值都换成另一个流,然后把所有的流连接成一个流
     */
    @Test
    public void test2() {
        List<String> strings = Arrays.asList("wo ", "shi ", "ban ", "yun ", "gong!");
        List<String> newStrings = strings.stream().map(x -> x.toUpperCase()).peek(System.out::print).collect(Collectors.toList());
        List<String> nameList = emps.stream().map(Employee::getName).peek(System.out::println).collect(Collectors.toList());
        /*
        * map 和 flatMap的区别
        * */
        Stream<Stream<Character>> streamStream = strings.stream().map(TestStreamAPI::splitStr);
        streamStream.forEach(x->{
            x.forEach(subx->System.out.print(subx));
        } );
        System.out.println();
        Stream<Character> characterStream = strings.stream().flatMap(TestStreamAPI::splitStr);
        System.out.println("flatMap:"+characterStream.findFirst().get());

    }


    /**
     * 排序
     * 1.默认排序 (Comparable)
     * 2.自定义排序(Comparator)
     */
    @Test
    public void testSort(){
        List<String> list = Arrays.asList("AA", "aaa", "123", "111", "ccc", "bbb", "CC", "BB");
        //1.默认排序
        list.stream()
                .sorted()
                .forEach(System.out::println);
        System.out.println("-----------------------------------------------------------");

        //2.自定义排序: 薪水排序
        emps.stream()
                .sorted((x,y)-> Double.compare(x.getSalary(),y.getSalary()))
                .forEach(System.out::println);
    }





    /**
     * 拆解字符串
     * sorted
     * sorted(Comparator)
     *
     * @param str
     * @return
     */
    public static Stream<Character> splitStr(String str) {
        List<Character> list = new ArrayList<>();
        for (Character cha : str.toCharArray()) {
            list.add(cha);
        }
        return list.stream();
    }


    //    public void testSort2(){
//        Date d1 = new Date();
//        Date d2 = new Date(d1.getTime()-36000000L);
//        List<Employee> employees = Arrays.asList(
//                new Employee("张一一", "2019-11-11"),
//                new Employee("AAA", "2019-12-12"),
//                new Employee("李四四", "2019-12-12"),
//                new Employee("张二二", "2019-11-11"),
//                new Employee("CCC", "2019-01-11"),
//                new Employee("VVVV", "2019-04-12"),
//                new Employee("EEE", "2019-03-12"),
//                new Employee("RRR", "2018-12-12")
//        );
//        employees.stream()
//                .sorted((x,y)-> {
//                    Date xDate = MyUtils.parseStringDate(x.getBirthdate());
//                    Date yDate =  MyUtils.parseStringDate(y.getBirthdate());
//                   if(xDate.before(yDate)){
//                       return 1;
//                   } else {
//                       return -1;
//                   }
//
//                } )
//                .forEach(System.out::println);
//    }
}
