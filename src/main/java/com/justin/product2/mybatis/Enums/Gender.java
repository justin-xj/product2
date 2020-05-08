package com.justin.product2.mybatis.Enums;

import lombok.Getter;

@Getter
public enum  Gender {

    M("male","男"),
    F("female","女");

    Gender(String code ,String discrible){
        this.code = code;
        this.discrible = discrible;
    }
    private String code;
    private String discrible;


}
