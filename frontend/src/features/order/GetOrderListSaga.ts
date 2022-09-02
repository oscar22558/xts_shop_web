import { takeEvery, put, call, select} from "@redux-saga/core/effects"
import { AxiosError, AxiosResponse } from "axios"
import { RootState } from "../Store"
import UserSelector from "../user/UserSelector"
import { OrdersAction } from "./OrderAction"
import OrderApi from "./OrderApi"

function* getOrderList(){
    const user: RootState["user"] = yield select(UserSelector)
    const username = user.getUserResponse.data.username
    const response: AxiosResponse = yield call(OrderApi, {username})
    yield put(OrdersAction.succeed(response.data._embedded.orderRepresentationModelList))
    
}

function* tryToGetOrderList(){
    const { start, end, fail } = OrdersAction
    yield put(start())
    try{
        yield call(getOrderList)
    }catch(error: any){
        yield put(fail("Error on fetching orders."))
    }finally{
        yield put(end())
    }
}

function* getOrdetrListSaga(){
    yield takeEvery(OrdersAction.async, tryToGetOrderList)
}
export default getOrdetrListSaga