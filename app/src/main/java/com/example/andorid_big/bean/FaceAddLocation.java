package com.example.andorid_big.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class FaceAddLocation {
    @Getter
    @Setter
    private double left;
    @Getter
    @Setter
    private double top;
    @Getter
    @Setter
    private double width;
    @Getter
    @Setter
    private double height;
    @Getter
    @Setter
    private int rotation;
}
