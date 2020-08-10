import axios from 'axios';

// axios 객체 생성
export default axios.create({
<<<<<<< HEAD
     baseURL: 'http://localhost:8080',
=======
    baseURL:"http://localhost:8080", 
>>>>>>> 81e8a4ceee19f50975a898f1cc0a761e2dd05b6a
    headers: {
      'Content-type': 'application/json',
    },
  });