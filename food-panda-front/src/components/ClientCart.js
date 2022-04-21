import { useState, useEffect } from 'react'
import { useLocation } from "react-router-dom"
import { useNavigate } from "react-router-dom"
import Food from './Food'

const ClientCart = () =>{

    const {state} = useLocation()
    const navigate = useNavigate()



    const onLogOutSubmit = (e) => {
        e.preventDefault()

        navigate('/', {
        })
    }
    
    const onClickPlaceOrder = async(e) => {
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
        }
    }

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
                <div className = "container-2">
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
                <button className = "btn" style = {{marginLeft : 1100 }} onClick={(e) => onClickPlaceOrder(e)} >
                    Place Order
                 </button>
                </div>
               </div>  
               
            </div>
      </div>


    )

}
export default ClientCart