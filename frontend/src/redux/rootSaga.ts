import createSagaMiddleware, {Saga} from "redux-saga";

import {getSaga as getAllRoutesSaga} from "./api-routes/ApiRoutesSaga";
import {postAuthenticationSaga} from "./authentication/AuthenticationSaga"

import {getAllBrandsSaga as getAllBrandsSaga} from "./brands/BrandsSaga"

import {getItemsByIdSaga as getCartItemsByIdSaga} from "./cart-items/CartItemsSaga"
import getInvoiceSaga from "./cart-items/invoice/InvoiceSaga"
import {
    getAllSaga as getAllCategoriesSaga,
    getSaga as getCategorySaga
} from "./categories/CategoriesSaga";

import {getAllOfSaga as getAllItemsSaga} from "./items/ItemsSaga";

import getOrdetrListSaga from "./order/GetOrderListSaga";
import getOrderSaga from "./order/GetOrderSaga";

import postRegistrySaga from "./registry/RegistrySaga";

import getUserSaga from "./user/sagas/getUserSaga"
import updateUserSaga from "./user/sagas/updateUserSaga"
import updatePasswordSaga from "./user/sagas/updatePasswordSaga";
import addAddressSaga from "./user-addresses/add-address/AddAddressSaga";
import deleteAddressSaga from "./user-addresses/delete-address/DeleteAddressSaga";

export const sagaMiddleware = createSagaMiddleware()
export const rootSaga = [
    getAllRoutesSaga,
    postAuthenticationSaga,
    getAllBrandsSaga,
    getCartItemsByIdSaga,
    getInvoiceSaga,
    getAllCategoriesSaga,
    getCategorySaga,
    getAllItemsSaga,
    getOrdetrListSaga,
    getOrderSaga,
    postRegistrySaga,
    getUserSaga,
    updateUserSaga,
    updatePasswordSaga,
    addAddressSaga,
    deleteAddressSaga,
]

export const runSagas = (sagas: Saga[])=>{
    sagas.forEach((saga)=>sagaMiddleware.run(saga))
}

