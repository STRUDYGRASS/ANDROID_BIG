package com.example.andorid_big.bean;

import lombok.Getter;
import lombok.Setter;


public class FaceAddResult {
    @Getter
    @Setter
    private String face_token;
    @Getter
    @Setter
    private FaceAddLocation location;
}
