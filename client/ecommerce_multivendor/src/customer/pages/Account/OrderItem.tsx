import { ElectricBolt } from '@mui/icons-material'
import { Avatar } from '@mui/material'
import { teal } from '@mui/material/colors'
import React from 'react'

function OrderItem() {
  return (
    <div className='text-sm bg-white p-5 space-y-4 border rounded-md cursor-pointer'> 
    <div className='flex items-center gap-5'>
        <div>
            <Avatar sizes="small"
             sx ={{background:teal[500]}}>
                <ElectricBolt/>
            </Avatar>
        </div>
        <div>
            <h1 className='font-bold text-primary-color'>PENDING</h1>
            <p>Arriving By Fri Jan 2022</p>
        </div>
    </div>  
    <div className='p-5 bg-teal-50 flex gap-3'>
        <div>
            <img
            className='w-[70px]'
            src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjf8KEchalHXDschnJIH0wZGSC9iM5BuSLZQ&s'
            alt=''
            />
        </div>
        <div className='w-full space-y-2'>
            <h1 className='font-bold'>Virani Clothing</h1>
            <p>Cellecor RAY 1.43" AROLED Display | 700 NITS | AOD | BT-Calling | AI
                Voice | Split Screen Smartwatch (Black Strap, Free Size)
            </p>
            <p>
                <strong>
                    size:
                </strong>
                FREE 
            </p>
        </div>
    </div>     
    </div>
  )
}

export default OrderItem