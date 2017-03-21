package stream_api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import stream_api.data.Person;

public class Main
{

    public static void main(String[] args)
    {
	
	List<Person> persons = Person.createRoster();
		                   
	System.out.println("averageAge: " + averageAge(persons));

	System.out.println("averageAgeParallel: " + averageAgeParallel(persons));
	
//	System.out.println("Average age of male members: " + averageAgeCollect(persons).average());
		
//	System.out.println("namesOfMaleMembersCollect: " + namesOfMaleMembersCollect(persons));
		
//	System.out.println("totalAgeByGender: " + totalAgeByGender(persons));
		
//	System.out.println("averageAgeByGender: " + averageAgeByGender(persons));

    }
    
    public static double averageAge(List<Person> persons) {
	long t0 = new Date().getTime();
	long elapsed = 0;
	double averageAge = persons
		.parallelStream()
		.filter(p -> p.getGender() == Person.Sex.MALE)
		.mapToInt(Person::getAge)
		.average()
		.getAsDouble();
	elapsed = new Date().getTime() - t0;
	System.out.printf("averageAge: Elapsed time:\t %d ms", elapsed);
	System.out.println();
	return averageAge;
	
    }
    
    public static double averageAgeParallel(List<Person> persons) {
	long t0 = new Date().getTime();
	long elapsed = 0;
	double averageAgeParallel = persons
		    .parallelStream()
		    .filter(p -> p.getGender() == Person.Sex.MALE)
		    .mapToInt(Person::getAge)
		    .average()
		    .getAsDouble();
	elapsed = new Date().getTime() - t0;
	System.out.printf("averageAgeParallel: Elapsed time:\t %d ms", elapsed);
	System.out.println();
	return averageAgeParallel;
	
    }

    public static Map<Person.Sex, Double> averageAgeByGender(List<Person> persons) {
	return persons
		    .stream()
		    .collect(
		        Collectors.groupingBy(
		            Person::getGender,                      
		            Collectors.averagingInt(Person::getAge)));

	
    }

    public static Map<Person.Sex, Integer> totalAgeByGender(List<Person> persons) {
	return persons
	        .stream()
	        .collect(
	            Collectors.groupingBy(
	                Person::getGender,                      
	                Collectors.reducing(
	                    0,
	                    Person::getAge,
	                    Integer::sum)));
	
    }

    public static List<String> namesOfMaleMembersCollect(List<Person> persons) {
	return persons
		    .stream()
		    .filter(p -> p.getGender() == Person.Sex.MALE)
		    .map(p -> p.getName())
		    .collect(Collectors.toList());
	
    }

    public static Averager averageAgeCollect(List<Person> persons) {
	return persons.stream()
		    .filter(p -> p.getGender() == Person.Sex.MALE)
		    .map(Person::getAge)
		    .collect(Averager::new, Averager::accept, Averager::combine);
	
    }

}
