import { useState, useEffect } from 'react'
import { useLocation } from "react-router-dom"
import { useNavigate } from "react-router-dom"
import Food from './Food'
import emailjs from '@emailjs/browser'
import React, { useRef } from 'react';

const ClientCart = () =>{

    const {state} = useLocation()
    const navigate = useNavigate()
    const [details, setDetails] = useState('')
    const [address, setAddress] = useState('')


    const onLogOutSubmit = (e) => {
        e.preventDefault()

        navigate('/', {
        })
    }
    
    const onClickPlaceOrder = async(e) => {
        e.preventDefault()
        console.log(state.client)
        const data = {
            id_order : null,
            restaurant : state.restaurant,
            client : state.client,
            foods : state.cart.foods,
            status : null,
            price : state.cart.price,
            date : null,
            time : null
        }

        const res = await fetch(`http://localhost:8080/foodpanda/orders/make`, {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(data)
        })
		.then(response => response.json())
		.catch(error=>{ return error.response; })
		alert(res.message)

        if(res.success){
            navigate('/client/initial-view', {
                state: {
                    client : state.client
                }
            }) 

            sendEmail()
        }
    }


    const printpr = () => {
        var s = []
        state.cart.foods?.map((food,i) => {
            s[i] = food.name + " : " + food.price
        })
        
        console.log(s)
        
        return s
    }

    const sendEmail = () => {
        var a = printpr()
        const email = {
            from_name : state.client,
            price : state.cart.price,
            products :  JSON.stringify(a),//JSON.stringify(state.cart.foods),
            address : address,
            info : details
        }
        emailjs.send('service_c0oxztv','template_pyb128j',email,'MiFGxwnN0jAGffOKq')
      };

    return(
        <div>
            <div className = "row">
                 <div style={{color: '#800040', marginLeft : 50, fontSize : 50}}>FoodPanda</div>
                 <button className = "btn" style = {{marginLeft : 1000}} type="submit" id='btn-log-out' onClick={(e) => onLogOutSubmit(e)}>
                    Log out
                 </button>
            </div>
            <hr></hr>
            <div>
                <div className = "container-cart-1">
                    <div>
                        <h1 style={{marginLeft : 10, color: '#800040'}} className="details">Items</h1>
                        <br></br>
                        <div>
                            {state.cart.foods?.map((food, index) => (
                                <Food key ={index} food = {food}/>
                            ))}
                        </div> 
                    </div>
                <div className = "container-cart"> 
                <h3 className="price">
                        Total Price : {state.cart.price}
                </h3>
                </div>
               </div>  
               <form className="container-cart-2">
			        <div className="form-control">
				        <label> Address </label>
				        <input  type="text"
						placeholder="Enter address" value={address} onChange={(e) => setAddress(e.target.value)} />
			        </div>
			        <div className="form-control">
				        <label> Aditional Details </label>
				        <input type="text" placeholder="Enter aditional details" value={details} onChange={(e) => setDetails(e.target.value)}/>
			        </div>
                    <button className = "btn" style = {{marginLeft : 1100 }} onClick={(e) => onClickPlaceOrder(e)} >
                    Place Order
                 </button>
                 </form>
            </div>
      </div>


    )

}
export default ClientCart