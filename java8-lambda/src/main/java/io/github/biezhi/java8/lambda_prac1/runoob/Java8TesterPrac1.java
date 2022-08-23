package io.github.biezhi.java8.lambda_prac1.runoob;

/**
 * @author JackChern @create 2022-08-19 18:02
 */
public class Java8TesterPrac1 {
    public static void main( String args[] ) {
        Java8TesterPrac1 tester = new Java8TesterPrac1();

        // 类型声明
        MathOperation addition = ( int a, int b ) -> a + b;


        // 不用类型声明
        MathOperation additon1 = ( a, b ) -> a + b;
        MathOperation subtraction = ( a, b ) -> a - b;
        // 大括号中的返回语句
        MathOperation multiplication = ( a, b ) -> {
            return a * b;
        };


        // 没有大括号及返回语句
        MathOperation division = (a,b) ->  a / b;


        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));

        // 不用括号
        GreetingService greetingService1 = message -> System.out.println("Hello1  " + message);

        // 用括号
        GreetingService greetingService2 = (message) -> System.out.println("Hello2" + message);


        greetingService1.sayMessage("world");
        greetingService2.sayMessage("USA");
    }

    interface MathOperation {
        int operation( int a, int b );
    }

    interface GreetingService {
        void sayMessage( String message );
    }

    private int operate( int a, int b, MathOperation mathOperation ) {
        return mathOperation.operation(a, b);
    }
}
