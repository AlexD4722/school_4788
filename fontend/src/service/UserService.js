import http from "./http-common";
const login = async (userName, password) => {
    const data = {
        userName: userName,
        password: password
    };
    const response = await http.post("/auth/login", data);
    return response.data;
};

const UserService = {
    login
};

export default UserService;