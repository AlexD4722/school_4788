import { createSlice } from '@reduxjs/toolkit';

const initialState = {
 tasks: [],
 status: 'idle',
};

export const taskSlice = createSlice({
 name: 'task',
 initialState,
 reducers: {
   addTask: (state, action) => {
     state.tasks.push(action.payload);
   }
 }
});

export const { addTask } = taskSlice.actions;
export const selectTask = (state) => state.task;
export default taskSlice.reducer;
