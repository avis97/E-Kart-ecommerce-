import React, { useEffect } from "react";
import CartItem from "./CartItem";
import { Button } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { getItem } from "../../../Store/Cart/Action";
function Cart() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { cart } = useSelector((store) => store);
  const handleCheckOut = () => {
    navigate("checkout?step=2");
  };
  useEffect(() => {
    dispatch(getItem());
  }, [cart.deleteCartItems,cart.updateCartItems]);
  return (
    <div>
      <div className="lg:grid grid-cols-3 lg:px-16 relative">
        <div className="col-span-2">
          {cart.cart?.cartItemList.map((item) => (
            <CartItem item={item} />
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
                <span className="mr-3">₹{cart.cart?.totalPrice}₹</span>
              </div>

              <div className="flex justify-between pt-3 text-black">
                <span className="ml-3">Discounts</span>
                <span className="text-green-600 mr-3">
                ₹{cart.cart?.discount}
                </span>
              </div>
              <div className="flex justify-between pt-3 text-black">
                <span className="ml-3">Delivery Charge</span>
                <span className="text-green-600 mr-3">Free</span>
              </div>
              <hr />
              <div className="flex justify-between pt-3 text-black">
                <span className="ml-3">Total Amount</span>
                <span className="text-green-600 mr-3">
                ₹{cart.cart?.totalDiscountPrice} 
                </span>
              </div>
            </div>
            <Button
              onClick={handleCheckOut}
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
  );
}

export default Cart;
