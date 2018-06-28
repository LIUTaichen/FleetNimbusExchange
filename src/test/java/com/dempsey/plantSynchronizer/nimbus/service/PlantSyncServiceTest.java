package com.dempsey.plantSynchronizer.nimbus.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("dev")
public class PlantSyncServiceTest {

    @Autowired
    private PlantSyncService plantSyncService;
    @Test
    public void diff() throws Exception {
        plantSyncService.diff();
    }

}