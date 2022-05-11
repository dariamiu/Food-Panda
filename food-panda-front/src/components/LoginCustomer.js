import { useState } from 'react'
import { useNavigate } from 'react-router-dom';

const LoginCustomer = () => {
	const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')

	
    const navigate = useNavigate()


	const onClickRegister = async () => {
	

		console.log(username)
		console.log(password)


		const data = {
			username: username,
			password: password
		}

		const addCustomer = async (data) => {
			const res = await fetch(`http://localhost:8080/foodpanda/customers/register`, {
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

		if(!username){
			alert("Enter username!")
			return
		}
		if(!password){
			alert("Enter password!")
			return
		}
		addCustomer(data);
	}


	const onClickSave = async () =>{
	console.log(username)
	console.log(password)


	const data = {
		username: username,
		password: password
	}


	const loginCustomer = async (url) => {
		const res = await fetch(`http://localhost:8080/foodpanda/customers/login`, {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(data)
        })
		.then(response => response.json())
		.catch(error=>{ return error.response; })
		console.log(res.success)

		if(res.success){
			console.log(username)
            navigate('/client/initial-view', {
                state: {
                    client : username
                }
            })
        }else{
			alert(res.message)
		}
    }

	loginCustomer(data);


	};


    return (
		<div className='containerWithBorder'>
		<h1 className='coloredLoginTitle'>Login Customer</h1>
		<form className="add-form">
			<div className="form-control">
				<label> Email </label>
				<input  type="text"
						placeholder="Enter email" value={username} onChange={(e) => setUsername(e.target.value)} />
			</div>
			<div className="form-control">
				<label> Password </label>
				<input type="password" placeholder="Enter password" value={password} onChange={(e) => setPassword(e.target.value)}/>
			</div>
			</form>
			<div>
		      	<button onClick={(e) => onClickSave(e)} class="btn btn-primary btn-block" type="submit">Login</button>
				<button onClick={(e) => onClickRegister(e)} class="btn btn-primary btn-block" type="submit">Register</button>
			</div>
      </div>
    )
};



export default LoginCustomer
