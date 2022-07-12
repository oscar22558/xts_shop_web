import {CategoriesSlice} from "./CategoriesReducer";
import {createAction} from "@reduxjs/toolkit";

const sliceActions = CategoriesSlice.actions
const CateorgiesActions = {
    getAll: {
        start   : sliceActions.getAllStart,
        end     : sliceActions.getAllEnd,
        success : sliceActions.getAllSuccess,
        fail    : sliceActions.getAllFail,
        async   : createAction("categories/getAllAsync"),
    },
    get: {
        start   : sliceActions.getStart,
        end     : sliceActions.getEnd,
        success : sliceActions.getSuccess,
        fail    : sliceActions.getFail,
        async: createAction<string>("categories/getAsync")
    }
}
export default CateorgiesActions