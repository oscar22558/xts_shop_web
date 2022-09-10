import { createAction } from "@reduxjs/toolkit";
import ItemDetailSlice, { ItemDetailReducer } from "./ItemDetailSlice";

const actions = ItemDetailSlice.actions
const ItemDetailAction = {
    start: actions.getItemDetailStart,
    end: actions.getItemDetailEnd,
    fail: actions.getItemDetailFail,
    succeed: actions.getItemDetailSucceed,
    async: createAction<number>("item-detail/async")
}
export default ItemDetailAction