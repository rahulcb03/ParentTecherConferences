import './App.css';
import Layout from './components/Layout';
import Header from './components/header/Header';
import Home from './components/home/Home';
import Form from './components/form/Form'
import {useState} from 'react';


import { Routes, Route} from 'react-router-dom';


function App() {
  const [student, setStudent] = useState(null);

  return (
    <div className="App">
      <Header/>
      <Routes>
        <Route path = "/" element = {<Layout/>}>
          <Route path="/" element={<Home setStudent={setStudent}/>}></Route>
          <Route path="/confrence" element={<Form student = {student}/>}></Route>
          
        </Route>
      </Routes>
    </div>
  );
}

export default App;
