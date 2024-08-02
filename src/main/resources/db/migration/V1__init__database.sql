#
# INSERT INTO `classroom_types` (name, status, last_modified_date, created_at)
# VALUES ('Computer Lab', 1,  NOW(), NOW()),
#        ('Lecture Hall', 1,  NOW(), NOW()),
#        ('Seminar Room', 1,  NOW(), NOW()),
#        ('Workshop Room', 1,  NOW(), NOW()),
#        ('Auditorium', 1,  NOW(), NOW());
#
# INSERT INTO `classrooms` (name, capacity, classroom_type_id, status, last_modified_date, created_at)
# VALUES ('Math Classroom', 30, 1, 1, NOW(), NOW()),
#        ('Science Classroom', 25, 2, 1, NOW(), NOW()),
#        ('English Classroom', 35, 3, 1, NOW(), NOW()),
#        ('History Classroom', 40, 4, 1, NOW(), NOW()),
#        ('Art Classroom', 30, 5, 1, NOW(), NOW());
#
# INSERT INTO `roles` (name, status, last_modified_date, created_at)
# VALUES ('ROLE_ADMIN', 1, NOW(), NOW()),
#        ('ROLE_TEACHER', 1, NOW(), NOW()),
#        ('ROLE_STUDENT', 1, NOW(), NOW()),
#        ('ROLE_GUARDIAN', 1, NOW(), NOW());
# INSERT INTO `users` (code , user_name, password, email, phone, address, status, last_modified_date, created_at)
# VALUES
#     (SUBSTRING(MD5(RAND()) FROM 1 FOR 12), 'user1', 'B72+4VYQzD66F5+Y969N5L/EtOD24ISQ0gsTVu4hhMM=', 'user1@example.com', '1234567890', 'Address 1', 1, NOW(), NOW()),
#     (SUBSTRING(MD5(RAND()) FROM 1 FOR 12), 'user2', 'B72+4VYQzD66F5+Y969N5L/EtOD24ISQ0gsTVu4hhMM=', 'user2@example.com', '1234567891', 'Address 2', 1, NOW(), NOW()),
#     (SUBSTRING(MD5(RAND()) FROM 1 FOR 12), 'user3', 'B72+4VYQzD66F5+Y969N5L/EtOD24ISQ0gsTVu4hhMM=', 'user3@example.com', '1234567892', 'Address 3', 1, NOW(), NOW()),
#     (SUBSTRING(MD5(RAND()) FROM 1 FOR 12), 'user4', 'B72+4VYQzD66F5+Y969N5L/EtOD24ISQ0gsTVu4hhMM=', 'user4@example.com', '1234567893', 'Address 4', 1, NOW(), NOW()),
#     (SUBSTRING(MD5(RAND()) FROM 1 FOR 12), 'user5', 'B72+4VYQzD66F5+Y969N5L/EtOD24ISQ0gsTVu4hhMM=', 'user5@example.com', '1234567894', 'Address 5', 1, NOW(), NOW()),
#     (SUBSTRING(MD5(RAND()) FROM 1 FOR 12), 'user6', 'B72+4VYQzD66F5+Y969N5L/EtOD24ISQ0gsTVu4hhMM=', 'user6@example.com', '1234567895', 'Address 6', 1, NOW(), NOW()),
#     (SUBSTRING(MD5(RAND()) FROM 1 FOR 12), 'user7', 'B72+4VYQzD66F5+Y969N5L/EtOD24ISQ0gsTVu4hhMM=', 'user7@example.com', '1234567896', 'Address 7', 1, NOW(), NOW()),
#     (SUBSTRING(MD5(RAND()) FROM 1 FOR 12), 'user8', 'B72+4VYQzD66F5+Y969N5L/EtOD24ISQ0gsTVu4hhMM=', 'user8@example.com', '1234567897', 'Address 8', 1, NOW(), NOW()),
#     (SUBSTRING(MD5(RAND()) FROM 1 FOR 12), 'user9', 'B72+4VYQzD66F5+Y969N5L/EtOD24ISQ0gsTVu4hhMM=', 'user9@example.com', '1234567898', 'Address 9', 1, NOW(), NOW()),
#     (SUBSTRING(MD5(RAND()) FROM 1 FOR 12), 'user10', 'B72+4VYQzD66F5+Y969N5L/EtOD24ISQ0gsTVu4hhMM=', 'user10@example.com', '1234567899', 'Address 10', 1, NOW(), NOW());
# INSERT INTO `user_roles` (user_id, role_id, status,  last_modified_date, created_at)
# VALUES (1, 1, 1,  NOW(),  NOW()),
#        (2, 1, 1, NOW(), NOW()),
#        (3, 1, 1, NOW(),  NOW()),
#        (1, 2,  1, NOW(),  NOW()),
#        (1, 3,  1, NOW(),  NOW()),
#        (3, 3,  1, NOW(),  NOW());
#
#
# INSERT INTO `students` (code, first_name, last_name, data_of_birth, enrollment_date, gender, image, address, status, last_modified_date, created_at, user_id)
# VALUES (SUBSTRING(MD5(RAND()) FROM 1 FOR 12), 'John', 'Doe', '2000-01-01', '2022-01-01', 1,'https://www.radiustheme.com/demo/html/psdboss/akkhor/akkhor/img/figure/student.png', "Hà Nội ", 1, NOW(), NOW(), 1),
#        (SUBSTRING(MD5(RAND()) FROM 1 FOR 12), 'Jane', 'Smith', '2000-02-02', '2022-02-02', 2,'https://www.radiustheme.com/demo/html/psdboss/akkhor/akkhor/img/figure/student.png', "Hà Nội ", 1, NOW(), NOW(), 2),
#        (SUBSTRING(MD5(RAND()) FROM 1 FOR 12), 'Robert', 'Johnson', '2000-03-03', '2022-03-03', 1, 'https://www.radiustheme.com/demo/html/psdboss/akkhor/akkhor/img/figure/student.png',"Hồ Chí Minh ", 1, NOW(), NOW(), 3),
#        (SUBSTRING(MD5(RAND()) FROM 1 FOR 12), 'Michael', 'Williams', '2000-04-04', '2022-04-04', 1, 'https://www.radiustheme.com/demo/html/psdboss/akkhor/akkhor/img/figure/student.png',"Hà Nội ", 1, NOW(), NOW(), 4),
#        (SUBSTRING(MD5(RAND()) FROM 1 FOR 12), 'Sarah', 'Brown', '2000-05-05', '2022-05-05', 2, 'https://www.radiustheme.com/demo/html/psdboss/akkhor/akkhor/img/figure/student.png',"Hà Nội ", 1, NOW(), NOW(), 5),
#        (SUBSTRING(MD5(RAND()) FROM 1 FOR 12), 'David', 'Jones', '2000-06-06', '2022-06-06', 1, 'https://www.radiustheme.com/demo/html/psdboss/akkhor/akkhor/img/figure/student.png',"Đà Nẵng", 1, NOW(), NOW(), 6),
#        (SUBSTRING(MD5(RAND()) FROM 1 FOR 12), 'Emily', 'Davis', '2000-07-07', '2022-07-07', 2, 'https://www.radiustheme.com/demo/html/psdboss/akkhor/akkhor/img/figure/student.png',"Hà Nội ", 1, NOW(), NOW(), 7),
#        (SUBSTRING(MD5(RAND()) FROM 1 FOR 12), 'James', 'Miller', '2000-08-08', '2022-08-08', 1,'https://www.radiustheme.com/demo/html/psdboss/akkhor/akkhor/img/figure/student.png',"Vĩnh Phúc", 1, NOW(), NOW(), 8),
#        (SUBSTRING(MD5(RAND()) FROM 1 FOR 12), 'Jessica', 'Wilson', '2000-09-09', '2022-09-09', 2, 'https://www.radiustheme.com/demo/html/psdboss/akkhor/akkhor/img/figure/student.png',"Vĩnh Phúc",  1,  NOW(), NOW(), 9),
#        (SUBSTRING(MD5(RAND()) FROM 1 FOR 12), 'Thomas', 'Moore', '2000-10-10', '2022-10-10', 1, 'https://www.radiustheme.com/demo/html/psdboss/akkhor/akkhor/img/figure/student.png',"Vĩnh Phúc",  1, NOW(), NOW(), 10);