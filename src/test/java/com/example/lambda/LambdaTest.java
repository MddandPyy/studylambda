package com.example.lambda;

import com.example.lambda.entity.Employee;
import com.example.lambda.service.MyPredicate;
import com.example.lambda.service.impl.MyPredicateByAge;
import com.example.lambda.service.impl.MyPredicateBySalary;
import org.junit.jupiter.api.Test;

import java.util.*;

public class LambdaTest {

    @Test
    public void test1(){
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                    return Integer.compare(o1,o2);
            }
        };

        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    //lambda
    @Test
    public void test2(){
        Comparator<Integer> com = (x,y)-> Integer.compare(x,y);
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    List<Employee> list = Arrays.asList(
            new Employee("zhangsan1",18,3000),
            new Employee("zhangsan2",30,5000),
            new Employee("zhangsan3",40,6000),
            new Employee("zhangsan4",20,2500),
            new Employee("zhangsan5",50,10000)
    );

    //获取年龄大于等于40
    public List<Employee> filterEmployee1(List<Employee> list){
        List<Employee> emps = new ArrayList<>();
        for (Employee e:list) {
            if(e.getAge()>=40){
                emps.add(e);
            }
        }
        return emps;
    }

    //获取工资大于等于5000
    public List<Employee> filterEmployee2(List<Employee> list){
        List<Employee> emps = new ArrayList<>();
        for (Employee e:list) {
            if(e.getSalary()>=5000){
                emps.add(e);
            }
        }
        return emps;
    }

    //优化方式一  策略模式
    public List<Employee> filterEmployee3(List<Employee> list, MyPredicate<Employee> mp){
        List<Employee> emps = new ArrayList<>();
        for (Employee e:list) {
            if(mp.test(e)){
                emps.add(e);
            }
        }
        return emps;
    }


    @Test
    public void test3(){
        List<Employee> employees1 = filterEmployee1(list);
        System.out.println(employees1.toString());
        System.out.println("----------------------");
        List<Employee> employees2 = filterEmployee2(list);
        System.out.println(employees2.toString());
    }

    @Test
    public void test4(){
        List<Employee> employees1 = filterEmployee3(list,new MyPredicateByAge());
        System.out.println(employees1.toString());
        System.out.println("----------------------");
        List<Employee> employees2 = filterEmployee3(list,new MyPredicateBySalary());
        System.out.println(employees2.toString());
    }

    //优化方式二 匿名内部类
    @Test
    public void test5(){
        List<Employee> employees = filterEmployee3(list, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee e) {
                return e.getSalary() >= 4000;
            }
        });

        System.out.println(employees.toString());
    }

    //优化方式三 lambda
    @Test
    public void test6(){
        List<Employee> employees = filterEmployee3(list,(e)->e.getSalary()>=5000);
        employees.forEach(System.out::println);
    }

    //优化方式四 (上面的代码除了list，其他的都没有) stream API
    @Test
    public void test7(){

        list.stream()
            .filter((e)->e.getSalary()>=5000)
            .limit(2)
            .forEach(System.out::println);

        System.out.println("----------------------");

        list.stream()
            .map(Employee::getName)
            .forEach(System.out::println);

    }


}
