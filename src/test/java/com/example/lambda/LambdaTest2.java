package com.example.lambda;

import com.example.lambda.entity.Employee;
import com.example.lambda.service.MyFun;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * 一、Lambda 表达式的基础语法：java8中引入的一个新的操作符 "->"，该操作符成为箭头操作符或者lambda操作符。
 * 箭头操作符将lambda表达式拆分成两部分：
 * 左侧：lambda 表达式的参数列表（其实就是类的抽象方法的参数列表，MyPredicate中的test(T t)）
 * 右侧：lambda 表达式中要执行的功能，lambda体
 * lambda参数列表的参数类型可以省略不写，因为jvm编译器会通过上下文推断出类型。
 * 左右遇一括号省、左侧推断类型省
 *
 * 二、lambda需要函数式接口的支持
 * 函数式接口：接口中只有一个抽象方法的接口，成为函数式接口。可以使用@FunctionalInterface检查一下。
 * 函数式接口一般不需要自己去建，有内置接口供使用
 */
public class LambdaTest2 {

    /**
     *   语法格式一：无参数，无返回值
     *   ()->System.out.println("Hello Lambda");
     */
    @Test
    public void test1(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        };

        r.run();
        System.out.println("------------------------");

        AtomicInteger i = new AtomicInteger();

        // Lambda其实是一个匿名函数，在匿名函数里的变量引用，也叫做变量引用泄露，会导致线程安全问题，
        // 因此在Java8之前，如果在匿名类内部引用函数局部变量，必须将其声明为final，即不可变对象。
        // Java8这里加了一个语法糖：在lambda表达式以及匿名类内部，如果引用某局部变量，则直接将其视为final。所以不能修改引用值。
        Runnable r1 = () -> System.out.println("hello lambda" + i.getAndIncrement());
        r1.run();
    }

    /**
     *   语法格式二：有一个参数，无返回值
     *   (X)->System.out.println(X);
     *   如果之后一个参数的话，()可以不写
     *   X->System.out.println(X);
     */
    @Test
    public void test2(){
        Consumer<String> con = (X)->System.out.println(X);
        con.accept("测试有一个参数，无返回值");
    }

    /**
     *   语法格式三：有两个以上的接口，有多条lambda体，有返回值
     *   (x,y)->{
     *       lambda体1；
     *       lambda体2；
     *       return 返回值
     *   }
     *
     *   有两个以上的接口，有一条lambda体，有返回值（大括号和return都可以省略）
     *   (x,y)-> lambda体（也就是返回值）
     */
    @Test
    public void test3(){
        Comparator<Integer> com = (x,y)->{
            System.out.println("函数式接口");
            return Integer.compare(x,y);
        };
        int compare = com.compare(1, 2);
        System.out.println(compare);

        Comparator<Integer> com1 = (x,y)->Integer.compare(x,y);
        int compare2 = com1.compare(1, 2);
        System.out.println(compare2);
    }

    @Test
    public void test4(){
        //上下文中可以推断出类型
        String[] str = {"aaa","bbb","ccc"};
        //上下文中无法推断，所以会报错
//        String[] str2;
//        str2 = {"aaa","bbb","ccc"};
    }

    //需求：对一个数进行计算
    @Test
    public void test5(){
        Integer operation = operation(1, i -> i + 2);
        System.out.println(operation);
        Integer operation1 = operation(3, i -> i - 1);
        System.out.println(operation1);
    }

    public  Integer operation(Integer i, MyFun mf){
        return mf.getValue(i);
    }

    List<Employee> list = Arrays.asList(
            new Employee("zhangsan1",18,3000),
            new Employee("zhangsan2",40,5000),
            new Employee("zhangsan3",40,6000),
            new Employee("zhangsan4",20,2500),
            new Employee("zhangsan5",50,10000)
    );

    @Test
    public void test6(){
        Collections.sort(list,(e1,e2)->{
            if(e1.getAge()==e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else{
                return -Integer.compare(e1.getAge(),e2.getAge());
            }
        });
        list.forEach(System.out::println);
    }
}
