import React from 'react'
import { Link } from 'react-router-dom'

import ImageLight from '../assets/img/login-office.jpeg'
import ImageDark from '../assets/img/login-office-dark.jpeg'
import { GithubIcon, TwitterIcon } from '../icons'
import { Label, Input, Button } from '@windmill/react-ui'
import { useState, useEffect } from 'react'
import { useDispatch } from 'react-redux'
import { login } from '../features/auth/LoginSlice'
import { useHistory } from 'react-router-dom';
import Cookies from 'js-cookie';
import AuthService from '../service/AuthService';
import { selectLayoutAction } from '../features/layout/LayoutSlice'
import { getRoleFromToken } from '../utils/auth';
function Login() {
  const [userName, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const [loginSuccess, setLoginSuccess] = useState(false);
  const history = useHistory();
  const dispatch = useDispatch();
  // const navigate = useNavigate(); 
  // const loginStatus = useSelector((state) => state.login.status);
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const token = Cookies.get('tokenValue');
    const checkAuthentication = async () => {
      const valueToken = Cookies.get('tokenValue');
      if (!valueToken) {
        console.log("Token not found");
        setIsLoading(false);
        return;
      }

      try {
        const response = await AuthService.authToken(valueToken);
        if (response.httpStatus === 200) {
          setIsAuthenticated(true);
        } else {
          setIsAuthenticated(false);
        }
      } catch (error) {
        console.error("Error during authentication", error);
        setIsAuthenticated(false);
      }
      setIsLoading(false);
    };
    checkAuthentication();
  }, [history]);
  if (isAuthenticated) {
    history.push('/app');
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const data = await dispatch(login({ userName, password }));
      if (data.payload && data.payload.httpStatus === 200) {
        setLoginSuccess(true); // Update state on successful login
      } else {
        setLoginSuccess(false);
      }
    } catch (error) {
      setLoginSuccess(false); // Handle error case
      console.error("Login failed:", error);
    }
  };
  useEffect(() => {
    if (loginSuccess) {
      dispatch(selectLayoutAction(getRoleFromToken()));
      console.log("Role", getRoleFromToken()) // Get role from token;
      console.log('Login success', loginSuccess);
      history.push('/app'); // Chuyển hướng đến trang dashboard sau khi đăng nhập thành công
    }
  }, [loginSuccess, history]);
  if (isLoading) {
    return <div>Loading...</div>; // Hoặc một component loading tùy chỉnh
  }
  return (
    <div className="flex items-center min-h-screen p-6 bg-gray-50 dark:bg-gray-900">
      <div className="flex-1 h-full max-w-4xl mx-auto overflow-hidden bg-white rounded-lg shadow-xl dark:bg-gray-800">
        <div className="flex flex-col overflow-y-auto md:flex-row">
          <div className="h-32 md:h-auto md:w-1/2">
            <img
              aria-hidden="true"
              className="object-cover w-full h-full dark:hidden"
              src={ImageLight}
              alt="Office"
            />
            <img
              aria-hidden="true"
              className="hidden object-cover w-full h-full dark:block"
              src={ImageDark}
              alt="Office"
            />
          </div>
          <main className="flex items-center justify-center p-6 sm:p-12 md:w-1/2">
            <form onSubmit={handleSubmit} className="w-full">
              <h1 className="mb-4 text-xl font-semibold text-gray-700 dark:text-gray-200">Login</h1>
              <Label>
                <span>User Name</span>
                <Input className="mt-1" type="text" placeholder="john@doe.com"
                  value={userName}
                  onChange={(e) => setUserName(e.target.value)}
                />
              </Label>

              <Label className="mt-4">
                <span>Password</span>
                <Input className="mt-1" type="password" placeholder="***************"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                />
              </Label>

              <Button type="submit" className="mt-4" block>
                Log in
              </Button>

              <hr className="my-8" />

              <Button block layout="outline">
                <GithubIcon className="w-4 h-4 mr-2" aria-hidden="true" />
                Github
              </Button>
              <Button className="mt-4" block layout="outline">
                <TwitterIcon className="w-4 h-4 mr-2" aria-hidden="true" />
                Twitter
              </Button>

              <p className="mt-4">
                <Link
                  className="text-sm font-medium text-purple-600 dark:text-purple-400 hover:underline"
                  to="/forgot-password"
                >
                  Forgot your password?
                </Link>
              </p>
              <p className="mt-1">
                <Link
                  className="text-sm font-medium text-purple-600 dark:text-purple-400 hover:underline"
                  to="/create-account"
                >
                  Create account
                </Link>
              </p>
            </form>
          </main>
        </div>
      </div>
    </div>
  )
}
  
export default Login
