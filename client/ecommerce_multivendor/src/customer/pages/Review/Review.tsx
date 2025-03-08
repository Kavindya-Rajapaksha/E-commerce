import React from 'react'
import ReviewCard from './ReviewCard'
import { Divider } from '@mui/material'

function Review() {
  return (
    <div className='p-5 lg:px-20 flex flex-col lg:flex-row gap-20'>
      <section className='w-full md:w-1/2 lg:w[30%] space-y-2 '>
      <img 
      src='https://www.houseofmasaba.com/cdn/shop/files/Masaba100410copy.jpg?v=1720173528'
      alt=''
      />

      <div>
        <div>
          <p className='font-bold text-xl'>Virani Clothing</p>
          <p className='text-lg text-gray-600'>Men's shirt</p>
        </div>
        <div className="price flex items-center gap-3 mt-5 text-2xl">
                <span className="font-sans text-gray-800">
                    Rs:4000
                </span>
                <span className="line-through text-gray-400">
                    Rs:4500
                </span>
                <span className="text-primary-color font-semibold">
                    60%
                </span>
            </div>
      </div>

      </section>
      <section className='space-y-5 w-full'>
        {[1,1,1,1].map((item)=>
        <div className='space-y-3'>
        <Divider/>
        <ReviewCard/>
        </div>
        )}
        
      </section>
        
    </div>
  )
}

export default Review