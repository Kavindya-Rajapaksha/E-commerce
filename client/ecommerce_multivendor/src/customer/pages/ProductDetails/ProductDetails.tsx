import React, { useState } from 'react'
import StarIcon from '@mui/icons-material/Star';
import { teal } from '@mui/material/colors';
import { Button, Divider } from '@mui/material';
import { Add, AddShoppingCart, FavoriteBorder, LocalShipping, Remove, Shield, Wallet, WorkspacePremium } from '@mui/icons-material';
import SimilarProducts from './SimilarProducts';
import ReviewCard from '../Review/ReviewCard';

function ProductDetails() {
  const [quantity , setQuantity]=useState(1);
  return (
    <div className='px-5 lg:px-20 pt-10'>
      <div className='grid grid-cols-1 lg:grid-cols-2 gap-10'>
        <section className='flex flex-col lg:flex-row gap-5'>
          <div className='w-full lg:w-[15%] flex flex-wrap lg:flex-col gap-3'>
            {[1,1,1,1].map((item)=>
            <img className='lg-full w-[50pc] cursor-pointer'
            src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTSFfSBea2OHae2ahLj8IIxCpatsv2pcpF-HM0W-WhbzKhH3IffQsQWdgzA1zy1SXXseDU&usqp=CAU'
            alt=''/>
            )}
          </div>
          <div className='w-full lg:w-[85%]'>
            <img className='w-full rounded-md'
            src='https://www.houseofmasaba.com/cdn/shop/files/Masaba100410copy.jpg?v=1720173528'
            alt=''
            />

          </div>
        </section>
        <section>
          <h1 className='font-bold text-lg text-primary-color'>
            Ram Clothing
          </h1>
          <p className='text-gray-500 font-semibold'>
            Men bLack Shirt
          </p>
          <div className='flex justify-between items-center py-2 border w-[180px] px-3 mt-5'>
            <div className='flex gap-1 item-center'>
              <span>4</span>
              <StarIcon
              sx={{color:teal[500],
                fontSize:"17px",
              }}/>
            </div>
            <Divider orientation="vertical" flexItem/>
            <span>
              234 Rating
            </span>
          </div>
          
          <div>
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
            <p className='text-sm'>
              Inclusive of all terms. Free shipping above Rs 45000
            </p>
          </div>
          <div className='mt-7 space-y-3'>
          <div className='flex items-center gap-4'>
            <Shield sx={{color:teal[500]}}/>
            <p>Authentic and quality assured</p>
          </div>

          <div className='flex items-center gap-4'>
            <WorkspacePremium sx={{color:teal[500]}}/>
            <p>100% money back gurantee</p>
          </div>

          <div className='flex items-center gap-4'>
            <LocalShipping sx={{color:teal[500]}}/>
            <p>Free shipping and resturant</p>
          </div>

          <div className='flex items-center gap-4'>
            <Wallet sx={{color:teal[500]}}/>
            <p>Pay on delivery might be available</p>
          </div>
          </div>
          <div className='mt-7 space-y-2'>
            <h1>
              QUANTITY
            </h1>
            <div className='flex items-center gap-2 w-[140px] justify-between '>
              <Button disabled={quantity==1}
              onClick={()=>setQuantity(quantity-1)}>
                <Remove/>
              </Button>
              <span>
                {quantity}
              </span>
              <Button onClick={()=>setQuantity(quantity+1)}>
                <Add/>
              </Button>

            </div>

          </div>
          <div className='mt-10 flex items-center gap-5'>
            <Button 
            fullWidth
            variant='contained'
            startIcon={<AddShoppingCart/>}
            sx={{py:"1rem"}}>
              Add to bag
            </Button>

            <Button 
            fullWidth
            variant='outlined'
            startIcon={<FavoriteBorder/>}
            sx={{py:"1rem"}}>
              Add to wishlist
            </Button>
          </div>
          <div className='mt-5'>
            <p>
            The saree comes with an unstitched blouse piece The blouse worn by the
            model might be for modelling purpose only. Check the image of the blouse
            piece to understand how the actual blouse piece looks like.
            </p>
          </div>
          <div className='mt-7 space-y-5'>
            <ReviewCard/>
            <Divider/>
          </div>
          
        </section>
      </div>
      <div className='mt-20 ' >
        <h1 className='text-lg font-bold'>
          Similar Product
        </h1>
        <div className='pt-5'>
        <SimilarProducts/>
        </div>
        
      </div>
    </div>
  )
}

export default ProductDetails