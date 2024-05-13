import React, { useEffect } from "react";
import AddressCard from "../addressCard/AddressCard";
import CartItem from "../cart/CartItem";
import { Button } from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import { useLocation } from "react-router-dom";
import { getOrderById } from "../../../Store/Order/Action";

function OrderSummary() {
  const dispatch = useDispatch();
  const location = useLocation();
  const { order } = useSelector((store) => store);
  const searchParams = new URLSearchParams(location.search);
  const orderId = searchParams.get("order_id");

  useEffect(() => {
    dispatch(getOrderById(orderId));
  }, [orderId]);

  return (
    <div>
      <div className="p-5 shadow-lg rounded-s-md border mb-5">
        <AddressCard />
      </div>
      <div>
        <div className="lg:grid grid-cols-3 lg:px-16 relative">
          <div className="col-span-2">
            {order.order?.orderItemList.map((item) => (
              <CartItem item={item}/>
            ))}
          </div>
          <div className="px-5 sticky top-0 h-[100vh] mt-5 lg:mt-0">
            <div className="border border-r-8">
              <p
                className="uppercase font-bold 
            opacity-60 pb-4 text-center"
              >
                price Details
              </p>
              <hr />
              <div className="space-y-3 font-semibold">
                <div className="flex justify-between pt-3 text-black">
                  <span className="ml-3">Price</span>
                  <span className="mr-3">757</span>
                </div>

                <div className="flex justify-between pt-3 text-black">
                  <span className="ml-3">Discounts</span>
                  <span className="text-green-600 mr-3">₹1000</span>
                </div>
                <div className="flex justify-between pt-3 text-black">
                  <span className="ml-3">Delivery Charge</span>
                  <span className="text-green-600 mr-3">Free</span>
                </div>
                <hr />
                <div className="flex justify-between pt-3 text-black">
                  <span className="ml-3">Total Amount</span>
                  <span className="text-green-600 mr-3">₹1088</span>
                </div>
              </div>
              <Button
                variant="contained"
                sx={{ px: "2.5rm", py: ".7rem", mt: "1rem" }}
                className="w-full"
              >
                Checkout
              </Button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default OrderSummary;
