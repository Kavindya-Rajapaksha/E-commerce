import { Avatar, Box, Button, IconButton, useMediaQuery, useTheme } from '@mui/material'
import React, { useState } from 'react'
import MenuIcon from '@mui/icons-material/Menu';
import SearchIcon from '@mui/icons-material/Search';
import { AddShoppingCart, FavoriteBorder, Storefront } from '@mui/icons-material';
import CategorySheet from './CategorySheet';
import { mainCategory } from '../../../data/Category/mainCategory';
import { useNavigate } from 'react-router-dom';

function Navbar() {
    const theme=useTheme();
    //in large it gives true
    const isLarge = useMediaQuery(theme.breakpoints.up("lg"))
    const [selectedCategory, setSelectedCategory]=useState<string | undefined>("men");
    const [showCategorySheet, setShowCategorySheet]=useState(false);
    const navigate = useNavigate()
  return (
    <>
        <Box className="sticky top-0 left-0 right-0 bg-white" sx={{zIndex:2}}>
            <div className='flex items-center justify-between px-5 lg:px-20 h-[70px] border-b'>
                <div className='flex item-center gap-9'>
                    <div className='flex items-center gap-2'>
                        {!isLarge && <IconButton>
                            <MenuIcon/>
                        </IconButton>}
                        <h1 onClick={()=>navigate("/")}
                        className='logo cursor-pointer text-lg md:text-2xl text-primary-color'>
                            ShopUs
                        </h1>
                    </div>
                    <ul className='flex items-center font-medium text-gray-800'>
                        {mainCategory.map((item)=>
                        <li onMouseLeave={()=>{
                            setShowCategorySheet(false);
                        }}
                        onMouseEnter={()=>{
                            setShowCategorySheet(true);
                            setSelectedCategory(item.categoryId);
                        }}
                            className='mainCtegory hover:text-primary-color 
                            hover:border-b-2 px-4
                            border-primary-color flex items-center '>
                                {item.name}
                                </li>
                            )}
                        
                    </ul>
                </div>
                <div className='flex gap-1 lg:gap items-center'>
                    <IconButton>
                        <SearchIcon/>
                    </IconButton>
                    {
                        true?<Button onClick={()=>navigate("/account/orders")}
                         className='flex items-center gap-2'>
                            <Avatar
                            sx={{width:29,height:29}}
                            src='https://yt3.ggpht.com/ahxwG4fsSS0JlA9uEeHJibq4j0j95_4SL2b92QIXlHpt3l7CD6z1S6EGoifepAqo3JZudLRyhg=s88-c-k-c0x00ffffff-no-rj'/>
                            <h1 className='font-semibold hidden lg:block'>
                            Kavindya
                            </h1>
                        </Button>:<Button variant='contained'>Login</Button>
                    }
                    <IconButton>
                        <FavoriteBorder sx={{fontSize:29}}/>
                    </IconButton>
                    <IconButton onClick={()=>navigate("/cart")}>
                        <AddShoppingCart className='text-gray-700' sx={{fontSize:29}}/>
                    </IconButton>
                    
                    {
                        isLarge && <Button onClick={()=>navigate("/become-seller")}
                        startIcon={<Storefront/>} variant='outlined'>
                            Become Seller
                        </Button>
                    }
                </div>
            </div>
            {showCategorySheet &&
                <div 
                onMouseLeave={()=>setShowCategorySheet(false)}
                onMouseEnter={()=>setShowCategorySheet(true)}
                className='categorySheet absolute top-[4.41rem] left-20 right-20 border'>
                    <CategorySheet sellectedCategory={selectedCategory}/>
                </div>
            }
        </Box>
    </>
  )
}

export default Navbar