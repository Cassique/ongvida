package com.ongvida.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SubjectCode {
    MATH(0),
    PORTUGUESE(1),
    SCIENCE(2),
    BIOLOGY(3),
    CHEMICAL(4),
    PHYSICS(5),
    HISTORY(6),
    GEOGRAPHY(7),
    LITERATURE(8),
    NEWS(9);

    private int code;
}
