package io.github.biezhi.java8.stream.lesson2;

/**
 * @author JackChern @create 2022-08-23 15:07
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

/**
 * (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
 * (2) 交易员都在哪些不同的城市工作过？
 * (3) 查找所有来自于剑桥的交易员，并按姓名排序。
 * (4) 返回所有交易员的姓名字符串，按字母顺序排序。
 * (5) 有没有交易员是在米兰工作的？
 * (6) 打印生活在剑桥的交易员的所有交易额。
 * (7) 所有交易中，最高的交易额是多少？
 * (8) 找到交易额最小的交易。
 *
 * @author biezhi
 * @date 2018/2/12
 */
public class QuizEndPrac {
    // 交易员
    @Data
    @AllArgsConstructor
    static class Trader {
        // 姓名
        private String name;
        // 城市
        private String city;


    }

    // 交易
    @Data
    @AllArgsConstructor
    static class Transaction {
        private Trader trader;
        // 交易年份
        private int year;
        // 交易额
        private int value;
    }

    public static List<Transaction> buildData() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        return transactions;
    }

    /**
     * (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
     */
    @Test
    public void test1() {
        List<Transaction> transactions = buildData();
        List<Transaction> transactionList = transactions.stream().filter(t -> t.getYear() == 2011).
                sorted(Comparator.comparing(Transaction::getValue)).
                collect(Collectors.toList());
        transactionList.forEach(System.out::println);
    }

    /**
     * (2) 交易员都在哪些不同的城市工作过？
     */
    @Test
    public void test2() {
        List<Transaction> transactions = buildData();
        List<String> cityList = transactions.stream().map(t -> t.getTrader().getCity()).distinct().
                collect(Collectors.toList());
        cityList.forEach(System.out::println);
    }


    /**
     * (3) 查找所有来自于剑桥的交易员，并按姓名排序。
     */
    @Test
    public void test3() {
        List<Transaction> transactions = buildData();
//        transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge")).
//                map(t -> t.getTrader().getName()).sorted(Comparator.comparing(Trader::getName))
//        .collect(Collectors.toList());
    }

    //     * (4) 返回所有交易员的姓名字符串，按字母顺序排序。
    @Test
    public void test4() {
        List<Transaction> transactions = buildData();
        List<String> stringList = transactions.stream().map(Transaction::getTrader).
                map(Trader::getName).distinct().sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        stringList.forEach(System.out::println);
    }

    //     * (5) 有没有交易员是在米兰工作的？
    @Test
    public void test5() {
        List<Transaction> transactions = buildData();
        boolean match = transactions.stream().map(Transaction::getTrader).
                anyMatch(t -> t.getCity().equals("Milan"));
        System.out.println(match);
    }

    //     * (6) 打印生活在剑桥的交易员的所有交易额。
    @Test
    public void test6() {
        List<Transaction> transactions = buildData();
        int sum = transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .mapToInt(Transaction::getValue).sum();
        System.out.println(sum);

        Integer sum1 = transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue).reduce(0, Integer::sum);
        System.out.println(sum1);
    }

    //    (7) 所有交易中，最高的交易额是多少？
    @Test
    public void test7() {
        List<Transaction> transactions = buildData();
        OptionalInt max = transactions.stream().mapToInt(Transaction::getValue).max();
        System.out.println(max);
    }


    //     * (8) 找到交易额最小的交易。
    //方法一
    @Test
    public void test8() {
        List<Transaction> transactions = buildData();
//        String string = transactions.stream().
//                reduce(( t1, t2 ) -> t1.getValue() <= t2.getValue() ? t1 : t2).toString();

        Transaction transaction = transactions.stream().
                reduce(( t1, t2 ) -> t1.getValue() <= t2.getValue() ? t1 : t2).get();
        System.out.println(transaction);
    }


    //     * (8) 找到交易额最小的交易。
    //方法二
    @Test
    public void test9() {
        List<Transaction> transactions = buildData();
        Transaction transaction = transactions.stream().collect(Collectors.minBy(Comparator.
                comparing(Transaction::getValue))).get();
        System.out.println(transaction);
    }
}
