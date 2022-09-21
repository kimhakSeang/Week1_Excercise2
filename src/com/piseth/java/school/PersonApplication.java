package com.piseth.java.school;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.piseth.java.school.model.Gender;
import com.piseth.java.school.model.Person;
import com.piseth.java.school.model.Pet;
import com.piseth.java.school.service.PersonService;
import com.piseth.java.school.service.PersonServiceImpl;

public class PersonApplication {
	
	private final PersonService personService = new PersonServiceImpl();
	
	private final List<Person> LIST_OF_PEOPLE = List.of(
			new Person("Dara", Gender.MALE, 18, List.of(Pet.CAT)),
			new Person("Thida", Gender.FEMALE, 17, List.of(Pet.DOG)),
			new Person("Dalin", Gender.FEMALE, 19, List.of(Pet.DOG, Pet.CAT)),
			new Person("Veasna", Gender.MALE, 16, List.of()),
			new Person("Sopheak", Gender.FEMALE, 17, List.of(Pet.BIRD)),
			new Person("Vannda", Gender.MALE, 21, List.of(Pet.BIRD)),
			new Person("Nary", Gender.FEMALE, 23, List.of(Pet.DOG, Pet.BIRD, Pet.CAT)),
			new Person("Pisey", Gender.FEMALE, 16, List.of(Pet.CAT)),
			new Person("Sovann", Gender.FEMALE, 18, List.of(Pet.CAT)),
			new Person("Vannary", Gender.FEMALE, 20, List.of(Pet.FISH))
			
			);

	public static void main(String[] args) {
		PersonApplication application = new PersonApplication();
		//Count number of people by gender
		application.showNumberOfPeopleByGender();
		//People Who Have Pet More Than 1 Type
		application.peopleWhoHavePetMoreThanOneType();
		//People Who have cat
		application.peopleWhoHaveCat();
		//Gender Which like cat the most
		application.showGenderOfPeopleWhoLikeCatTheMost();
		//Is there any person who doesn't have pet? 
		application.showIfThereIsPersonWhoDoesNotHavePet();
		//The youngest person is
		application.displayYoungestPerson();
	    // Count Kind of Pet 
		application.countKindOfPet();
        // Group People By Age
		application.groupPeopleByAge();
		// person's age higher than 18
		application.whoCanElection();
		// Group People By Kind Of Pet
		application.showPeopleByKindOfPet();
		// Person Like Cat, Don't Like Dog and 18+ years old
		application.showPersonLikeCat_UnlikeDog_Female_18to21();
	}
	
	// Count number of people by gender
	public void showNumberOfPeopleByGender() {
		System.out.println("\n\t=== Count number of people by gender ======");
		Map<Gender, Long> countNumberOfPeopleByGender = personService.countNumberOfPeopleByGender(LIST_OF_PEOPLE);
		System.out.println("\t"+countNumberOfPeopleByGender);
	}
	
	public void peopleWhoHavePetMoreThanOneType() {
		System.out.println("\n\t=== People Who Have Pet More Than 1 Type ===");
		List<Person> listPeople = personService.findByNumberOfPetMoreThan(LIST_OF_PEOPLE, 1);
		listPeople.forEach(p -> System.out.println("\t"+p.getName()));
	}
	
	public void peopleWhoHaveCat() {
		System.out.println("\n\t=========== People Who have cat ==========");
		List<Person> peopleHaveCat = personService.findByPetType(LIST_OF_PEOPLE, Pet.CAT);
		peopleHaveCat.forEach(System.out::println);
	}
	
	public void showGenderOfPeopleWhoLikeCatTheMost() {
		System.out.println("\n\t======= Gender Which like cat the most ======");
		Gender genderWhichLikeCatTheMost = personService.mostFavouriteGenderToHavePet(LIST_OF_PEOPLE, Pet.BIRD);
		System.out.println("\t"+genderWhichLikeCatTheMost);
	}
	
	public void showIfThereIsPersonWhoDoesNotHavePet() {
		System.out.println("\n\t= Is there any person who doesn't have pet? =");
		boolean hasPersonWhoDoesNotHavePet = personService.hasPersonWhoDoesNotHavePet(LIST_OF_PEOPLE);
		System.out.println("\t"+hasPersonWhoDoesNotHavePet);
	}
	
	public void displayYoungestPerson() {
		System.out.println("\n\t=========== The youngest person is ==========");
		Person youngestPerson = personService.youngestPerson(LIST_OF_PEOPLE);
		System.out.println("\t"+youngestPerson.getName());
	}
	
	public void countKindOfPet() {
		System.out.println("\n\t============ Count Kind Of Pet ==============");
		long numKind=personService.findKindOfPet(LIST_OF_PEOPLE).stream().count();
		System.out.println("\tKind Of Animal have "+numKind);
	}
	
	public void groupPeopleByAge() {
		System.out.println("\n\t============ Group People By Age =============");
		Map<Integer,List<Person>> groupByAge = personService.groupByAge(LIST_OF_PEOPLE);
	    List<Integer> listAges = ListAge(LIST_OF_PEOPLE);
	    for (Integer age : listAges) {
	    	System.out.println(groupByAge.get(age));
	    }
	}
	private List<Integer> ListAge(List<Person> list){
		List<Integer> listAge = list.stream()
			    .map(a->a.getAge())
			    .collect(Collectors.toList())
										    .stream()
										    .distinct()
										    .collect(Collectors.toList());
		return listAge;
	}
	public void whoCanElection() {
		System.out.println("\n\n\t========== Find Person Can Election ==========");
		List<Person> listPeple = personService.findPerson18plus(LIST_OF_PEOPLE);
		head();
		listPeple.forEach(p-> System.out.println(p.toString()));
	}
	
	
	public void  showPeopleByKindOfPet() {
		System.out.println("\n\n\t======== Group People By Kind Of Pets ========");
		List<List<Person>> people = personService.groupPeopleByKindOfPet(LIST_OF_PEOPLE);
		people.stream().peek(s->System.out.println("\n\t[ PET : "+s.get(0).getPets().get(0)+" ]")).forEach(p->System.out.println(p.toString()));
	}
	
	
	public void showPersonLikeCat_UnlikeDog_Female_18to21() {
		System.out.println("\n\n\t===== Find Person feed cat but don't feed dog ,her age between 18 to 21 ====");
		List<Person> listPeple = personService.findPeople_LikeCat_UnlikeDog_Female_18to21(LIST_OF_PEOPLE);
		head();
		listPeple.forEach(p-> System.out.println(p.toString()));
	}
	
	
	
	
	private void head() {
		System.out.println("\tName\t\tGender\tAge\tPet"
				        +"\n\t______________________________________________");
	}

}
