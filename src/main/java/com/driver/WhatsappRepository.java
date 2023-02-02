package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class WhatsappRepository {

    //Assume that each user belongs to at most one group
    //You can use the below mentioned hashmaps or delete these and create your own.
    private static HashMap<Group, List<User>> groupUserMap;
    private HashMap<Group, List<Message>> groupMessageMap;
    private HashMap<Message, User> senderMap;
    private HashMap<Group, User> adminMap;
    private HashSet<String> userMobile;
    private static int customGroupCount;
    private static int messageId;

    static Map<String,String> user = new HashMap<>();

    public WhatsappRepository(){
        this.groupMessageMap = new HashMap<Group, List<Message>>();
        this.groupUserMap = new HashMap<Group, List<User>>();
        this.senderMap = new HashMap<Message, User>();
        this.adminMap = new HashMap<Group, User>();
        this.userMobile = new HashSet<>();
        this.customGroupCount = 0;
        this.messageId = 0;
    }

    public static String create_user(String name, String mobile) {
        if(!user.containsValue(mobile)){
            user.put(name,mobile);
            return "SUCCESS";
        }
        return "User already exists";

    }

    public static Group create_group(List<User> users) {
        // Check if there are at least 2 users in the list
        if (users.size() < 2) {
            throw new IllegalArgumentException("A group must have at least 2 users.");
        }

        // Create the group and set the first user as the admin
        Group group = new Group("Group"+customGroupCount++,users.size());
        User admin = users.get(0);





        // Add the group to the groups map
        groupUserMap.put(group, users);

        return group;
    }


    public static int create_message(String content) {
        messageId++;
        Message message = new Message(messageId, content, new Date());
        return messageId;
    }

    public static int send_message(Message message, User sender, Group group) {
        int result = 0;
        if (group.hasUser(sender) && message != null) {
            group.addMessage(message);
            result = 1;
        } else {
            result = -1;
        }
        return result;
    }

    public static String change_admin(User approver, User user, Group group) {
        if(group.hasUser(approver) && group.hasUser(user) && group.getAdmin() == approver) {
            group.setAdmin(user);
            return "Admin Changed Successfully";
        } else {
            return "Error Changing Admin";
        }
    }

    public static int remove_user(User user, Group group) {
        if(group.hasUser(user)) {
            group.participants.remove(user);
            group.getNumberOfParticipants()--;
            return 1;
        } else {
            return 0;
        }
    }

    public static String find_message(Date start, Date end, int k, Group group) {
        List<Message> foundMessages = new ArrayList<>();
        for (Message message : group.messages) {
            if (message.getTimestamp().compareTo(start) >= 0 && message.getTimestamp().compareTo(end) <= 0) {
                foundMessages.add(message);
            }
        }
        if (foundMessages.size() == 0) {
            return "No messages found in the specified date range.";
        }
        if (foundMessages.size() < k) {
            return "Requested message number is larger than the number of messages found.";
        }
        return "Message id: " + foundMessages.get(k - 1).getId() + ", Content: " + foundMessages.get(k - 1).getContent();
    }
}
