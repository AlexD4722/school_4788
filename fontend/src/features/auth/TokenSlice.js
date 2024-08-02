import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import AuthService from '../../service/AuthService';

// create async thunk action
export const token = createAsyncThunk(
    'token/authToken',
    async ({ stringToken }, { rejectWithValue }) => {
        try {
            const response = await AuthService.authToken(stringToken)
            return response; // return data
        } catch (error) {
            return rejectWithValue(error.response.data);
        }
    }
);

const tokenSlice = createSlice({
    name: 'token',
    initialState: {
        status: 'idle', // 'idle: 0', 'loading: 1', 'succeeded: 2', 'failed: 3'
        error: null,
    },
    reducers: {
        // Reducer thông thường
    },
    extraReducers: (builder) => {
        builder
            .addCase(token.pending, (state) => {
                state.status = 'loading';
            })
            .addCase(token.fulfilled, (state, action) => {
                state.status = 'succeeded';
            })
            .addCase(token.rejected, (state, action) => {
                state.status = 'failed';
            });
    },
});

export default tokenSlice.reducer;