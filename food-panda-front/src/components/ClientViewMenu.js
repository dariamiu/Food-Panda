import { useState, useEffect } from 'react'
import { useLocation } from "react-router-dom"
import { useNavigate } from "react-router-dom"
import Category2 from './Category2'
import ShoppingCart from './ShoppingCart'
const ClientViewMenu = () =>  {

    const {state} = useLocation()
    const [menuCategories, setMenuCategories] = useState([])
    const navigate = useNavigate()
    var cart = new ShoppingCart

    const fecthMenuCategories = async() => {
        const res = await fetch(`http://localhost:8080/foodpanda/food/view/${state.restaurant}`)
        const data = await res.json()
        console.log(data)
        return data
    }

    useEffect(() =>{
        const getMenuCategories = async() => {
            const menuCategoriesLocal = await fecthMenuCategories()
            setMenuCategories(menuCategoriesLocal)
        }
        getMenuCategories()
    
    },[])

    console.log(menuCategories)

    const onLogOutSubmit = (e) => {
        e.preventDefault()

        navigate('/', {
        })
    }

    const onClickViewCart = (e) => {
        e.preventDefault()
        console.log(cart.foods)
        console.log(state.client)
        navigate('/client/cart', {
            state: {
                cart : cart,
                client : state.client,
                restaurant : state.restaurant
            }
        })
    }



    return(
        <div>
            <div className = "row">
                 <div style={{color: '#800040', marginLeft : 50, fontSize : 50}}>FoodPanda</div>
                 <button className = "btn" style = {{marginLeft : 900}} type="submit" id='btn-log-out' onClick={(e) => onClickViewCart(e)}>
            View Cart
        </button>
                 <button className = "btn" style = {{marginLeft : 20}} type="submit" id='btn-log-out' onClick={(e) => onLogOutSubmit(e)}>
                    Log out
                 </button>
            </div>
            <hr></hr>
            <div>
                <div className = "container-2">
                    <div>
                        <h1 style={{marginLeft : 10, color: '#800040'}} className="details">{"Menu " + state.restaurant} </h1>
                        <br></br>
                        <div >
                            {menuCategories?.map((menuCategory, index) => (
                                <Category2 key ={index} menuCategory = {menuCategory} cart={cart}/>
                            ))}
                        </div>  
                    </div>
                </div>
            </div>
      </div>


    )

}
export default ClientViewMenu