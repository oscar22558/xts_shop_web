import { createAction } from "@reduxjs/toolkit"
import { CartItemsSliceAction } from "./CartItemsReducer"

const sliceAction = CartItemsSliceAction
const CartItemsAction = {
    getItemsById: {
        start: sliceAction.getItemsByIdStart,
        end: sliceAction.getItemsByIdEnd,
        success: sliceAction.getItemsByIdSuccess,
        fail: sliceAction.getItemsByIdFail,
        clear: sliceAction.clearGetItemsResponse,
        async: createAction<number[]>("cart-items/getItemsById/aysnc")
    }
}
export default CartItemsAction