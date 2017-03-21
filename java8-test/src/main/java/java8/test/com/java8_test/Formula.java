package java8.test.com.java8_test;

public interface Formula
{
    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
