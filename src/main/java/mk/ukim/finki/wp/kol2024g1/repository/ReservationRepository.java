package mk.ukim.finki.wp.kol2024g1.repository;

import mk.ukim.finki.wp.kol2024g1.model.Reservation;
import org.h2.mvstore.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaPaginationReservationRepository<Reservation, Long> {
}
