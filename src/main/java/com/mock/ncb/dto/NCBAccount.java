package com.mock.ncb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NCBAccount {
    private Long accountNo;
    private String schemeCode;
    private String schemeType;
    private String accountStatus;
    private String accountBalance;
    private String effectiveAvailableBalance;
    private String currency;
    private String modeOfOperation;
    private String accountName;
    private List<NCBAccountHolders> accountHolders;
}
