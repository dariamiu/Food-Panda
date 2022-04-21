import { useState, useEffect } from 'react'
import { useLocation } from "react-router-dom"
import { useNavigate } from "react-router-dom"
import Restaurant from './Restaurants'

const ClientViewRestaurants= () => {

    const {state} = useLocation()
    const [restaurants, setRestaurants] = useState([])
    const [restaurantsCopy, setRestaurantsCopy] = useState([])
    const navigate = useNavigate()
    const [restaurantSearch, setRestaurantSearch] = useState("")

    const fetchRestaurants = async() => {
        const res = await fetch(`http://localhost:8080/foodpanda/restaurants/view`)
        const data = await res.json()
        console.log(data)
        return data
    }

    useEffect(() =>{
        const getRestaurants = async() => {
            const restaurantsLocal = await fetchRestaurants()
            setRestaurants(restaurantsLocal)
            setRestaurantsCopy(restaurantsLocal)
        }
        getRestaurants()       
    },[])

    //console.log(restaurants)

    const onLogOutSubmit = (e) => {
        e.preventDefault()
        navigate('/', {
        })
    }

    const goToRestaurant = ({restaurant, e}) => {
        console.log(restaurant.name)
        console.log(state.client)
        e.preventDefault()
        navigate('/client/restaurants/view-menu', {
            state : {
                restaurant : restaurant.name,
                client : state.client
            }
        })
    }

   /* function transformString(string){
        return string + " "
    }
    function search(restaurants){
        return restaurants.filter((restaurant)=> transformString(restaurant.name).toLowerCase().indexOf(restaurantSearch.toLowerCase()) > -1)
    }
    const restaurants2 = search(restaurants)*/

/*
    const onClickSearch = async(e) => {
        e.preventDefault()
        console.log(restaurantSearch)

        var PATTERN = restaurantSearch.toLocaleLowerCase()
        var restaurants2 = []
        for(var i = 0; i < restaurants.length ; i++){
            console.log(restaurants[i].name)
            var rest = (restaurants[i].name + '').toLocaleLowerCase()
            console.log(rest)
            if(rest.indexOf(PATTERN) > -1){
                console.log(rest)
                restaurants2.push(rest)
            }
        }
          <button onClick={(e) => onClickSearch(e)} class="btn" type="submit">Search</button>
                <button onClick={(e) => onViewAll(e)} class="btn" type="submit">View All</button>
       setRestaurantsCopy(restaurants2)
    }

    const onViewAll = (e) => {
        e.preventDefault()
        setRestaurantsCopy(restaurants)
    }*/

    function search(rows){
        return rows.filter((row)=> row.name.toLowerCase().indexOf(restaurantSearch.toLowerCase()) > -1)
    }
    const patients2 = search(restaurants)

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
            <div className='form-control-s container-search'>
                <input type="text"
                       placeholder="Restaurant Name" value={restaurantSearch} onChange={(e) => setRestaurantSearch(e.target.value)}/>
              
            </div>
            <div>
                <div className = "container-2">
                    <div>
                        <h1 style={{marginLeft : 10, color: '#800040'}} className="details"> All Restaurants</h1>
                        <br></br>
                        <div>
                            {patients2?.map((restaurant, index) => (
                                <Restaurant key ={index} restaurant = {restaurant} onClick = {(e) => goToRestaurant({restaurant, e})}/>
                            ))}
                        </div>  
                    </div>
                </div>
            </div>
      </div>


    )
 
}

export default ClientViewRestaurants