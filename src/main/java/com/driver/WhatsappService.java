package com.driver;

import java.util.Date;
import java.util.List;

public class WhatsappService {
    public String createUser(String name, String mobile) {
        return WhatsappRepository.create_user(name,mobile);
    }

    public Group createGroup(List<User> users) {
        return WhatsappRepository.create_group(users);
    }

    public int createMessage(String content) {
        return WhatsappRepository.create_message(content);
    }

    public int sendMessage(Message message, User sender, Group group) {
        return WhatsappRepository.send_message(message,sender,group);
    }

    public String changeAdmin(User approver, User user, Group group) {
        return WhatsappRepository.change_admin(approver,user,group);
    }
    public int removeUser(User user) {
        return WhatsappRepository.remove_user(user);
    }

    public String findMessage(Date start, Date end, int k) {
        return WhatsappRepository.find_message(start,end,k);
    }
}
