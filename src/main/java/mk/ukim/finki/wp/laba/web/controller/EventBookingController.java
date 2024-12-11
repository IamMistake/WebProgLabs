package mk.ukim.finki.wp.laba.web.controller;

import mk.ukim.finki.wp.laba.model.Event;
import mk.ukim.finki.wp.laba.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/eventBooking")
public class EventBookingController {

    private final EventService eventService;

    public EventBookingController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String eventBooking(
            @RequestParam String eventName,
            @RequestParam Integer numTickets,
            Model model
    ) {
        Event eventByName = eventService.findEventByName(eventName).orElse(null);
        if (eventByName == null) {
            return "redirect:/eventBooking";
        }
        model.addAttribute("event", eventByName);
        model.addAttribute("numTickets", numTickets);

        return "bookingConfirmation";
    }
}
