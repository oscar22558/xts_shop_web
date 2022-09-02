import { Action, createSlice, PayloadAction } from "@reduxjs/toolkit"
import HttpResponse from "../models/HttpResponse"
import Brand from "./models/Brand"

interface State{
    allBrands: HttpResponse<Brand[]>
}
const initialState: State = {
    allBrands: {
        data: [],
        error: null,
        loading: false
    }
}
export const BrandsSlice = createSlice({
    name: "brands",
    initialState,
    reducers: {
        getAllBrandsStart: (state: State)=>{
            state.allBrands.loading = true
        },
        getAllBrandsEnd: (state: State)=>{
            state.allBrands.loading = false
        },
        getAllBrandsSuccess: (state: State, action: PayloadAction<Brand[]>)=>{
            state.allBrands.data = action.payload
        },
        getAllBrandFail: (state: State, action: PayloadAction<string>)=>{
            state.allBrands.error = action.payload
        }
    }
})

export const BrandsRecuder = BrandsSlice.reducer
export default BrandsRecuder