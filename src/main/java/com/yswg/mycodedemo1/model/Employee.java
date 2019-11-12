package com.yswg.mycodedemo1.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Employee {

	private int id;
	private String name;
	private int age;
	private double salary;

	private Status status;

	private String birthdate;

	public Employee(int age) {
		this.age = age;
	}

	public Employee(int id, int age) {
		this.id = id;
		this.age = age;
	}

	public Employee(int id, String name, int age, double salary, Status status) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.status = status;
	}

	public Employee(String name, String birthdate) {
		this.name = name;
		this.birthdate = birthdate;
	}

	public Employee(int id, String name, int age, double salary) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", 姓名='" + name + '\'' +
				", 年龄=" + age +
				", 薪水($)=" + salary +
				", 状态=" + status +
				", 出生=" + birthdate +
				'}';
	}
}
