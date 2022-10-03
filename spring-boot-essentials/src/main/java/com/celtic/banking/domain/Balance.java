package com.celtic.banking.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Balance {
    @JsonProperty("account_balance")
    private BigDecimal balance;

    @JsonProperty("last_update")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime lastUpdate;
}
