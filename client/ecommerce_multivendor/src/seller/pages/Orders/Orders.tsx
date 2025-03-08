import React from 'react'
import OrderTable from './CreateOrderTable'

export default function Orders() {
  return (
    <div>
      <h1 className='font-bold mb-5 text-xl'>All Orders</h1>
      <OrderTable/>
    </div>
  )
}
