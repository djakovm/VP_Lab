package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
