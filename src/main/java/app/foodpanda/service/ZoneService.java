package app.foodpanda.service;

import app.foodpanda.model.Zone;
import app.foodpanda.dto.ZoneDTO;
import app.foodpanda.repository.ZoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 Service class containing the methods with the application logic that uses data from the zone table
 @author Daria Miu
 */
@Service
public class ZoneService {

    @Autowired
    ZoneRepository zoneRepository;

    Logger logger = LoggerFactory.getLogger(ZoneService.class);

    /**
     * Method to find all zones in the zone table in the database
     * @return list of ZoneDTO objects, ZoneDTO object contains only the information relevant to be display in the
     * fronted from a Restaurant object
     */
    public List<ZoneDTO> findAll(){

        List<Zone> zones = zoneRepository.findAll();
        if(zones.isEmpty()){
            logger.warn("There are no zones in the database");
        }
        List<ZoneDTO> zonesDTO = new ArrayList<>();
        for (Zone zone : zones) {
            zonesDTO.add(new ZoneDTO(zone.getName()));
        }
        return zonesDTO;
    }
}
