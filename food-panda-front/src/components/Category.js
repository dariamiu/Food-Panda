import Food from "./Food"
const Category = ({menuCategory}) =>{
    return(
        <div>
            <div className = "speciality">
                <h3>
                    {menuCategory.categoryName}
                </h3>
            </div>
            <div>
                {menuCategory.foods?.map((food,index) => (
                    <Food key = {index} food = {food}/>
                ))} 
            </div>
        </div>
    )
}

export default Category