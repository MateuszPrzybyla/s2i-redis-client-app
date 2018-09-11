package com.mateuszprzybyla.playground.openshift.redis;

import org.springframework.data.repository.CrudRepository;

public interface PersonCacheDao extends CrudRepository<Person, String> {
}
