package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/songDetails")
public class SongDetailsServlet extends HttpServlet {

    @Autowired
    private  SongService songService;
    @Autowired
    private ArtistService artistService;
    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String artistId = req.getParameter("artistId");
        Optional<Artist> artist = artistService.findById(Long.valueOf(artistId));

        String trackId = req.getParameter("trackId");
        Song song = songService.findByTrackId(trackId);

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        context.setVariable("song", song);

        songService.addArtistToSong(artist.get(), song);

        springTemplateEngine.process("songDetails.html", context, resp.getWriter());
    }

}
