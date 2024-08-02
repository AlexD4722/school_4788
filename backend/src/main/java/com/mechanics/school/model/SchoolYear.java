package com.mechanics.school.model;

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
@Table(name = "school_years")
public class SchoolYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;
    @Column(unique = true, name = "name", nullable = false, columnDefinition = "NVARCHAR(50)")
    String name;
    @Column(name = "start_date", nullable = false)
    Date startDate;
    @Column(name = "end_date", nullable = false)
    Date endDate;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    Status status;
    @Column(name = "last_modified_date", nullable = false)
    Date lastModifiedDate;
    @Column(name = "created_at", nullable = false)
    Date createdAt;

    @OneToMany(mappedBy = "schoolYear")
    List<StudentYearLevel> studentYearLevels;
    @OneToMany(mappedBy = "schoolYear")
    List<Term> terms;
    @OneToMany(mappedBy = "schoolYear")
    List<Lesson> lessons;
    @OneToMany(mappedBy = "schoolYear")
    List<GroupDescription> groupDescriptions;
}
