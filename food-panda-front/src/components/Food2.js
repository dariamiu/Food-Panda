const Food2 = ({food,cart}) => {

    var nameString = ' ' + food.name
    console.log(nameString.length)
    var dotss = [' ']
        for(var i = 0 ; i < 135 - nameString.length ; i ++){
            dotss.push('   .   ')
        }
    
    const onClick = ({e,food,cart}) => {
        e.preventDefault()
        
        cart.foods.push(food)
        cart.price = cart.price + food.price
        alert(food.name + " added to cart \n total price: " + cart.price)
    }
    return(
        <div>
            <div className = "food">
            <h3>
                {food.name}
                {dotss}
                {food.price  + " RON"}
            </h3>
            <h3>
                {food.description}
            </h3>
            <button class="btn btn-add" type="submit" onClick={(e) => onClick({e,food,cart})}>Add</button>
        </div>
        
        </div>
        
        
    )
}

export default Food2