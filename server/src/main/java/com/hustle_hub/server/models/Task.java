package com.hustle_hub.server.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private Date due_date;
    private String priority;
    private String status;

    @ElementCollection
    @CollectionTable(name = "task_categories", joinColumns = @JoinColumn(name = "task_id"))
    @Column(name = "category")
    private List<String> categories = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "task",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Notification> notifcations = new ArrayList<>();

}
