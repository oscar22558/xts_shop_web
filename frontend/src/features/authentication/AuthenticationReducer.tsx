import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import HttpResponse from "../models/HttpResponse";
import Authentication from "./models/AuthenticationResponse";


type State = {
    authentication: HttpResponse<Authentication>
}

const initialState: State ={
    authentication: {
        data: {
            token: null,
            username: null
        },
        loading: false,
        error: null
    }
}

export const AuthenticationSlice = createSlice({
    name: "authentication",
    initialState,
    reducers: {
        authenticationStart: (state: State)=>{
            state.authentication.loading = true
        },
        authenticationEnd: (state: State)=>{
            state.authentication.loading = false
        },
        authenticationSucceed: (state: State, action: PayloadAction<Authentication>)=>{
            state.authentication.error = null
            state.authentication.data = action.payload 
        },
        authenticationFail: (state: State, action: PayloadAction<string>)=>{
            state.authentication.error = action.payload
        },
        clearAuthenticationError: (state: State)=>{
            state.authentication.error = null
        },
        destroyAuthentication: (state: State)=>{
            state.authentication.data = {
                token: null,
                username: null
            }
        },

    }
})

export const AuthenticationReducer = AuthenticationSlice.reducer
export default AuthenticationReducer