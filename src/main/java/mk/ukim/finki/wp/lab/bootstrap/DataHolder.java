package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.ArtistRepository;
import mk.ukim.finki.wp.lab.repository.SongRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class DataHolder {
    public final SongRepository songRepository;
    public final ArtistRepository artistRepository;
    public final AlbumRepository albumRepository;
    public static final AtomicLong songIdGenerator = new AtomicLong(1);

    public DataHolder(SongRepository songRepository, ArtistRepository artistRepository, AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
    }

    @PostConstruct
    public void init() {
        // Add Artists
        artistRepository.save(new Artist(1L, "Nirvana", "", "Legendary grunge pioneers"));
        artistRepository.save(new Artist(2L, "Queen", "", "Iconic rock legends"));
        artistRepository.save(new Artist(3L, "Pink", "Floyd", "Psychedelic rock innovators"));
        artistRepository.save(new Artist(4L, "Pearl", "Jam", "Seattle grunge icons"));
        artistRepository.save(new Artist(5L, "Muse", "", "Alternative rock band"));
        artistRepository.save(new Artist(6L, "Soundgarden", "", "Pioneers of grunge"));

        // Add Albums
        albumRepository.save(new Album(1L, "Nevermind", "Grunge", "1991", new ArrayList<>()));
        albumRepository.save(new Album(2L, "A Night at the Opera", "Rock", "1975", new ArrayList<>()));
        albumRepository.save(new Album(3L, "The Dark Side of the Moon", "Progressive Rock", "1973", new ArrayList<>()));
        albumRepository.save(new Album(4L, "Ten", "Grunge", "1991", new ArrayList<>()));
        albumRepository.save(new Album(5L, "Black Holes and Revelations", "Alternative Rock", "2006", new ArrayList<>()));

        // Add Songs
        Album nevermind = albumRepository.findById(1L).orElse(null);
        Album nightAtTheOpera = albumRepository.findById(2L).orElse(null);
        Album darkSide = albumRepository.findById(3L).orElse(null);
        Album ten = albumRepository.findById(4L).orElse(null);
        Album blackHoles = albumRepository.findById(5L).orElse(null);

        songRepository.save(new Song(songIdGenerator.getAndIncrement(), "1", "Smells Like Teen Spirit", "Grunge", 1991, nevermind, new ArrayList<>(), new ArrayList<>()));
        songRepository.save(new Song(songIdGenerator.getAndIncrement(), "2", "Bohemian Rhapsody", "Rock", 1975, nightAtTheOpera, new ArrayList<>(), new ArrayList<>()));
        songRepository.save(new Song(songIdGenerator.getAndIncrement(), "3", "Time", "Progressive Rock", 1973, darkSide, new ArrayList<>(), new ArrayList<>()));
        songRepository.save(new Song(songIdGenerator.getAndIncrement(), "4", "Alive", "Grunge", 1991, ten, new ArrayList<>(), new ArrayList<>()));
        songRepository.save(new Song(songIdGenerator.getAndIncrement(), "5", "Starlight", "Alternative Rock", 2006, blackHoles, new ArrayList<>(), new ArrayList<>()));
        songRepository.save(new Song(songIdGenerator.getAndIncrement(), "6", "Come As You Are", "Grunge", 1991, nevermind, new ArrayList<>(), new ArrayList<>()));
        songRepository.save(new Song(songIdGenerator.getAndIncrement(), "7", "Somebody to Love", "Rock", 1976, nightAtTheOpera, new ArrayList<>(), new ArrayList<>()));
        songRepository.save(new Song(songIdGenerator.getAndIncrement(), "8", "Money", "Progressive Rock", 1973, darkSide, new ArrayList<>(), new ArrayList<>()));
        songRepository.save(new Song(songIdGenerator.getAndIncrement(), "9", "Even Flow", "Grunge", 1991, ten, new ArrayList<>(), new ArrayList<>()));
        songRepository.save(new Song(songIdGenerator.getAndIncrement(), "10", "Supermassive Black Hole", "Alternative Rock", 2006, blackHoles, new ArrayList<>(), new ArrayList<>()));
        songRepository.save(new Song(songIdGenerator.getAndIncrement(), "11", "Lithium", "Grunge", 1991, nevermind, new ArrayList<>(), new ArrayList<>()));
        songRepository.save(new Song(songIdGenerator.getAndIncrement(), "12", "Love of My Life", "Rock", 1975, nightAtTheOpera, new ArrayList<>(), new ArrayList<>()));
        songRepository.save(new Song(songIdGenerator.getAndIncrement(), "13", "Brain Damage", "Progressive Rock", 1973, darkSide, new ArrayList<>(), new ArrayList<>()));
        songRepository.save(new Song(songIdGenerator.getAndIncrement(), "14", "Black", "Grunge", 1991, ten, new ArrayList<>(), new ArrayList<>()));
        songRepository.save(new Song(songIdGenerator.getAndIncrement(), "15", "Knights of Cydonia", "Alternative Rock", 2006, blackHoles, new ArrayList<>(), new ArrayList<>()));
    }
}
