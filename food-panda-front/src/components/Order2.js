import { useEffect, useState } from "react"
import Food from "./Food"
const Order2 = ({order}) =>{

    const[showFoods,setShowFoods] = useState(false)
    const[acceptDecline, setAcceptDecline] = useState(false)
    const[inDelivery,setInDelivery] = useState(false)
    const[delivered1,setDelivered1] = useState(false)

    const onClick = (e) =>{
        e.preventDefault()
        console.log(showFoods)
        console.log(order.foods)
        setShowFoods(!showFoods)
    }
    

    const changeStatus = (e) =>{
        e.preventDefault()
        if(order.status == "PENDING"){
            setAcceptDecline(true)
            console.log(order.id_order)
            console.log("hello")
        }else if(order.status == "ACCEPTED"){
            setInDelivery(true)
            console.log("hello1")
        }else if(order.status == "IN_DELIVERY"){
            setDelivered1(true)
            console.log("hello2")
        }
    }  

    const accept = async(e) => {
        var mystatus = "ACCEPTED"
        console.log(mystatus)
        console.log(order.id_order)
        
        const res = await fetch(`http://localhost:8080/foodpanda/order/update/${order.id_order}/${mystatus}`, {
            method: "PUT",
            headers: {
                "Content-type": "application/json"
            },
          
        })
		.then(response => response.json())
		.catch(error=>{return error.response;})
		alert(res.message)
        window.location.reload(false);
    }

    const decline = async(e) => {
        var mystatus = "DECLINED"
      
        const res = await fetch(`http://localhost:8080/foodpanda/order/update/${order.id_order}/${mystatus}`, {
            method: "PUT",
            headers: {
                "Content-type": "application/json"
            },
        
        })
		.then(response => response.json())
		.catch(error=>{return error.response;})
		alert(res.message)
        window.location.reload(false);
    }

    const in_delivery = async(e) => {
        var mystatus = "IN_DELIVERY"
       
        const res = await fetch(`http://localhost:8080/foodpanda/order/update/${order.id_order}/${mystatus}`, {
            method: "PUT",
            headers: {
                "Content-type": "application/json"
            },
          
        })
		.then(response => response.json())
		.catch(error=>{return error.response;})
		alert(res.message)
        window.location.reload(false);
    }

    const delivered = async(e) => {
        var mystatus = "DELIVERED"
       
        const res = await fetch(`http://localhost:8080/foodpanda/order/update/${order.id_order}/${mystatus}`, {
            method: "PUT",
            headers: {
                "Content-type": "application/json"
            },
           
        })
		.then(response => response.json())
		.catch(error=>{return error.response;})
		alert(res.message)
        window.location.reload(false);
    }


    return(
        <div>
            <div className = "speciality" onClick={(e) => onClick(e)}>
                <h3>
                    {order.client}
                </h3>
                <h3>
                    {order.price + " RON"}
                </h3>
                <h3 >
                    {order.date + " at " + order.time}
                </h3>
                <h3 className="status2">
                    {order.status}
                </h3>
               
            </div>
           {(order.status != "DELIVERED" && order.status != "DECLINED") && <button class="btn btn-status" type="submit" onClick={(e) => changeStatus(e)} >Change Status</button>} 
            <hr></hr>
            {showFoods && <div className = "container-foods">
                            {order.foods.map((food, index) => (
                                <Food key = {index} food = {food} />
                            ))}
            </div>} 
            {acceptDecline && <div className = "container-btn-order">
            <button class="btn btn-status" type="submit" onClick={(e) => accept(e)}>Accept</button>
            <button class="btn btn-status" type="submit" onClick={(e) => decline(e)}>Decline</button>               
            </div>} 
            {inDelivery && <div className = "container-btn-order">
            <button class="btn btn-status" type="submit" onClick={(e) => in_delivery(e)}>In Delivery</button>             
            </div>} 
            {delivered1 && <div className = "container-btn-order">
            <button class="btn btn-status" type="submit" onClick={(e) => delivered(e)}>Delivered</button>             
            </div>} 
        </div>
       
    )
}

export default Order2