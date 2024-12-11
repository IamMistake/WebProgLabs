package mk.ukim.finki.wp.laba.repository;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.laba.model.Location;
import mk.ukim.finki.wp.laba.service.EventService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class LocationRepository {
    private List<Location> locations;
    private EventService eventService;

    @PostConstruct
    private void init() {
        locations = new ArrayList<>();
        locations.add(new Location("Tech Hall", "123 Tech Street, NY", "500", "A state-of-the-art conference hall."));
        locations.add(new Location("Harmony Park", "456 Harmony Lane, LA", "1000", "An open park ideal for music festivals."));
        locations.add(new Location("Art Gallery", "789 Canvas Road, SF", "200", "An intimate space for art exhibitions."));
        locations.add(new Location("Science Center", "101 Lab Drive, TX", "300", "A modern facility for science fairs."));
        locations.add(new Location("Cineplex Arena", "505 Cinema Street, SF", "1500", "A large theater perfect for film premieres."));
    }

    public List<Location> findAll() {
        return locations;
    }

    public Optional<Location> findById(Long id) {
        return locations.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst();
    }
}
