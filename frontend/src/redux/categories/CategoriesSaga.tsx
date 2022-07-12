import {call, put, select, takeEvery} from "@redux-saga/core/effects";
import CateorgiesActions from "./CategoriesAction";
import routesSelector from "../api-routes/ApiRoutesSelector"
import CategoriesApi from "./CategoriesApi"
import {PayloadAction} from "@reduxjs/toolkit";


export function* getAllSaga(){
    yield takeEvery(CateorgiesActions.getAll.async, getAll)
}
export function* getSaga(){
    yield takeEvery(CateorgiesActions.get.async, get)
}

export function* getAll(): Generator<any, any, any>{
    const { start, end, success, fail } = CateorgiesActions.getAll
    yield put(start())
    try{
        const routes = yield select(routesSelector)
        const url = routes.get?.data?.categories
        const res = yield call(CategoriesApi.getAll, url)
        yield put(success(res.data?._embedded?.categoryRepresentationModelList ?? []))
    }catch (ex: any) {
       console.error(ex)
        yield put(fail(ex.message as string))
    }finally {
        yield put(end())
    }
}

export function* get(action: PayloadAction<string>): Generator<any, any, any>{
    const { start, end, success, fail } = CateorgiesActions.get
    yield put(start())
    try{
        const url = action.payload
        const res = yield call(CategoriesApi.get, url)
        yield put(success(res.data))
    }catch (ex: any) {
        console.error(ex)
        yield put(fail(ex.message as string))
    }finally {
        yield put(end())
    }
}

