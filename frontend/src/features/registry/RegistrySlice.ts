import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import EmptyHttpResponse from "../models/EmptyHttpResponse";
import PostRegistryError from "./models/PostRegistryError";

type State = {
    postRegistryResponse: EmptyHttpResponse<PostRegistryError>
}

export const initialState: State = {
    postRegistryResponse: {
        error: {
            username: "",
            password: "",
            email: "",
            phone: ""
        },
        loading: false
    }
}

const RegistrySlice = createSlice({
    name: "registry",
    initialState,
    reducers: {
        postRegistryStart: (state: State)=>{
            state.postRegistryResponse.loading = true
        },
        postRegistryEnd: (state: State)=>{
            state.postRegistryResponse.loading = true
        },
        postRegistrySucceed: (state: State)=>{
            state.postRegistryResponse.error = initialState.postRegistryResponse.error
        },
        postRegistryFail: (state: State, {payload}:PayloadAction<PostRegistryError>)=>{
            state.postRegistryResponse.error = payload
        }
    }
})
export const RegistryReducer = RegistrySlice.reducer
export default RegistrySlice