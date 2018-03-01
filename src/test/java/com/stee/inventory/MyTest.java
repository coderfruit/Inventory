package com.stee.inventory;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MyTest {
    public static void main(String[] args) {
        List<String> list= Arrays.asList("java","php","c++");
        boolean match = list.stream().allMatch((str) -> str.length() > 3);
        List<String> collect = list.stream().map((s -> s + "c")).collect(Collectors.toList());
        System.out.println(collect);
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        Integer reduce = integers.stream().reduce(1, (x, y) -> x + y);
        System.out.println(reduce);
        IntSummaryStatistics statistics = integers.stream().collect(Collectors.summarizingInt((x) -> x));
//        System.out.println(statistics.getAverage());
//        System.out.println(statistics.getCount());
//        System.out.println(statistics.getMax());
//        System.out.println(statistics.getSum());
        Double collect1 = integers.stream().collect(Collectors.averagingInt((x) -> x));
        System.out.println(collect1);
        Optional<Integer> collect2 = integers.stream().collect(Collectors.maxBy((x, y) -> x - y));
        System.out.println(collect2.get());
        List<Integer> collect3 = integers.stream().map((x) -> x * x).collect(Collectors.toList());
        System.out.println(collect3);
    }
}
