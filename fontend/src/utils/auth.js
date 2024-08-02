import Cookies from 'js-cookie';
import { jwtDecode } from "jwt-decode";
export const getRoleFromToken = () => {
  const token = Cookies.get('tokenValue'); // Correct method to get the token from cookies
  if (!token) return null;
  try {
    const decodedToken = jwtDecode(token); // Use named import to decode the token
    return decodedToken.role;
  } catch (error) {
    console.error('Invalid token:', error);
    return null;
  }
};