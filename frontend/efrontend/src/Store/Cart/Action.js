import {
  ADD_ITEM_TO_CART_FAILURE,
  ADD_ITEM_TO_CART_REQUEST,
  ADD_ITEM_TO_CART_SUCCESS,
  REMOVE_CART_ITEM_REQUEST,
  REMOVE_CART_ITEM_SUCCESS,
  REMOVE_CART_ITEM_FAILURE,
  UPDATE_CART_ITEM_REQUEST,
  UPDATE_CART_ITEM_SUCCESS,
  UPDATE_CAR_ITEM_FAILURE,
  GET_CART_REQUEST,
  GET_CART_SUCCESS,
  GET_CART_FAILURE,
} from "./ActionType";
import { api } from "../../config/Apiconfig";

export const addItemToCart = (reqData) => async(dispatch) => {
  dispatch({ type: ADD_ITEM_TO_CART_REQUEST });
  try {
    const { data } = await api.put("/api/cart/add", reqData.data);
    dispatch({ type: ADD_ITEM_TO_CART_SUCCESS, payload: data });
  } catch (error) {
    dispatch({ type: ADD_ITEM_TO_CART_FAILURE, payload: error.message });
  }
};

export const getItem = (reqData) => async (dispatch) => {
  dispatch({ type: GET_CART_REQUEST });
  try {
    const { data } = await api.put("/api/cart/", reqData.data);
    dispatch({ type: GET_CART_SUCCESS, payload: data });
  } catch (error) {
    dispatch({ type: GET_CART_FAILURE, payload: error.message });
  }
};

export const removeCartItem = (reqData) => async (dispatch) => {
  dispatch({ type: REMOVE_CART_ITEM_REQUEST });
  try {
    const { data } =await api.delete(`/api/cart_item/remove/${reqData.cartItemId}`);
    dispatch({ type: REMOVE_CART_ITEM_SUCCESS, payload: data });
  } catch (error) {
    dispatch({ type: REMOVE_CART_ITEM_FAILURE, payload: error.message });
  }
};

//this have to make
export const updateCartItem = (reqData) => async (dispatch) => {
  dispatch({ type: UPDATE_CART_ITEM_REQUEST });
  try {
    const { data } =await api.delete(`/api/cart/add`, reqData.data);
    dispatch({ type: UPDATE_CART_ITEM_SUCCESS, payload: data });
  } catch (error) {
    dispatch({ type: UPDATE_CAR_ITEM_FAILURE, payload: error.message });
  }
};
