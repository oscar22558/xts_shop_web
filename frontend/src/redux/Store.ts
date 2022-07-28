import { configureStore } from '@reduxjs/toolkit'
import middlewares  from "./middleware";
import {runSagas, rootSaga} from "./RootSaga";
import RootReducer from "./RootReducer";

const configureAppStore = ()=>{
    const store = configureStore({
        reducer: RootReducer,
        middleware: (getDefaultMiddleware)=>[...middlewares, ...getDefaultMiddleware()]
    })
    runSagas(rootSaga)
    return store
}
export const store = configureAppStore()
export type RootState = ReturnType<typeof store.getState>
export type Dispatch = typeof store.dispatch