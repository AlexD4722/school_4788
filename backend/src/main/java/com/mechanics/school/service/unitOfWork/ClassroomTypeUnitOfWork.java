//package com.mechanics.school.service.unitOfWork;
//
//import com.mechanics.school.model.ClassroomType;
//import com.mechanics.school.service.unitOfWork.database.ClassroomTypeDb;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static com.mechanics.school.utils.LoggerUtils.LOGGER;
//
//@Service
//public class ClassroomTypeUnitOfWork implements IUnitOfWork<ClassroomType> {
//    private Map<String, List<ClassroomType>> context;
//    private ClassroomTypeDb classroomTypeDb;
//
//    @Autowired
//    public ClassroomTypeUnitOfWork(ClassroomTypeDb classroomTypeDb) {
//        this.classroomTypeDb = classroomTypeDb;
//        this.context = new HashMap<>();
//    }
//
//    @Override
//    public void registerNew(ClassroomType classroomType) {
//        LOGGER.info("Registering {} for insert in context.", classroomType.getName());
//        register(classroomType, IUnitOfWork.INSERT);
//    }
//
//    @Override
//    public void registerModified(ClassroomType classroomType) {
//        LOGGER.info("Registering {} for modify in context.", classroomType.getName());
//        register(classroomType, IUnitOfWork.MODIFY);
//    }
//
//    @Override
//    public void registerDeleted(ClassroomType classroomType) {
//        LOGGER.info("Registering {} for delete in context.", classroomType.getName());
//        register(classroomType, IUnitOfWork.DELETE);
//    }
//
//    @Override
//    public boolean commit() {
//        if (context == null || context.isEmpty()) {
//            LOGGER.info("No operation to commit.");
//            return false;
//        }
//        LOGGER.info("Commit started");
//        try {
//            if (context.containsKey(IUnitOfWork.INSERT)) {
//                List<ClassroomType> classroomTypeToBeInserted = context.get(IUnitOfWork.INSERT);
//                for (ClassroomType classroomType : classroomTypeToBeInserted) {
//                    LOGGER.info("Saving {} to database.", classroomType.getName());
//                    this.classroomTypeDb.insert(classroomType);
//                }
//            }
//
//            if (context.containsKey(IUnitOfWork.MODIFY)) {
//                List<ClassroomType> modifiedClassroomTypes = context.get(IUnitOfWork.MODIFY);
//                for (ClassroomType classroomType : modifiedClassroomTypes) {
//                    LOGGER.info("Modifying {} to database.", classroomType.getName());
//                    this.classroomTypeDb.modify(classroomType);
//                }
//            }
//
//            if (context.containsKey(IUnitOfWork.DELETE)) {
//                List<ClassroomType> deletedClassroomTypes = context.get(IUnitOfWork.DELETE);
//                for (ClassroomType classroomType : deletedClassroomTypes) {
//                    LOGGER.info("Deleting {} to database.", classroomType.getName());
//                    this.classroomTypeDb.delete(classroomType);
//                }
//            }
//            LOGGER.info("Commit finished.");
//        } catch (Exception e) {
//            LOGGER.error("Error occurred during commit. Rolling back changes.", e);
//        } finally {
//            // Clear the context after commit
//            context.clear();
//        }
//    }
//
//    private boolean register(ClassroomType classroomType, String operation) {
//        var classroomTypeToOperate = context.get(operation);
//        if (classroomTypeToOperate == null) {
//            classroomTypeToOperate = new ArrayList<>();
//        }
//        classroomTypeToOperate.add(classroomType);
//        context.put(operation, classroomTypeToOperate);
//    }
//}