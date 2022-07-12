import {ApiRoutesSlice} from "./ApiRoutesReducer";
import {createAction} from "@reduxjs/toolkit";

const sliceAction = ApiRoutesSlice.actions
const ApiRoutesActions = {
    get: {
        start   : sliceAction.getStart,
        end     : sliceAction.getEnd,
        success : sliceAction.getSuccess,
        fail    : sliceAction.getFail,
        async: createAction("apiRoutes/get/async")
    },
}

export default ApiRoutesActions