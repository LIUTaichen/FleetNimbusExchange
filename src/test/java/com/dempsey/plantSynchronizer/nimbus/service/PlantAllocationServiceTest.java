package com.dempsey.plantSynchronizer.nimbus.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PlantAllocationServiceTest {
    private Logger log = LoggerFactory.getLogger(PlantAllocationServiceTest.class);


    @Autowired
    private PlantAllocationService plantAllocationService;


    @Test
    public void findAll() throws Exception {
        plantAllocationService.findAll().forEach(p -> log.info(p.toString()));
    }

}