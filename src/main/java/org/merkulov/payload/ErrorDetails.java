package org.merkulov.payload;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
@Getter
public class ErrorDetails {

private final Date timestamp;
private final String message;
private final String details;
}
