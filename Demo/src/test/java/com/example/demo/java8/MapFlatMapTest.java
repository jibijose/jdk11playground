package com.example.demo.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class MapFlatMapTest {

  @Test
  public void checkFlatMap() {
    List<String> teamIndia = Arrays.asList("Virat", "Dhoni", "Jadeja");
    List<String> teamAustralia = Arrays.asList("Warner", "Watson", "Smith");
    List<String> teamEngland = Arrays.asList("Alex", "Bell", "Broad");
    List<String> teamNewZeland = Arrays.asList("Kane", "Nathan", "Vettori");
    List<String> teamSouthAfrica = Arrays.asList("AB", "Amla", "Faf");
    List<String> teamWestIndies = Arrays.asList("Sammy", "Gayle", "Narine");
    List<String> teamSriLanka = Arrays.asList("Mahela", "Sanga", "Dilshan");
    List<String> teamPakistan = Arrays.asList("Misbah", "Afridi", "Shehzad");

    List<List<String>> playersInWorldCup2016 = new ArrayList<>();
    playersInWorldCup2016.add(teamIndia);
    playersInWorldCup2016.add(teamAustralia);
    playersInWorldCup2016.add(teamEngland);
    playersInWorldCup2016.add(teamNewZeland);
    playersInWorldCup2016.add(teamSouthAfrica);
    playersInWorldCup2016.add(teamWestIndies);
    playersInWorldCup2016.add(teamSriLanka);
    playersInWorldCup2016.add(teamPakistan);

    List<String> listOfAllPlayers =
        playersInWorldCup2016.stream()
            .flatMap(teams -> teams.stream())
            .collect(Collectors.toList());
  }

  @Test
  public void findMaxSalaryEmployees() {
    int maxSalary =
        getEmployees().stream()
            .max(Comparator.comparing(Employee::getSalary))
            .orElseThrow(RuntimeException::new)
            .salary;
    log.debug("Max salary is {}", maxSalary);
    List<Employee> maxSalaryEmployees =
        getEmployees().stream().filter(emp -> emp.salary == maxSalary).collect(Collectors.toList());
    // .sort(Comparator.comparing(Employee::getSalary));
    maxSalaryEmployees.stream()
        .forEach(emp -> log.debug("Max salary {} is for {}", emp.salary, emp.name));
  }

  @Test
  public void findMaxSalaryEmployeesBygrouping() {
    Map<Integer, List<Employee>> groupedEmployees =
        getEmployees().stream()
            .collect(Collectors.groupingBy(Employee::getSalary, Collectors.toList()));
    log.debug("Max salary {} is for {}");

    List<Integer> salaries = groupedEmployees.keySet().stream().collect(Collectors.toList());
    salaries.sort(Comparator.reverseOrder());
    int nthSalary = salaries.get(2 - 1);

    log.debug("2nd Max salary employees {}", Arrays.asList(groupedEmployees.get(nthSalary)));
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
