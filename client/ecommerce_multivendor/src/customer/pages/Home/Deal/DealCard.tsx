import React from 'react'

function DealCard() {
  return (
    <div className='w-[13rem] cursor-pointer'>
        <img className='border-x-[7px] border-t-[5px] border-pink-600 w-full h-[12rem] object-cover object-top'
        src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSPDGpbim96l_hiSgddue0mdNhNJWyLI9mgug&s'
        alt=''/>
        <div className='border-4 border-black bg-black text-white pd-2 text-center'>
            <p className='text-lg font-semibold'>Smart Watch</p>
            <p className='text-2xl font-bold'>20% off</p>
            <p className='text-balance text-lg'>Shop Now</p>
        </div>
    </div>
  )
}

export default DealCard