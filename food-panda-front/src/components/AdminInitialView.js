import { useNavigate } from "react-router-dom"
import { useState, useEffect } from "react"
import { useLocation } from "react-router-dom"

const AdminInitialView = () => {

    const navigate = useNavigate()
    const {state} = useLocation()
    const admin = state.admin
    const [restaurant, setRestaurant] = useState('') 

    const fetchRestaurant = async()=> {
        const admin = state.admin
        const res = await fetch(`http://localhost:8080/foodpanda/restaurants/${admin}`)
        const data = await res.json()
        console.log(data)
        return data; 
    }

    
    useEffect(() => {

        const getRestaurant = async() => {
            const restaurantLocal = await fetchRestaurant()
            console.log(restaurantLocal)
            setRestaurant(restaurantLocal)
        }
        getRestaurant()
        
    }, [])

    console.log(restaurant.name)


    const onClickCreateRestaurant = async(e) => {
        e.preventDefault()
        if(restaurant.name != null){
            alert("You have already created a restaurant");
        }else{
             navigate('/admin/create-restaurant', {
            state: {
                admin : admin
            }
        })
        }
       
    }

    const onClickManageMenu = async(e) => {
        e.preventDefault()
        if(restaurant.name == null){
            alert("You should create a restaurant first!");
        }else {
            navigate('/admin/manage-menu', {
            state: {
                admin : admin,
                restaurant : restaurant
            }
        })
        }
    }

    const onClickViewMenu = async(e) => {
        e.preventDefault()
        if(restaurant.name == null){
            alert("You should create a restaurant first!");
        }else{
             navigate('/admin/view-menu-admin', {
            state: {
                restaurant : restaurant
            }
        })
        }
       
    }

    const onClickManageOrders = async(e) => {
        e.preventDefault()
        if(restaurant.name == null){
            alert("You should create a restaurant first!");
        }else{
             navigate('/admin/orders/view', {
            state: {
                admin : admin,
                restaurant : restaurant
            }
        })
        }
       
    }
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

        
        <div className='containerWithBorder centerContainer'>
                <button  onClick={(e) => onClickCreateRestaurant(e)} 
                class="btn btn-primary btn-block firstButton" type="submit">
                    Create Restaurant</button>
                <button onClick={(e) => onClickManageMenu(e)} 
                class="btn btn-primary btn-block" type="submit">
                    Manage Menu</button>
                <button onClick={(e) => onClickViewMenu(e)} 
                class="btn btn-primary btn-block" type="submit">
                    View Menu</button>
                <button onClick={(e) => onClickManageOrders(e)} 
                class="btn btn-primary btn-block" type="submit">
                    Manage orders</button>
        
        </div>
        </div>
    
        )
}

export default AdminInitialView