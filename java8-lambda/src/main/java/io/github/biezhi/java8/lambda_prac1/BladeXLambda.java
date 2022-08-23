package io.github.biezhi.java8.lambda_prac1;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author JackChern @create 2022-08-22 9:32
 */
public class BladeXLambda {
    static Runnable r1 = () -> System.out.println("Hello 1");
    static Runnable r2 = new Runnable() {
        @Override
        public void run() {
            System.out.println("Hello 2");
        }
    };

    public static void process( Runnable r ) {
        r.run();
    }


    static List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 0);

    @FunctionalInterface
    interface UncheckedFunction<T, R> {
        R apply( T t ) throws Exception;
    }


    public static void main( String[] args ) {
//        process(r1);
//        process(r2);
//
//        process(() -> System.out.println("Hello 3"));

        integers.forEach(i -> {
            try {
                System.out.println(50 / i);
            } catch (ArithmeticException e) {
                System.err.println("Arithmetic Exception occured : " + e.getMessage());
            }
        });


    }

}


//     class Try {
//        public static <T, R> Function<T, R> of( UncheckedFunction<T, R> mapper ) {
//            Objects.requireNonNull(mapper);
//            return t -> {
//                try {
//                    return mapper.apply(t);
//                } catch (Exception e) {
//                    //这是什么意思？
////                    throw Exception.unchecked(e);
//                }
//            };
//        }
//
//        @FunctionalInterface
//        public interface UncheckedFunction<T, R> {
//            R apply( T t ) throws Exception;
//        }
//    }



