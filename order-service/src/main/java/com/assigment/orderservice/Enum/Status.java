package com.assigment.orderservice.Enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    AVAILABLE("Available"),
    NOT_AVAILABLE("Not Available"),
    RESERVED("Reserved"),
    BORROWED("Borrowed"),
    RETURNED("Returned");

    private final String code;
}
