import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import HttpResponse from "../models/HttpResponse";
import Authentication from "./models/Authentication";


type State = {
    authentication: HttpResponse<Authentication>
}

const initialState: State ={
    authentication: {
        data: {
            authToken: null,
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
            state.authentication.data = action.payload 
        },
        authenticationFail: (state: State, action: PayloadAction<string>)=>{
            state.authentication.error = action.payload
        }
    }
})

export const AuthenticationReducer = AuthenticationSlice.reducer
export default AuthenticationReducer