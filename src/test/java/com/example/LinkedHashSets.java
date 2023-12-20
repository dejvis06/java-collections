package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@SpringBootTest
public class LinkedHashSets {

    /**
     * Demonstrates the usage of a LinkedHashSet for maintaining an ordered playlist.
     * The LinkedHashSet preserves the order of insertion, providing an ordered iteration of elements.
     * The Playlist class uses LinkedHashSet to store songs, and the Song class has overridden
     * equals() and hashCode() methods for proper handling in a set.
     */
    @Test
    void contextLoads() {

        Playlist playlist = new Playlist();

        // Adding songs
        playlist.addSong("Song1", "Artist1");
        playlist.addSong("Song2", "Artist2");
        playlist.addSong("Song3", "Artist3");

        // Displaying playlist
        System.out.println("Playlist:");
        playlist.displayPlaylist();

        // Removing a song
        playlist.removeSong("Song2");

        // Displaying updated playlist
        System.out.println("\nPlaylist after removing Song2:");
        playlist.displayPlaylist();

        // Playing random songs
        playlist.playRandomSongs();
    }

    class Playlist {
        private Set<Song> songs;

        public Playlist() {
            // Using LinkedHashSet to maintain insertion order
            this.songs = new LinkedHashSet<>();
        }

        public void addSong(String title, String artist) {
            Song newSong = new Song(title, artist);
            songs.add(newSong);
        }

        public void removeSong(String title) {
            songs.removeIf(song -> song.getTitle().equals(title));
        }

        public void displayPlaylist() {
            for (Song song : songs) {
                System.out.println("Title: " + song.getTitle() + ", Artist: " + song.getArtist());
            }
        }

        public void playRandomSongs() {
            System.out.println("\nPlaying Random Songs:");
            for (int i = 0; i < 3; i++) {
                Song randomSong = getRandomSong();
                System.out.println("Now playing: " + randomSong.getTitle() + " by " + randomSong.getArtist());
            }
        }

        private Song getRandomSong() {
            int playlistSize = songs.size();
            int randomIndex = (int) (Math.random() * playlistSize);
            int currentIndex = 0;

            for (Song song : songs) {
                if (currentIndex == randomIndex) {
                    return song;
                }
                currentIndex++;
            }

            // This should not happen, but return null if there is an issue
            return null;
        }
    }

    class Song {
        private String title;
        private String artist;

        public Song(String title, String artist) {
            this.title = title;
            this.artist = artist;
        }

        public String getTitle() {
            return title;
        }

        public String getArtist() {
            return artist;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Song otherSong = (Song) obj;
            return Objects.equals(title, otherSong.title) && Objects.equals(artist, otherSong.artist);
        }

        @Override
        public int hashCode() {
            return Objects.hash(title, artist);
        }
    }
}
