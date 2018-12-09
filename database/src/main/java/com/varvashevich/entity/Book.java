package com.varvashevich.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {"genre",
        "authors",
        "publishingHouse",
        "yearPublishing",
        "pages",
        "quantity"})
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "book", schema = "my_library")
public class Book extends BaseEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToMany
    @JoinTable(name = "book_author", schema = "my_library",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "publishing_house_id")
    private PublishingHouse publishingHouse;

    @Column(name = "year_publishing")
    private Integer yearPublishing;

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "quantity")
    private Integer quantity;
}