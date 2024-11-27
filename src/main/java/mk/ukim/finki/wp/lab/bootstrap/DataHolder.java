package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class DataHolder {
    public static List<Song> songs = null;
    public static List<Artist> artists = null;
    public static List<Album> albums = null;
    public static final AtomicLong songIdGenerator = new AtomicLong(1);

    @PostConstruct
    public void init() {

        artists = new ArrayList<>(5);
        artists.add(new Artist(1L, "ABBA", "", "Popular pop band"));
        artists.add(new Artist(2L, "Freddie", "Mercury", "Popular rock band"));
        artists.add(new Artist(3L, "Pink", "Floyd", "Popular rock band"));
        artists.add(new Artist(4L, "AC", "DC", "Popular rock band"));
        artists.add(new Artist(5L, "MUSE", "", "Popular rock band"));
        artists.add(new Artist(5L, "Ekaterina", "Velika", "Popular rock band"));

        albums = new ArrayList<>(5);
        albums.add(new Album(1L, "Album 1", "Rock", "1990", new ArrayList<>()));
        albums.add(new Album(2L, "Album 2", "Pop", "2000", new ArrayList<>()));
        albums.add(new Album(3L, "Album 3", "Jazz", "2010", new ArrayList<>()));
        albums.add(new Album(4L, "Album 4", "Classical", "1980", new ArrayList<>()));
        albums.add(new Album(5L, "Album 5", "Electronic", "2020", new ArrayList<>()));

        songs = new ArrayList<>(5);
        songs.add(new Song(songIdGenerator.getAndIncrement(), "1", "Time", "Rock", 1974, albums.get(0), new ArrayList<Artist>(), new ArrayList<>()));
        songs.add(new Song(songIdGenerator.getAndIncrement(), "2", "Mama Mia", "Pop", 1980, albums.get(1), new ArrayList<>(), new ArrayList<>()));
        songs.add(new Song(songIdGenerator.getAndIncrement(), "3", "Supremacy", "Rock", 2013,albums.get(2), new ArrayList<>(), new ArrayList<>()));
        songs.add(new Song(songIdGenerator.getAndIncrement(), "4", "Krug", "Rock", 1976, albums.get(3),new ArrayList<>(), new ArrayList<>()));
        songs.add(new Song(songIdGenerator.getAndIncrement(), "5", "Crazy Little Thing Called Love", "Rock", 1971,albums.get(4), new ArrayList<>(), new ArrayList<>()));

    }
}
