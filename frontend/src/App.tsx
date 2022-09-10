import './App.css';
import {Provider} from "react-redux";
import {persistor, store} from "./features/Store";
import FetchApiRoutes from "./data-sources/api-routes/FetchApiRoutes";
import AppRoutes from "./routes/AppRoutes"
import AuthenticationProvider from './data-sources/authentication/AuthenticationProvider';
import { PersistGate } from 'redux-persist/integration/react';

const App = ()=>{
	return (
		<Provider store={store}>
			<PersistGate loading={null} persistor={persistor}>
				<AuthenticationProvider>
					<FetchApiRoutes />
					<AppRoutes />
				</AuthenticationProvider>
			</PersistGate>
		</Provider>
	);
}

export default App;
