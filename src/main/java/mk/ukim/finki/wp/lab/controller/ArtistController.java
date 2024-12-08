package mk.ukim.finki.wp.lab.controller;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/artist")
public class ArtistController {

    private final ArtistService artistService;
    private final SongService songService;

    public ArtistController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @GetMapping
    public String getArtistsForSong(
            @RequestParam String trackId,
            @RequestParam int rating,
            Model model) {
        Song song = songService.findByTrackId(trackId);
        if (song == null) {
            model.addAttribute("error", "Song not found for trackId: " + trackId);
            return "error";
        }

        song.addRating(rating);

        List<Artist> artists = artistService.listArtists();

        model.addAttribute("artists", artists);
        model.addAttribute("song", song);

        return "artistsList";
    }

    @PostMapping
    public String addArtistToSong(
            @RequestParam String trackId,
            @RequestParam Long artistId,
            Model model) {
        Song song = songService.findByTrackId(trackId);
        Optional<Artist> artist = artistService.findById(artistId);

        if (song == null || artist.isEmpty()) {
            model.addAttribute("error", "Invalid song or artist");
            return "error";
        }

        song.addPerformer(artist.get());

        return "redirect:/songs";
    }
}
