package com.polugrudov.classmanager.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassnameDto {

    @NonNull
    private int id;

    @NonNull
    private int digit;

    @NonNull
    private Character letter;
}
