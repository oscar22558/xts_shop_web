import {Action, createSlice, PayloadAction} from "@reduxjs/toolkit";
import Category from "../dto/Category";


interface State {
    all: {
        data: Category[],
        loading: boolean,
        error: string | null
    },
    one: {
        data: Category | null,
        loading: boolean,
        error: string | null
    },
}
const initState: State = {
    all: {
        data: [],
        loading: false,
        error: null
    },
    one: {
        data: null,
        loading: false,
        error: null
    }
}
export const slice = createSlice({
    name: "categories",
    initialState: initState,
    reducers: {
        getAllStart: (state: State, action: Action) => {
            state.all.loading = true
        },
        getAllEnd: (state: State, action: Action) => {
            state.all.loading = false
        },
        getAllSuccess: (state: State, action: PayloadAction<Category[]>) => {
            state.all.data = action.payload
        },
        getAllFail: (state: State, action: PayloadAction<string>) => {
            state.all.error = action.payload
        },
        getStart: (state: State, action: Action) => {
            state.one.loading = true
        },
        getEnd: (state: State, action: Action) => {
            state.one.loading = false
        },
        getSuccess: (state: State, action: PayloadAction<Category>) => {
            state.one.data = action.payload
        },
        getFail: (state: State, action: PayloadAction<string>) => {
            state.one.error = action.payload
        }
    }
})

export const reducer = slice.reducer
export default reducer