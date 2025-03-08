import React from 'react'
import ElectricCategory from './ElectricCategory/ElectricCategory'
import CategoryGrid from './CategoryGrid/CategoryGrid'
import Deal from './Deal/Deal'
import ShopByCategory from './ShopByCategory/ShopByCategory'
import { Button } from '@mui/material'
import { Storefront } from '@mui/icons-material'

function Home() {
  return (
    <>
    <div className='space-y-5 lg:space-y-10 relative pb-20'>
        <ElectricCategory/>
        <CategoryGrid/>
        <section className='pt-20'>
          <h1 className='text-lg lg:text-4xl font-bold text-primary-color pb-5 lg:pb-20 text-center'>
            TODAY'S DEAL
          </h1>
            <Deal/>
        </section>
        
        <section className='pt-20'>
          <h1 className='text-lg lg:text-4xl font-bold text-primary-color pb-5 lg:pb-20 text-center'>
            SHOP BY CATEGORY
          </h1>
            <ShopByCategory/>
        </section>

        <section className='pt-20 lg:px-20 relative h-[200px] lg:h-[450px] object-cover'>
          <img className='w-full h-full'
          src="https://img.freepik.com/free-photo/pretty-stylish-redhead-girl-pointing-finger-left-smiling_176420-21359.jpg?t=st=1741193587~exp=1741197187~hmac=7df216909daf800307e15a3019c41b37a82de2d86dbc466f4e4c6754279838d8&w=1380"
          alt=''
          />
          
          <div className='absolute top-1/2 left-4 lg:left-[15rem] transform-translate-y-1/2 font-semibold lg:text-4xl space-y-3 '>
          <h1> Sell your product</h1>
          <p className='text-lg md:text-2xl'> With
          <span className='logo'> ShopUs</span>
          </p>
          <div className='pt-6 flex justify-center'>
            <Button startIcon={<Storefront/>} variant='contained' size='large'>
              Become Seller
            </Button>
          </div>
          </div>
        </section>
        
    </div>

    </>
  )
}

export default Home