package com.mock.ncb.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceAccountEvent {

    private Long accountId;
    private String userId;
    private String currency;
}
