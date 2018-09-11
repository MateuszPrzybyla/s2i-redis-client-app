package com.mateuszprzybyla.playground.openshift.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "people", timeToLive = 15)
public class Person {

    @Id
    private final String name;
    private final int age;
    private final String country;

    public Person(String name, int age, String country) {
        this.name = name;
        this.age = age;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return name + " (age: " + age + ", country: " + country + ")";
    }
}
