package com.github.eliux.dm.persistence;

import com.github.eliux.dm.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void whenPersonSavedAndFindByName_thenReturnPerson() {
        //Given
        final Person samplePerson = new Person("Jon Doe");
        entityManager.persist(samplePerson);
        entityManager.flush();

        //When
        final Optional<Person> foundPerson = personRepository.findByName("Jon Doe");

        //Then
        Assert.assertTrue("The person was not saved", foundPerson.isPresent());
        Assert.assertEquals(
                "Unexpected found person",
                samplePerson.getName(),
                foundPerson.get().getName()
        );
    }
}