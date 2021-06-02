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
public class LedgerAccountCreatedEvent {

    private String userId;
    private Long accountId;
    private String ledgerAccountId;
}
