import './App.css';
import {Provider} from "react-redux";
import {store} from "./features/Store";
import FetchApiRoutes from "./data-sources/api-routes/FetchApiRoutes";
import FetchCategories from "./data-sources/categories/FetchCategories";
import AppRoutes from "./routes/AppRoutes"
import FetchBrands from "./data-sources/brands/FetchBrands"
import AuthenticationProvider from './data-sources/authentication/AuthenticationProvider';

function App() {
	return (
		<Provider store={store}>
			<AuthenticationProvider>
				<FetchApiRoutes />
				<FetchCategories />
				<FetchBrands />
				<AppRoutes />
			</AuthenticationProvider>
		</Provider>
	);
}

export default App;
