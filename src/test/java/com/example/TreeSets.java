package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.TreeSet;

@SpringBootTest
public class TreeSets {

    @Test
    void contextLoads() {
        EventScheduler scheduler = new EventScheduler();

        scheduler.scheduleEvent("Meeting A", LocalDate.of(2023, 1, 10), 2);
        scheduler.scheduleEvent("Conference", LocalDate.of(2023, 1, 8), 3);
        scheduler.scheduleEvent("Lunch", LocalDate.of(2023, 1, 12), 1);
        scheduler.scheduleEvent("Meeting B", LocalDate.of(2023, 1, 11), 2);

        scheduler.displayScheduleByStartTime();
        scheduler.displayScheduleByDuration();
    }

    class EventScheduler {

        // TreeSet to store events ordered by start time in ascending order
        private TreeSet<Event> eventSetByStartTime = new TreeSet<>(Comparator.comparing(Event::getStartTime));

        // TreeSet to store events ordered by duration in descending order
        private TreeSet<Event> eventSetByDuration = new TreeSet<>(Comparator.comparing(Event::getDuration).reversed());

        public void scheduleEvent(String eventName, LocalDate startTime, int duration) {
            Event newEvent = new Event(eventName, startTime, duration);
            eventSetByStartTime.add(newEvent);
        }

        public void displayScheduleByStartTime() {
            System.out.println("Scheduled Events by Start Time:");
            for (Event event : eventSetByStartTime) {
                System.out.println(event);
            }
        }

        public void displayScheduleByDuration() {
            System.out.println("\nScheduled Events by Duration:");
            for (Event event : eventSetByDuration) {
                System.out.println(event);
            }
        }
    }

    class Event {
        private String name;
        private LocalDate startTime;
        private int duration;

        Event(String name, LocalDate startTime, int duration) {
            this.name = name;
            this.startTime = startTime;
            this.duration = duration;
        }

        public LocalDate getStartTime() {
            return startTime;
        }

        public int getDuration() {
            return duration;
        }

        @Override
        public String toString() {
            return "Event{" +
                    "name='" + name + '\'' +
                    ", startTime=" + startTime +
                    ", duration=" + duration +
                    '}';
        }
    }
}
