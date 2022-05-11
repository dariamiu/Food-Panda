import { useState, useEffect } from 'react'
import { useLocation } from "react-router-dom"
import { useNavigate } from "react-router-dom"
import Category from './Category'
import ReactToPrint from 'react-to-print';

const AdminViewMenu = () => {

    const {state} = useLocation()
    const [menuCategories, setMenuCategories] = useState([])
    const navigate = useNavigate()
    var componentRef

    const fecthMenuCategories = async() => {
        const res = await fetch(`http://localhost:8080/foodpanda/food/view/${state.restaurant.name}`)
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
                <div  className = "container-2">
                    <div  ref={(response) => (componentRef = response)}>
                        <h1 style={{marginLeft : 10, color: '#800040'}} className="details">Menu </h1>
                        <br></br>
                        <div >
                            {menuCategories?.map((menuCategory, index) => (
                                <Category key ={index} menuCategory = {menuCategory}/>
                            ))}
                        </div>  
                    </div>
                </div>
            </div>
            <ReactToPrint
            content={() => componentRef}
            trigger={() => <button className="btn btn-primary">Print to PDF!</button>}
          />
      </div>


    )

}

export default AdminViewMenu