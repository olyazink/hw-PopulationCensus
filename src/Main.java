import PopulationCensus.Education;
import PopulationCensus.Person;
import PopulationCensus.Sex;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long young = persons.stream()
                .filter(age -> age.getAge() < 18)
                .count();
        System.out.println("Молодежь: " + young);

        List<String> conscript = persons.stream()
                .filter(age -> age.getAge() <= 27 && age.getAge() >= 18)
                .filter(sex -> sex.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .limit(10)
                .collect(Collectors.toList());
        System.out.println("Призывники: " + conscript);

        List<String> workable = persons.stream()
                .filter(educ -> educ.getEducation() == Education.HIGHER)
                .filter(age -> age.getAge() >= 18)
                .filter(as -> (as.getAge() < 60 && as.getSex() == Sex.WOMAN) || (as.getAge() < 65 && as.getSex() == Sex.MAN))
                .map(Person::getFamily)
                .sorted(Comparator.naturalOrder())
                .limit(10)
                .collect(Collectors.toList());
        System.out.println("Работяги: " + workable);
    }
}
