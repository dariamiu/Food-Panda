const Food = ({food}) => {

    var nameString = ' ' + food.name
    console.log(nameString.length)
    var dotss = [' ']
        for(var i = 0 ; i < 126 - nameString.length  ; i ++){
            dotss.push('   .   ')
        }
    
    return(
        <div className = "food">
            <h3>
                {food.name}
                {dotss}
                {food.price  + " RON"}
            </h3>
            <h3>
                {food.description}
            </h3>
        </div>
    )
}

export default Food