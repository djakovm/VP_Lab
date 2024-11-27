package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Album {
    private long id;
    private String name;
    private String genre;
    private String releaseYear;
    @OneToMany
    private List<Song> songs;
}
