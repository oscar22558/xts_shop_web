import {slice} from "./reducer";
import {createAction} from "@reduxjs/toolkit";

const sliceAction = slice.actions
const actions = {
    get: {
        start   : sliceAction.getStart,
        end     : sliceAction.getEnd,
        success : sliceAction.getSuccess,
        fail    : sliceAction.getFail,
        async: createAction("apiRoutes/get_async")
    },
}

export default actions