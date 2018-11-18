package com.varvashevich.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"tariffCategory"})
@ToString(callSuper = true)
@Entity
@PrimaryKeyJoinColumn(name = "user_id")
@Data
@Table(name = "librarian", schema = "my_library")
public class Librarian extends User {

    @Column
    private Integer tariffCategory;

    public Librarian(String fullName, UserDetail userDetail, Role role, String login, String password, Integer tariffCategory) {
        super(fullName, userDetail, role, login, password);
        this.tariffCategory = tariffCategory;
    }
}
