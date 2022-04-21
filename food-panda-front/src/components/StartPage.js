import { useState } from 'react'
import { useNavigate } from 'react-router-dom'


const StartPage = () => {

    const navigate = useNavigate()

    const onClickCustomer = (e) => {
        e.preventDefault()
        navigate('/customer', {
        })
    }

    const onClickAdmin = (e) => {
        e.preventDefault()
        navigate('/admin', {
        })
    }
    return(
        
    <div>

    <div className='container'>
		<h1 className='coloredTitle'>Welcome</h1>
        <img src={require('./logo.png')} />
    </div>
    <div className='container'>		
            <button onClick={(e) => onClickCustomer(e)} 
            class="btn btn-primary btn-block" type="submit">
                Customer</button>
            <button onClick={(e) => onClickAdmin(e)} 
            class="btn btn-primary btn-block" type="submit">
                Administrator</button>
    </div>


    </div>

    )
}

export default StartPage;