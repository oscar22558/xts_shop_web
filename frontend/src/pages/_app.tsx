import '../styles/globals.css'
import type { AppProps } from 'next/app'
import {Provider} from "react-redux";
import {store} from "../redux/store";
import FetchRouteList from "../components/TopBar/FetchRouteList";

function MyApp({ Component, pageProps }: AppProps) {
    return (
        <Provider store={store}>
            <FetchRouteList />
            <Component {...pageProps} />
        </Provider>
    )
}

export default MyApp
