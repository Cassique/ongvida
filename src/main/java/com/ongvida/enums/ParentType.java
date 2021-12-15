package com.ongvida.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ParentType {
    MOM(0), DAD(1);
   private final Integer code;
}
