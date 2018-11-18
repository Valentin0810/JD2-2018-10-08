package com.varvashevich.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Builder
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"name"})
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "genre", schema = "my_library")
public class Genre extends BaseEntity<Integer> {

    @Column(name = "name", unique = true, nullable = false)
    private String name;
    }