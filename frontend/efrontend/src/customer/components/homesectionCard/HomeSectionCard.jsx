import React from "react";
import {useNavigate} from "react-router-dom"

function HomeSectionCard({ product }){
  const navigate=useNavigate();
  const goProduct=()=>{
    navigate("/men/clothing/shirt")
  }
  return (
    <div onClick={goProduct} className="curser-pointer flex flex-col items-center bg-white rounded-lg shadow-lg overflow-hidden w-[13rem] mx-4 border border-black">
      <div>
        <img
          className="object-cover object-top w-80 h-70"
          src={product}
          alt=""
          style={{ width: "100%", height: "100%" }}
        />
      </div>
      <div className="p-4">
        <h3 className="text-lg font-medium text-gray-900">Men Casual Shirt</h3>
        <p className="mt-2 text-sm text-gray-500">Men Solid Pure Cotton Shirt</p>
      </div>
    </div>
  );
}

export default HomeSectionCard;

