import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import HttpResponse from "../models/HttpResponse";
import AuthenticationFormError from "./models/AuthenticationFormError";
import Authentication from "./models/AuthenticationResponse";


type State = {
    authentication: HttpResponse<Authentication, AuthenticationFormError>
}

const initialState: State ={
    authentication: {
        data: {
            token: null,
            username: null
        },
        loading: false,
        error: {
            username: "",
            password: "",
            form: ""
        }
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
            state.authentication.error = initialState.authentication.error
            state.authentication.data = action.payload 
        },
        authenticationFail: (state: State, action: PayloadAction<AuthenticationFormError>)=>{
            state.authentication.error = action.payload
        },
        clearAuthenticationError: (state: State)=>{
            state.authentication.error = initialState.authentication.error
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