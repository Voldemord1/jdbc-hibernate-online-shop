package factory;

import service.impl.MailServiceImpl;
import service.interfaces.MailService;

import java.util.Objects;

public class MailServiceFactory {
    private static MailService instance;

    private MailServiceFactory() {
    }

    public static MailService getInstance() {
        return (Objects.isNull(instance)) ? instance = new MailServiceImpl() : instance;
    }
}
