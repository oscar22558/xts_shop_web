import {select, call, put, takeEvery} from "@redux-saga/core/effects";
import { PayloadAction } from "@reduxjs/toolkit";
import { AxiosResponse } from "axios";
import ApiRouteSelector from "../api-routes/ApiRoutesSelector";
import { RootState } from "../Store";
import CartItemsAction from './CartItemsAction'
import CartItemsByIdApi from "./CartItemsApi";
import CartItemsApiResponse from "./models/CartItemsApiResponse";

function* getListItemByIdApiRoute(){
    const allRoutes: RootState["routes"] = yield select(ApiRouteSelector)
    return allRoutes.get.data?.items ?? ""
}

function* getItemsById(ids: number[]){
    const apiRoute: string = yield call(getListItemByIdApiRoute)
    const response: AxiosResponse = yield call(CartItemsByIdApi, {
        url: apiRoute,
        params: {
            ids
        }
    })
    const data: CartItemsApiResponse = response.data
    const itemList = data._embedded.itemRepresentationModelList
    yield put(CartItemsAction.getItemsById.success(itemList))
}

function* tryToGetItemsById(action: PayloadAction<number[]>){
    const {start, end, fail} = CartItemsAction.getItemsById
    yield put(start())
    try{
        yield call(getItemsById, action.payload)
    }catch(ex: any){
        console.error(ex)
        yield put(fail(ex?.message))
    }finally{
        yield put(end())
    }
}

export function* getItemsByIdSaga(){
    yield takeEvery(CartItemsAction.getItemsById.async, tryToGetItemsById)
}