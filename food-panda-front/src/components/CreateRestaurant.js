import { useState, useEffect } from 'react'
import { useLocation } from "react-router-dom"
import Zone from "./Zone"
import { useNavigate } from "react-router-dom"
const CreateRestaurantView = () => {

    const [name, setName] = useState('')
    const [location, setLocationR] = useState('')
    const {state} = useLocation()
    const [zones, setZones] = useState([])
    const [deliveryZones,setDeliveryZones] = useState([])
    const adminName = state.admin
    const navigate = useNavigate()


    const fetchZones = async() => {
        const res = await fetch(`http://localhost:8080/foodpanda/zones`)
        const data = await res.json()
        return data;
    }


    
    useEffect(() => {
        const getZones = async () => {
            const zonesLocal = await fetchZones()
            setZones(zonesLocal)
        }
        getZones()
        
    }, [])


    const onClickSave = async() =>{
        

        var checkboxes = document.querySelectorAll('input[type=checkbox]:checked')
        if(checkboxes.length == 0){
            alert("Select delivery zones!")
            return
        }
        for (var i = 0; i < checkboxes.length; i++) {
            deliveryZones.push(checkboxes[i].value)
        }

        console.log(adminName)
        console.log(name);
        console.log(location);
        console.log(deliveryZones);

        const data = {
            name : name,
            location : location,
            deliveryZones : deliveryZones,
            adminName : adminName
        }

        if(!name){
            alert("Enter name!")
            return
        }
        if(!location){
            alert("Enter address!")
            return
        }

        const res = await fetch(`http://localhost:8080/foodpanda/restaurants/add`, {
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
            navigate('/admin/initial-view', {
                state: {
                    admin : adminName
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
        
        <div className='containerWithBorder'>
		<h1 className='coloredLoginTitle'>Create Restaurant</h1>
		<form className="add-form">
			<div className="form-control">
				<label> Restaurant Name </label>
				<input  type="text"
						placeholder="Enter name" value={name} onChange={(e) => setName(e.target.value)} />
			</div>
			<div className="form-control">
				<label> Location </label>
				<input type="text" placeholder="Enter Address" value={location} onChange={(e) => setLocationR(e.target.value)}/>
			</div>
            <div>
				<label> Zones </label>
                <form >
                    {zones.map((z,index) => (
                        <Zone key={index} zone = {z}/>
                    ))}
                </form>
			</div>

			</form>
      
				<div>
				
		      	<button onClick={(e) => onClickSave(e)} class="btn btn-primary btn-block" type="submit">Create </button>
				
				</div>
      </div>
      </div>
        

    )
};

export default CreateRestaurantView