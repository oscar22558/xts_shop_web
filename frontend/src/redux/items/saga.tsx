import {call, put, select, takeEvery} from "@redux-saga/core/effects";
import actions from "./action";
import api from "./api"
import {PayloadAction} from "@reduxjs/toolkit";
import selector from "./selector"
import FetchItemOptions from "../../dataSources/dto/FetchItemOptions";

function* getAllOf(action: PayloadAction<string>): Generator<any, any, any>{
    const { start, end, success, fail } = actions.getAll.of
    const url = action.payload
    yield put(start())
    try{
        const fetchItemOptions = (yield select(selector)).fetchItemOptions as FetchItemOptions
        const urlWithRequestParams = addRequestParams(url, fetchItemOptions)
        const res = yield call(api.getAll.of, urlWithRequestParams)
        yield put(success(res.data?._embedded?.itemModelList ?? []))
    }catch (ex: any) {
        console.error(ex)
        yield put(fail(ex.message as string))
    }finally {
        yield put(end())
    }
}

function addRequestParams(url: string, option: FetchItemOptions){
    let urlWithFilter = url

    urlWithFilter += option.sorting?.method == "price" 
        ? "?sortingField=price"
        : "?sortingField=latest"
    urlWithFilter += option.sorting?.order == "asc" 
        ? "&sortingDirection=asc"
        : "&sortingDirection=desc"
    urlWithFilter = option.filter.maxPrice != null ? (
        urlWithFilter+"&maxPrice="+option?.filter.maxPrice
    ): urlWithFilter
    
    urlWithFilter = option.filter.minPrice != null ? (
        urlWithFilter+"&minPrice="+option?.filter.minPrice
    ): urlWithFilter
    return urlWithFilter
}
export function* getAllOfSaga(){
    yield takeEvery(actions.getAll.of.async, getAllOf)
}


