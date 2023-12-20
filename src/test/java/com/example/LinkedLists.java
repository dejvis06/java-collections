package com.example;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

@SpringBootTest
public class LinkedLists {

    private final Logger logger = LoggerFactory.getLogger(LinkedLists.class);

    /**
     * LinkedList is more efficient than ArrayList when the size changes frequently because it doesn't require reallocation of memory.
     * In LinkedList, each element is stored in a separate node with references to the next and previous nodes
     */
    @Test
    void contextLoads() throws InterruptedException {
        Playlist playlist = new Playlist();

        playlist.addSong("Song1", "Artist1");
        playlist.addSong("Song2", "Artist2");
        playlist.addSong("Song3", "Artist3");

        logger.info("Playlist:");
        playlist.displayPlaylist();

        playlist.removeSong("Song2");

        logger.info("\nPlaylist after removing Song2:");
        playlist.displayPlaylist();

        int i = playlist.size();
        do {
            playlist.playRandomSong();
            --i;
        } while (i >= 0);
    }

    class Playlist {
        private LinkedList<Song> songs;
        private final Random random;
        private final Set<Integer> playedIndexes;

        Playlist() {
            this.songs = new LinkedList<>();
            random = new Random();
            playedIndexes = new HashSet<>();
        }

        void addSong(String title, String artist) {
            Song newSong = new Song(title, artist);
            songs.addFirst(newSong);
        }

        void removeSong(String title) {
            songs.removeIf(song -> song.getTitle().equals(title));
        }

        void displayPlaylist() {
            for (Song song : songs) {
                logger.info("Title: {}, Artist: {}", song.getTitle(), song.getArtist());
            }
        }

        int size() {
            return songs.size();
        }

        void playRandomSong() {
            if (!songs.isEmpty()) {
                int playlistSize = songs.size();

                // Ensure there are still unplayed indexes
                if (playedIndexes.size() < playlistSize) {
                    int randomIndex;
                    do {
                        randomIndex = random.nextInt(playlistSize);
                    } while (playedIndexes.contains(randomIndex));

                    playedIndexes.add(randomIndex);

                    Song randomSong = songs.get(randomIndex);
                    logger.info("\nNow playing: {} by {}", randomSong.getTitle(), randomSong.getArtist());
                } else {
                    logger.info("\nAll songs have been played. Resetting the playlist.");
                    playedIndexes.clear();
                }
            } else {
                logger.info("\nPlaylist is empty. Add some songs first.");
            }
        }
    }

    class Song {
        private String title;
        private String artist;

        Song(String title, String artist) {
            this.title = title;
            this.artist = artist;
        }

        String getTitle() {
            return title;
        }

        String getArtist() {
            return artist;
        }
    }
}
