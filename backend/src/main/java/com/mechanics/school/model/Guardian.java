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
@Table(name = "guardians")
public class Guardian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;
    @Column(name = "code", nullable = false,  unique = true, columnDefinition = "NVARCHAR(12)")
    String code;
    @Column(name = "first_name", nullable = false, length = 50, columnDefinition = "NVARCHAR(50)")
    String firstName;
    @Column(name = "last_name", nullable = false, length = 50, columnDefinition = "NVARCHAR(50)")
    String lastName;
    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    Gender gender;
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

    @OneToMany(mappedBy = "guardian")
    List<StudentGuardian> studentGuardians;
}
