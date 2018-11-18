package com.varvashevich.entity;


public enum Role {

    READER("Читатель"),
    LIBRARIAN("Библиотекарь"),
    ADMIN("Администратор");

    Role(String description) {
        this.description = description;
    }

    private String description;
}