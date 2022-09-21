package com.piseth.java.school.service;

import java.util.List;
import java.util.Map;

import com.piseth.java.school.model.Gender;
import com.piseth.java.school.model.Person;
import com.piseth.java.school.model.Pet;

public interface PersonService {
	Map<Gender, Long> countNumberOfPeopleByGender(List<Person> list);

	List<Person> findByNumberOfPetMoreThan(List<Person> list, int minNumber);

	List<Person> findByPetType(List<Person> list, Pet petType);

	Gender mostFavouriteGenderToHavePet(List<Person> list, Pet petType);

	boolean hasPersonWhoDoesNotHavePet(List<Person> list);

	Person youngestPerson(List<Person> list);
	
	List<Pet> findKindOfPet(List<Person> list);
	
	Map<Integer,List<Person>> groupByAge(List<Person> list);
	
    List<Person> findPerson18plus(List<Person> list) ;
    
    List<List<Person>> groupPeopleByKindOfPet(List<Person> list);
 
    List<Person> findPeople_LikeCat_UnlikeDog_Female_18to21(List<Person> people);
}
