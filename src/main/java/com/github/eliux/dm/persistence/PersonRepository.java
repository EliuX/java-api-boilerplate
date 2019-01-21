package com.github.eliux.dm.persistence;

import com.github.eliux.dm.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    Optional<Person> findByName(String name);

    Optional<Person> findByAge(Integer age);
}
