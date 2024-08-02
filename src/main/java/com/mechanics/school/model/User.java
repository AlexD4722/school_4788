package com.mechanics.school.model;

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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;
    @Column(name = "code", nullable = false,  unique = true, columnDefinition = "NVARCHAR(12)")
    String code;
    @Column(unique = true, name = "user_name", nullable = false, length = 50, columnDefinition = "NVARCHAR(50)")
    String userName;
    @Column(name = "password", nullable = false, columnDefinition = "NVARCHAR(255)")
    String password;
    @Column(unique = true, name = "email", nullable = false, columnDefinition = "NVARCHAR(255)")
    String email;
    @Column(name = "phone", nullable = false, columnDefinition = "NVARCHAR(50)")
    String phone;
    @Column(name = "address", nullable = false, columnDefinition = "NVARCHAR(255)")
    String address;
    @Column(name = "verify_code",  nullable = true, columnDefinition = "NVARCHAR(12)")
    String verifyCode;
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    Student student;
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    Guardian guardian;
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    Teacher teacher;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    Status status;
    @Column(name = "last_modified_date", nullable = false)
    Date lastModifiedDate;
    @Column(name = "created_at", nullable = false)
    Date createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<UserRole> userRoles;
}
