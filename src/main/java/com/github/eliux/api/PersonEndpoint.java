package com.github.eliux.api;

import com.github.eliux.dm.Person;
import com.github.eliux.dm.persistence.PersonRepository;
import com.github.eliux.validations.ElementNotFoundException;
import com.github.eliux.validations.InvalidDataException;
import com.github.eliux.validations.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

@RestController
@RequestMapping("/people")
@ControllerAdvice
public class PersonEndpoint  extends ResponseEntityExceptionHandler {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public Iterable<Person> getAll() {
        return personRepository.findAll();
    }

    @GetMapping("/name/{personName}")
    public Person findByName(@PathVariable String personName) {
        return personRepository.findByName(personName)
                .orElseThrow(ElementNotFoundException::new);
    }

    @GetMapping("/age/{age}")
    public Person findByAge(@PathVariable Integer age) {
        return personRepository.findByAge(age)
                .orElseThrow(ElementNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@RequestBody Person person) {
        Preconditions.checkNotNull(person);
        return personRepository.save(person);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Person updatePerson(@RequestBody Person person, @PathVariable Long id) {
        Optional.ofNullable(person.getId())
                .filter(id::equals)
                .orElseThrow(InvalidDataException::new);
        personRepository.findById(id)
                .orElseThrow(ElementNotFoundException::new);
        return personRepository.save(person);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        final Person existingPerson = personRepository.findById(id)
                .orElseThrow(ElementNotFoundException::new);
        personRepository.deleteById(id);
    }

    @ExceptionHandler({ ElementNotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Person not found",
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
