package com.example.lambda;


import com.example.lambda.entity.Employee;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * java8 四大核心函数式接口
 * Consumer<T> :消费型接口
 *    void accept(T t);
 * Supplier<T> :供给型接口
 *    T get();
 * Function<T,R> :函数型接口
 *    R apply(T t);
 * Predicate<T> :断言型接口
 *    boolean test(T t);
 */
public class LambdaTest3 {
    /**
     * 消费型接口
     */
    @Test
    public void test1(){
        happy(1000,money->System.out.println("用"+money+"元，买东西"));
    }
    private void happy(double money, Consumer<Double> con){
        con.accept(money);
    }

    /**
     * 供给型接口
     * 需求产生指定数量的整数，放入集合
     */
    @Test
    public void test2(){
        List<Integer> list = getList(10, () -> {
            return (int) (Math.random() * 100);
        });
        list.forEach(System.out::println);
    }
    private List<Integer> getList(int num, Supplier<Integer> s){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
          list.add(s.get());
        }
        return list;
    }

    /**
     * 函数型接口，获取字符串的长度
     */
    @Test
    public void test3(){
        Integer l = getStrLength("asdf", str -> str.length());
        System.out.println(l);
    }
    private Integer getStrLength(String str, Function<String,Integer> f){
        return f.apply(str);
    }

    /**
     * 断言型接口，获取数是否等于
     */
    @Test
    public void test4(){
        boolean predicate = predicate(2, num -> num == 3);
        System.out.println(predicate);
    }
    private boolean predicate(int num, Predicate<Integer> p){
        return p.test(num);
    }



    List<Employee> list = Arrays.asList(
            new Employee("zhangsan1",18,3000),
            new Employee("zhangsan2",40,5000),
            new Employee("zhangsan3",40,6000),
            new Employee("zhangsan4",20,2500),
            new Employee("zhangsan5",50,10000)
    );



    /**
     * 断言型接口，判断是否符合条件
     */
    @Test
    public void test5(){
        List<Employee> employees = filterEmployee(list, e -> e.getSalary() >= 5000);
        employees.forEach(System.out::println);
    }
    private List<Employee> filterEmployee(List<Employee> list,Predicate<Employee> p){
        List<Employee> employees = new ArrayList<>();
        for (Employee e:list){
            if(p.test(e)){
                employees.add(e);
            }
        }
        return employees;
    }


    /**
     * 其他接口，判断是否相等
     */
    @Test
    public void test6(){
        boolean b = biFunction(2, 3, (num1, num2) -> num1 == num2);
        System.out.println(b);
    }
    private boolean biFunction(int num1, int num2, BiFunction<Integer,Integer,Boolean> bi){
        return bi.apply(num1,num2);
    }

}
