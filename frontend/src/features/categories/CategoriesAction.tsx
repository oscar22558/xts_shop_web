import {CategoriesSlice} from "./CategoriesReducer";
import {createAction} from "@reduxjs/toolkit";

const sliceActions = CategoriesSlice.actions
const CategoriesActions = {
    getAllCategories: {
        start   : sliceActions.getAllCategoriesStart,
        end     : sliceActions.getAllCategoriesEnd,
        success : sliceActions.getAllCategoriesSuccess,
        fail    : sliceActions.getAllCategoriesFail,
        async   : createAction("categories/getAllAsync"),
    },
    getCategory: {
        start   : sliceActions.getCategoryStart,
        end     : sliceActions.getCategoryEnd,
        success : sliceActions.getCategorySuccess,
        fail    : sliceActions.getCategoryFail,
        async: createAction<string>("categories/getAsync")
    },
    setSelectedCategoryTabIndex: sliceActions.setSelectedCategoryTabIndex
}
export default CategoriesActions