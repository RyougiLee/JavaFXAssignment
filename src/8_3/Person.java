import java.util.ArrayList;
import java.util.Comparator;

public class Person {

    String name;
    int age;
    String city;

    public Person(String name, int age, String city){
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name+" "+age+" "+city;
    }

    public static void main(String[] args) {
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person("A",10,"NYC"));
        persons.add(new Person("B",8,"WDC"));
        persons.add(new Person("C",15,"LA"));
        persons.add(new Person("D",2,"BJ"));

        Comparator<Person> comparator = (person, other) -> person.age - other.age;

        persons.sort(comparator);
        persons.forEach(System.out::println);

        persons.removeIf(p -> !p.city.equals("NYC") );
        persons.forEach(System.out::println);
    }
}
