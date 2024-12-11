package mk.ukim.finki.wp.laba.service.impl;

import mk.ukim.finki.wp.laba.model.EventBooking;
import mk.ukim.finki.wp.laba.repository.EventBookingRepository;
import mk.ukim.finki.wp.laba.service.EventBookingService;
import org.springframework.stereotype.Service;

@Service
public class EventBookingServiceImpl implements EventBookingService {
    private final EventBookingRepository eventBookingRepository;

    public EventBookingServiceImpl(EventBookingRepository eventBookingRepository) {
        this.eventBookingRepository = eventBookingRepository;
    }

    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        return eventBookingRepository.placeBooking(eventName,attendeeName,attendeeAddress,Long.parseLong(String.valueOf(numberOfTickets)));
    }
}
