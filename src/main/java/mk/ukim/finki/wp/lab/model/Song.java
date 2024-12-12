package mk.ukim.finki.wp.lab.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String trackId;
    String title;
    String genre;
    int releaseYear;
    @ManyToOne
    Album album;
    @ManyToMany
    List<Artist> performers;
    @OneToMany
    List<Review> reviews;

    public Song(String trackId, String title, String genre, int releaseYear) {
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public double getRating() {
        return reviews.stream().map(Review::getRating).mapToDouble(r -> r).average().orElse(0.0);
    }

    public void addPerformer(Artist artist) {
        performers.add(artist);
    }
}
