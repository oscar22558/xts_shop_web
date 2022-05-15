import React from 'react';
import './App.css';
import {Provider} from "react-redux";
import {store} from "./redux/store";
import FetchApiRoutes from "./views/FetchApiRoutes";
import FetchCategories from "./views/FetchCategories";
import AppRoutes from "./AppRoutes"
import { BrowserRouter } from 'react-router-dom';

function App() {
  return (
      <Provider store={store}>
        <BrowserRouter>
          <FetchApiRoutes />
          <FetchCategories />
          <AppRoutes />
        </BrowserRouter>
      </Provider>
  );
}

export default App;
