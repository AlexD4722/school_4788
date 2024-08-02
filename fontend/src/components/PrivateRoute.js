import React, { useState, useEffect } from 'react';
import { Route, Redirect } from 'react-router-dom';
import Cookies from 'js-cookie';
import AuthService from '../service/AuthService';

const PrivateRoute = ({ component: Component, ...rest }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [isLoading, setIsLoading] = useState(true);
  useEffect(() => {
    const checkAuthentication = async () => {
      const valueToken = Cookies.get('tokenValue');
      if (!valueToken) {
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
  }, []);

  if (isLoading) {
    // Hoặc một component loading tùy chỉnh
    return <div>Loading...</div>; 
  }

  return (
    <Route
      {...rest}
      render={props =>
        isAuthenticated ? (
          <Component {...props} />
        ) : (
          <Redirect to="/login" />
        )
      }
    />
  );
};

export default PrivateRoute;