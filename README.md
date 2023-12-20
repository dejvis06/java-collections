# Java Collections

All classes can be found by the collection name in plural under the test package.

## LinkedLists and Playlist Management

This example demonstrates the use of a LinkedList for managing a playlist of songs. The LinkedList offers advantages when the playlist size changes frequently, avoiding reallocation of memory.

### LinkedList Efficiency in Dynamic Size Changes
LinkedList is more efficient than ArrayList when the size changes frequently. Unlike ArrayList, LinkedList doesn't require reallocation of memory. Each element in LinkedList is stored in a separate node with references to the next and previous nodes, providing flexibility in dynamic size changes.

### Playlist Management

#### Playlist Class
The `Playlist` class includes methods for adding, removing, displaying, and playing random songs in the playlist. It uses a LinkedList to store songs, a Random object for randomization, and a Set to keep track of played indexes.

##### Play Random Song
The `playRandomSong` method selects a random song from the playlist, ensuring each song is played only once before resetting the playlist.

#### Example Usage in `contextLoads` Test
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

## Understanding hashCode(), equals(), and HashSet in Java

In Java, the `hashCode()` and `equals()` methods are crucial for managing object equality, and the `HashSet` collection leverages these methods for efficient storage and retrieval of unique elements.

#### User Class Implementation

```java
public class HashSets {

    /**
     * The User class has an overridden hashCode() method that computes the hash code based on the username field.
     * The equals() method checks for equality based on the username.
     * The HashSet is used to store User objects.
     * When checking for the existence of a user using contains, the HashSet uses the overridden hashCode()
     * and equals() methods to determine whether an equivalent user exists in the set.
     */
    @Test
    void contextLoads() {
        Set<User> userSet = new HashSet<>();

        // Adding users
        User johnDoe = new User("john_doe");
        User janeDoe = new User("jane_doe");

        userSet.add(johnDoe);
        userSet.add(janeDoe);

        // Checking existence
        User queriedUser = new User("john_doe");
        boolean exists = userSet.contains(queriedUser);

        System.out.println("User 'john_doe' exists: " + exists);
    }

    class User {
        private String username;

        public User(String username) {
            this.username = username;
        }

        @Override
        public int hashCode() {
            return Objects.hash(username);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            User otherUser = (User) obj;
            return Objects.equals(username, otherUser.username);
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    '}';
        }
    }
}
```
## LinkedHashSet and Playlist

This Java example demonstrates the usage of a `LinkedHashSet` for maintaining an ordered playlist. The `LinkedHashSet` is a collection that preserves the order of insertion, providing an ordered iteration of elements. The primary focus is on managing a playlist of songs.

#### Playlist Class

The `Playlist` class serves as a manager for the song playlist. It employs a `LinkedHashSet` to store songs, ensuring that the order of song insertion is maintained. Key functionalities include adding songs, removing songs, displaying the playlist, and playing random songs.
```java
private Set<Song> songs;

public Playlist() {
    // Using LinkedHashSet to maintain insertion order
    this.songs = new LinkedHashSet<>();
}
```

hashCode() & equals() work the same as HashSet:
```java
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
```


