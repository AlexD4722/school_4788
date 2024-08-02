import http from "./http-common";

const getAll = () => {
    return http.get("/classroomType");
  };
  const ClassroomTypeService = {
    getAll
  };
  
  export default ClassroomTypeService;