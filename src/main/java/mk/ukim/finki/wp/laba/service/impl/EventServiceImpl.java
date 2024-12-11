package mk.ukim.finki.wp.laba.service.impl;

import mk.ukim.finki.wp.laba.model.Event;
import mk.ukim.finki.wp.laba.model.EventBooking;
import mk.ukim.finki.wp.laba.repository.EventRepository;
import mk.ukim.finki.wp.laba.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return eventRepository.searchEvents(text);
    }

    @Override
    public Optional<Event> findEventByName(String name) {
        return eventRepository.findEventByName(name);
    }

    @Override
    public List<Event> findEventsByRating(double rating) {
        return eventRepository.filterEventByRating(rating);
    }

    @Override
    public void addEvent(Event event) {
        eventRepository.addEvent(event);
    }
}
