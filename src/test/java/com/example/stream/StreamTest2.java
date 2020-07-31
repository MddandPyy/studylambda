package com.example.stream;

import com.example.lambda.entity.Employee;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * 中间操作：
 * 一、筛选和切片
 * 1、filter 接口lambda，从流中排除某些元素
 * 2、limit(n) 截断流，使其元素不超过给定数量
 * 3、skip(n) 跳过元素，返回一个扔掉前n个元素的流，若流中元素不足n个，返回一个空流。与limit互补
 * 4、distinct 筛选，通过流所产生的元素的hashcode()和equals()去除重复元素
 * 注意：多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作，
 * 否则中间操作不会执行任何处理，而是在终止操作时一次性全部处理，成为惰性处理
 * 二、映射
 * 1、map 接受lambda 将元素转换成其他形式或者提取信息，接收一个函数作为参数，该函数会应用到每个元素上，并将其映射成新的元素。
 * 2、flatmap 接收一个函数作为参数，将流中的每个值都转换成另一个流，然后把所有流连成一个流
 * 三、排序
 * 1、自然排序 sorted()
 * 2、定制排序 sorted(Comparator com )
 */
public class StreamTest2 {
    List<Employee> list = Arrays.asList(
            new Employee("zhangsan1",18,3000),
            new Employee("zhangsan2",30,5000),
            new Employee("zhangsan3",40,6000),
            new Employee("zhangsan4",20,2500),
            new Employee("zhangsan5",50,20000),
            new Employee("zhangsan5",50,10000),
            new Employee("zhangsan5",50,50000)
    );

    /**
     * Stream<T> filter(Predicate<? super T> var1);
     * boolean test(T var1);
     * filter中的lambda表达式返回结果为boolean，true满足条件，false不满足
     */
    @Test
    public void test1(){

//        Stream<Employee> stream = list.stream().filter((e)->{
//            System.out.println("filter执行");
//            return e.getAge()>=40;
//        });
        //终止操作，如果此操作不行，那么上面的中间操作也不会输出结果。
       // stream.forEach(System.out::println);


        //中间操作的顺序，与其所在位置有关。
        //流水线：先filter，后distinct，然后判断是否足够limit数量，最终输出。Stream中的每一条记录都是如此。
        //基本数据类型 是 值传递，不允许数据改变，对象类型 是 地址传递，值是可以改变的，但地址不能改变
       Employee emp = new Employee();
       boolean flag = false;
        list.stream().filter((e)->{
            //基础类型赋值报错，不允许修改
            //flag = true;
            emp.setName("lzp");
            System.out.println("filter执行");
            return e.getAge()>=40;
        }).distinct().limit(3).forEach(System.out::println);
        System.out.println(emp);
    }

    @Test
    public void test2(){
        List<String> list =Arrays.asList("aaa","bbb","ccc","ddd");
        list.stream().map((str)->str.toUpperCase()).forEach(System.out::println);
    }

    @Test
    public void test3(){
        Stream<String> stringStream = list.stream().map((e) -> e.getName());
        stringStream.forEach(System.out::println);
    }

    @Test
    public void test4(){
        Employee e = new Employee();
        Consumer<String> con = (X)->e.setName(X);
        con.accept("liuzp");
        System.out.println(e);
    }


    @Test
    public void test5(){
//        List<String> strs = Arrays.asList("bb","dd","aa","cc");
//        strs.stream().sorted().forEach(System.out::println);

        //list.stream().sorted().forEach(System.out::println);
//        list.stream().sorted((e1,e2)->{
//            if(e1.getAge()==e2.getAge()){
//                return Double.compare(e1.getSalary(),e2.getSalary());
//            }else{
//                return Integer.compare(e1.getAge(),e2.getAge());
//            }
//        }).forEach(System.out::println);

        //Comparator.comparing(Employee::getSalary).reversed() 薪水降序排列
        //Comparator.comparing(Employee::getAge).thenComparing(Employee::getSalary,Comparator.reverseOrder()) 年龄升序，薪水降序
        list.stream().sorted(Comparator.comparing(Employee::getAge).thenComparing(Employee::getSalary,Comparator.reverseOrder())).forEach(System.out::println);
    }
}
