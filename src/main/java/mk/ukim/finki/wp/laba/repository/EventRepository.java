package mk.ukim.finki.wp.laba.repository;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.laba.model.Event;
import mk.ukim.finki.wp.laba.model.Location;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Repository
public class EventRepository {

    List<Event> eventList;

    @PostConstruct
    public void init() {
        eventList = new ArrayList<>();
        eventList.add(new Event("Tech Conference", "A conference about the latest in tech.", 85.5));
        eventList.add(new Event("Music Festival", "A festival featuring live bands and artists.", 92.0));
        eventList.add(new Event("Art Exhibition", "An exhibition of modern and contemporary art.", 78.3));
        eventList.add(new Event("Science Fair", "A showcase of innovative science projects.", 88.4));
        eventList.add(new Event("Book Launch", "Launch of a new bestselling novel.", 74.2));
        eventList.add(new Event("Food Carnival", "A carnival showcasing a variety of cuisines.", 95.0));
        eventList.add(new Event("Sports Meet", "An event for various athletic competitions.", 80.7));
        eventList.add(new Event("Film Premiere", "Premiere of a highly anticipated movie.", 90.1));
        eventList.add(new Event("Charity Gala", "A gala dinner to raise funds for a cause.", 83.6));
        eventList.add(new Event("Startup Pitch", "Pitch event for budding entrepreneurs.", 87.9));

        ArrayList<Location> locations = new ArrayList<>();
        locations.add(new Location("Tech Hall", "123 Tech Street, NY", "500", "A state-of-the-art conference hall."));
        locations.add(new Location("Harmony Park", "456 Harmony Lane, LA", "1000", "An open park ideal for music festivals."));
        locations.add(new Location("Art Gallery", "789 Canvas Road, SF", "200", "An intimate space for art exhibitions."));
        locations.add(new Location("Science Center", "101 Lab Drive, TX", "300", "A modern facility for science fairs."));
        locations.add(new Location("Cineplex Arena", "505 Cinema Street, SF", "1500", "A large theater perfect for film premieres."));

        Random random = new Random();
        eventList.forEach(event -> {
            Location randomLocation = locations.get(random.nextInt(locations.size()));
            event.setLocation(randomLocation);
        });
    }

    public List<Event> findAll() {
        return eventList;
    }

    public List<Event> searchEvents(String text) {
        return eventList.stream()
                .filter(e -> {
                    String celosno = e.getName() + " " + e.getDescription();
                    return celosno.contains(text);
                }).toList();
    }

    public Optional<Event> findEventByName(String name) {
        return eventList.stream()
                .filter(e -> e.getName().equals(name))
                .findFirst();
    }

    public List<Event> filterEventByRating(double rating) {
        return eventList.stream()
                .filter(e -> e.getPopularityScore() >= rating)
                .toList();
    }

    public void addEvent(Event event) {
        eventList.add(event);
    }

    public Optional<Event> findEventById(Long id) {
        return eventList.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }

    public void deleteEvent(Event event) {
        eventList.stream()
                .filter(e -> e.getId() == event.getId())
                .findFirst()
                .ifPresent(e -> eventList.remove(e));
    }
}
