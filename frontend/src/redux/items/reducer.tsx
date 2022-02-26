import {Action, createSlice, PayloadAction} from "@reduxjs/toolkit";
import HttpResponse from "../dto/HttpResponse";
import Item from "../dto/Item";
interface State {
    all: {
        of: {
            categoryId: number | null
        } & HttpResponse<Item[]>
    }
}
const initState: State = {
    all: {
        of: {
            categoryId: null,
            data: [],
            error: null,
            loading: false
        }
    }
}
export const slice = createSlice({
    name: "items",
    initialState: initState,
    reducers: {
        getAllOfCategoryStart: (state: State, action: Action) => {
            state.all.of.loading = true
        },
        getAllOfCategoryEnd: (state: State, action: Action) => {
            state.all.of.loading = false
        },
        getAllOfCategorySuccess: (state: State, action: PayloadAction<Item[]>) => {
            state.all.of.data = action.payload
        },
        getAllOfCategoryFail: (state: State, action: PayloadAction<string>) => {
            state.all.of.error = action.payload
        },
    }
})
export const reducer = slice.reducer
export default reducer