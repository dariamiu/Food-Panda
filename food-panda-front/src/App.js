import { BrowserRouter as Router, Routes, Route } from "react-router-dom"
import LoginCustomer from "./components/LoginCustomer"
import LoginAdmin from "./components/LoginAdmin"
import StartPage from "./components/StartPage";
import AdminInitialView from "./components/AdminInitialView";
import CreateRestaurantView from "./components/CreateRestaurant";
import ManageMenu from "./components/ManageMenu";
import AdminViewMenu from "./components/AdminViewMenu";
import ClientViewRestaurants from "./components/ClientViewRestaurants";
import ClientInitialView from "./components/ClientInitialView";
import ClientViewMenu from "./components/ClientViewMenu";
import ClientViewOrders from "./components/ClientViewOrders";
import ClientCart from "./components/ClientCart";
import AdminViewOrders from "./components/AdminViewOrders";


function App() {
  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<StartPage />}/>
        <Route exact path="/customer" element={<LoginCustomer />}/>
        <Route exact path="/admin" element={<LoginAdmin />}/>
        <Route exact path="/admin/initial-view" element={<AdminInitialView />}/>
        <Route exact path="/admin/create-restaurant" element={<CreateRestaurantView />}/>
        <Route exact path="/admin/manage-menu" element={<ManageMenu />}/>
        <Route exact path="/admin/view-menu-admin" element={<AdminViewMenu/>}/>
        <Route exact path="/client/initial-view" element={<ClientInitialView/>}/>
        <Route exact path="/client/restaurants" element={<ClientViewRestaurants/>}/>
        <Route exact path="/client/restaurants/view-menu" element={<ClientViewMenu/>}/>
        <Route exact path="/client/orders/view" element={<ClientViewOrders/>}/>
        <Route exact path="/client/cart" element={<ClientCart/>}/>
        <Route exact path="/admin/orders/view" element={<AdminViewOrders/>}/>
      </Routes>
    </Router>
  );
}

export default App;

