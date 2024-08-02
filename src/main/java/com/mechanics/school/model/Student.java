package com.mechanics.school.model;

import com.mechanics.school.utils.enums.Gender;

import com.mechanics.school.utils.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "students", indexes = {@Index(name = "idx_students_code", columnList = "code")})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;
    @Column(name = "code", nullable = false,  unique = true, columnDefinition = "NVARCHAR(12)")
    String code;
    @Column(name = "first_name", nullable = false, columnDefinition = "NVARCHAR(50)")
    String firstName;
    @Column(name = "last_name", nullable = false, columnDefinition = "NVARCHAR(50)")
    String lastName;
    @Column(name = "data_of_birth", nullable = false)
    Date dataOfBirth;
    @Column(name = "enrollment_date", nullable = false)
    Date enrollmentDate;
    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    Gender gender = Gender.MALE;
    @Column(name = "image", columnDefinition = "NVARCHAR(255)")
    String image;
    @Column(name = "address", columnDefinition = "NVARCHAR(255)")
    String address;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
     User user;
    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    Status status;
    @Column(name = "last_modified_date", nullable = false)
    Date lastModifiedDate;
    @Column(name = "created_at", nullable = false)
    Date createdAt;

    @OneToMany(mappedBy = "student")
    List<StudentGuardian> studentGuardians;
    @OneToMany(mappedBy = "student")
    List<StudentYearLevel> studentYearLevels;
    @OneToMany(mappedBy = "student")
    List<Scores> scores;
    @OneToMany(mappedBy = "student")
    List<GroupDescription> groupDescriptions;
}
