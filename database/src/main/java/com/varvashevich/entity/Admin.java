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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@PrimaryKeyJoinColumn(name = "user_id")
@Table(name = "admin", schema = "my_library")
public class Admin extends User {

    @Column(name = "e_mail")
    private String eMail;

    public Admin(String fullName, UserDetail userDetail, Role role, String login, String password, String eMail) {
        super(fullName, userDetail, role, login, password);
        this.eMail = eMail;
    }
}