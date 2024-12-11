package mk.ukim.finki.wp.laba.service;

import mk.ukim.finki.wp.laba.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text);
    Optional<Event> findEventByName(String name);
    List<Event> findEventsByRating(double rating);
    void addEvent(Event event);
}
