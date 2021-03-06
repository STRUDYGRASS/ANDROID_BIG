package com.example.andorid_big.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class FaceSearchResult {
    @Getter
    @Setter
    private String face_token;
    @Getter
    @Setter
    private List<FaceSearchList> user_list;
}
