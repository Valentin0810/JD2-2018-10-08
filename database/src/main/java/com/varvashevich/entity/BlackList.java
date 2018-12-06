package com.varvashevich.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@ToString
@Entity
@Table(name = "black_list", schema = "my_library")
public class BlackList extends BaseEntity<Long> {

    @OneToOne
    @JoinColumn(name = "user_id")
    private Reader reader;
}
