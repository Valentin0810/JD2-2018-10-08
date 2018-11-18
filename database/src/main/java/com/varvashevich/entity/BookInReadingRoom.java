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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Table(name = "book_in_reading_room", schema = "my_library")
public class BookInReadingRoom extends BaseEntity<Long> {

    @OneToOne
    @JoinColumn(name = "book_order_id")
    private BookOrder bookOrder;

    @Column(name = "date")
    private LocalDate date;
}
