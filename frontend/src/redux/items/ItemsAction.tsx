import {ItemsSlice} from "./ItemsReducer";
import {createAction} from "@reduxjs/toolkit";

const sliceActions = ItemsSlice.actions
export const ItemsAction = {
    getAll: {
        of: {
            start  : sliceActions.getAllOfCategoryStart,
            end    : sliceActions.getAllOfCategoryEnd,
            success: sliceActions.getAllOfCategorySuccess,
            fail   : sliceActions.getAllOfCategoryFail,
            async  : createAction<string>("items/getAll/of/category/async")
        }
    },
    setFetchItemOptions: {
        brandFilter: sliceActions.setFetchItemBrandFilter,
        priceFilter: sliceActions.setFetchItemPriceFilter,
        sorting: sliceActions.setSortingOptions
    }
}
export default ItemsAction