import { useState, useEffect } from 'react'
import { useLocation } from "react-router-dom"
import { useNavigate } from "react-router-dom"
import Order from './Order'
import Food from './Food'

const ClientViewOrders = () => {
    const {state} = useLocation()
    const [orders, setOrders] = useState([])
    const navigate = useNavigate()


    const fetchOrders = async() => {
        const res = await fetch(`http://localhost:8080/foodpanda/orders-client/${state.client}`)
        const data = await res.json()
        console.log(data)
        return data
    }

    useEffect(() =>{
        const getOrders = async() => {
            const ordersLocal = await fetchOrders()
            setOrders(ordersLocal)
        }
        getOrders()       
    },[])

   

    const onLogOutSubmit = (e) => {
        e.preventDefault()

        navigate('/', {
        })
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
                        <h1 style={{marginLeft : 10, color: '#800040'}} className="details">Orders</h1>
                        <br></br>
                        <div>
                            {orders?.map((order, index) => (
                                <Order key ={index} order = {order} />
                            ))}
                        </div>
            
                    </div> 
                    </div>
                </div>
            </div>
      


    )

}

export default ClientViewOrders