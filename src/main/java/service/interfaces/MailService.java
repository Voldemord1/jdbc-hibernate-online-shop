package service.interfaces;

import model.User;

public interface MailService {

    void sendConfirmCode(User user);
}
