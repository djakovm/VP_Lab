package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SongRepository {

    public List<Song> findAll() {
        return DataHolder.songs;
    }

    public Song findBtTrackId(String trackId) {
        return DataHolder.songs.stream().filter(song -> song.getTrackId().equals(trackId)).findFirst().orElse(null);
    }

    public Artist addArtistToSong(Artist artist, Song song) {
        if (!song.getPerformers().contains(artist))
            song.getPerformers().add(artist);
        return artist;
    }

    public void addSong(Song song) {
        DataHolder.songs.add(song);
    }

    public void removeSong(Song song) {
        DataHolder.songs.remove(song);
    }

    public Optional<Song> findById(Long id) {
        return DataHolder.songs.stream().filter(song -> song.getId().equals(id)).findFirst();
    }

    public Optional<Song> editSong(Long songId, String title, String trackId, String genre, int releaseYear) {
        Optional<Song> song = findById(songId);
        if (song.isPresent()) {
            song.get().setTitle(title);
            song.get().setTrackId(trackId);
            song.get().setGenre(genre);
            song.get().setReleaseYear(releaseYear);
        } else {
            throw new IllegalArgumentException("Song with ID " + songId + " does not exist.");
        }
        return song;
    }

}
