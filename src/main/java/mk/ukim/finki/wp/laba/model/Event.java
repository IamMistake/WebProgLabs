package mk.ukim.finki.wp.laba.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor
public class Event {
    private String name;
    private String description;
    private double popularityScore;
    private long id;
    private Location location;

    public Event(String name, String description, double popularityScore) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.id = (long) Math.random() * 1000;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Description: %s, Rating: %.2f, Location: %s", name, description, popularityScore, location.getName());
    }
}
