import axios from 'axios';

// axios 객체 생성
export default axios.create({
<<<<<<< HEAD
     baseURL: 'http://52.79.249.53:8080',
=======
    baseURL:"http://52.79.249.53:8080", 
>>>>>>> 7b2eb19da29ccf08797d7fba587d2f2c986c69d9
    headers: {
      'Content-type': 'application/json',
    },
  });