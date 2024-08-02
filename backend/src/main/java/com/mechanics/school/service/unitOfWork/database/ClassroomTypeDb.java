//package com.mechanics.school.service.unitOfWork.database;
//
//import com.mechanics.school.contracts.interfaces.ClassroomType.IClassroomTypeRepository;
//import com.mechanics.school.model.ClassroomType;
//import com.mechanics.school.service.ClassroomType.ClassroomTypeCommandService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ClassroomTypeDb {
//    private final ClassroomTypeCommandService classroomTypeCommandService;
//    private final IClassroomTypeRepository classroomTypeRepository;
//    @Autowired
//    public ClassroomTypeDb(ClassroomTypeCommandService classroomTypeCommandService, IClassroomTypeRepository classroomTypeRepository {
//        this.classroomTypeCommandService = classroomTypeCommandService;
//        this.classroomTypeRepository = classroomTypeRepository;
//    }
//    public void insert(ClassroomType classroomType) {
//        classroomTypeCommandService.insert(classroomType);
//    }
//
//    public void modify(ClassroomType classroomType) {
//        classroomTypeCommandService.modify(classroomType);
//    }
//
//    public void delete(ClassroomType classroomType) {
//        classroomTypeCommandService.delete(classroomType);
//    }
//}
