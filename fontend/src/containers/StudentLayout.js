import React, { useContext, Suspense, useEffect, lazy } from 'react'
import { Switch, Route, Redirect, useLocation } from 'react-router-dom'
import routes from '../routes'

import Sidebar from '../components/Sidebar'
import Header from '../components/Header'
import Main from '../containers/Main'
import ThemedSuspense from '../components/ThemedSuspense'
import { SidebarContext } from '../context/SidebarContext'
import RoleBasedRoute from '../components/RoleBasedRoute';
const Page404 = lazy(() => import('../pages/404'))

function StudentLayout
  () {
  const { isSidebarOpen, closeSidebar } = useContext(SidebarContext)
  let location = useLocation()

  useEffect(() => {
    closeSidebar()
  }, [location])
  return (
    <div
      className={`flex h-screen bg-gray-50 dark:bg-gray-900 ${isSidebarOpen && 'overflow-hidden'}`}
    >
      <Sidebar />

      <div className="flex flex-col flex-1 w-full">
        <Header />
        <Main>
          <h1>Layout Student</h1>
          <Suspense fallback={<ThemedSuspense />}>
            <Switch>
              {routes.map((route, i) => {
                return route.component ? (
                  <RoleBasedRoute
                    key={i}
                    exact={true}
                    path={`/app${route.path}`}
                    component={route.component}
                    allowedRoles={route.allowedRoles || []} // Add allowedRoles to your route definitions
                  />
                ) : null
              })}
              <Redirect exact from="/app" to="/app/student-info" />
              <Route component={Page404} />
            </Switch>
          </Suspense>
        </Main>
      </div>
    </div>
  )
}

export default StudentLayout

