package com.varvashevich.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"name"})
@Table(name = "publishing_house", schema = "my_library")
public class PublishingHouse extends BaseEntity<Integer> {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "publishingHouse")
    private Set<Book> books = new HashSet<>();
}