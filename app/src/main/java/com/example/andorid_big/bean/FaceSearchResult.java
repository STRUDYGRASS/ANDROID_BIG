package com.example.andorid_big.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class FaceSearchResult {
    @Getter
    @Setter
    private String group_id;
    @Getter
    @Setter
    private String user_id;
    @Getter
    @Setter
    private String user_info;
    @Getter
    @Setter
    private float score;
}
