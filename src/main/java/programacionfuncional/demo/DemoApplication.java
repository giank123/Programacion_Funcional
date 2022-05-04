package programacionfuncional.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import programacionfuncional.demo.model.Person;
import programacionfuncional.demo.model.Product;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		Person p1 = new Person(1, "Miro", LocalDate.of(1991,1,21));
		Person p2 = new Person(2, "Code", LocalDate.of(1990,2,21));
		Person p3 = new Person(3, "Jaime", LocalDate.of(1980,6,23));
		Person p4 = new Person(4, "Duke", LocalDate.of(2019,5,15));
		Person p5 = new Person(5, "James", LocalDate.of(2010,1,4));

		Product pr1 = new Product(1, "Ceviche", 15.0);
		Product pr2 = new Product(2, "Chilaquiles", 25.50);
		Product pr3 = new Product(3, "Bandeja Paisa", 35.50);
		Product pr4 = new Product(4, "Ceviche", 15.0);

		List<Person> persons = Arrays.asList(p1,p2,p3,p4,p5);
		List<Product> products = Arrays.asList(pr1, pr2, pr3, pr4);


		/*Recorrer una coleccion
		for(Person p : persons){
			System.out.println(p);
		}*/

		//products.forEach(x -> System.out.println(x));
		//products.forEach(System.out::println);

		// 1- Filter (param : Predicate)
		List<Person> filteredList1 = persons.stream()
				                         .filter(p -> DemoApplication.getAge(p.getBithDate())  >= 18)
				                         .collect(Collectors.toList());

		//DemoApplication.printList(filteredList1);

		//2. Map  (param: Function)
		 List<Integer> filteredList21 = persons.stream()
				                               //.map(p -> DemoApplication.getAge(p.getBithDate()))
				                               .map(p -> DemoApplication.getAge(p.getBithDate()))
				                               .collect(Collectors.toList());

		List<String> filteredList22 = persons.stream()
				                               .map(p -> "coder "+ p.getName())
				                               .collect(Collectors.toList());

		//con Function separado:  2 parametros , parametro de entrada y salida
		Function<String, String> coderFunction = name -> "Coder "+name;

		List<String> filteredList23 = persons.stream()
				 //Puedo hacer el la siguiente linea o la proxima porque esta haciendo un funcion de metodo de referencia de la clase Persona
				                               //.map(p -> p.getName())
				                               .map(Person::getName)
				                               .map(coderFunction)
				                               .collect(Collectors.toList());


		//DemoApplication.printList(filteredList23);

		// 3. Sorted (param: Comparator)

		Comparator<Person> byNameAsc = (Person o1, Person o2) -> o1.getName().compareTo(o2.getName());
		Comparator<Person> byNameDesc = (Person o1, Person o2) -> o2.getName().compareTo(o1.getName());
		Comparator<Person> byBirthDate = (Person o1, Person o2) -> o1.getBithDate().compareTo(o2.getBithDate());


		List<Person> filteredList3 =  persons.stream()
				.sorted(byBirthDate)
				.collect(Collectors.toList());

		//DemoApplication.printList(filteredList3);

		//4. Match (param: Predicate)
		//Creamos un Predicate para que haya una evaluacion general.
		Predicate<Person> startsWithPredicate = person -> person.getName().startsWith("J");

		//  - anyMatch: No evalua todo el String (No recorre todo , a la primera coincidencia retorna el true o false)
		boolean rpta1 =  persons.stream()
				.anyMatch(startsWithPredicate);


		//  - allMatch: Evalua todo el stream bajo la condicion (Que todo la lista tiene que comenzar con "J")
		boolean rpta2 = persons.stream()
				               .allMatch(startsWithPredicate);

		// - noneMatch: Evalua todo el stream bajo la condicion (Que en toda la lista ninguno conincida)
		boolean rpta3 = persons.stream()
				.noneMatch(startsWithPredicate);

		// 5. Limit/Skip
		int pageNumber = 0
				;
		int pageSize = 2;

		List<Person> filteredList4 =  persons.stream()
				                              .skip(pageNumber*pageSize)
				                              .limit(pageSize)
				                              .collect(Collectors.toList());

		//DemoApplication.printList(filteredList4);

        // 6. Collectors
		//GroupingBy:
		//Devuelve un map el tipo del que se especifica y la Clase del mismo
		Map<String, List<Product>> collect1 = products.stream()
				.filter(p -> p.getPrice() > 20)
				.collect(Collectors.groupingBy(Product::getName));

		//System.out.println(collect1);

		// Counting
		//
        Map<String, Long>  collect2 = products.stream()
				                      .collect(Collectors.groupingBy(
						                        Product::getName, Collectors.counting()
									            )
									  );

		System.out.println(collect2);
	}

	public static int getAge(LocalDate birthdate){
		return Period.between(birthdate, LocalDate.now()).getYears();
	}

	public static void printList(List<?> list){
		list.forEach(System.out::println);
	}

}
