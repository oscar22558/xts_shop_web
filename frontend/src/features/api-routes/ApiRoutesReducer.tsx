import {Action, createSlice, PayloadAction} from "@reduxjs/toolkit";
import RouteList from "./models/RouteList";

interface State {
    get: {
        data: RouteList | null,
        loading: boolean,
        error: string | null
    },
}
const initState: State = {
    get: {
        data: null,
        loading: false,
        error: null
    }
}
export const ApiRoutesSlice = createSlice({
    name: "routes",
    initialState: initState,
    reducers: {
        getStart: (state: State, action: Action) => {
            state.get.loading = true
        },
        getEnd: (state: State, action: Action) => {
            state.get.loading = false
        },
        getSuccess: (state: State, action: PayloadAction<RouteList>) => {
            state.get.data = action.payload
        },
        getFail: (state: State, action: PayloadAction<string>) => {
            state.get.error = action.payload
        }
    }
})

export const ApiRoutesReducer = ApiRoutesSlice.reducer
export default ApiRoutesReducer