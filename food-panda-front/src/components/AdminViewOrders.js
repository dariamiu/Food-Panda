import { useState, useEffect } from 'react'
import { useLocation } from "react-router-dom"
import { useNavigate } from "react-router-dom"
import Order2 from './Order2'

const AdminViewOrders = () => {
    const {state} = useLocation()
    const [orders, setOrders] = useState([])
    const navigate = useNavigate()
    const [restaurantSearch, setRestaurantSearch] = useState("")

  
    const fetchOrders = async() => {
        const res = await fetch(`http://localhost:8080/foodpanda/orders-restaurant/${state.restaurant.name}`)
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

    console.log(orders)


    const onLogOutSubmit = (e) => {
        e.preventDefault()

        navigate('/', {
        })
    }

    
    function search(rows){
        return rows.filter((row)=> row.status.toLowerCase().indexOf(restaurantSearch.toLowerCase()) > -1)
    }
    const patients2 = search(orders)


    return(
        <div>
            <div className = "row">
                 <div style={{color: '#800040', marginLeft : 50, fontSize : 50}}>FoodPanda</div>
                 <button className = "btn" style = {{marginLeft : 1000}} type="submit" id='btn-log-out' onClick={(e) => onLogOutSubmit(e)}>
                    Log out
                 </button>
            </div>
            <hr></hr>
            <div className='form-control-s container-search'>
                <input type="text"
                       placeholder="Status" value={restaurantSearch} onChange={(e) => setRestaurantSearch(e.target.value)}/>
              
            </div>
            <div>
                <div className = "container-2">
                    <div>
                        <h1 style={{marginLeft : 10, color: '#800040'}} className="details">Orders</h1>
                        <br></br>
                        <div>
                            {patients2?.map((order, index) => (
                                <Order2 key ={index} order = {order} />
                            ))}
                        </div>
            
                    </div> 
                    </div>
                </div>
            </div>
      


    )

}

export default AdminViewOrders