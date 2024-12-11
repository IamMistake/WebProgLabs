package mk.ukim.finki.wp.laba.web.controller;

import mk.ukim.finki.wp.laba.model.Event;
import mk.ukim.finki.wp.laba.model.Location;
import mk.ukim.finki.wp.laba.service.EventService;
import mk.ukim.finki.wp.laba.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
