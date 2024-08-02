import React, { Suspense } from 'react';
import ReactDOM from 'react-dom';
import './assets/css/tailwind.output.css';
import App from './App';
import { SidebarProvider } from './context/SidebarContext';
import ThemedSuspense from './components/ThemedSuspense';
import { Windmill } from '@windmill/react-ui';
import * as serviceWorker from './serviceWorker';
import { store, persistor } from './app/store'; // Giả sử bạn đã export `persistor` từ file này
import { Provider } from 'react-redux';
import { PersistGate } from 'redux-persist/integration/react';

ReactDOM.render(
  <Provider store={store}>
    <PersistGate loading={<ThemedSuspense />} persistor={persistor}>
      <SidebarProvider>
        <Suspense fallback={<ThemedSuspense />}>
          <Windmill usePreferences>
            <App />
          </Windmill>
        </Suspense>
      </SidebarProvider>
    </PersistGate>
  </Provider>,
  document.getElementById('root')
);

serviceWorker.register();