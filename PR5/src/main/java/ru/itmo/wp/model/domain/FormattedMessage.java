package ru.itmo.wp.model.domain;

import ru.itmo.wp.model.service.UserService;

import java.util.Date;

public class FormattedMessage {
    private long id;
    private String from;
    private String to;
    private String text;
    private Date creationTime;

    public FormattedMessage(UserService userService, Talk talk) {
        this.text = talk.getText();
        this.id = talk.getId();
        this.from = userService.findById(talk.getSourceUserId()).getLogin();
        this.to = userService.findById(talk.getTargetUserId()).getLogin();
        this.creationTime = talk.getCreationTime();
    }

    public long getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public String getText() {
        return text;
    }
}
