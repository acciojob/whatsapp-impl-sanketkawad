package com.driver;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private int id;
    private String name;

    private int numberOfParticipants;
    List<Message> messages;
    List<User> participants;

    public Group(int id, String name, int numberOfParticipants) {
        this.id = id;
        this.name = name;
        this.numberOfParticipants = numberOfParticipants;
        this.messages = new ArrayList<>();
        this.participants = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public boolean hasUser(User user) {
        return participants.contains(user);
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void addParticipant(User user) {
        participants.add(user);
    }


    public User getAdmin() {
    }

    public void setAdmin(User user) {
        this.admin=admin;
    }
}
