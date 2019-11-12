package com.yswg.mycodedemo1;

import com.yswg.mycodedemo1.model.Employee;

import java.util.Arrays;
import java.util.List;

public class MockData {

    public static List<Employee> emps = Arrays.asList(
            new Employee(103, "张三", 18, 9999.99),
            new Employee(104, "李四", 59, 6666.66),
            new Employee(105, "王五", 28, 3333.33),
            new Employee(106, "赵六", 8, 7777.77),
            new Employee(107, "孙七", 38, 1111.55),
            new Employee(108, "周八", 48, 2222.55),
            new Employee(109, "吴九", 68, 4444.55),
            new Employee(110, "郑十", 33, 99999.55)
    );
}
