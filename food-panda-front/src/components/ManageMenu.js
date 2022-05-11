import { useState, useEffect } from 'react'
import { useLocation } from "react-router-dom"
import { useNavigate } from "react-router-dom"

const ManageMenu = () =>{

    const [category, setCategory] = useState('')
    const [name, setName] = useState('')
    const [description, setDescription] = useState('')
    const [price, setPrice] = useState('')
    const {state} = useLocation()
    const [categories, setCategories] =useState([])
    const navigate = useNavigate()

    const fetchCategories = async() => {
        const res = await fetch(`http://localhost:8080/foodpanda/food/categories`)
        const data = await res.json()
        console.log(data)
        return data;
    }
    
    useEffect(() => {
        const getCategories = async () => {
            const categoriesLocal = await fetchCategories()
            setCategories(categoriesLocal)
        }
        getCategories()
        
    }, [])

    const setCategoriess =  async() => {
        let str = '';
        categories.forEach( category =>
            str += `<option value='${category.name}'>${category.name}</option>`
        )
        document.getElementById('categories').innerHTML = str;
    }

    setCategoriess()
    

    const onClickSave = async() =>{

        var select = document.getElementById('categories');
        var category= select.options[select.selectedIndex].value;

        console.log(name);
        console.log(description)
        console.log(price)
        console.log(category)
        console.log(state.restaurant)

        if(!name){
            alert("Enter name")
            return
        }
        if(!description){
            alert("Enter description")
            return
        }
        if(!price){
            alert("Enter price")
            return
        }
        if(isNaN(price)){
            alert("Price should be a number")
            return
        }

        const data = {
            name : name,
            description : description,
            price : price,
            category : category,
            restaurant : state.restaurant.name
            
        }

        const res = await fetch(`http://localhost:8080/foodpanda/food/add`, {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(data)
        })
		.then(response => response.json())
		.catch(error=>{ return error.response; })
		alert(res.message)
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
                <h1 className='coloredLoginTitle'>Add Food</h1>

                <form className="add-form">
                    <div>
                        <label> Categories </label>
                        < select id="categories">   
                        </select>
                    </div>
                    <div className="form-control">
                        <label> Food Name </label>
                        <input  type="text"
                                placeholder="Enter food name" value={name} onChange={(e) => setName(e.target.value)} />
                    </div>
                    <div className="form-control">
                        <label> Description </label>
                        <input type="text" placeholder="Enter descriptiom" value={description} onChange={(e) => setDescription(e.target.value)}/>
                    </div>
                    <div className="form-control">
                        <label> Price </label>
                        <input type="text" placeholder="Enter price" value={price} onChange={(e) => setPrice(e.target.value)}/>
                    </div>
                    </form>
            
                        <div>
                        
                        <button onClick={(e) => onClickSave(e)} class="btn btn-primary btn-block" type="submit">Create </button>
                        
                        </div>
            </div>
      </div>
        

    )
};

export default ManageMenu