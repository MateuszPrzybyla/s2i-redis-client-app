package com.mateuszprzybyla.playground.openshift.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PeopleReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeopleReader.class);
    private final PersonCacheDao personCacheDao;

    @Autowired
    public PeopleReader(PersonCacheDao personCacheDao) {
        this.personCacheDao = personCacheDao;
    }

    @Scheduled(fixedRate = 1000, initialDelay = 0)
    public void readAllPeople() {
        LOGGER.info("Got person from cache: {}", personCacheDao.findById("Mateusz"));
    }
}
