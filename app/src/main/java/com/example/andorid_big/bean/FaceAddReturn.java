package com.example.andorid_big.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class FaceAddReturn {
    @Getter
    @Setter
    private String face_token;
    @Getter
    @Setter
    private FaceAddLocation location;
}
