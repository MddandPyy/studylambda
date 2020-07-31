package com.example.stream;

import com.example.lambda.entity.Employee;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 终止操作
 * 一、查找与匹配
 * allMatch 检查是否匹配所有元素
 * anyMatch 检查是否至少匹配一个元素
 * noneMatch 检查是否没有匹配所有元素
 * findFirst 返回第一个元素
 * findAny 返回当前流中的任意元素
 * count 返回流中元素总个数
 * max 返回最大值
 * min 返回最小值
 *
 * 二、归约
 * reduce 可以将流中元素反复结合起来，得到一个值
 *
 * 三、收集
 *collect 将流转换为其他形式，接收一个collector接口的实现，用于给Stream中元素做汇总的方法
 */
public class StreamTest3 {
    List<Employee> list = Arrays.asList(
            new Employee("zhangsan1",18,3000),
            new Employee("zhangsan2",30,5000),
            new Employee("zhangsan3",40,6000),
            new Employee("zhangsan4",20,2500),
            new Employee("zhangsan6",40,10000),
            new Employee("zhangsan6",50,10000),
            new Employee("zhangsan6",50,10000)
    );

    @Test
    public void test1(){
        //Optional<Employee> first = list.stream().findAny();
        Optional<Employee> first = list.stream().min(Comparator.comparing(Employee::getAge));
        System.out.println(first);
    }

    @Test
    public void test2(){
        //boolean b = list.stream().allMatch((e) -> e.getAge() >= 30);
        boolean b = list.stream().anyMatch((e) -> e.getAge() >= 30);
        System.out.println(b);
    }

    @Test
    public void test3(){
        long count = list.stream().count();
        System.out.println(count);
    }

    @Test
    public void test4(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8);
        Integer reduce = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(reduce);
    }

    @Test
    public void test5(){
        //filter+map+reduce，先过滤，再获取薪水，后加和
        Optional<Double> reduce = list.stream().filter((e)->e.getAge()>=40).map(Employee::getSalary).reduce(Double::sum);
        System.out.println(reduce);
    }

    @Test
    public void test6(){
        List<String> collect = list.stream().map(Employee::getName).collect(Collectors.toList());
        System.out.println(collect);


        Set<String> set = list.stream().map(Employee::getName).collect(Collectors.toSet());
        System.out.println(set);

        HashSet<String> collect1 = list.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
        System.out.println(collect1);

        //总数
        Long collect2 = list.stream().collect(Collectors.counting());
        System.out.println(collect2);

        //平均值
        Double collect3 = list.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(collect3);

        Double collect4 = list.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(collect4);
    }

    //分组
    @Test
    public void test7(){
        Map<Integer, List<Employee>> collect = list.stream().collect(Collectors.groupingBy(Employee::getAge));
        System.out.println(collect);

        Map<String, List<Employee>> collect1 = list.stream().collect(Collectors.groupingBy(Employee::getName));
        System.out.println(collect1);

        Map<String, Map<Integer, List<Employee>>> collect2 = list.stream().collect(Collectors.groupingBy(Employee::getName, Collectors.groupingBy(Employee::getAge)));
        System.out.println(collect2);

        Map<String, Map<String, List<Employee>>> collect3 = list.stream().collect(Collectors.groupingBy((e)->{
            return e.getName();
        }, Collectors.groupingBy((e) -> {
            if (e.getAge() >= 30) {
                return "青年";
            } else if (e.getAge() >= 50) {
                return "老年";
            } else {
                return "青少年";
            }
        })));
        System.out.println(collect3);
    }

    @Test
    public void test8(){
        Map<Boolean, List<Employee>> collect = list.stream().collect(Collectors.partitioningBy((e) -> e.getAge() >= 30));
        System.out.println(collect);
    }

    @Test
    public void test9(){
        DoubleSummaryStatistics collect = list.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(collect);
        System.out.println(collect.getCount());
        System.out.println(collect.getMax());
        System.out.println(collect.getMin());

    }

    //连接字符串
    @Test
    public void test10(){
        String collect = list.stream().map(Employee::getName).collect(Collectors.joining(",","开头-","-结尾"));
        System.out.println(collect);
    }

    @Test
    public void test11(){
        BigDecimal b = new BigDecimal(13.5000);
        int i = b.intValue();
        System.out.println(i);
    }
}
