import {call, put, select, takeEvery} from "@redux-saga/core/effects";
import {
    getAllAsync,
    getAllSuccess,
    getAllStart,
    getAllEnd,
    getAllFail,
    getEnd,
    getFail,
    getSuccess,
    getStart, getAsync
} from "./action";
import routesSelector from "../routes/selector"
import api from "./api"
import {PayloadAction} from "@reduxjs/toolkit";

export function* getAll(): Generator<any, any, any>{
    yield put(getAllStart())
    try{
        const routes = yield select(routesSelector)
        const url = routes.get?.data?.categories
        console.log(`Getting Data......, url: ${url}`)
        const res = yield call(api.getAll, url)
        console.log(res.data)
        yield put(getAllSuccess(res.data._embedded.categoryModelList))
    }catch (ex: any) {
       console.error(ex)
        yield put(getAllFail(ex.message as string))
    }finally {
        yield put(getAllEnd())
    }
}

export function* get(action: PayloadAction<string>): Generator<any, any, any>{
    yield put(getStart())
    try{
        const url = action.payload
        console.log(`Getting Data......, url: ${url}`)
        const res = yield call(api.get, url)
        yield put(getSuccess(res.data._embedded))
    }catch (ex: any) {
        console.error(ex)
        yield put(getFail(ex.message as string))
    }finally {
        yield put(getEnd())
    }
}

export function* getAllSaga(){
    yield takeEvery(getAllAsync, getAll)
}
export function* getSaga(){
    yield takeEvery(getAsync, get)
}
