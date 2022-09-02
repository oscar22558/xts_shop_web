import { put, call, takeEvery } from "@redux-saga/core/effects"
import { PayloadAction } from "@reduxjs/toolkit";
import GetUserAction from "../../user/UserAction";
import DeleteAddressForm from "../models/DeleteAddressForm";
import UserAddressAction from "../UserAddressesAction";
import DeleteAddressApi from "./DeleteUserAddressApi";

function* deleteAddress(deleteAddressForm: DeleteAddressForm){
    yield call(DeleteAddressApi, deleteAddressForm)
    yield put(GetUserAction.async())
}

function* tryToDeleteAddress(action: PayloadAction<DeleteAddressForm>){
    const {start, end, fail} = UserAddressAction.deleteAddress
    yield put(start())
    try{
        yield call(deleteAddress, action.payload)
    }catch(ex: any){
        yield put(fail(ex?.message))
    }finally{
        yield put(end())
    }
}

export function* deleteAddressSaga(){
    yield takeEvery(UserAddressAction.deleteAddress.async, tryToDeleteAddress)
}
export default deleteAddressSaga