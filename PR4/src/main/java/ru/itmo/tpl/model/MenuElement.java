package ru.itmo.tpl.model;

public class MenuElement {
    private final String name;
    private final String link;

    public MenuElement(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }
}
