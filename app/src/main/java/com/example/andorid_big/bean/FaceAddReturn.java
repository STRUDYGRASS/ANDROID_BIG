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
    private int error_code;
    @Getter
    @Setter
    private String error_msg;
    @Getter
    @Setter
    private long log_id;
    @Getter
    @Setter
    private long timestamp;
    @Getter
    @Setter
    private int cached;
    @Getter
    @Setter
    private FaceAddResult result;
}
