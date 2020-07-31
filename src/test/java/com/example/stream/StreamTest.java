package com.example.stream;

import com.example.lambda.entity.Employee;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一、Stream 的三个操作步骤
 * 1、创建stream
 * 2、中间操作
 * 3、终止操作，产生我们需要的结果
 *
 * stream自己不会存储元素
 * 不会改变对象，他们会返回一个持有结果的新Stream
 * 操作是延迟的，这意味着他们会需要结果的时候才会执行
 */
public class StreamTest {

    //创建Stream
    @Test
    public void Test1(){

        //1、可以通过Collection系列集合提供的串行流stream()方法，后者并行流parallelStream
       List<String> list = new ArrayList<>();
       Stream<String> stream1 =  list.stream();

        //2、通过Arrays中的静态方法stream()获取数组流
        Employee[] employees = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(employees);

        //3、通过Stream类中的静态方法of()
        Stream<String> stream3 = Stream.of("1","2","3");
        stream3.forEach(System.out::println);


        //4、无限流 0 +2 +2
        // 迭代
        Stream<Integer> stream4= Stream.iterate(0,(x)->x+2);
        stream4.limit(10).forEach(System.out::println);

        //生成
        Stream<Double> stream5 = Stream.generate(() -> Math.random());
        stream5.limit(10).forEach(System.out::println);
    }

}
