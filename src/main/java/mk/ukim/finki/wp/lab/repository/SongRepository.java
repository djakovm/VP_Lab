package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    // Custom query for finding a song by trackId
    Song findByTrackId(String trackId);

    // Optional: Additional custom query to find songs by album ID
    List<Song> findAllByAlbum_Id(Long albumId);
}
