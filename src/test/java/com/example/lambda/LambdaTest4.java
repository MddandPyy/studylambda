package com.example.lambda;

import com.example.lambda.entity.Employee;
import com.example.lambda.service.MyEmplyee;
import com.example.lambda.service.MyString;
import com.example.lambda.service.MyVoid;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一、方法引用：若Lambda体中的内容有方法已经实现了，我们可以使用“方法引用”
 * 可以理解为方法引用是Lambda表达式的另一种表现方式
 * 主要有三种语法格式
 * 对象::实例方法名
 * 类::静态方法名
 * 类::实例方法名
 *
 * 注意：
 * 1、Lambda体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致
 *
 * 二、构造器引用
 * 格式：
 * ClassName::new
 * 注意：需要调用的构造器的参数列表与函数式接口中抽象方法的参数列表保持一致
 *
 * 三、数组引用
 *
 *
 */
public class LambdaTest4 {

    //对象::实例方法名
    @Test
    public void test1(){
        Consumer<String> con = (X)->System.out.println(X);
        con.accept("qwe");

        PrintStream ps2 = System.out;
        Consumer<String> con3 = (X)->ps2.println(X);
        con3.accept("abd");

        //方法引用(前提是函数式接口中的方法类型和返回值，与println一致)
        PrintStream ps = System.out;
        Consumer<String> con2 = ps::println;
        con2.accept("abd");

        Consumer<String> con4 = System.out::println;
        con4.accept("cde");

        MyVoid myVoid = System.out::println;
        myVoid.process("test");

        MyVoid myVoid1 = (X)->System.out.println(X+"liu");
        myVoid1.process("test");

        Employee emp = new Employee("zhangsan1",18,3000);
        Supplier<String> s = ()->emp.getName();
        s.get();
        Supplier<String> s2 = emp::getName;
        s2.get();
        Supplier<String> s3 = new Employee("zhangsan2",18,3000)::getName;
        s3.get();


    }

    //类::静态方法名
    @Test
    public void test2(){

        Comparator<Integer> com = (x,y)->Integer.compare(x,y);
        int compare = com.compare(1, 1);
        System.out.println(compare);

        Comparator<Integer> com1 = Integer::compare;
        int compare2 = com1.compare(1,2);
        System.out.println(compare2);

    }

    //类::实例方法名(第一个参数是实例方法的调用者，第二个参数实例方法的参数)
    @Test
    public void test3(){
        BiPredicate<String,String> bp = (x,y)->x.equals(y);
        boolean test2 = bp.test("123", "abc");
        System.out.println(test2);

        BiPredicate<String,String> bp2 =String::equals;
        boolean test1 = bp2.test("123", "123");
        System.out.println(test1);
    }

    //构造器引用
    @Test
    public void test4(){
        Supplier<Employee> s = ()->new Employee("zhangsan1",18,3000);
        Employee e = s.get();
        System.out.println(e.getName());

        //自动匹配无参构造器
        Supplier<Employee> s1 =Employee::new;
        Employee e1 = s.get();
        e1.setName("lisi");
        System.out.println(e1.getName());

        //自动匹配全参构造器
        MyEmplyee emplyee = Employee::new;
        Employee liu = emplyee.get("liu", 18, 5000);
        System.out.println(liu);
    }

    @Test
    public void test5(){
        Function<Integer,String[]> f = (X)->new String[X];
        String[] apply = f.apply(10);
        System.out.println(apply.length);

        Function<Integer,String[]> f2 = String[]::new;
        String[] apply2 = f2.apply(15);
        System.out.println(apply2.length);
    }
}
