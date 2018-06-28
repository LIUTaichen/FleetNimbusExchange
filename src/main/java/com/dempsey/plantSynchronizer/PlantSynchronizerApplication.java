package com.dempsey.plantSynchronizer;

import com.dempsey.plantSynchronizer.fleet.repository.NiggleRepository;
import com.dempsey.plantSynchronizer.fleet.repository.FleetPlantRepository;
import com.dempsey.plantSynchronizer.nimbus.dao.NimbusCivilPlantRepository;
import com.dempsey.plantSynchronizer.nimbus.service.CategorySyncService;
import com.dempsey.plantSynchronizer.nimbus.service.PlantSyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlantSynchronizerApplication  implements CommandLineRunner{

	private Logger log = LoggerFactory.getLogger(PlantSynchronizerApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(PlantSynchronizerApplication.class, args);
	}


	@Autowired
	PlantSyncService plantSyncService;

	@Autowired
	CategorySyncService categorySyncService;



	@Override
	public void run(String... args) throws Exception {
		categorySyncService.diff();
		plantSyncService.insertNewPlants();
	}
}