package org.example.lectures;

// Interface with only one abstract method
// @FunctionalInterface restricts us to define only one abstract method in an interface failing which
// we get a compilation error
//This is optional just for better coding practice

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

//Un-comment the below line
//@FunctionalInterface
interface NotAFunctionalInterface {
    void firstMethod();
    void secondMethod();
}

@FunctionalInterface
interface MyFunc {
    void doOneThing(String val);
}
/*
* How many ways to implement a functional interface ?
* There are 3 ways
* 1 -> Class implements
* 2 -> Anonymous Class
* 3 -> Lambda Function
* */

//Method1
class MyFuncImpl implements MyFunc {

    public void doOneThing (String val) {

        System.out.println(val);
    }
}

public class L16FunctionalLambda {
    public static void main (String[] args) {
        //Method2: Using anonymous class

        MyFunc anonymousClassObject = new MyFunc() {
            @Override
            public void doOneThing(String val) {
                System.out.println(val);
            }
        };

        //Method3: Using lambda function

        MyFunc lambdaExpression = System.out::println;

        //calling all the three method
        MyFuncImpl funcImplObj = new MyFuncImpl();
        funcImplObj.doOneThing("tapas");
        anonymousClassObject.doOneThing("tapas");
        lambdaExpression.doOneThing("tapas");
    }
}

/*
* There are four types of functional interfaces that is provided out of the box
*
* Consumer : It takes only one generic input but no output
* @FunctionalInterface
* public interface Consumer<T> {
*   void accept(T t);
* }
*
* Supplier : It takes no input but provides generic output
* @FunctionalInterface
* public interface Supplier<T> {
*   void accept(T t);
* }
*
* Function : It takes one generic input and can provide one generic output
* @FunctionalInterface
* public interface Function <T, R>{
*   R apply(T t);
* }
*
* Predicate : It takes one generic input and provide a boolean as its output
* @FunctionalInterface
* public interface Predicate<T> {
*   boolean test(T t);
* }
*/

class FunctionalInterfaces {

    private final static Consumer<String> printString = System.out::println;
    private final static Supplier<Integer> getRandomNumber = () -> (int) (Math.random()*10);
    private final static Function<String, Integer> getLength = String::length;
    private final static Predicate<Integer> isEven = num -> num % 2 == 0;

    public static void main(String[] args) {
        printString.accept("Tapas");
        System.out.println(getRandomNumber.get());
        System.out.println(getLength.apply("Tapas"));
        System.out.println(isEven.test(69));

    }
}

/**
 * Extending from another interface
 * keeping in mind that a functional interface can only have one abstract method,
 * The method and signature of the functional interface and the parent interface must match
 * Note: It is allowed for the parent interface to have a default method
 * Question: Can then functional interface have a default method?
 * Question: Can functional interface have more than one method?
 * Answer: Answer to above two questions are yes. One default method is allowed to be declared inside a functional interface
 * */

@FunctionalInterface
interface FuncWithDefaultMethod {
    default void defaultMethod() {
        System.out.println("I am default method");
    }

    void doThatOneThing();
}