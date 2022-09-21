package com.piseth.java.school.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.piseth.java.school.model.Gender;
import com.piseth.java.school.model.Person;
import com.piseth.java.school.model.Pet;

public class PersonServiceImpl implements PersonService{

	
	@Override
	public Map<Gender, Long> countNumberOfPeopleByGender(List<Person> list) {
		return list.stream()
			.collect(Collectors.groupingBy(Person::getGender, Collectors.counting()));
	}

	@Override
	public List<Person> findByNumberOfPetMoreThan(List<Person> list, int minNumber) {
		return list.stream()
			.filter(p -> p.getPets().size() > minNumber)
			.collect(Collectors.toList());
	}

	@Override
	public List<Person> findByPetType(List<Person> list, Pet petType) {
		return list.stream()
				.filter(p -> p.getPets().contains(petType))
				.collect(Collectors.toList());
	}

	@Override
	public Gender mostFavouriteGenderToHavePet(List<Person> list, Pet petType) {
		Map<Gender, Long> mapNumberOfPeople = countNumberOfPeopleByGender(list);
		long numberOfMale = mapNumberOfPeople.get(Gender.MALE);
		long numberOfFemale = mapNumberOfPeople.get(Gender.FEMALE);
		
		List<Person> peopleWhoHaveCat = findByPetType(list, petType);
		Map<Gender, Long> mapByGenderPeopleHaveCat = countNumberOfPeopleByGender(peopleWhoHaveCat);
		
		long numberOfMaleWhoHaveCat = mapByGenderPeopleHaveCat.get(Gender.MALE);
		long numberOfFemaleWhoHaveCat = mapByGenderPeopleHaveCat.get(Gender.FEMALE);
		
		double malePercentage = (double)numberOfMaleWhoHaveCat/numberOfMale;
		double femalePercentage = (double)numberOfFemaleWhoHaveCat/numberOfFemale;
		
		return malePercentage > femalePercentage ? Gender.MALE : Gender.FEMALE;
	}

	@Override
	public boolean hasPersonWhoDoesNotHavePet(List<Person> list) {
		return list.stream()
				.anyMatch(p -> p.getPets().isEmpty());
	}

	@Override
	public Person youngestPerson(List<Person> list) {
		return list.stream()
				.min((a,b) -> a.getAge() - b.getAge())
				.get();
	}

	@Override
	public List<Pet> findKindOfPet(List<Person> listPeople) {
		List<Pet> listPets =listPeople.stream()
									  .flatMap(p->p.getPets().stream())
									  .distinct().toList();
		return listPets;
	}

	@Override
	public  Map<Integer,List<Person>> groupByAge(List<Person> list) {
		Map<Integer, List<Person>> group = list.stream().collect(Collectors.groupingBy(p->p.getAge()));
	return group;
}
	@Override
	public List<Person> findPerson18plus(List<Person> list) {
		return list.stream().filter(p->p.getAge()>=18).collect(Collectors.toList());
	}

	@Override
	public List<List<Person>> groupPeopleByKindOfPet(List<Person> list) {
		List<Pet> listPets = findKindOfPet(list);
		List<List<Person>> group = new ArrayList<>();
		for(Pet pet : listPets) {
			 group.add(list.stream().filter(p->p.getPets().contains(pet)).collect(Collectors.toList()));
		}
		return group;
	}

	@Override
	public List<Person> findPeople_LikeCat_UnlikeDog_Female_18to21(List<Person> people) {
		return findPerson18plus(people).stream()
										.filter(p->p.getPets().contains(Pet.CAT)&&!p.getPets().contains(Pet.DOG))
										.filter(a->a.getAge()>=18&&a.getAge()<=21)
										.filter(g->g.getGender().equals(Gender.FEMALE))
										.collect(Collectors.toList());
	}
	
	

	
	

}
