package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {
    List<Song> listSongs();
    Artist addArtistToSong(Artist artist, Song song);
    public Song findByTrackId(String trackId);
    public Optional<Song> findById(Long id);
    public void addSong(Song song);

    void editSong(Long songId, String title, String trackId, String genre, int releaseYear, Long albumId);

    void deleteSongById(Long id);

    void saveSong(String title, String trackId, String genre, int releaseYear, Long albumId);
}
