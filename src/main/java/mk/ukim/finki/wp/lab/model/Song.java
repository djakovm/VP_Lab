package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
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
    List<Artist> performers;
    List<Integer> ratings;

    public Song(String trackId, String title, String genre, int releaseYear) {
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }

    public void addRating(int rating) {
        ratings.add(rating);
    }

    public double getRating() {
        return ratings.stream().mapToInt(r -> r).average().orElse(0.0);
    }

    public void addPerformer(Artist artist) {
        performers.add(artist);
    }
}
