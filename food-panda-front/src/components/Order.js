import { useState } from "react"
import Food from "./Food"
const Order = ({order}) =>{

    const[showFoods,setShowFoods] = useState(false)
    const onClick = (e) =>{
        e.preventDefault()
        console.log(showFoods)
        console.log(order.foods)
        setShowFoods(!showFoods)
    }  
    return(
        <div>
            <div className = "speciality" onClick={(e) => onClick(e)}>
                <h3>
                    {order.restaurant}
                </h3>
                <h3>
                    {order.price + " RON"}
                </h3>
                <h3 >
                    {order.date + " at " + order.time}
                </h3>
                <h3 className="status">
                    {order.status}
                </h3>
            </div>
            {showFoods && <div className = "container-foods">
                            {order.foods.map((food, index) => (
                                <Food key = {index} food = {food} />
                            ))}
                        </div>} 
        </div>
       
    )
}

export default Order