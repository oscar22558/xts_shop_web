import {call, put, takeEvery} from "@redux-saga/core/effects";
import CategoriesActions from "./CategoriesAction";
import CategoriesApi from "./CategoriesApi"
import {PayloadAction} from "@reduxjs/toolkit";


export function* getAllSaga(){
    yield takeEvery(CategoriesActions.getAllCategories.async, getAll)
}
export function* getSaga(){
    yield takeEvery(CategoriesActions.getCategory.async, get)
}

export function* getAll(): Generator<any, any, any>{
    const { start, end, success, fail } = CategoriesActions.getAllCategories
    yield put(start())
    try{
        const res = yield call(CategoriesApi.getAll)
        yield put(success(res.data?._embedded?.categoryRepresentationModelList ?? []))
    }catch (ex: any) {
       console.error(ex)
        yield put(fail(ex.message as string))
    }finally {
        yield put(end())
    }
}

export function* get({payload}: PayloadAction<number>): Generator<any, any, any>{
    const { start, end, success, fail } = CategoriesActions.getCategory
    yield put(start())
    try{
        const res = yield call(CategoriesApi.get, payload)
        yield put(success(res.data))
    }catch (ex: any) {
        console.error(ex)
        yield put(fail(ex.message as string))
    }finally {
        yield put(end())
    }
}

