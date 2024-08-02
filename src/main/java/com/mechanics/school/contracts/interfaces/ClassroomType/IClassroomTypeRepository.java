package com.mechanics.school.contracts.interfaces.ClassroomType;

import com.mechanics.school.model.ClassroomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClassroomTypeRepository extends JpaRepository<ClassroomType, Long> {
}
