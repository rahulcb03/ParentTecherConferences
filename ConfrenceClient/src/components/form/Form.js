import React, { useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import api from '../../api/axiosConfig';
import AppointmentTable from './AppointmentTable';
import './Form.css';
import {useState} from 'react';




const Form = ({student}) => {

    const [selectedDateTime, setSelectedDateTime] = useState("");
    const [slots, setSlots] = useState(null);
    const [error, setError] = useState("");

    const navigate = useNavigate(); 

    const deleteAppointment = async(e) =>{
        e.preventDefault(); 
        try{
                
            const response = await api.delete(`api/v1/appointments/${student.id}`)
            if(response.data === "removed"){
                alert("Your confrence time has been removed")
                navigate(`/`);
            }

        }
        catch(err){
            console.log(err);
        }
    }

    const addAppointment = async(e)=>{
        e.preventDefault();

        const dt = selectedDateTime.split('T');
        let cnt=0; 
        let check = false; 

        while(selectedDateTime && slots[dt[0]] && cnt<slots[dt[0]].length){
            if(slots[dt[0]][cnt].time === dt[1]&& slots[dt[0]][cnt].available){
                check = true;
            }
            cnt++;
        }
        
        if(check){
            

            try{
                
                const response = await api.post("api/v1/appointments", {date:selectedDateTime, teacher:student.teacher.id, student:student.id })

                
                if(response.status===201){
                    alert("Your Confrence was successfully created at " + response.data.dateTime);
                }
            }
            catch(err){
                console.log(err);
                alert("Error creating your appointment. Please Retry")

            }

            navigate(`/`);
        }
        else{
            setError("The date and time you selected is not available");
        }
    }

    if(student===null){
        return(
            <div className='nullDiv'>
                <p>Please navigate to the Home tab and enter a student name</p>
            </div>
        )
    }

    if(student.dateTime !== ""){
        //do something here so users can remove appointments 
        return(
            <div className='appointmentSetDiv'>
                <p>Your Parent-Teacher Confrence has already been scheduled at {student.dateTime}</p>
                <button className='delete-button' onClick={deleteAppointment}>Reschedule</button>
            </div>
        )
    }

    

    useEffect( () =>{
        const appointments = student.teacher.available; 
        const slots={}; 

        let n=0; 

        while(n<appointments.length){
            let dateTime = appointments[n].split("T"); 

            if(!slots[dateTime[0]]){
                slots[dateTime[0]] = []; 
            }
            slots[dateTime[0]].push({time: dateTime[1], available: true} );
            n++; 
        }

        const booked = student.teacher.appointments; 

        n=0; 
        let counter=0; 

        while(n<booked.length){
            let s = booked[n].dateTime.split("T");

            let date = slots[s[0]];
            counter =0; 
            while(counter<date.length){
                if(date[counter].time=== s[1]){
                    date[counter].available = false; 
                }
                counter++; 
            }
            n++;
            
        }

        setSlots(slots); 
    }, [student]);
    
    return (
        <div className='Form '>
            <div className='AppointmentTable'>
                {slots ? (<AppointmentTable appointments={slots} />) : (<p>Loading...</p>)}
            </div>

            <div className='EntryForm'>
                <form onSubmit={addAppointment}>
                    <label>
                        Select Date and Time:
                        <input
                            type="datetime-local"
                            value={selectedDateTime}
                            onChange={(e) => setSelectedDateTime(e.target.value)}
                        />
                    </label>
                    <button type="submit">Schedule Appointment</button>
                </form>

                {error && <p className='error'>{error}</p>}
    
            </div>
            

            
        </div>
    )
}
export default Form;