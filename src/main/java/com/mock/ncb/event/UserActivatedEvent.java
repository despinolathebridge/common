package com.mock.ncb.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserActivatedEvent {
    private String userId;
    private String country;
    private String name;
    private String lastName;
    private String status;
}
