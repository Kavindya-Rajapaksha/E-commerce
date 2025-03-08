import { Divider } from '@mui/material'
import React from 'react'

function PricingCart() {
  return (
    <>
    <div className='space-y-3 p-5'>
        <div className='flex justify-between items-center'>
            <span>Subtotal</span>
            <span>$899</span>
        </div>
        <div className='flex justify-between items-center'>
            <span>Discount</span>
            <span>$699</span>
        </div>
        <div className='flex justify-between items-center'>
            <span>Shipping</span>
            <span>$79</span>
        </div>
        <div className='flex justify-between items-center'>
            <span>Platform Fee</span>
            <span>Free</span>
        </div>
        <div>

        </div>
       
    </div>
    <Divider/>
        <div className='flex justify-between items-center p-5 text-primary-color'>
            <span>Total</span>
            <span>$679</span>
        </div>

    </>
  )
}

export default PricingCart