package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.SongRepository;
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
        return songRepository.addArtistToSong(artist, song);
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findBtTrackId(trackId);
    }

    @Override
    public Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }

    @Override
    public void addSong(Song song) {
        songRepository.addSong(song);
    }

    @Override
    public void editSong(Long songId, String title, String trackId, String genre, int releaseYear, Long albumId) {
        Optional<Album> album = albumRepository.findById(albumId);
        Optional<Song> song = songRepository.editSong(songId, title, trackId, genre, releaseYear);
        song.get().setAlbum(album.get());
    }

    @Override
    public void deleteSongById(Long id) {
        Song song = songRepository.findById(id).orElse(null);
        songRepository.removeSong(song);
    }

    @Override
    public void saveSong(String title, String trackId, String genre, int releaseYear, Long albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new IllegalArgumentException("Album not found"));

        Song song = new Song(title, trackId, genre, releaseYear);
        song.setId(DataHolder.songIdGenerator.getAndIncrement());
        song.setAlbum(album);
        song.setRatings(new ArrayList<>());
        song.setPerformers(new ArrayList<>());

        songRepository.addSong(song);
    }
}
