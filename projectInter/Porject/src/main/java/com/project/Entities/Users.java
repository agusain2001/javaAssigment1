package com.project.Entities;


import lombok.*;

import javax.persistence.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "comment_from", nullable = false)
    private String commentFrom;

    @Column(name = "comment_to", nullable = false)
    private String commentTo;
}
