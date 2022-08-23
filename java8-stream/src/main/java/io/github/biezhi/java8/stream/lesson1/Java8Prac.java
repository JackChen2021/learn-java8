package io.github.biezhi.java8.stream.lesson1;

import io.github.biezhi.java8.stream.Project;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author JackChern @create 2022-08-23 9:57
 */
public class Java8Prac {
    public static void main( String[] args ) {
        List<Project> projects = Project.buildData();

        long count = projects.stream().filter(p -> p.getStars() > 3000).count();
        System.out.println("star>3000的项目数量：" + count);
        long count1 = projects.stream().filter(p -> {
            return p.getStars() > 4000;
        }).count();
        System.out.println("star>4000的项目数量：" + count1);

        List<String> nameList = projects.stream().
                filter(p -> p.getStars() > 3000).map(p -> p.getName()).limit(2).
                collect(Collectors.toList());
        nameList.stream().forEach(n -> System.out.println(n));
    }
}
