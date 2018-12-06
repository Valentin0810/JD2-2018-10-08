package com.varvashevich.entity;


public enum Role {

    READER("Читатель"),
    LIBRARIAN("Библиотекарь"),
    ADMIN("Администратор");

    private String description;

    Role(String description) {
        this.description = description;
    }
}