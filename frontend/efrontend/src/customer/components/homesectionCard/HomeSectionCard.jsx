import React from "react";

function HomeSectionCard() {
  return (
    <div
      className="curser-pointer flex flex-col items-center
    bg-white rounded-lg shadow-lg overflow-hidden  w-[13rem] mx-4 border border-black"
    >
      <div>
        <img
          className="object-cover object-top
        w-80 h-70 "
          src="https://assets.ajio.com/medias/sys_master/root/20231205/G3z0/656ed440ddf7791519b1e6e2/-473Wx593H-461119105-blue-MODEL.jpg"
          alt=""
        />
      </div>
      <div className="p-4">
        <h3 className="text-lg font-medium text-gray-900">No_filter</h3>
        <p className="mt-2 text-sm text-gray-500">
          Men Solid Pure Cotton Shirt
        </p>
      </div>
    </div>
  );
}

export default HomeSectionCard;
