# Java Collections

## LinkedLists and Playlist Management

This example demonstrates the use of a LinkedList for managing a playlist of songs. The LinkedList offers advantages when the playlist size changes frequently, avoiding reallocation of memory.

### LinkedList Efficiency

### Dynamic Size Changes
LinkedList is more efficient than ArrayList when the size changes frequently. Unlike ArrayList, LinkedList doesn't require reallocation of memory. Each element in LinkedList is stored in a separate node with references to the next and previous nodes, providing flexibility in dynamic size changes.

### Playlist Management

#### Playlist Class
The `Playlist` class includes methods for adding, removing, displaying, and playing random songs in the playlist. It uses a LinkedList to store songs, a Random object for randomization, and a Set to keep track of played indexes.

##### Play Random Song
The `playRandomSong` method selects a random song from the playlist, ensuring each song is played only once before resetting the playlist.

### Example Usage in `contextLoads` Test
The `contextLoads` test demonstrates the playlist management functionalities, including adding songs, removing a song, displaying the playlist, and playing random songs.

```java
@Test
void contextLoads() throws InterruptedException {
    Playlist playlist = new Playlist();

    // Add songs to the playlist
    playlist.addSong("Song1", "Artist1");
    playlist.addSong("Song2", "Artist2");
    playlist.addSong("Song3", "Artist3");

    // Display the playlist
    logger.info("Playlist:");
    playlist.displayPlaylist();

    // Remove a song
    playlist.removeSong("Song2");
    logger.info("\nPlaylist after removing Song2:");
    playlist.displayPlaylist();

    // Play random songs
    int i = playlist.size();
    do {
        playlist.playRandomSong();
        --i;
    } while (i >= 0);
}
```
