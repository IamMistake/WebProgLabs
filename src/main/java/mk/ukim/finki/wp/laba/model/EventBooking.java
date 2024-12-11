package mk.ukim.finki.wp.laba.model;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventBooking {
    private String eventName;
    private String attendeeName;
    private String attendeeAddress;
    private Long numberOfTickets;

    @Override
    public String toString() {
        return String.format("%s %s %s %d", eventName, attendeeName, attendeeAddress, numberOfTickets);
    }
}
