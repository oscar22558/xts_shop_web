import {select, call, put, takeEvery} from "@redux-saga/core/effects";
import { Action } from "@reduxjs/toolkit";
import BrandsAction from "./BrandsAction";
import {getAllBrandsApi} from "./BrandsApi"
import routesSelector from "../api-routes/ApiRoutesSelector"
import GetAllBrandsResponse from "./models/GetAllBrandsResponse";
import { RootState } from "../Store";


export function* getAllBrandsSaga(){
    yield takeEvery(BrandsAction.getAllBrands.async, tryToGetAllBrands)
}

function* tryToGetAllBrands(action: Action): Generator<any, any, any>{
    const { start, end, fail } = BrandsAction.getAllBrands
    yield put(start)
    try{
        yield getAllBrands()
    }catch(ex: any){
        console.error(ex)
        yield put(fail(ex.message))
    }finally{
        yield put(end)
    }
}

function* getAllBrands(): Generator<any, any, any>{
    const response = yield call(getAllBrandsApi)
    const brands = (response.data as GetAllBrandsResponse)
        ._embedded
        .brandRepresentationModelList
    yield put(BrandsAction.getAllBrands.success(brands))
}