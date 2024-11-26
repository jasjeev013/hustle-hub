package com.hustle_hub.server.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private LocalDate due_date;
    private LocalDate created_date;
    private String priority;
    private String status;
    private List<String> categories = new ArrayList<>();

}
