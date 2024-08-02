package com.mechanics.school.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mechanics.school.utils.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "classrooms")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;
    @Column(unique = true, name = "name", nullable = false, columnDefinition = "NVARCHAR(50)")
    String name;
    @Column(name = "capacity", nullable = false)
    int capacity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_type_id")
    @JsonIgnore
    ClassroomType classroomType;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    Status status;
    @Column(name = "last_modified_date", nullable = false)
    Date lastModifiedDate;
    @Column(name = "created_at", nullable = false)
    Date createdAt;


    @OneToMany(mappedBy = "classRoom", fetch = FetchType.LAZY)
    List<Lesson> lessons;
//    @OneToMany(mappedBy = "classRoom")
//    Lis
}
