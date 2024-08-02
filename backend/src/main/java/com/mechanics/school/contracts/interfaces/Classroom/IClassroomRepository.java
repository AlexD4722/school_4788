package com.mechanics.school.contracts.interfaces.Classroom;

import com.mechanics.school.model.Classroom;
import com.mechanics.school.model.ClassroomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IClassroomRepository extends JpaRepository<Classroom, Long> {
    @Query("SELECT c from Classroom c "+
            "WHERE (:id is NULL OR c.id = :id) "+
            "AND (:name is NULL OR c.name LIKE %:name%)"
    )
    List<Classroom> searchClassroomType (@Param("id") Long id, @Param("name") String name);
}
