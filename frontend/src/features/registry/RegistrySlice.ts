import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import EmptyHttpResponse from "../models/EmptyHttpResponse";

type State = {
    postRegistryResponse: EmptyHttpResponse
}

const initialState: State = {
    postRegistryResponse: {
        error: null,
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
            state.postRegistryResponse.error = null
        },
        postRegistryFail: (state: State, {payload}:PayloadAction<string>)=>{
            state.postRegistryResponse.error = payload
        }
    }
})
export const RegistryReducer = RegistrySlice.reducer
export default RegistrySlice