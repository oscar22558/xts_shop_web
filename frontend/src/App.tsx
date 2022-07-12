import './App.css';
import {Provider} from "react-redux";
import {store} from "./redux/Store";
import FetchApiRoutes from "./data-sources/FetchApiRoutes";
import FetchCategories from "./data-sources/FetchCategories";
import AppRoutes from "./AppRoutes"
import FetchBrands from "./data-sources/FetchBrands"

function App() {
	return (
		<Provider store={store}>
			<FetchApiRoutes />
			<FetchCategories />
			<FetchBrands />
			<AppRoutes />
		</Provider>
	);
}

export default App;
