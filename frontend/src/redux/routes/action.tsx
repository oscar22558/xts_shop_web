import {slice} from "./reducer";
import {createAction} from "@reduxjs/toolkit";

const actions = {
    ...slice.actions,
    getAsync: createAction("routes/get_async")
}

export const {
    getStart,
    getEnd,
    getSuccess,
    getFail,
    getAsync,
} = actions
export default actions