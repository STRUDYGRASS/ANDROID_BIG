package com.example.andorid_big.bean;

import java.lang.reflect.Array;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class FaceSearchReturn {
    @Getter
    @Setter
    private String face_token;
    @Getter
    @Setter
    private List<FaceSearchResult> searchResults;
}
