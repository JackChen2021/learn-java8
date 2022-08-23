package io.github.biezhi.java8.stream.runnoob;

import com.sun.org.apache.xalan.internal.xsltc.dom.SortingIterator;
import com.sun.org.apache.xpath.internal.SourceTree;
import io.github.biezhi.java8.stream.Project;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.maxBy;

/**
 * @author JackChern @create 2022-08-23 10:26
 */
public class Java8Stream {
    /**
     * 生成流
     * 在 Java 8 中, 集合接口有两个方法来生成流：
     * <p>
     * stream() − 为集合创建串行流。
     * <p>
     * parallelStream() − 为集合创建并行流
     */
    @Test
    public void test1() {
        List<String> strings = Arrays.asList("abc", "", " ", "bc", "efg", "abcd", "", "jkl");


        List<String> list = Arrays.asList("hello", "world");

        Stream<String> stream2 = list.stream();

        Stream<String> stream = Arrays.stream(new String[]{"abc", "", " ", "bc", "efg", "abcd", ""});

        Stream<String> stream1 = Stream.of("hello", "world");

        stream1.forEach(System.out::println);
        System.out.println("_______________");

        List<String> filtered = strings.stream().filter(string ->
                !string.isEmpty()).collect(Collectors.toList());
        filtered.forEach(System.out::println);
    }

    /**
     * filter 方法用于通过设置的条件过滤出元素。
     * 以下代码片段使用 filter 方法过滤出空字符串：
     */
    @Test
    public void test2() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
// 获取空字符串的数量
        long count = strings.stream().filter(string -> string.isEmpty()).count();
        System.out.println(count);

        //混搭lambda表达式
        long count2 = strings.stream().filter(String::isEmpty).count();
        System.out.println(count2);


