import { createAction } from "@reduxjs/toolkit"
import {BrandsSlice} from "./BrandsReducer"

const sliceActions = BrandsSlice.actions
const BrandsAction = {
    getAllBrands: {
        start: sliceActions.getAllBrandsStart,
        end: sliceActions.getAllBrandsEnd,
        success: sliceActions.getAllBrandsSuccess,
        fail: sliceActions.getAllBrandFail,
        async: createAction("brands/getAllBrands/async")
    }
}
export default BrandsAction