import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import { getRoleFromToken } from '../utils/auth';

const RoleBasedRoute = ({ component: Component, allowedRoles, ...rest }) => (
  <Route
    {...rest}
    render={props => {
      const role = getRoleFromToken();
      if(!role){
        return <Redirect to="/login" />;
      }
      if (role.some(r => allowedRoles.includes(r))) {
        return <Component {...props} />;
      } else {
        console.log('Unauthorized access');
        return <Redirect to="/404" />;
      }
    }}
  />
);

export default RoleBasedRoute;