import {call, put, select, takeEvery} from "@redux-saga/core/effects";
import ItemsAction from "./ItemsAction";
import ItemsApi from "./ItemsApi"
import {PayloadAction} from "@reduxjs/toolkit";
import ItemsSelector from "./ItemsSelector"
import FetchItemOptions from "../../data-sources/models/FetchItemOptions";

function* getAllOf({payload}: PayloadAction<number>): Generator<any, any, any>{
    const { start, end, success, fail } = ItemsAction.getAll.of
    yield put(start())
    try{
        const fetchItemOptions = (yield select(ItemsSelector)).fetchItemOptions as FetchItemOptions
        const getRequestConfig = addRequestParams(payload, fetchItemOptions)
        const res = yield call(ItemsApi.getAll.of, getRequestConfig)
        yield put(success(res.data?._embedded?.itemRepresentationModelList ?? []))
    }catch (ex: any) {
        console.error(ex)
        yield put(fail(ex.message))
    }finally {
        yield put(end())
    }
}

function addRequestParams(categoryId: number, option: FetchItemOptions){
    let getRequestConfig = {}

    if(option.sorting != null){
        getRequestConfig = {
            sortingMethod: option.sorting.method,
            sortingDirection: option.sorting.order    
        }
    }
    getRequestConfig = {
        ...getRequestConfig,
        ...option.filter,
    }
    const getConfig = {
        url: `/categories/${categoryId}/items`,
        params: getRequestConfig
    }
    return getConfig
}

export function* getAllOfSaga(){
    yield takeEvery(ItemsAction.getAll.of.async, getAllOf)
}


