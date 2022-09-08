import { configureStore, Store } from '@reduxjs/toolkit'
import { persistReducer, persistStore } from 'redux-persist';

import middlewares  from "./middleware";
import {runSagas, rootSaga} from "./RootSaga";
import RootReducer from "./RootReducer";
import PersistReducerConfig from './PersistReducerConfig';
import { setAuthorizationHeader } from './ApiRequest';


const configureAppStore = ()=>{
    const persistedReducer = persistReducer(PersistReducerConfig, RootReducer)
    const store = configureStore({
        reducer: persistedReducer,
        middleware: (getDefaultMiddleware)=>[...middlewares, ...getDefaultMiddleware()]
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