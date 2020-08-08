import axios from 'axios';

// axios 객체 생성
export default axios.create({
    // 배포용
    baseURL: 'http://52.79.249.53:8080',
    // 로컬용
    //baseURL: 'http://localhost:8080',
    headers: {
      'Content-type': 'application/json',
    },
  });