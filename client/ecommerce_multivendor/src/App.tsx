import { ThemeProvider } from '@mui/material/styles';  
import './App.css';
import Navbar from './customer/components/Navbar/Navbar';
import customeTheme from './Theme/customeTheme';
// import Home from './customer/pages/Home/Home';
import Product from './customer/pages/Product/Product';
import PageDetails from './customer/pages/ProductDetails/ProductDetails';
import Review from './customer/pages/Review/Review';
import Cart from './customer/pages/Cart/Cart';
import Checkout from './customer/pages/CheckOut/Checkout';
import Account from './customer/pages/Account/Account';
import { Route, Routes } from 'react-router-dom';
import Home from './customer/pages/Home/Home';
import ProductDetails from './customer/pages/ProductDetails/ProductDetails';
import Order from './customer/pages/Account/Order';
import BecomeSeller from './customer/pages/BecomeSeller/BecomeSeller';
import SellerDashboard from './seller/pages/SellerDashboard/SellerDashboard';

function App() {
  return (
    <ThemeProvider theme={customeTheme}>  {/* Apply the custom theme here */}
      <div>
        <Navbar />
        {/* <Home/> */}
        {/* <Product/> */}
        {/* <PageDetails /> */}
        {/* <Review/> */}
        {/* <Cart/> */}
        {/* <Checkout/> */}
        {/* <Account/> */}
        {/* <Order/> */}
        <Routes>
          <Route path ="/" element={<Home/>}/>
          <Route path ="/products/:category" element={<Product/>}/>
          <Route path ="/reviews/:productId" element={<Review/>}/>
          <Route path ="/product-details/:categoryId/:name/productId" element={<ProductDetails/>}/>
          <Route path ="/cart" element={<Cart/>}/>
          <Route path ="/checkout" element={<Checkout/>}/>
          <Route path = "/account/*" element={<Account/>}/>
          <Route path='/become-seller' element={<BecomeSeller/>}/>
          <Route path ="/seller/*" element={<SellerDashboard/>}/>
        </Routes>
      </div>
    </ThemeProvider>
  );
}

export default App;
