package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    private long id;
    private String author;
    private String name;
    private String publishing_house;
    private Integer the_year_of_publishing;
    private Integer pages;
}