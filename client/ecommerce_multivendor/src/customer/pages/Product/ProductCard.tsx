import React, { useEffect, useState } from "react";
import "./ProductCard.css"
import { Button } from "@mui/material";
import { Favorite, ModeComment } from "@mui/icons-material";
import { teal } from "@mui/material/colors";


  const images = [
    "https://sanvt.com/cdn/shop/files/SANVT_Lightweight_Men_White_1.jpg?v=1684936344",
    "https://i4.cloudfable.net/styles/550x550/8.569/White/think-im-see-temper-funny-t-shirt-20240203082948-k5bot0oc-s4.jpg",
  ];

  const ProductCard =()=>{
    const [currentImage, setCurrentImage]=useState(0);
    const [isHovered, setIsHovered]=useState(false);

    useEffect(()=>{
        let interval:any
        if(isHovered){
            interval=setInterval(()=>{
                setCurrentImage((prevImage)=>(prevImage+1)% images.length);
            },1000);
        }
        else if(interval){
            clearInterval(interval);
            interval = null;
          }
          return ()=> clearInterval(interval);
    },[isHovered])  

  return (
    <>
      <div className="group px-4 relative">
        <div className="card"
        onMouseEnter={()=>setIsHovered(true)}
        onMouseLeave={()=>setIsHovered(false)}>
          {images.map((item,index) => 
            <img className="card-media object-top"
            src={item} alt=""
            style={{transform:`translateX(${(index-currentImage)*100}%)`}}
            />
          )}
          {isHovered &&
            <div className="indicator flex flex-col items-center space-y-2 pl-[10rem]">
                <div className="flex gap-3">
                    <Button variant="contained" sx={{ backgroundColor: "white",filter: "brightness(0.9)" }}>
                        <Favorite sx={{color:teal[500]}}/>
                    </Button>
                    <Button variant="contained" sx={{ backgroundColor: "white",filter: "brightness(0.9)" }}>
                        <ModeComment sx={{color:teal[500]}}/>
                    </Button>
                </div>  

            </div>
          }
        </div>
        <div className="details pt-3 space-y-1 group-hover-effect rounded-md">
            <div className="name">
                <h1>Nike</h1>
                <p>Blue Shirt</p>

            </div>
            <div className="price flex items-center gap-3">
                <span className="font-sans text-gray-800">
                    Rs:4000
                </span>
                <span className="thin-line-through text-gray-400">
                    Rs:4500
                </span>
                <span className="text-primary-color font-semibold">
                    60%
                </span>
                
            </div>

        </div>
      </div>
    </>
  );
}

export default ProductCard;
