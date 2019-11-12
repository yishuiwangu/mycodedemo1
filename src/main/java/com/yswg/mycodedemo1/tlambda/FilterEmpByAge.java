package com.yswg.mycodedemo1.tlambda;


import com.yswg.mycodedemo1.model.Employee;

public class FilterEmpByAge implements MyFilterFun1<Employee> {
    @Override
    public boolean test(Employee t) {
        //编写过滤规则
        return t.getAge() >= 35;
    }
}
