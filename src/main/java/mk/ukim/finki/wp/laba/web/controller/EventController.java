package mk.ukim.finki.wp.laba.web.controller;

import mk.ukim.finki.wp.laba.model.Event;
import mk.ukim.finki.wp.laba.model.Location;
import mk.ukim.finki.wp.laba.service.EventService;
import mk.ukim.finki.wp.laba.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final LocationService locationService;

    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping
    public String getEventsPage(
            @RequestParam(required = false) String error,
            Model model
    ) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        model.addAttribute("events", eventService.listAll());
        return "listEvents";
    }

    @GetMapping("/add-event")
    public String saveEvent(
            @RequestParam(required = false) String error,
            Model model
    ) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("error", error);
        }
        model.addAttribute("locations", locationService.findAll());
        return "add-event";
    }

    @PostMapping("/add")
    public String addEvent(
            @RequestParam String eventName,
            @RequestParam String eventDesc,
            @RequestParam Double eventScore,
            @RequestParam Long eventLocationId,
            Model model
    ) {

        Optional<Location> byId = locationService.findById(eventLocationId);
        if (byId.isEmpty()) {
            model.addAttribute("error", "Location not found");
            return "redirect:/events/add-event";
        }
        Event event = new Event(eventName, eventDesc, eventScore);
        event.setLocation(byId.get());

        eventService.addEvent(event);

        return "redirect:/events";
    }

    @GetMapping("/edit-event/{eventId}")
    public String getEditForm(@PathVariable Long eventId, Model model) {
        Optional<Event> eventById = eventService.findEventById(eventId);
        if (eventById.isEmpty()) {
            model.addAttribute("error", "Event not found");
            return "redirect:/events";
        }
        model.addAttribute("event", eventById.get());
        model.addAttribute("locations", locationService.findAll());
        return "add-event";
    }

    @PostMapping("/edit/{eventId}")
    public String editEvent(
            @PathVariable Long eventId,
            @RequestParam(required = false) String eventName,
            @RequestParam(required = false) String eventDesc,
            @RequestParam(required = false) Double eventScore,
            @RequestParam(required = false) String eventLocationId,
            Model model
    ) {
        Optional<Event> eventById = eventService.findEventById(eventId);
        if (eventById.isEmpty()) {
            model.addAttribute("error", "Event not found");
            return "redirect:/events";
        }
        Event event = eventById.get();

        if (eventName != null && !eventName.isEmpty()) event.setName(eventName);
        if (eventDesc != null && !eventDesc.isEmpty()) event.setDescription(eventDesc);
        if (eventScore != null) event.setPopularityScore(eventScore);
        if (eventLocationId != null) {
            Optional<Location> byId = locationService.findById(Long.valueOf(eventLocationId));
            byId.ifPresent(event::setLocation);
        }

        return "redirect:/events";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id, Model model) {
        Optional<Event> eventById = eventService.findEventById(id);
        if (eventById.isEmpty()) {
            model.addAttribute("error", "Event not found");
            return "redirect:/events";
        }

        Event event = eventById.get();

        // delete event
        eventService.deleteEvent(event);

        return "redirect:/events";
    }
}
