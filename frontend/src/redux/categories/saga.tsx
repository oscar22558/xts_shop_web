import {call, put, select, takeEvery} from "@redux-saga/core/effects";
import actions from "./action";
import routesSelector from "../apiRoutes/selector"
import api from "./api"
import {PayloadAction} from "@reduxjs/toolkit";


export function* getAll(): Generator<any, any, any>{
    const { start, end, success, fail } = actions.getAll
    yield put(start())
    try{
        const routes = yield select(routesSelector)
        const url = routes.get?.data?.categories
        console.log(`Getting Data......, url: ${url}`)
        const res = yield call(api.getAll, url)
        console.log(res.data)
        yield put(success(res.data?._embedded?.categoryModelList ?? []))
    }catch (ex: any) {
       console.error(ex)
        yield put(fail(ex.message as string))
    }finally {
        yield put(end())
    }
}

export function* get(action: PayloadAction<string>): Generator<any, any, any>{
    const { start, end, success, fail } = actions.get
    yield put(start())
    try{
        const url = action.payload
        console.log(`Getting Data......, url: ${url}`)
        const res = yield call(api.get, url)
        console.log(res.data)
        yield put(success(res.data))
    }catch (ex: any) {
        console.error(ex)
        yield put(fail(ex.message as string))
    }finally {
        yield put(end())
    }
}

export function* getAllSaga(){
    yield takeEvery(actions.getAll.async, getAll)
}
export function* getSaga(){
    yield takeEvery(actions.get.async, get)
}
