import http from "./http-common";
const authToken = async (stringToken) => {
    const data = {
        token: stringToken,
    };
    const response = await http.post("/auth/authenticateToken", data);
    return response.data;
};

const AuthService = {
    authToken
};

export default AuthService;