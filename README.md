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

## HashSet, hashCode() and equals()

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

## TreeSets Example

In this example, we demonstrate the use of `TreeSet` for managing and displaying scheduled events. </br>
The use of `TreeSet` simplifies the management of events and allows for efficient retrieval in the desired order without the need for explicit sorting.

#### contextLoads() Method

The `contextLoads()` method is the entry point that schedules events and displays them in two different orders.
```java
@Test
void contextLoads() {
    // Creating an instance of the EventScheduler class to manage scheduled events
    EventScheduler scheduler = new EventScheduler();

    // Scheduling events with names, start dates, and durations
    scheduler.scheduleEvent("Meeting A", LocalDate.of(2023, 1, 10), 2);
    scheduler.scheduleEvent("Conference", LocalDate.of(2023, 1, 8), 3);
    scheduler.scheduleEvent("Lunch", LocalDate.of(2023, 1, 12), 1);
    scheduler.scheduleEvent("Meeting B", LocalDate.of(2023, 1, 11), 2);

    // Displaying scheduled events ordered by start time
    scheduler.displayScheduleByStartTime();

    // Displaying scheduled events ordered by duration in descending order
    scheduler.displayScheduleByDuration();
}
```

#### EventScheduler Class
The EventScheduler class manages the scheduling and display of events using two TreeSet instances.

```java
class EventScheduler {
// TreeSet to store events ordered by start time
private TreeSet<Event> eventSetByStartTime = new TreeSet<>(Comparator.comparing(Event::getStartTime));

    // TreeSet to store events ordered by duration in descending order
    private TreeSet<Event> eventSetByDuration = new TreeSet<>(Comparator.comparing(Event::getDuration).reversed());

    // Method to schedule an event with a given name, start time, and duration
    public void scheduleEvent(String eventName, LocalDate startTime, int duration) {
        Event newEvent = new Event(eventName, startTime, duration);
        eventSetByStartTime.add(newEvent);
    }
    
    // ... Other methods for displaying events (displayScheduleByStartTime, displayScheduleByDuration)
}
```

#### Use of `TreeSet`

- **eventSetByStartTime:** This `TreeSet` is used to store events and automatically orders them based on their start times in ascending order.

- **eventSetByDuration:** This `TreeSet` is used to store events and orders them based on their durations in descending order. The comparator is configured to achieve this ordering.

## PriorityQueues Example

#### Task Class

The `Task` class represents a task with a name and priority. It implements the `Comparable` interface to define the natural ordering based on priority.

```java
class Task implements Comparable<Task> {
    private String name;
    private int priority;

    // Constructor to initialize task with a name and priority
    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    // CompareTo method from Comparable interface
    @Override
    public int compareTo(Task other) {
        // Higher priority tasks come first, so reverse the order
        return Integer.compare(other.priority, this.priority);
    }

    // toString method to provide a string representation of the Task
    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                '}';
    }
}
```

#### Main Test Method
The contextLoads method in the PriorityQueues class initializes a PriorityQueue of Task objects, adds tasks with different priorities, and processes tasks in order of priority using the poll() method.

```java
@Test
void contextLoads() {
PriorityQueue<Task> taskQueue = new PriorityQueue<>();

    // Adding tasks with different priorities to the PriorityQueue
    taskQueue.add(new Task("Task A", 3));
    taskQueue.add(new Task("Task B", 1));
    taskQueue.add(new Task("Task C", 2));

    // Processing tasks in order of priority
    while (!taskQueue.isEmpty()) {
        // Dequeue the task with the highest priority
        Task task = taskQueue.poll();

        // Display information about the task being processed
        System.out.println("Processing: " + task);
    }
}
```
The tasks are processed based on their priority, and the compareTo method in the Task class defines the ordering within the PriorityQueue.




