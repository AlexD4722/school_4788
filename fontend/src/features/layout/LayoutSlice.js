import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

const layoutSlice = createSlice({
    name: 'layout',
    initialState: {
        option: '', 
    },
    reducers: {
        selectLayoutAction: (state, action) => {
            console.log('action.payload', action.payload);
            state.option = action.payload
        }   
        // Reducer thông thường

    },
});

export const selectLayoutData = (state) => state.option;
export  const { selectLayoutAction } = layoutSlice.actions;
export default layoutSlice.reducer;