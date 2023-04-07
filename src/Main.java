import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        Stream<Person> firstStream = persons.stream();
        Stream<Person> secondStream = persons.stream();
        Stream<Person> thirdStream = persons.stream();
        long firstTask = firstStream.filter((Person person) -> person.getAge() < 18).count();

        List<String> secondTask = secondStream.filter((Person person) -> person.getAge() > 18)
                .filter((Person person) -> person.getAge() < 27)
                .map((Person person) -> person.getFamily())
                .collect(Collectors.toList());
        Collection<Person> filteredPersons = thirdStream.filter(person -> person.getAge() > 18)
                .filter(person -> person.getSex().equals(Sex.WOMAN) ? person.getAge() < 60 : person.getAge() < 65)
                .filter((person -> person.getEducation().equals(Education.HIGHER)))
                .sorted(Comparator.comparing(Person::getFamily)).collect(Collectors.toList());
        System.out.println(firstTask);
        System.out.println(secondTask);
        System.out.println(filteredPersons);
    }
}