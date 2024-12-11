package mk.ukim.finki.wp.laba.repository;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.laba.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventBookingRepository {

    List<EventBooking> bookingList;

    @PostConstruct
    public void init() {
        bookingList = new ArrayList<>();
        bookingList.add(new EventBooking("Tech Conference", "Alice Johnson", "123 Tech Street, NY", 2L));
        bookingList.add(new EventBooking("Music Festival", "Bob Smith", "456 Harmony Lane, LA", 4L));
        bookingList.add(new EventBooking("Art Exhibition", "Clara Brown", "789 Canvas Road, SF", 1L));
        bookingList.add(new EventBooking("Science Fair", "David Wilson", "101 Lab Drive, TX", 3L));
        bookingList.add(new EventBooking("Book Launch", "Eva Green", "202 Novel Avenue, NY", 2L));
        bookingList.add(new EventBooking("Food Carnival", "Frank White", "303 Gourmet Blvd, LA", 5L));
        bookingList.add(new EventBooking("Sports Meet", "Grace Taylor", "404 Athlete Lane, TX", 3L));
        bookingList.add(new EventBooking("Film Premiere", "Hank Harris", "505 Cinema Street, SF", 2L));
        bookingList.add(new EventBooking("Charity Gala", "Ivy Morgan", "606 Charity Circle, NY", 1L));
        bookingList.add(new EventBooking("Startup Pitch", "Jack Carter", "707 Innovation Park, LA", 4L));
    }

    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, Long numberOfTickets) {
        EventBooking eventBooking = new EventBooking(eventName, attendeeName, attendeeAddress, numberOfTickets);
        bookingList.add(eventBooking);
        return eventBooking;
    }
}
