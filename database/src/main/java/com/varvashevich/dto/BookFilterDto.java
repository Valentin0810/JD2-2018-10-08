package com.varvashevich.dto;

import com.varvashevich.entity.Genre;
import com.varvashevich.entity.PublishingHouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Data
@Builder
public class BookFilterDto {

    private Genre genre;
    private PublishingHouse publishingHouse;
    }