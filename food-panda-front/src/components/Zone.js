import {useEffect } from 'react'
const Zone = (zone) => {
    useEffect(() => {
        document.getElementById(zone.zone.name).innerHTML = zone.zone.name
        console.log(zone.zone.name)
    }, [])
    
    return (
     <div>
         <input name = "zonez" type = "checkbox" value={zone.zone.name}/>
         <label id={zone.zone.name}/>
     </div>
    )
    
}

export default Zone