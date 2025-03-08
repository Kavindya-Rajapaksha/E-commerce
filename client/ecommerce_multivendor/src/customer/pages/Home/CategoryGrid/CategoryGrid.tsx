import React from 'react'

function CategoryGrid() {
  return (
    <div className='grid gap-4 grid-rows-12 grid-cols-12 lg:h-[600px] px-5 lg:px-20'>
        
        <div className='col-span-3 row-span-12 text-white'>
            <img className='w-full h-full object-cover object-top rounded-md' 
            src='https://www.joshindia.com/cdn/shop/files/Petrol_2.jpg?v=1684753194&width=533' 
            alt=''/>
         </div>

         <div className='col-span-2 row-span-6 text-white'>
            <img className='w-full h-full object-cover object-top rounded-md' 
            src='https://static.nike.com/a/images/t_PDP_936_v1/f_auto,q_auto:eco/450ed1df-8e17-4d87-a244-85697874661c/NIKE+REVOLUTION+7.png' 
            alt=''/>
         </div>

         <div className='col-span-4 row-span-6 text-white'>
            <img className='w-full h-full object-cover object-top rounded-md' 
            src='https://cdn.pixabay.com/photo/2021/06/26/00/26/fashion-6364998_640.jpg' 
            alt=''/>
         </div>

         <div className='col-span-3 row-span-12 text-white'>
            <img className='w-full h-full object-cover object-top rounded-md' 
            src='https://images.unsplash.com/photo-1597983073512-90bd150e19f6?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8c2hhbHdhciUyMGthbWVlenxlbnwwfHwwfHx8MA%3D%3D' 
            alt=''/>
         </div>

         <div className='col-span-4 row-span-6 text-white'>
            <img className='w-full h-full object-cover object-top rounded-md' 
            src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR1hwMfh5zNGNFEJ_LCsounJ56P5IwUuNICaA&s' 
            alt=''/>
         </div>
         
         <div className='col-span-2 row-span-6 text-white'>
            <img className='w-full h-full object-cover object-top rounded-md' 
            src='https://panditjewellers.com/wp-content/uploads/2020/07/diamond_hjp-1.jpg' 
            alt=''/>
         </div>

    </div>
  )
}

export default CategoryGrid