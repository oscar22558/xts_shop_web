import {Action, createSlice, PayloadAction} from "@reduxjs/toolkit";
import HttpResponse from "../models/HttpResponse";
import Category from "./models/Category";


interface State {
    getAllCategoriesResponse: HttpResponse<Category[]>
    getCategoryResponse: HttpResponse<Category|null>
    selectedCategoryTabIndex: number
}
const initState: State = {
    getAllCategoriesResponse: {
        data: [],
        loading: false,
        error: null
    },
    getCategoryResponse: {
        data: null,
        loading: false,
        error: null
    },
    selectedCategoryTabIndex: -1
}
export const CategoriesSlice = createSlice({
    name: "categories",
    initialState: initState,
    reducers: {
        getAllCategoriesStart: (state: State) => {
            state.getAllCategoriesResponse.loading = true
        },
        getAllCategoriesEnd: (state: State) => {
            state.getAllCategoriesResponse.loading = false
        },
        getAllCategoriesSuccess: (state: State, {payload}: PayloadAction<Category[]>) => {
            state.getAllCategoriesResponse.data = payload
        },
        getAllCategoriesFail: (state: State, {payload}: PayloadAction<string>) => {
            state.getAllCategoriesResponse.error = payload
        },
        getCategoryStart: (state: State) => {
            state.getCategoryResponse.loading = true
        },
        getCategoryEnd: (state: State) => {
            state.getCategoryResponse.loading = false
        },
        getCategorySuccess: (state: State, action: PayloadAction<Category>) => {
            state.getCategoryResponse.data = action.payload
        },
        getCategoryFail: (state: State, action: PayloadAction<string>) => {
            state.getCategoryResponse.error = action.payload
        },
        setSelectedCategoryTabIndex: (state: State, {payload}: PayloadAction<number>)=>{
            state.selectedCategoryTabIndex = payload
        }
    }
})

export const CategoriesReducer = CategoriesSlice.reducer
export default CategoriesReducer