import './App.css';
import {Provider} from "react-redux";
import {persistor, store} from "./features/Store";
import FetchApiRoutes from "./data-sources/api-routes/FetchApiRoutes";
import FetchCategories from "./data-sources/categories/FetchCategories";
import AppRoutes from "./routes/AppRoutes"
import FetchBrands from "./data-sources/brands/FetchBrands"
import AuthenticationProvider from './data-sources/authentication/AuthenticationProvider';
import { PersistGate } from 'redux-persist/integration/react';

const App = ()=>{
	return (
		<Provider store={store}>
			<PersistGate loading={null} persistor={persistor}>
				<AuthenticationProvider>
					<FetchApiRoutes />
					<FetchCategories />
					<FetchBrands />
					<AppRoutes />
				</AuthenticationProvider>
			</PersistGate>
		</Provider>
	);
}

export default App;
