import React from 'react'
import { Route, Routes } from 'react-router-dom'
import SellerTable from '../admin/Pages/Sellers/SellerTable'
import Coupon from '../admin/Pages/Coupon/Coupon'
import AddNewCoupon from '../admin/Pages/Coupon/AddNewCoupon'
import GridTable from '../admin/Pages/HomePage/GridTable'
import Electronics from '../admin/Pages/HomePage/Electronics'
import ShopByCategory from '../admin/Pages/HomePage/ShopByCategory'
import Deal from '../admin/Pages/HomePage/Deal'

function AdminRoutes() {
  return (
    <div>
        <Routes>
        <Route path="/" element={<SellerTable />} />
        <Route path="/coupon" element={<Coupon />} />
        <Route path="/add-coupon" element={<AddNewCoupon />} />
        <Route path="/home-grid" element={<GridTable />} />
        <Route path="/electronics-category" element={<Electronics />} />
        <Route path="/deals" element={<Deal />} />
        <Route path="/shop-by-category" element={<ShopByCategory />} />
      </Routes>
    </div>
  )
}

export default AdminRoutes