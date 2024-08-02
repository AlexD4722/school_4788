import { all } from 'axios'
import { lazy } from 'react'

// use lazy for better code splitting, a.k.a. load faster
const Dashboard = lazy(() => import('../pages/Dashboard'))
const StudentInfo = lazy(() => import('../pages/StudentInfo'))
const Forms = lazy(() => import('../pages/Forms'))
const Cards = lazy(() => import('../pages/Cards'))
const Charts = lazy(() => import('../pages/Charts'))
const Buttons = lazy(() => import('../pages/Buttons'))
const Modals = lazy(() => import('../pages/Modals'))
const Tables = lazy(() => import('../pages/Tables'))
const Page404 = lazy(() => import('../pages/404'))
const Blank = lazy(() => import('../pages/Blank'))

/**
 * ⚠ These are internal routes!
 * They will be rendered inside the app, using the default `containers/Layout`.
 * If you want to add a route to, let's say, a landing page, you should add
 * it to the `App`'s router, exactly like `Login`, `CreateAccount` and other pages
 * are routed.
 *
 * If you're looking for the links rendered in the SidebarContent, go to
 * `routes/sidebar.js`
 */
const routes = [
  {
    path: '/student-info', // the url
    component: StudentInfo, // view rendered
    allowedRoles: ["ROLE_ADMIN", "ROLE_TEACHER", "ROLE_STUDENT"],
  },
  {
    path: '/dashboard', // the url
    component: Dashboard, // view rendered
    allowedRoles: ["ROLE_ADMIN"],
  },
  {
    path: '/forms',
    component: Forms,
    allowedRoles: ["ROLE_ADMIN", "ROLE_TEACHER", "ROLE_STUDENT"],
  },
  {
    path: '/cards',
    component: Cards,
    allowedRoles: ["ROLE_ADMIN", "ROLE_TEACHER", "ROLE_STUDENT"],
  },
  {
    path: '/charts',
    component: Charts,
    allowedRoles: ["ROLE_ADMIN", "ROLE_TEACHER", "ROLE_STUDENT"],
  },
  {
    path: '/buttons',
    component: Buttons,
    allowedRoles: ["ROLE_ADMIN", "ROLE_TEACHER", "ROLE_STUDENT"],
  },
  {
    path: '/modals',
    component: Modals,
    allowedRoles: ["ROLE_ADMIN", "ROLE_TEACHER", "ROLE_STUDENT"],
  },
  {
    path: '/tables',
    component: Tables,
    allowedRoles: ["ROLE_ADMIN", "ROLE_TEACHER", "ROLE_STUDENT"],
  },
  {
    path: '/404',
    component: Page404,
    allowedRoles: ["ROLE_ADMIN", "ROLE_TEACHER", "ROLE_STUDENT"],
  },
  {
    path: '/blank',
    component: Blank,
    allowedRoles: ["ROLE_ADMIN", "ROLE_TEACHER", "ROLE_STUDENT"],
  },
]

export default routes
