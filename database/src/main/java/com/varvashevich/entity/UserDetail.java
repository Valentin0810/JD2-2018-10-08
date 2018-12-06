package com.varvashevich.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Embeddable
@Data
@Builder

public class UserDetail {

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private Integer phoneNumber;
}
