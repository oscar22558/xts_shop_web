import '../styles/globals.css'
import type { AppProps } from 'next/app'
import {Provider} from "react-redux";
import {store} from "../redux/store";
import FetchApiRoutes from "../view/FetchApiRoutes";
import AppTopBar from "../view/TopBar/AppTopBar";
import FetchCategories from "../view/FetchCategories";

function MyApp({ Component, pageProps }: AppProps) {
    return (
        <Provider store={store}>
            <FetchApiRoutes />
            <FetchCategories />
            <AppTopBar />
            <Component {...pageProps} />
        </Provider>
    )
}

export default MyApp
