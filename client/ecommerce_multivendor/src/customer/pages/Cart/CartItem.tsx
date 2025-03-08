import { Add, Close, Remove } from "@mui/icons-material";
import { Button, Divider, IconButton } from "@mui/material";
import React from "react";

function CartItem() {
    const handleUpdateQuantity = ()=>{

    }
  return (
    <div className="border rounded-md relative">
      <div className="p-5 flex gap-3">
        <div>
          <img
            className="w-[90px] rounded-md"
            src="https://www.houseofmasaba.com/cdn/shop/files/Masaba100410copy.jpg?v=1720173528"
          />
        </div>
        <div className="space-y-2">
          <h1 className="font-semibold text-lg">Ram Clothing</h1>
          <p className="text-grey-600 font-medium text-sm">
            Turqoise blue stonework stain designer saree
          </p>
          <p className="text-gray-400 text-xs">
            <strong>Sold by:</strong>
            Natural Lifestyle products private limited
          </p>
          <p className="text-sm">7 days replacement available</p>
          <p className="text-sm text-gray-500">
            <strong>Quantity:</strong>5
          </p>
        </div>
        
      </div>
      <Divider />
       <div className="flex justify-between items-center ">
       <div className="px-5 py-2 flex justify-between items-center">
          <div className="flex item-center gap-2 w-[140px] justify-between">
            <Button
            onClick={handleUpdateQuantity}
              disabled={true}
            >
              <Remove />
            </Button>
            <span>{5}</span>
            <Button onClick={handleUpdateQuantity}>
              <Add />
            </Button>
          </div>
        </div>
        <div className="pr-5">
            <p className="text-gray-700 font-medium">$799</p>
        </div>
       </div>
       <div className="absolute top-1 right-1">
        <IconButton color="primary">
            <Close/>
        </IconButton>

       </div>
    </div>
  );
}

export default CartItem;
