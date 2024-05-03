import axios from "axios"
import { API_BASE_URL } from "../../config/Apiconfig";
import {GET_USER_FAILURE, GET_USER_REQUEST, GET_USER_SUCCESS, LOGIN_FAILURE, LOGIN_REQUEST, LOGIN_SUCCESS, LOGOUT, REGISTER_FAILURE, REGISTER_REQUEST, REGISTER_SUCCESS} from "./ActionType"

const token=localStorage.getItem("jwt")
const registerRequest=()=>({type:REGISTER_REQUEST});
const registerSuccess=(user)=>({type:REGISTER_SUCCESS,payload:user});
const registerFailure=(error)=>({type:REGISTER_FAILURE,payload:error});

export const register=(userData)=> async (dispatch)=>{
    dispatch(registerRequest());
    try{
        const res=await axios.post(`${API_BASE_URL}/auth/signup`,userData);
        const user=res.data;
        console.log(user);
        if(user.token){
            localStorage.setItem("jwt",user.token)
        }
        dispatch(registerSuccess(user.token))
    }catch(error){
        dispatch(registerFailure(error.massage))
    }
}

const loginRequest=()=>({type:LOGIN_REQUEST});
const loginSuccess=(user)=>({type:LOGIN_SUCCESS,payload:user});
const loginFailure=(error)=>({type:LOGIN_FAILURE,payload:error});

export const login=(userData)=> async (dispatch)=>{
    dispatch(loginRequest());
    try{
        const res=await axios.post(`${API_BASE_URL}/auth/login`,userData);
        const user=res.data;
        console.log(user);
        if(user.token){
            localStorage.setItem("jwt",user.token)
        }
        dispatch(loginSuccess(user.token))
    }catch(error){
        dispatch(loginFailure(error.massage))
    }
}

const getUserRequest=()=>({type:GET_USER_REQUEST});
const getUserSuccess=(user)=>({type:GET_USER_SUCCESS,payload:user});
const getUserFailure=(error)=>({type:GET_USER_FAILURE,payload:error});

export const getUser=(jwt)=> async (dispatch)=>{
    dispatch(getUserRequest());
    try{
        const res=await axios.post(`${API_BASE_URL}/api/user/profile/`,{
            headers:{
                "Authorization":`Bearer ${jwt}`
            }
        });
        const user=res.data;
        console.log(user);
        dispatch(getUserSuccess(user))
    }catch(error){
        dispatch(getUserFailure(error.massage))
    }
}

export const logout=()=>(dispatch)=>{
    dispatch({type:LOGOUT,payload:null})
}