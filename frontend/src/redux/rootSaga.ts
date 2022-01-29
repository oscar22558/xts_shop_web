import createSagaMiddleware, {Saga} from "redux-saga";
import {getAllSaga as categoriesGetAllSaga, getSaga as categoriesGetSaga} from "./categories/saga";
import {getSaga as routesGetSaga} from "./routes/saga";

export const sagaMiddleware = createSagaMiddleware()
export const rootSaga = [
    routesGetSaga,
    categoriesGetAllSaga,
    categoriesGetSaga
]
export const runSagas = (sagas: Saga[])=>{
    sagas.forEach((saga)=>sagaMiddleware.run(saga))
}

