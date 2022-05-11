import { useNavigate } from "react-router-dom"
import { useState, useEffect } from "react"
import { useLocation } from "react-router-dom"

const ClientInitialView = () => {

    const navigate = useNavigate()
    const {state} = useLocation()
    const client = state.client


    const onClickFood = async(e) => {
        e.preventDefault()
        console.log(state.client)
        navigate('/client/restaurants', {
            state: {
                client : state.client
            }
        })
    }
       

    const onClickViewOrders = async(e) => {
        e.preventDefault()
        navigate('/client/orders/view', {
            state: {
                client : state.client
            }
        })
    }

    const onLogOutSubmit = (e) => {
        e.preventDefault()

        navigate('/', {
        })
    }

    const onClickViewCart = (e) => {
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
                <button  onClick={(e) => onClickFood(e)} 
                    class="btn btn-primary btn-block firstButton" type="submit">
                    I want food
                </button>
                <button onClick={(e) => onClickViewOrders(e)} 
                    class="btn btn-primary btn-block" type="submit">
                    My Orders
                </button>
            </div>
        </div>
    
        )
}

export default ClientInitialView