import React from "react";
import { menLevelTwo } from "../../../data/Category/level two/menLevelTwo";
import { womenLevelTwo } from "../../../data/Category/level two/womenLevelTwo";
import { electronicsLevelTwo } from "../../../data/Category/level two/electronicsLevelTwo";
import { furnitureLevelTwo } from "../../../data/Category/level two/furnitureLevelTwo";
import { womenLevelThree } from "../../../data/Category/level three/womenLevelThree";
import { menLevelThree } from "../../../data/Category/level three/menLevelThree";
import { electronicsLevelThree } from "../../../data/Category/level three/electronicsLevelThree";
import { furnitureLevelThree } from "../../../data/Category/level three/furnitureLevelThree.ts";
import { Box } from "@mui/material";
import { useNavigate } from "react-router-dom";

const categoryTwo: { [key: string]: any[] } = {
  men: menLevelTwo,
  women: womenLevelTwo,
  electronics: electronicsLevelTwo,
  home_furniture: furnitureLevelTwo,
};
const categoryThree: { [key: string]: any[] } = {
  men: menLevelThree,
  women: womenLevelThree,
  electronics: electronicsLevelThree,
  home_furniture: furnitureLevelThree,
};
function CategorySheet({ sellectedCategory, setShowSheet }: any) {
  const childCategory = (category: any, parentCategoryId: any) => {
    return category.filter(
      (child: any) => child.parentCategoryId === parentCategoryId
    );
  };
  const navigate = useNavigate();
  return (
    <Box
      sx={{
        zIndex: 2,
      }}
      className="bg-white shadow-lg lg:h-[300px] overflow-y-auto"
    >
      <div className="flex text-sm flex-wrap ">
        {categoryTwo[sellectedCategory]?.map((item: any, index) => (
          <div
            className={`p-8 lg:w-[20%] ${
              index % 2 === 0 ? "bg-slate-50" : "bg-white"
            }`}
          >
            <p className="text-primary-color mb-5 front-semibold">
              {item.name}
            </p>
            <ul className="space-y-3">
              {childCategory(
                categoryThree[sellectedCategory],
                item.categoryId
              ).map((item: any) => (
                <div>
                  <li onClick={()=>navigate("/products/"+item.categoryId)}
                   className="hover:text-primary-color cursor-pointer">
                    {item.name}
                  </li>
                </div>
              ))}
            </ul>
          </div>
        ))}
      </div>
    </Box>
  );
}

export default CategorySheet;
