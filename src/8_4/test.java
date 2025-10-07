import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/*--------Task 1--------*/
class CalcuMean{

    public static void main(String[] args) {
        int[] numbers = {4, 8, 15, 16, 23, 42};

        System.out.println(
                Arrays.stream(numbers)
                        .average()
        );
    }
}
/*--------Task 2--------*/

class Filter{

    public static void main(String[] args) {
        int[] numbers = {4, 8, 15, 16, 23, 42};
        System.out.println(
                Arrays.stream(numbers)
                        .filter(n -> (n%2)!=0)
                        .map(n -> n*2)
                        .sum()
        );
    }
}
