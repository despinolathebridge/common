package com.mock.ncb.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ResponseKafkaDto {
    private int status;
    private int message;
}
