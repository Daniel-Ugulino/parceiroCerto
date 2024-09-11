package com.example.requestservice.Domain.Enum;

import lombok.Getter;
@Getter
public enum RequestStatus {
    CREATED,
    ACCEPTED,
    IN_PROGRESS,
    DONE,
    CANCELED
}
