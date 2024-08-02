//package com.mechanics.school.controller;
//
//import com.mechanics.school.mapper.ClassroomTypeMapper;
//import com.mechanics.school.mapper.dtos.ClassroomType.ClassroomTypeDto;
//import com.mechanics.school.model.ClassroomType;
//import com.mechanics.school.service.ClassroomType.ClassroomTypeCommandService;
//import com.mechanics.school.service.unitOfWork.ClassroomTypeUnitOfWork;
//import com.mechanics.school.utils.LoggerUtils;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import com.mechanics.school.responses.ResponseHandler;
//
//import static org.springframework.http.ResponseEntity.ok;
//
//@RestController
//@RequestMapping("/api/demo")
//public class DemoController {
//    private final ClassroomTypeMapper classroomTypeMapper;
//    private final ClassroomTypeUnitOfWork classroomTypeUnitOfWork;
//    private final ClassroomTypeCommandService classroomTypeCommandService;
//    @Autowired
//    public DemoController(ClassroomTypeMapper classroomTypeMapper, ClassroomTypeUnitOfWork classroomTypeUnitOfWork, ClassroomTypeCommandService classroomTypeCommandService) {
//        this.classroomTypeMapper = classroomTypeMapper;
//        this.classroomTypeUnitOfWork = classroomTypeUnitOfWork;
//        this.classroomTypeCommandService = classroomTypeCommandService;
//    }
//
//
////    @GetMapping("/classroomType/{id}")
////    public ResponseEntity<Object> getClassroomType(@PathVariable Long id) {
////        try {
////            ClassroomTypeDto classroomTypeDto =  classroomTypeCommandService.FindClassroomTypeDtoById(id);
////            if (classroomTypeDto == null) {
////                return ResponseHandler.responseBuilder(
////                        "Classroom Type not found",
////                        HttpStatus.NOT_FOUND.value(),
////                        null
////                );
////            }
////            return ResponseHandler.responseBuilder(
////                    "Classroom Type found",
////                    HttpStatus.OK.value(),
////                    classroomTypeDto
////            );
////        } catch (Exception e) {
////            LoggerUtils.LOGGER.error("Error occurred during finding ClassroomTypeDto by id.", e);
////            return  ResponseHandler.responseBuilder(
////                    "Error occurred during finding ClassroomTypeDto by id.",
////                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
////                    null
////            );
////        }
////    }
//    @PostMapping("/CreateClassroomType")
//    public ResponseEntity<Object> createClassroomType(@Valid @RequestBody ClassroomTypeDto classroomTypeDto) {
//        ClassroomType classroomType = classroomTypeMapper.classroomTypeDtoToClassroomType(classroomTypeDto);
//        classroomTypeUnitOfWork.registerNew(classroomType);
//        classroomTypeUnitOfWork.commit();
//        LoggerUtils.LOGGER.info("Classroom Type Created Successfully");
//        return ResponseHandler.responseBuilder(
//                "Classroom Type Created Successfully",
//                HttpStatus.OK.value(),
//                classroomType);
//    }
//
////    @ResponseStatus(HttpStatus.BAD_REQUEST)
////    @ExceptionHandler(MethodArgumentNotValidException.class)
////    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
////        Map<String,String> errors = new HashMap<>();
////        ex.getBindingResult().getAllErrors().forEach((error) -> {
////            String fieldName = ((FieldError) error).getField();
////            String errorMessage = error.getDefaultMessage();
////            errors.put(fieldName, errorMessage);
////        });
////        return errors;
////    }
//}
//
