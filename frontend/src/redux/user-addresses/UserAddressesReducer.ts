import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import HttpResponse from "../models/HttpResponse";

type State = {
    addAddressResponse: HttpResponse<null>,
    deleteAddressResponse: HttpResponse<null>
}

const initialStateTemplate: HttpResponse<null> = {
    data: null,
    error: null,
    loading: false
}

const initialState: State = {
    addAddressResponse: initialStateTemplate,
    deleteAddressResponse: initialStateTemplate
}

export const UserAddressesSlice = createSlice({
    name: "addresses",
    initialState,
    reducers: {
        addAddressStart: (state: State)=>{
            state.addAddressResponse.loading = true
        },
        addAddressEnd: (state: State)=>{
            state.addAddressResponse.loading = false
        },
        addAddressSucceed: (state: State)=>{
            state.addAddressResponse.error = null
        },
        addAddressFail: (state: State, action: PayloadAction<string>)=>{
            state.addAddressResponse.error = action.payload
        },
        clearAddAddressError: (state: State)=>{
            state.addAddressResponse.error = null
        },
        deleteAddressStart: (state: State)=>{
            state.deleteAddressResponse.loading = true
        },
        deleteAddressEnd: (state: State)=>{
            state.deleteAddressResponse.loading = false
        },
        deleteAddressSucceed: (state: State)=>{
            state.deleteAddressResponse.error = null
        },
        deleteAddressFail: (state: State, action: PayloadAction<string>)=>{
            state.deleteAddressResponse.error = action.payload
        },
        clearDeleteAddressError: (state: State)=>{
            state.deleteAddressResponse.error = null
        },
    }
})

const UserAddressesReducer = UserAddressesSlice.reducer
export default UserAddressesReducer