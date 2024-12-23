package mk.ukim.finki.wp.kol2024g1.repository;

import mk.ukim.finki.wp.kol2024g1.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface JpaPaginationReservationRepository<Reservation, Long> extends JpaRepository<Reservation, Long> {
    Page<Reservation> findAll(Specification<Reservation> filter, Pageable pageable);
}
