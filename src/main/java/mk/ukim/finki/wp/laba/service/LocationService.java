package mk.ukim.finki.wp.laba.service;

import mk.ukim.finki.wp.laba.model.Location;
import java.util.List;
import java.util.Optional;


public interface LocationService {
    List<Location> findAll();
    Optional<Location> findById(Long id);
}
