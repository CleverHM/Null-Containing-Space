import axios from 'axios';

// axios 객체 생성
export default axios.create({
<<<<<<< HEAD
    baseURL: 'http://localhost:8080',
=======
    baseURL:"http://localhost:8080", 
>>>>>>> 93c269e844728ba3ec251b639d5884b350d13421
    headers: {
      'Content-type': 'application/json',
    },
  });