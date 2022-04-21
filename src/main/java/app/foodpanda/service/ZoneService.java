package app.foodpanda.service;

import app.foodpanda.model.Restaurant;
import app.foodpanda.model.Zone;
import app.foodpanda.model.ZoneDTO;
import app.foodpanda.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ZoneService {

    @Autowired
    ZoneRepository zoneRepository;

    public List<ZoneDTO> findAll(){
        List<Zone> zones = zoneRepository.findAll();
        List<ZoneDTO> zonesDTO = new ArrayList<>();
        for (Zone zone : zones) {
            for (Restaurant restaurant : zone.getRestaurants()) {
                System.out.println(restaurant.getName());
            }
            zonesDTO.add(new ZoneDTO(zone.getName()));
        }
        System.out.println("stop ba");
        return zonesDTO;
    }
}