        //f非空字符串的数量
        long count1 = strings.stream().filter(string -> !string.isEmpty()).count();
        System.out.println(count1);


    }

    /**
     * sorted():从小到大排序
     */
    @Test
    public void test4() {
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);
        System.out.println("*************");
        List<Integer> integers = Arrays.asList(2, 3, 2, 1, 6, 5, 7, 8, 11, 4);
        integers.stream().sorted().limit(3).collect(Collectors.toList()).forEach(System.out::println);
    }

    @Test
    public void test5() {
        Random random = new Random();
        random.ints().sorted().limit(10).forEach(System.out::println);
    }


    /**
     * Collectors
     * Collectors 类实现了很多归约操作，
     * 例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串：
     * <p>
     * collect(Collectors.joining(","))
     */
    @Test
    public void test6() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        //"合并字符串: "
        String mergedString = strings.stream().filter(s -> !s.isEmpty()).collect
                (Collectors.joining(","));
        System.out.println(mergedString);

    }

    /**
     * 统计结果的收集器：mapToInt（）
     */
    @Test
    public void test7() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5, 7);
        IntSummaryStatistics intSummaryStatistics = numbers.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("sum=" + intSummaryStatistics.getSum());
        System.out.println("average=" + intSummaryStatistics.getAverage());
        System.out.println(intSummaryStatistics.getCount());
        System.out.println(intSummaryStatistics.getMax());
        System.out.println(intSummaryStatistics.getMin());
    }

    /**
     * 创建无限流
     * iterate
     */
    @Test
    public void test8() {
        Stream.iterate(0, n -> 2 * n + 1).limit(10).forEach(System.out::println);
    }

    @Test
    public void test9() {
        List<String> list = Arrays.asList("I am a boy", "I love the girl", "But the girl loves another girl");
//        list.stream().map(str -> str.split(" ")).map(Arrays::stream).collect(Collectors.toList())
//                .forEach(System.out::println);

        list.stream().map(str -> str.split(" ")).distinct().flatMap(Arrays::stream).collect(Collectors.toList())
                .forEach(System.out::println);
    }


    /**
     * anyMatch
     * allMatch
     * noneMatch
     */
    @Test
    public void test10() {
        List<Project> projects = Project.buildData();
        boolean match = projects.stream().anyMatch(s -> s.getName().equalsIgnoreCase("BLADE"));
        System.out.println(match);
        boolean match1 = projects.stream().anyMatch(s -> s.getName().equals("blade1"));
        System.out.println(match1);

        boolean match2 = projects.stream().allMatch(s -> s.getName().equals("blade1"));
        System.out.println(match2);

        boolean match3 = projects.stream().noneMatch(s -> s.getName().equals("Blade"));
        System.out.println(match3);
    }


    /**
     * 归约（reduce）
     * <p>
     * 1.元素求和
     * 2.
     *
     * @author biezhi
     * @date 2018/2/12
     */
    @Test
    public void test11() {
        List<Integer> numbers = Arrays.asList(2, 4, 5, 6);
        Integer sum = numbers.stream().reduce(0, ( a, b ) -> a + b);
        System.out.println(sum);

    }

    /**
     * 数值流
     * <p>
     * IntStream、DoubleStream、LongStream
     *
     * @author biezhi
     * @date 2018/2/12
     */
    @Test
    public void test12() {
        List<Project> projects = Project.buildData();
        OptionalInt max = projects.stream().mapToInt(p -> p.getStars()).max();
        System.out.println(max);


    }

    /**
     * 3. 斐波纳契元组序列
     * <p>
     * 斐波纳契数列是著名的经典编程练习。
     * 下面这个数列就是斐波纳契数列的一部分：
     * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55…数列中开始的两个数字是 0 和 1，后续的每个数字都是前两个数字之和。
     * <p>
     * 斐波纳契元组序列与此类似，是数列中数字和其后续数字组成的元组构成的序列：
     * (0, 1), (1, 1), (1, 2), (2,  * 你的任务是用iterate方法生成斐波纳契元组序列中的前20个元素。
     * 3), (3, 5), (5, 8), (8, 13), (13, 21) …
     *
     * @author biezhi
     * @date 2018/2/12
     */
    @Test
    public void test13() {
        Stream.iterate(new Tuple(0, 1), a -> new Tuple(a.second, a.first + a.second)).
                limit(10).forEach(a -> System.out.print("(" + a.first + "," + a.second + ")," ));
    }


    /**
     *  2.
     * <p>
     * 2.1 给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
     * 例如，给定[1, 2, 3, 4, 5]，应该返回[1, 4, 9, 16, 25]
     * <p>
     */
    @Test
    public void test14() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> integerList = integers.stream().map(i -> i * i).collect(Collectors.toList());
        integerList.forEach(System.out::println);

    }

    /**
     * 2.2 给定两个数字列表，如何返回所有的数对呢？例如，给定列表[1, 2, 3]和列表[3, 4]，
     * 应该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。
     * 为简单起见，你可以用有两个元素的数组来代表数对。
     */
    @Test
    public void test15() {
        List<Integer> integers1 = Arrays.asList(1, 2, 3,5);
        List<Integer> integers2 = Arrays.asList(3,4);
        List<int[]> collect = integers1.stream().flatMap(i -> integers2.stream().map(j -> new int[]{i, j})).
                collect(Collectors.toList());
        collect.forEach(c -> System.out.print("(" + c[0] + "," + c[1] + "),"));

    }

    /**
     * * 2.3 如何扩展前一个例子，只返回总和能被3整除的数对呢？例如(2, 4)和(3, 3)是可以的。
     */
    @Test
    public void test16() {
        List<Integer> integers1 = Arrays.asList(1, 2, 3,5);
        List<Integer> integers2 = Arrays.asList(3,4);
        List<int[]> collect = integers1.stream().flatMap(i -> integers2.stream().map(j -> new int[]{i, j})).
                filter(c -> (c[0] * c[1]) % 3 == 0).
                collect(Collectors.toList());
        collect.forEach(c -> System.out.print("(" + c[0] + "," + c[1] + "),"));
    }


    /**
     * 4. 请使用reduce计算 biezhi 的项目有多少 star
     *
     * @author biezhi
     * @date 2018/2/12
     */
    @Test
    public void test17() {
        List<Project> projects = Project.buildData();
        Integer sum = projects.stream().filter(p -> p.getAuthor().equals("biezhi")).map(p -> p.getStars()).
                reduce(0, Integer::sum);
        System.out.println(sum);

    }



    /**
     * 1. 计数
     * <p>
     * Collectors.counting
     * count
     * <p>
     * 2. 最值
     * Collectors.maxBy
     * <p>
     * 3. 求和
     * Collectors.summingInt
     * <p>
     * 4. 求平均值
     * Collectors.averagingInt
     * <p>
     * 5. 连接字符串
     * Collectors.joining
     * <p>
     * 6. 一般归约
     * Collectors.reducing
     *
     * @author biezhi
     * @date 2018/3/2
     */

    /**
     * 1. 计数
     * <p>
     * Collectors.counting
     */
    @Test
    public void test18() {
        List<Project> projects = Project.buildData();
        Long count = projects.stream().collect(counting());
        System.out.println(count);
    }


//    2. 最值
//     * Collectors.maxBy
    @Test
    public void test19() {
        List<Project> projects = Project.buildData();
        Project project = projects.stream().collect(maxBy(Comparator.comparing(Project::getStars))).get();
        System.out.println(project);

    }

    @Test
    public void test20() {


    }

    @Test
    public void test21() {


    }

    @Test
    public void test22() {


    }
















    @AllArgsConstructor
    static class Tuple {
        int first;
        int second;
    }
}
