const Restaurant = ({restaurant, onClick}) =>{
    console.log(restaurant.name)
    return(
        <div>
            <div className = "speciality" onClick={onClick}>
                <h3>
                    {restaurant.name}
                </h3>
                <h3>
                    {restaurant.location}
                </h3>
            </div>
        </div>
    )
}

export default Restaurant