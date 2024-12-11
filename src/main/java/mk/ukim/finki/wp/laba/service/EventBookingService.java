package mk.ukim.finki.wp.laba.service;

import mk.ukim.finki.wp.laba.model.EventBooking;

public interface EventBookingService{
    EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets);
}
