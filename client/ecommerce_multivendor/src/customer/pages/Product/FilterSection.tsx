import {
  Button,
  Divider,
  FormControl,
  FormControlLabel,
  FormLabel,
  Radio,
  RadioGroup,
} from "@mui/material";
import { teal } from "@mui/material/colors";
import React, { useState } from "react";
import { colors } from "../../../data/Category/filters/Color";
import { useSearchParams } from "react-router-dom";
// import { discount } from "../../../data/Category/filters/Discount";
import { priceRanges } from "../../../data/Category/filters/PriceRanges";
import { discount } from "../../../data/Category/filters/Discount";

function FilterSection() {
  const [expendColor, setExpendColor] = useState(false);
  const [searchParams, setSearchParams]=useSearchParams();

  const handleColorToggle=()=>{
    setExpendColor(!expendColor);
  };

  const updatedFilterParams = (e:any)=>{
    const {value,name} = e.target;
    if(value){
      searchParams.set(name,value);
    }else{
      searchParams.delete(name);
    }
    setSearchParams(searchParams);
  }

  const clearAllFilters = ()=>{
    console.log("clear all filters",searchParams)
    searchParams.forEach((value:any,key:any)=>{
      searchParams.delete(key);
    })
    setSearchParams(searchParams)
  }

  return (
    <div className="-z-50 space-y-5 bg-white">
      <div className="flex items-center justify-between h-[40px] px-9 lg:border-r">
        <p className="text-lg font-semibold">Filters</p>
        <Button
        onClick={clearAllFilters}
          size="small"
          className="text-teal-600 font-semibold cursor-pointer "
        >
          Clear All
        </Button>
      </div>
      <Divider />
      <div className="px-9 space-y-6">
      <section>
        <FormControl>
          <FormLabel sx={{fontSize:"16px", fontWeight:"bold", color:teal[500], pb:"14px"}}
          className="text-2xl font-semibold" id="color">Colour</FormLabel>
          <RadioGroup
            aria-labelledby="color"
            defaultValue=""
            name="color"
            onChange={updatedFilterParams}
          >
            {colors.slice(0,expendColor?colors.length:5).map((item)=>
            <FormControlLabel
              value={item.name}
              control={<Radio />}
              label={<div className="flex items-center gap-3 ">
                <p>{item.name}</p>
                <p style={{backgroundColor:item.hex}}
                className={`h-5 w-5 rounded-full ${item.name === "White"?"border":""}`}>
                </p>
              </div>}
            />
          )}
            </RadioGroup>
        </FormControl>
        <div>
          <button 
          onClick={handleColorToggle}
          className="text-primary-color cursor-pointer hover:text-teal-900 flex items-center">
            {expendColor?"hide":`+${colors.length-5}more`}
          </button>
        </div>
      </section>
      <Divider/>
      <section>
      <FormControl>
          <FormLabel sx={{fontSize:"16px", fontWeight:"bold", color:teal[500], pb:"14px"}}
          className="text-2xl font-semibold" id="price">Price</FormLabel>
          <RadioGroup
            aria-labelledby="price"
            onChange={updatedFilterParams}
            defaultValue=""
            name="price"
          >
            {priceRanges.map((item)=>
            <FormControlLabel
            key={item.name}
            value={item.value}
              control={<Radio  size="small"/>}
              label={item.name}
            />
          )}
            </RadioGroup>
        </FormControl>
      </section>
      <Divider/>
      <section>
      <FormControl>
          <FormLabel sx={{fontSize:"16px", fontWeight:"bold", color:teal[600], pb:"14px"}}
          className="text-2xl font-semibold" id="price">Discount</FormLabel>
          <RadioGroup
            aria-labelledby="discount"
            onChange={updatedFilterParams}
            defaultValue=""
            name="price"
          >
            {discount.map((item)=>
            <FormControlLabel
            key={item.name}
            value={item.value}
              control={<Radio  size="small"/>}
              label={item.name}
            />
          )}
            </RadioGroup>
        </FormControl>
      </section>
      </div>
    </div>
  );
}

export default FilterSection;
