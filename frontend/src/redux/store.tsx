import { configureStore } from '@reduxjs/toolkit'
import middlewares  from "./middleware";
import {runSagas, rootSaga} from "./rootSaga";
import rootReducer from "./rootReducer";

const configureAppStore = ()=>{
    const store = configureStore({
        reducer: rootReducer,
        middleware: (getDefaultMiddleware)=>[...middlewares, ...getDefaultMiddleware()]
    })
    runSagas(rootSaga)
    return store
}
export const store = configureAppStore()
export type RootState = ReturnType<typeof store.getState>
export type Dispatch = typeof store.dispatch