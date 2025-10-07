import org.checkerframework.checker.units.qual.A;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Number {

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(List.of(10,5,8,20,15,3,12));

        numbers.removeIf(n -> n%2 ==0);
        numbers.forEach(System.out::println);

        numbers.replaceAll(n -> n*=2);
        numbers.forEach(System.out::println);

        int[] sum = {0};
        numbers.forEach(n -> sum[0]+=n);
        System.out.println(sum[0]);
    }
}
