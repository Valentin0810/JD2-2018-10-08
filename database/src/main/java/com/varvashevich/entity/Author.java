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
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@Entity
@ToString
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "author", schema = "my_library")
public class Author extends BaseEntity<Long> {

    @Column(name = "full_name")
    private String name;

    @Column(name = "date_birth")
    private String dateBirth;

    @Column(name = "date_death")
    private String dateDeath;

    @Column(name = "brief_information")
    private String briefInformation;

    @ManyToMany
    @JoinTable(name = "book_author", schema = "my_library",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Author> authors = new HashSet<>();
}