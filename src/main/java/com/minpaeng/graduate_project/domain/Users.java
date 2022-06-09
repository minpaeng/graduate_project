package com.minpaeng.graduate_project.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Users {
    @Id
    private String userId;

    @Column
    private String name;
}
