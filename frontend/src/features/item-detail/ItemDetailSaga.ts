import {takeEvery, call, put} from "@redux-saga/core/effects"
import { PayloadAction } from "@reduxjs/toolkit"
import { AxiosResponse } from "axios"
import Item from "../items/models/Item"
import ItemDetailAction from "./ItemDetailAction"
import ItemDetailApi from "./ItemDetailApi"

function* tryToGetItemDetail({payload: itemId}: PayloadAction<number>){
    const {start, end, succeed, fail} = ItemDetailAction
    yield put(start())
    try{
        const response: AxiosResponse<Item> = yield call(ItemDetailApi, itemId)
        yield put(succeed(response.data))
    }
    catch(ex: any){
        yield put(fail(ex?.message))
    }finally{
        yield put(end())
    }
}

function* getItemDetailSaga(){
    yield takeEvery(ItemDetailAction.async, tryToGetItemDetail)
}
export default getItemDetailSaga