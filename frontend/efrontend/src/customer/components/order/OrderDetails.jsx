import React from "react";
import AddressCard from "../addressCard/AddressCard";
import OrderTracker from "./OrderTracker";
import { Box, Grid } from "@mui/material";
import { deepPurple } from "@mui/material/colors";
import StarBorderIcon from '@mui/icons-material/StarBorder';
function OrderDetails() {
  return (
    <div className="px:5 lg:px-20">
      <div>
        <h1
          className="font-bold 
        text-xl py-7"
        >
          Delivery Address
        </h1>
        <AddressCard />
      </div>
      <div className="py-10">
        <OrderTracker activeStep={3} />
      </div>
      {[1,1,1,1,1].map((item)=><Grid className="space-x-5 hover:shadow-2xl" container>
        <Grid
          item
          container
          className="shadow-xl rounded-md
           p-5 border"
          sx={{ alignItems: "center", justifyContent: "space-between" }}
        >
          <Grid item xs={6}>
            <div className="flex items-center space-x-4">
              <img
                className="w-[5rem] h-[5rem] object-cover object-top"
                src="https://rukminim2.flixcart.com/image/612/612/xif0q/shirt/e/f/x/2xl-mens-staylist-chex-strecheble-shirt-sti-original-imagyxuhvkqzguzy.jpeg?q=70"
                alt=""
              />
              <div className="space-y-2 ml-5">
                <p className="font-semibold">Men Slim Fit Shirt</p>
                <p className="space-x-5 opacity-50 text-xs font-semibold">
                  <span>Color: red</span>
                  <span> Size: M</span>
                </p>
                <p>Seller: Puma</p>
                <p>1009</p>
              </div>
            </div>
          </Grid>
          <Grid item>
                <Box sx={{color: deepPurple[500]}}>
                    <StarBorderIcon sx={{fontSize:"2rem"}} 
                    fontSize={"2px"} className="px-2"/>
                    <span>Rate & Review Product</span>
                </Box> 
          </Grid>
        </Grid>
      </Grid>)}
      
    </div>
  );
}

export default OrderDetails;
