package com.github.eliux.dm;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "people")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 3, max = 32)
    private String name;

    private Integer age;

    public Person() {
        this("Anonymous");
    }

    public Person(String name) {
        this.name = name;
        this.age = 18;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
