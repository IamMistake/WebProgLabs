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

//@WebServlet(name = "EventBookingServlet", urlPatterns = "/eventBooking")
public class EventBookingServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final EventService eventService;

    public EventBookingServlet(SpringTemplateEngine springTemplateEngine, EventService eventService) {
        this.springTemplateEngine = springTemplateEngine;
        this.eventService = eventService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        String eventName = req.getParameter("eventName");
        int numTickets = Integer.parseInt(String.valueOf(req.getParameter("numTickets")));

        Event eventByName = eventService.findEventByName(eventName).orElse(null);
        context.setVariable("event", eventByName);
        context.setVariable("numTickets", numTickets);

        springTemplateEngine.process("bookingConfirmation.html", context, resp.getWriter());

    }

}
