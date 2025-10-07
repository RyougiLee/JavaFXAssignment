import java.util.Arrays;
import java.util.List;

class MethodReferenceExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Example 1: Reference to a static method
        names.forEach(MethodReferenceExample::printName);

        // Example 2: Reference to an instance method of a particular object
        MethodReferenceExample example = new MethodReferenceExample();
        names.forEach(example::printWithPrefix);
    }

    public static void printName(String name) {
        System.out.println(name);
    }

    public void printWithPrefix(String name) {
        System.out.println("Name: " + name);
    }
}
