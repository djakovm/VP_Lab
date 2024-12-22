package mk.ukim.finki.wp.lab.controller;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.AlbumRepository;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/songs")
public class SongController {
    public final SongService songService;
    public final AlbumRepository albumRepository;
    public final ArtistService artistService;
    public final AlbumService albumService;

    public SongController(SongService songService, AlbumRepository albumRepository, ArtistService artistService, AlbumService albumService) {
        this.songService = songService;
        this.albumRepository = albumRepository;
        this.artistService = artistService;
        this.albumService = albumService;
    }

    @GetMapping
    public String getSongsPage(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Long albumId,
            Model model) {
        List<Song> songs;
        if (albumId != null) {
            songs = songService.getSongsByAlbum(albumId);
        } else if (genre != null && !genre.equals("All")) {
            songs = songService.getSongsByGenre(genre);
        } else {
            songs = songService.listSongs();
        }

        model.addAttribute("songs", songs);
        model.addAttribute("error", error);
        List<String> genres = songService.getGenres();
        model.addAttribute("genres", genres);
        List<Album> albums = albumService.findAll();
        model.addAttribute("albums", albums);
        return "listSongs";
    }



    @GetMapping("/add")
    public String getAddSongForm(Model model) {
        model.addAttribute("albums", albumRepository.findAll());
        return "add-song";
    }

    @PostMapping("/add")
    public String saveSong(
            @RequestParam String title,
            @RequestParam String trackId,
            @RequestParam String genre,
            @RequestParam int releaseYear,
            @RequestParam Long albumId
    ) {
        songService.saveSong(title, trackId, genre, releaseYear, albumId);
        return "redirect:/songs";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/songs";
    }

    @GetMapping("/edit/{songId}")
    public String getEditSongForm(@PathVariable Long songId, Model model) {
        Optional<Song> song = songService.findById(songId);
        if (song.isEmpty() || !song.get().getId().equals(songId)) {
            return "redirect:/songs?error=Song not found";
        }

        model.addAttribute("song", song.get());
        model.addAttribute("albums", albumRepository.findAll());
        return "add-song";
    }

    @PostMapping("/edit/{songId}")
    public String updateSong(
            @PathVariable Long songId,
            @RequestParam String title,
            @RequestParam String trackId,
            @RequestParam String genre,
            @RequestParam int releaseYear,
            @RequestParam Long albumId
    ) {
        songService.editSong(songId, title, trackId, genre, releaseYear, albumId);
        return "redirect:/songs";
    }


    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id){
        songService.deleteSongById(id);
        return "redirect:/songs";
    }

    @PostMapping("/details")
    public String addArtistToSong(
            @RequestParam Long artistId,
            @RequestParam String trackId,
            Model model) {
        Optional<Artist> artist = artistService.findById(artistId);
        Song song = songService.findByTrackId(trackId);

        artist.ifPresent(value -> songService.addArtistToSong(value, song));

        model.addAttribute("song", song);
        return "songDetails";
    }


}
