//package mk.ukim.finki.wp.lab.web;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.HttpConstraint;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.ukim.finki.wp.lab.model.Artist;
//import mk.ukim.finki.wp.lab.model.Song;
//import mk.ukim.finki.wp.lab.repository.ArtistRepository;
//import mk.ukim.finki.wp.lab.service.ArtistService;
//import mk.ukim.finki.wp.lab.service.SongService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet("/artist")
//
//public class ArtistServlet extends HttpServlet {
//
//    @Autowired
//    private  ArtistService artistService;
//    @Autowired
//    private SongService songService;
//    @Autowired
//    private SpringTemplateEngine springTemplateEngine;
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String trackId = req.getParameter("trackId");
//        if (trackId == null || trackId.isEmpty()) {
//            System.out.println("trackId is null" + trackId);
//        }
//
//        Song song = songService.findByTrackId(trackId);
//        if (song == null) {
//            System.out.println("Song not found for trackId: " + trackId);
//
//        }
//        int rating = Integer.parseInt(req.getParameter("rating"));
//
//        song.addRating(rating);
//
//        List<Artist> artists = artistService.listArtists();
//
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//
//        WebContext context = new WebContext(webExchange);
//
//        context.setVariable("artists", artists);
//        context.setVariable("song", song);
//
//        springTemplateEngine.process("artistsList.html", context, resp.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
//    }
//}
