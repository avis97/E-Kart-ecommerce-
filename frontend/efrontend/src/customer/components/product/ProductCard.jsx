import React from "react";
import "./ProductCard.css";
import { useNavigate } from "react-router-dom";
function ProductCard({product}) {
  const navigate=useNavigate();
  return (
    <div onClick={()=>navigate(`/product/${5}`)}
      className="productCard w-[15rem] m-3 transition-all 
    curser-pointer"
    >
      <div className="h-[20rem]">
        <img
          className="h-full w-full object-cover object-left-top "
          src={product.image}
          alt=""
        />
      </div>
      <div className="textPart bg-white p-3">
        <div>
          <p className="font-bold opacity-60">Shirt</p>
          <p>Casual shirt for blue</p>
        </div>
        <div className=""></div>
        <p className="font-semibold">$199</p>
        <p className="line-through opacity-50">$1999</p>
        <p className="text-green-600 font-semibold">70% off</p>
      </div>
    </div>
  );
}

export default ProductCard;
