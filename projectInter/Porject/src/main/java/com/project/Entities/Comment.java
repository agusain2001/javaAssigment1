package com.project.Entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String message;


    @Column(nullable = false)
    private LocalDateTime commentDateTime;

    @ManyToOne
    @JoinColumn(name = "posted_by_user_id")
    private Users postedByUser;


}
