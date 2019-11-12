package com.yswg.mycodedemo1.tlambda;


import com.yswg.mycodedemo1.model.Employee;

public class FilterEmpBySalary implements MyFilterFun1<Employee> {

    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() > 6666.66;
    }
}
