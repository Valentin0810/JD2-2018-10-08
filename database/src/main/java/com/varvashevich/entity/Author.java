package com.varvashevich.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Builder
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "author", schema = "my_library")
public class Author extends BaseEntity<Long> {

    @Column(name = "full_name")
    private String name;

    @Column(name = "years_of_life")
    private String yearsOfLife;

    @Column(name = "brief_information")
    private String briefInformation;
}