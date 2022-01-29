import {slice} from "./reducer";
import {createAction} from "@reduxjs/toolkit";

const actions = {
    ...slice.actions,
    getAllAsync: createAction("categories/get_all_async"),
    getAsync: createAction<string>("categories/get_async")
}
export const {
    getAllStart,
    getAllEnd,
    getAllSuccess,
    getAllFail,
    getAllAsync,
    getStart,
    getEnd,
    getSuccess,
    getFail,
    getAsync,
} = actions
export default actions