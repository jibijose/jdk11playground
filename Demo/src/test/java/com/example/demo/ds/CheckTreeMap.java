package com.example.demo.ds;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CheckTreeMap {

  @Test
  public void testTreeMap() {
    List<Employee> employees = getEmployees();
  }

  private List<Employee> getEmployees() {
    List<Employee> employees = new ArrayList<>();
    employees.add(new Employee("John", 800));
    employees.add(new Employee("Stella", 1000));
    employees.add(new Employee("Clara", 800));
    employees.add(new Employee("Robert", 700));
    employees.add(new Employee("Daniel", 1000));
    employees.add(new Employee("Alvin", 800));
    employees.add(new Employee("John", 800));
    employees.add(new Employee("Pope", 600));
    employees.add(new Employee("Jerry", 800));
    employees.add(new Employee("Tom", 600));
    return employees;
  }

  private static class Employee {
    public String name;
    public int salary;

    public Employee(String name, int salary) {
      this.name = name;
      this.salary = salary;
    }

    public int getSalary() {
      return salary;
    }

    public String toString() {
      return "Name=[" + name + "] Salary=[" + salary + "]";
    }
  }
}
