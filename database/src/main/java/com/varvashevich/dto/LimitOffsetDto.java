package com.varvashevich.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Data
@Builder
public class LimitOffsetDto {

    private Integer limit;
    private Integer offset;
}