import Food2 from "./Food2"
const Category2 = ({menuCategory,cart}) =>{
    
    return(
        <div>
            <div className = "speciality">
                <h3>
                    {menuCategory.categoryName}
                </h3>
            </div>
            <div>
                {menuCategory.foods?.map((food,index) => (
                    <Food2 key = {index} food = {food} cart={cart}/>
                ))} 
            </div>
        </div>
    )
}

export default Category2