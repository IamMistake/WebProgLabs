package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SongRepository {
    List<Song> songs;

    @PostConstruct
    public void init() {
        songs = new ArrayList<Song>();
        List<Artist> artists = new ArrayList<Artist>();
        artists.add(new Artist((long) 6, "Trajko", "Trajkoski", "Pasino Ruvci!"));
        artists.add(new Artist((long) 7, "Cunko", "Petreski", "Sekirci!"));
        artists.add(new Artist((long) 1, "Nikola", "Jagurinoski", "Aren sum, ti?"));
        songs.add(new Song("1", "Pictures", "Pop", 1997, new ArrayList<>()));
        songs.add(new Song("2", "Tracking Time", "Rock", 1989, artists));
        songs.add(new Song("3", "Listen to me...", "Pop", 2001, new ArrayList<>()));
        songs.add(new Song("4", "Let me cook", "Hip hop", 2003, artists));
        songs.add(new Song("5", "He is cooking", "Black Metal", 2021, new ArrayList<>()));
//        songs.get(0).getReleaseYear()
    }

    public List<Song> findAll() {
        return songs;
    }

    public Optional<Song> findByTrackId(String trackId) {
        return songs.stream().filter(song -> song.getTrackId().equals(trackId)).findFirst();
    }

    public Artist addArtistToSong(Artist artist, Song song) {
        song.getPerformers().add(artist);
        return artist;
    }


}
