# REST API - axios

> http 통신 javaScript 라이브러리
>
> REST API 연동을 위해 사용하며 GET, POST, PUT, DELETE 등의 메서드를 사용한다.

### :black_medium_small_square: 사용 방법

1. 설치하기

   ```bash
   $ npm i axios
   ```

2. import 후 사용

   ```javascript
   import axios from 'axios';
   ```

### :black_medium_small_square: Project 사용

> Backend 서버와의 통신을 위해 자주 사용되기 때문에 `http-common.js` 파일 안에 정의한 후 불러와서 사용

```javascript
import axios from 'axios';

// axios 객체 생성
export default axios.create({
    baseURL:"포트주소", 
    headers: {
      'Content-type': 'application/json',
    },
  });
```



## GET

> 데이터 조회에 사용되는 메서드
>
> 가장 자주 사용 (ex. Team Data, NewsFeed post 등)

```javascript
import http from "../../util/http-common.js"; // 이후 해당 부분 생략

export default {
methods: {
    bringList() {
      http
      .get(`/post/${storage.getItem("NickName")}`)
      .then((res) => {
            this.articles = [...this.articles, ...res.data.feeddata]
      })
      .catch(err => {
        console.error(err);
      })
    },
}
```

- 대체로 URL에 필요한 데이터를 넣어보내지만 따로 params를 쓰기도 한다.

- Vue.js에서 List 객체 처리가 어렵기 때문에 params로 List 객체를 보낼 때는 아래와 같은 방식을 사용한다.

  ```javascript
  http
  .get(`/post/hash/${storage.getItem("NickName")}/${this.limit}`, {
      params: {
          hashtag: this.clicktags + ''
      }
  })
  ```

  -  `+ ''` 을 붙여줌으로서 Backend에서 List 객체를 전달받을 수 있다.



## POST

> 데이터 등록에 사용되는 메서드
>
> Get과 마찬가지로 자주 사용된 메서드로 Create, 좋아요, 팔로우, 알림 요청 등에 사용되었다.

```javascript
export default {
methods: {
    submitOn() {
      http
      .post("/post/create",
        formData,
        {
          headers: {
              'Content-Type': 'multipart/form-data'
          }
        }
      )
      .then((res) => {
        
      })
      .catch((err) => {
        console.log(err)
      })
          
    },
}
```



## PUT

> 데이터 수정에 사용되는 메서드

```javascript
export default {
methods: {
    submitModify() {
    	http
        .put(`/post/isFalse/${postId}`, formData)
        .then((res) => {
        	this.$router.replace({ name: 'FeedDetail', params: { postId: postId }});
        })
        .catch((err) => {
        	console.log(err)
        })  
    },
}
```



## DELETE

> 데이터 제거에 사용되는 메서드

```javascript
export default {
methods: {
    deletePost() {
    	http
      	.delete(`/post/${this.article.pid}`)
      	.then((res) => {
        	this.$router.replace({ name: 'FeedMain' });
      	})
      	.catch((err) => {
        	console.log(err)
      	})
    },
}
```



## parameter

- 기본 형태

  ```javascript
  axios('url', {
      params: {
          param_name: data,
      }
  })
  ```

- FormData

  ```javascript
  export default {
  methods: {
      submitOn() {
          
        let formData = new FormData();
        formData.append("files", this.article.file);
        formData.append("email", storage.getItem("User"));
        formData.append("title", this.article.title);
        formData.append("content", this.article.content);
        formData.append("hashtags", this.article.hashtags);
          
        http
        .post("/post/create",
          formData,
          {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
          }
        )
        .then((res) => {
          
        })
        .catch((err) => {
          console.log(err)
        })
            
      },
  }
  ```

  - `FormData` 안에 params로 보낼 데이터를 집어 넣어서 보낸다.
    - Backend에서 받을 param 명과 동일하게 맞춰준다.