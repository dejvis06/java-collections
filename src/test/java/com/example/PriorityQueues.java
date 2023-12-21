package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.PriorityQueue;

@SpringBootTest
public class PriorityQueues {

    @Test
    void contextLoads() {
        PriorityQueue<Task> taskQueue = new PriorityQueue<>();

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

    // Task class implements Comparable to define the natural ordering based on priority
    class Task implements Comparable<Task> {
        private String name;
        private int priority;

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

        @Override
        public String toString() {
            return "Task{" +
                    "name='" + name + '\'' +
                    ", priority=" + priority +
                    '}';
        }
    }
}
