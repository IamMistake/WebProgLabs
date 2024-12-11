package mk.ukim.finki.wp.laba.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.laba.model.Event;
import mk.ukim.finki.wp.laba.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EventListServlet", urlPatterns = "")
public class EventListServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final EventService eventService;

    public EventListServlet(SpringTemplateEngine springTemplateEngine, EventService eventService) {
        this.springTemplateEngine = springTemplateEngine;
        this.eventService = eventService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        context.setVariable("events", eventService.listAll());

        springTemplateEngine.process("listEvents.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        String filterByName = req.getParameter("filterByName");
        String filterByRating1 = req.getParameter("filterByRating");

        Double filterByRating = null;
        if (filterByRating1 != null && !filterByRating1.isEmpty()) {
            filterByRating = Double.parseDouble(filterByRating1);
        }

        if (filterByName != null && !filterByName.isEmpty() && filterByRating != null && !filterByRating.isNaN()) {
            List<Event> byName = eventService.searchEvents(filterByName);
            List<Event> eventsByRating = eventService.findEventsByRating(filterByRating);
            context.setVariable("events", byName.stream().filter(eventsByRating::contains).toList());
        } else if (filterByName != null && !filterByName.isEmpty()) {
            context.setVariable("events", eventService.searchEvents(filterByName));
        } else if (filterByRating != null && !filterByRating.isNaN()) {
            context.setVariable("events", eventService.findEventsByRating(filterByRating));
        } else {
            context.setVariable("events", eventService.listAll());
        }

        springTemplateEngine.process("listEvents.html", context, resp.getWriter());
    }
}
