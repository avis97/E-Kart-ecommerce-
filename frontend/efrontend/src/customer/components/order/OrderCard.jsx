import React from "react";
import { Grid } from "@mui/material";
import { useNavigate } from "react-router-dom";
function OrderCard() {
  const navigate = useNavigate();
  return (
    <div
      onClick={() => navigate(`/account/order/${4}`)}
      className="p-5 shadow-lg hover:shadow-2xl border mb-4"
    >
      <Grid container spacing={2} sx={{ justifyContent: "space-between" }}>
        <Grid item xs={6}>
          <div className="flex cursor-pointer">
            <img
              className="w-[5rem] h-[5rem] object-cover object-top"
              src="https://rukminim2.flixcart.com/image/612/612/xif0q/shirt/z/g/d/xl-st2-vebnor-original-imagpw72vhqfczsp.jpeg?q=70"
              alt=""
            />
            <div className="ml-5 space-y-2">
              <p className="">Men Slim-Fit Shirt For Men</p>
              <p className="opacity-50 text-xs font-semibold">Size: M</p>
              <p className="opacity-50 text-xs font-semibold">Color: Black</p>
            </div>
          </div>
        </Grid>
        <Grid item xs={2}>
          <p>₹1000</p>
        </Grid>
        <Grid item xs={4}>
          {true && (
            <div>
              {" "}
              <p>
                <span>Delivered On March 03</span>
              </p>
              <p className="text-xs">Your Item Have Been Delivered</p>
            </div>
          )}
          {false && (
            <p>
              <span>Expected Delivered On March 03</span>
            </p>
          )}
        </Grid>
      </Grid>
    </div>
  );
}

export default OrderCard;
