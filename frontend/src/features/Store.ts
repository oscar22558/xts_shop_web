import { configureStore } from '@reduxjs/toolkit'
import { persistReducer, persistStore } from 'redux-persist';

import middlewares  from "./middleware";
import {runSagas, rootSaga} from "./RootSaga";
import RootReducer from "./RootReducer";
import PersistReducerConfig from './PersistReducerConfig';
import { setAuthorizationHeader } from './ApiRequest';
import thunk from "redux-thunk"

const configureAppStore = ()=>{
    const persistedReducer = persistReducer( PersistReducerConfig, RootReducer)
    const store = configureStore({
        reducer: persistedReducer,
        middleware: ()=>[...middlewares, thunk]
    })
    const persistor = persistStore(store, null, ()=>{
        const authToken = store.getState().authentication.authentication.data.token ?? ""
        setAuthorizationHeader(authToken)
    })
    runSagas(rootSaga)
    return {store, persistor}
}
const persistedStore = configureAppStore()
export const store = persistedStore.store
export const persistor = persistedStore.persistor
export type RootState = ReturnType<typeof store.getState>
export type Dispatch = typeof store.dispatch