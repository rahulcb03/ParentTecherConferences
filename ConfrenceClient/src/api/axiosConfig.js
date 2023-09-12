import axios from 'axios';

export default axios.create({
    baseURL: 'https://2a55-24-228-179-164.ngrok-free.app',
    headers: {"ngrok-skip-browser-warning":"true"}
});