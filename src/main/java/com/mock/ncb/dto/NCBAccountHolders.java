package com.mock.ncb.dto;

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
public class NCBAccountHolders {
    private String customerId;
    private String customerName;
    private String trn;
    private String accountHolderType;
}
