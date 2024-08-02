import { configureStore, combineReducers } from '@reduxjs/toolkit';
import { persistStore, persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage'; // defaults to localStorage for web
import taskReducer from '../features/task/taskSlice';
import loginReducer from '../features/auth/LoginSlice';
import layoutReducer from '../features/layout/LayoutSlice';

// Combine reducers into a single reducer function
const rootReducer = combineReducers({
  task: taskReducer,
  login: loginReducer,
  layout: layoutReducer,
});

const persistConfig = {
  key: 'root',
  storage,
};

const persistedReducer = persistReducer(persistConfig, rootReducer);

// Use Redux Toolkit's configureStore
export const store = configureStore({
  reducer: persistedReducer,
});

export const persistor = persistStore(store);