package com.github.eliux.dm.persistence;

import com.github.eliux.dm.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
public class PersonRepositoryWithMockingTest {

    @MockBean
    private PersonRepository personRepository;

    @Test
    public void whenPersonSavedAndFindByName_thenReturnPerson2() {
        //Given
        Mockito.when(personRepository.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(new Person("Ronald McDonald's")));
        Mockito.when(personRepository.findByName(ArgumentMatchers.contains("Doe")))
                .thenReturn(Optional.of(new Person("Jon Doe")));

        //When
        final Optional<Person> foundPerson = personRepository.findByName("Jon Doe");
        final Optional<Person> foundPerson2 = personRepository.findByName("Anyone");

        //Then
        Assert.assertEquals(
                "Unexpected found person",
                "Jon Doe",
                foundPerson.get().getName()
        );

        Assert.assertEquals(
                "Unexpected second person found",
                "Ronald McDonald's",
                foundPerson2.get().getName()
        );
    }
}