import {Action, createSlice, PayloadAction} from "@reduxjs/toolkit";

interface State {
    get: {
        data: any | null,
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
export const slice = createSlice({
    name: "routes",
    initialState: initState,
    reducers: {
        getStart: (state: State, action: Action) => {
            state.get.loading = true
        },
        getEnd: (state: State, action: Action) => {
            state.get.loading = false
        },
        getSuccess: (state: State, action: PayloadAction<any>) => {
            state.get.data = action.payload
        },
        getFail: (state: State, action: PayloadAction<string>) => {
            state.get.error = action.payload
        }
    }
})

export const reducer = slice.reducer
export default reducer