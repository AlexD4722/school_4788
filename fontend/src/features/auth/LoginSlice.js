import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import UserService from '../../service/UserService';
import Cookies from 'js-cookie';
// create async thunk action
export const login = createAsyncThunk(
    'login/userLogin',
    async ({ userName, password }, { rejectWithValue }) => {
        try {
            const response = await UserService.login(userName, password)
            return response; // return data
        } catch (error) {
            return rejectWithValue(error.response.data);
        }
    }
);

const loginSlice = createSlice({
    name: 'login',
    initialState: {
        status: 'idle', // 'idle', 'loading', 'succeeded', 'failed'
    },
    reducers: {
        // Reducer thông thường
    },
    extraReducers: (builder) => {
        builder
            .addCase(login.pending, (state) => {
                state.status = 'loading';
            })
            .addCase(login.fulfilled, (state, action) => {
                state.status = 'succeeded';
                //lưu login vào cookie
                Cookies.set('tokenValue', action.payload.data.token, { expires: 7, secure: true, sameSite: 'strict' });
            })
            .addCase(login.rejected, (state, action) => {
                state.status = 'failed';
            });
    },
});

export const selectLogin = (state) => state.login;
export  const { loginAction } = loginSlice.actions;
export default loginSlice.reducer;