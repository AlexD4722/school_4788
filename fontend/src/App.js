import React, { lazy, useEffect, useState } from 'react'
import { BrowserRouter as Router, Switch, Route, Redirect } from 'react-router-dom'
import AccessibleNavigationAnnouncer from './components/AccessibleNavigationAnnouncer'
import PrivateRoute from './components/PrivateRoute'
import Page404 from './pages/404'
import { getRoleFromToken } from './utils/auth'
import { useSelector } from 'react-redux'
import { selectLayoutData } from './features/layout/LayoutSlice'
import { useMemo } from 'react'
// import { useNavigate } from 'react-router-dom'

const Layout = lazy(() => import('./containers/Layout'))
const StudentLayout = lazy(() => import('./containers/StudentLayout'))
const Login = lazy(() => import('./pages/Login'))
const CreateAccount = lazy(() => import('./pages/CreateAccount'))
const ForgotPassword = lazy(() => import('./pages/ForgotPassword'))

const roleToLayout = {
  'ROLE_ADMIN': Layout,
  'ROLE_STUDENT': StudentLayout,
  // 'Teacher': AdminLayout,
  // Thêm các role và layout tương ứng khác tại đây
};

// Định nghĩa một mảng ưu tiên cho các role
const rolePriority = ['ROLE_ADMIN', 'ROLE_TEACHER', 'ROLE_GUARDIAN', 'ROLE_STUDENT'];

function getHighestPriorityLayout(userRoles) {
  if (!userRoles) {
    return roleToLayout['ROLE_STUDENT'];
  }
  const highestPriorityRole = rolePriority.find(role => userRoles.includes(role));

  // Trả về layout tương ứng với role có ưu tiên cao nhất
  return roleToLayout[highestPriorityRole];
}

function App() {
  // const navigate = useNavigate();
  // /.if role is not found in token, redirect to login page
  const [UserLayout, setUserLayout] = useState(null);
  const userRoles = useSelector(selectLayoutData); // Lấy roles từ Redux store
  console.log('userRoles', userRoles);
  useEffect(() => {
    setUserLayout(getHighestPriorityLayout(userRoles));
    console.log('UserLayout', UserLayout);
  }, [userRoles]);

  return (
    <>
      <Router>
        <AccessibleNavigationAnnouncer />
        <Switch>
          <Route path="/login" component={Login} />
          <Route path="/create-account" component={CreateAccount} />
          <Route path="/forgot-password" component={ForgotPassword} />
          <Route path="/404" component={Page404} />
          <PrivateRoute path="/" component={UserLayout} />
          <Redirect exact from="/" to="/login" />
        </Switch>
      </Router>
    </>
  );
}
export default App