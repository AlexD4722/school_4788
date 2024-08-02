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
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;
    @Column(name = "code", nullable = false,  unique = true, columnDefinition = "NVARCHAR(12)")
    String code;
    @Column(unique = true, name = "first_name", nullable = false,  columnDefinition = "NVARCHAR(50)")
    String firstName;
    @Column(unique = true, name = "last_name", nullable = false, columnDefinition = "NVARCHAR(50)")
    String lastName;
    @Column(name = "email", nullable = false, unique = true)
    String email;
    @Column(name = "phone_number", nullable = false, unique = true, columnDefinition = "NVARCHAR(50)")
    String phoneNumber;
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
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    Status status;
    @Column(name = "last_modified_date", nullable = false)
    Date lastModifiedDate;
    @Column(name = "created_at", nullable = false)
    Date createdAt;

    @OneToMany(mappedBy = "teacher")
    List<Lesson> lessons;
    @OneToMany(mappedBy = "teacher")
    List<GroupDescription> groupDescriptions;
}
