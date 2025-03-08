import React from "react";

function SimilarProductCard() {
  return (
    <div>
      <div className="group px-4 relative">
        <div className="card">
          <img
            className="card-media object-top"
            src={
              "https://ivalinmabia.com/cdn/shop/files/sita-dark-maroon-semi-silk-saree-sarees-110.webp?v=1725373031&width=1500"
            }
            // alt=""
          />
        </div>
        <div className="details pt-3 space-y-1 group-hover-effect rounded-md">
          <div className="name">
            <h1>Nike</h1>
            <p>Blue Shirt</p>
          </div>
          <div className="price flex items-center gap-3">
            <span className="font-sans text-gray-800">Rs:4000</span>
            <span className="thin-line-through text-gray-400">Rs:4500</span>
            <span className="text-primary-color font-semibold">60%</span>
          </div>
        </div>
      </div>
    </div>
  );
}

export default SimilarProductCard;
