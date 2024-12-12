package mk.ukim.finki.wp.lab.service.Impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.SongRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {
    public final SongRepository songRepository;
    public final AlbumRepository albumRepository;

    public SongServiceImpl(SongRepository songRepository, AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {

        if (!song.getPerformers().contains(artist)) {
            song.getPerformers().add(artist);
            songRepository.save(song);
        }
        return artist;
    }
    @Transactional
    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId)
                .orElseThrow(() -> new IllegalArgumentException("Song not found for trackId: " + trackId));
    }



    @Override
    public Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }

    @Override
    public void addSong(Song song) {
        songRepository.save(song);
    }

    @Override
    public void editSong(Long songId, String title, String trackId, String genre, int releaseYear, Long albumId) {
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new IllegalArgumentException("Song with ID " + songId + " not found"));
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new IllegalArgumentException("Album with ID " + albumId + " not found"));

        song.setTitle(title);
        song.setTrackId(trackId);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(album);

        songRepository.save(song);
    }

    @Override
    public void deleteSongById(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public void saveSong(String title, String trackId, String genre, int releaseYear, Long albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new IllegalArgumentException("Album not found"));

        Song song = new Song(title, trackId, genre, releaseYear);
        song.setId(DataHolder.songIdGenerator.getAndIncrement());
        song.setAlbum(album);
        song.setReviews(new ArrayList<>());
        song.setPerformers(new ArrayList<>());

        songRepository.save(song);
    }

    @Override
    public List<String> getGenres() {
        return songRepository.getGenres();
    }

    @Override
    public List<Song> getSongsByGenre(String genre) {
        return songRepository.findAllByGenre(genre);
    }

    @Override
    public List<Song> getSongsByAlbum(Long albumId) {
        return songRepository.findAllByAlbum_Id(albumId);
    }
}
