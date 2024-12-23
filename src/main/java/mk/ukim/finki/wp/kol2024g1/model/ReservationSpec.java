package mk.ukim.finki.wp.kol2024g1.model;

import org.springframework.data.jpa.domain.Specification;

public class ReservationSpec {
    public static Specification<Reservation> filterByGuestName(String guestName) {
        if (guestName == null || guestName.isEmpty()) {
            return null;
        }
        // TODO ce me dupi zaradi lowercase i da bidi contains
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("guestName"), String.format("%%%s%%", guestName));
    }

    public static Specification<Reservation> filterByRoomType(RoomType roomType) {
        if (roomType == null) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("roomType"), roomType.name());
    }

    public static Specification<Reservation> filterByHotel(Long hotel) {
        if (hotel == null) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("hotel").get("id"), hotel);
    }
}
