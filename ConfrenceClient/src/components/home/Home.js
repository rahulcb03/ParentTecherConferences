import React from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../../api/axiosConfig';
import './Home.css';
import {useState} from 'react';




const Home = ({setStudent}) => {

    const [inputValue, setInputValue] = useState('');
    const [studentFound, setStudentFound] = useState(true);
    const navigate = useNavigate(); 

    const handleInputChange = (event) =>{
        setInputValue(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const response = await api.get(`/api/v1/student/name/${inputValue}`); 
            const studentData = response.data;
            if(studentData){
                 
                setStudent(studentData);
                navigate(`/confrence`);
                
            } else{
                //FIX ME
                setStudentFound(false);
            }
        } catch (error) {
            console.error('Error fetching user data:', error);
        }
        
       
        setInputValue(''); 
    };
    
    return (
        <div className='name-entry'>
            <h1 className='app-title'>Welcome to Kidzland Conference Scheduler</h1>
            
            <p className='intro'>
                Easily schedule Parent-Teacher conferences for Kidzland Childcare and Learning Center's school year.
            </p>
    
            <div className='instructions'>
                <p className='directions'>To get started, please enter the student's first and last name.</p>
            </div>

            {!studentFound && <p className='error'>Student Not Found. Please re-enter name.</p>}

            <form onSubmit={handleSubmit} className='name-form'>
                <input
                    type="text"
                    value={inputValue}
                    onChange={handleInputChange}
                    placeholder="Enter student's full name (e.g., John Smith)"
                    className='name-input'
                />
                <button type="submit" className='submit-button'>Schedule Conference</button>
            </form>


            
        </div>
    )
    
}
export default Home;