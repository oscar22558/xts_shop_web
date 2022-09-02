import {Action, createSlice, PayloadAction} from "@reduxjs/toolkit";
import BrandFilter from "../../data-sources/models/BrandFilter";
import FetchItemOptions from "../../data-sources/models/FetchItemOptions";
import ItemSorting from "../../data-sources/models/ItemSorting";
import PriceFilter from "../../data-sources/models/PriceFilter";
import HttpResponse from "../models/HttpResponse";
import Item from "./models/Item";

interface State {
    all: {
        of: {
            categoryId: number | null
        } & HttpResponse<Item[]>
    },
    fetchItemOptions: FetchItemOptions
}
const initState: State = {
    all: {
        of: {
            categoryId: null,
            data: [],
            error: null,
            loading: false
        }
    },
    fetchItemOptions: {
        filter: {
            maxPrice: 10000,
            minPrice: 0,
            brandIds: null
        },
        sorting: {
            method: "price",
            order: "asc"  
        }
    }
}
export const ItemsSlice = createSlice({
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
        setFetchItemBrandFilter: (state: State, action: PayloadAction<BrandFilter>) => {
            state.fetchItemOptions.filter.brandIds = action.payload.brandIds
        },
        setFetchItemPriceFilter: (state: State, action: PayloadAction<PriceFilter>)=>{
            state.fetchItemOptions.filter = {
                ...state.fetchItemOptions.filter,
                ...action.payload
            }
        },
        setSortingOptions: (state: State, action: PayloadAction<ItemSorting>)=>{
            state.fetchItemOptions.sorting = action.payload
        },
    }
})
export const ItemsReducer = ItemsSlice.reducer
export default ItemsReducer