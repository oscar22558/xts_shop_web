import { createSlice, PayloadAction } from "@reduxjs/toolkit"
import HttpResponse from "../models/HttpResponse"
import UpdatePasswordError from "./models/UpdatePasswordError"
import UpdateUserColumnErrors from "./models/UpdateUserColumnErrors"
import UpdateUserErrorResponse from "./models/UpdateUserErrorResponse"
import User from "./models/User"

type State = {
    getUserResponse: HttpResponse<User>
    updateUserResponse: HttpResponse<null, UpdateUserColumnErrors>,
    updatePasswordResponse: HttpResponse<null, UpdatePasswordError | null>
}

const initialState: State = {
    getUserResponse: {
        data: {
            id: -1,
            username: "",
            email: "",
            phone: "",
            addresses: []
        },
        error: null,
        loading: false
    },
    updateUserResponse: {
        data: null,
        error: {
            username: "",
            email: "",
            phone: ""
        },
        loading: false
    },
    updatePasswordResponse: {
        data: null,
        error: null,
        loading: false
    }
}

export const UsersSlice = createSlice({
    name: "users",
    initialState,
    reducers: {
        getUserStart: (state: State)=>{
            state.getUserResponse.loading = true
        },
        getUserEnd: (state: State)=>{
            state.getUserResponse.loading = false
        },
        getUserSucceed: (state: State, action: PayloadAction<User>)=>{
            state.getUserResponse.data = action.payload
            state.getUserResponse.error = null
        },
        getUserFail: (state: State, action: PayloadAction<string>)=>{
            state.getUserResponse.error = action.payload
        },
        updateUserStart: (state: State)=>{
            state.updateUserResponse.loading = true
        },
        updateUserEnd: (state: State)=>{
            state.updateUserResponse.loading = false
        },
        updateUserSucceed: (state: State, {payload}: PayloadAction<User>)=>{
            state.getUserResponse.data = payload
            state.updateUserResponse.error = initialState.updateUserResponse.error
        },
        updateUserFail: (state: State, {payload}: PayloadAction<UpdateUserColumnErrors>)=>{
            state.updateUserResponse.error = payload
        },
        clearUpdateUserError: (state: State)=>{
            state.updateUserResponse.error = initialState.updateUserResponse.error
        },
        updatePasswordStart: (state: State)=>{
            state.updatePasswordResponse.loading = true
        },
        updatePasswordEnd: (state: State)=>{
            state.updatePasswordResponse.loading = false
        },
        updatePasswordSucceed: (state: State)=>{
            state.updatePasswordResponse.error = null
        },
        updatePasswordFail: (state: State, action: PayloadAction<UpdatePasswordError>)=>{
            state.updatePasswordResponse.error = action.payload
        },
        clearUpdatePasswordError: (state: State)=>{
            state.updatePasswordResponse.error = null  
        }
    }
})

export const UserReducer = UsersSlice.reducer
export default UserReducer