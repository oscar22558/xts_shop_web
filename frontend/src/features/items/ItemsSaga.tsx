import {call, put, select, takeEvery} from "@redux-saga/core/effects";
import ItemsAction from "./ItemsAction";
import ItemsApi from "./ItemsApi"
import {PayloadAction} from "@reduxjs/toolkit";
import ItemsSelector from "./ItemsSelector"
import FetchItemOptions from "../../data-sources/models/FetchItemOptions";
import { RootState } from "../Store";
import { AxiosResponse } from "axios";
import ItemModelListResponse from "./models/ItemModelListResponse";

function* getAllItems(categoryId: number){
    const { success } = ItemsAction.getAll.of
    const url = buildUrl(categoryId)
    const res: AxiosResponse<ItemModelListResponse> = yield call(ItemsApi.getAll.of, {url})
    yield put(success(res.data?._embedded?.itemRepresentationModelList ?? []))
}

function* getAllItemsOfCategory(categoryId: number){
    const { success } = ItemsAction.getAll.of
    const itemState: RootState["items"] = yield select(ItemsSelector)
    const fetchItemOptions = itemState.fetchItemOptions

    const getRequestConfig = addRequestParams(categoryId, fetchItemOptions)
    const res: AxiosResponse<ItemModelListResponse> = yield call(ItemsApi.getAll.of, getRequestConfig)
    yield put(success(res.data?._embedded?.itemRepresentationModelList ?? []))
}

function* getAllOf({payload}: PayloadAction<number>){
    const { start, end, fail } = ItemsAction.getAll.of
    yield put(start())
    try{
        const apiGenerator = payload === -1 ? getAllItems : getAllItemsOfCategory
        yield call(apiGenerator, payload)
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
        url: buildUrl(categoryId),
        params: getRequestConfig
    }
    return getConfig
}

function buildUrl(categoryId: number){
    return categoryId === -1 ? `/items` : `/categories/${categoryId}/items`
}

export function* getAllOfSaga(){
    yield takeEvery(ItemsAction.getAll.of.async, getAllOf)
}


