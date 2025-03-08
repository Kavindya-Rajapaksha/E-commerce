import React from 'react'
import "./ShopByCategory.css"

function ShopByCategoryCard() {
  return (
    <div className='flex gap-3 flex-col justify-center items-center cursor-pointer group'>

        <div className=' custome-border w-[150px] h-[150px] lg:w-[249px] lg:h-[249px] rounded-full bg-primary-color'>
            <img className='rounded-full group-hover:scale-95 transition-transform transform-duration-700 object-cover object-top h-full w-full '
            src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjYZbcLKMaLMgF-ODTfjj_e6_plj_9FfpQWA&s'
            alt=''/>
        </div>
        <h1>Kitchen and Table</h1>

    </div>
  )
}

export default ShopByCategoryCard