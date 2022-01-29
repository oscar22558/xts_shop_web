import {Action, createSlice, PayloadAction} from "@reduxjs/toolkit";
import {stat} from "fs";
import {getEnabledCategories} from "trace_events";
const sampleData = [
    {
        name: "Tools",
        subCategories: [
            { name: "Axe", subCategories: [],
                items: [
                    { name: "Axe 1", price: 12.3, manufacturer: "manufacturer 1"},
                    { name: "Axe 2", price: 19.3, manufacturer: "manufacturer 2"},
                ]
            },
            { name: "Pickaxe", subCategories: [],
                items: [
                    { name: "Pickaxe 1", price: 11.3, manufacturer: "manufacturer 3"},
                    { name: "Pickaxe 2", price: 14.3, manufacturer: "manufacturer 4"},
                ]
            },
        ],
        items: []
    },
    {
        name: "Mobile Device Peripherals",
        subCategories: [
            { name: "Axe", subCategories: [],
                items: [
                    { name: "Pickaxe 1", price: 11.3, manufacturer: "manufacturer 3"},
                    { name: "Pickaxe 2", price: 14.3, manufacturer: "manufacturer 4"},
                ]
            },
            { name: "Pickaxe", subCategories: [],
                items: [
                    { name: "Pickaxe 1", price: 11.3, manufacturer: "manufacturer 3"},
                    { name: "Pickaxe 2", price: 14.3, manufacturer: "manufacturer 4"},
                ]
            },
        ],
        items: []
    }
]
interface Item{
    name: string
    price: number
    manufacturer: string
}
interface Category{
    name: string
    subCategories: Category[]
    items: Item[]
}

interface State {
    getAll: {
        data: Category[],
        loading: boolean,
        error: string | null
    },
    get: {
        data: Category,
        loading: boolean,
        error: string | null
    },
}
const initState: State = {
    getAll: {
        data: sampleData,
        loading: false,
        error: null
    }, get: {
        data: sampleData[0],
        loading: false,
        error: null
    }
}
export const slice = createSlice({
    name: "categories",
    initialState: initState,
    reducers: {
        getAllStart: (state: State, action: Action) => {
            state.getAll.loading = true
        },
        getAllEnd: (state: State, action: Action) => {
            state.getAll.loading = false
        },
        getAllSuccess: (state: State, action: PayloadAction<Category[]>) => {
            state.getAll.data = action.payload
        },
        getAllFail: (state: State, action: PayloadAction<string>) => {
            state.getAll.error = action.payload
        },
        getStart: (state: State, action: Action) => {
            state.get.loading = true
        },
        getEnd: (state: State, action: Action) => {
            state.get.loading = false
        },
        getSuccess: (state: State, action: PayloadAction<Category>) => {
            state.get.data = action.payload
        },
        getFail: (state: State, action: PayloadAction<string>) => {
            state.get.error = action.payload
        }
    }
})

export const reducer = slice.reducer
export default reducer