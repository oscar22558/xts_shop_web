import {call, put, select, takeEvery} from "@redux-saga/core/effects";
import actions from "./action";
import api from "./api"
import {PayloadAction} from "@reduxjs/toolkit";

function* getAllOf(action: PayloadAction<string>): Generator<any, any, any>{
    const { start, end, success, fail } = actions.getAll.of
    yield put(start())
    try{
        const url = action.payload
        console.log(`Getting Data......, url: ${url}`)
        const res = yield call(api.getAll.of, url)
        console.log(res.data)
        yield put(success(res.data?._embedded?.itemModelList ?? []))
    }catch (ex: any) {
       console.error(ex)
        yield put(fail(ex.message as string))
    }finally {
        yield put(end())
    }
}


export function* getAllOfSaga(){
    yield takeEvery(actions.getAll.of.async, getAllOf)
}


