package com.walkover.user.api.dao.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @Type(type = "localDateTime")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JsonIgnore
    @Type(type = "localDateTime")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    public void onUpdate() {
        updatedAt = new LocalDateTime();
    }

    @PrePersist
    public void onCreate() {
        updatedAt = createdAt = new LocalDateTime();
    }
}