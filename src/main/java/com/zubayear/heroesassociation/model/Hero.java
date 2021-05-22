package com.zubayear.heroesassociation.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Document
@RequiredArgsConstructor
@NoArgsConstructor
public class Hero {

    @Id
    private String id;

    @NonNull
    private String name;

    @NonNull
    private Character className;

    @NonNull
    private Integer rank;

    @NonNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate registerDate;
}
