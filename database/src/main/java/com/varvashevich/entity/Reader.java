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
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@PrimaryKeyJoinColumn(name = "user_id")
@Data
@Table(name = "reader", schema = "my_library")
public class Reader extends User {

    @Column(name = "passport_series")
    private String passportSeries;

    public Reader(String fullName, UserDetail userDetail, Role role, String login, String password, String passportSeries) {
        super(fullName, userDetail, role, login, password);
        this.passportSeries = passportSeries;
    }
}