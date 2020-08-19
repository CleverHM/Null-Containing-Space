# Infinite-loading

> paganation 대신 스크롤을 내리면 무한 스크롤이 될 수 있도록 해주는 라이브러리



## 사용 방법

1. 설치하기

   ```bash
   $ npm i vue-infinite-loading
   ```

2. 사용하기

   ```vue
   <template>
   	<div>
           
       <infinite-loading 
         @infinite="infiniteHandler" 
         ref="InfiniteLoading"
         spinner="waveDots">
         <div slot="no-results" style="font-size: 14px; padding: 10px 0px; margin: 180px 0px 180px 0px;">아직 게시물이 없습니다</div>
         <div slot="no-more" style="font-size: 14px; padding: 10px 0px;">더 이상 게시물이 없습니다</div>
       </infinite-loading>
           
       </div>
   </template>
   
   <script>
   import InfiniteLoading from 'vue-infinite-loading';
    
   export default {
   	methods: {
           bringList($state) {
               
         		const EACH_LEN = 5	// 한 번에 받아오는 DATA 개수
   
         		http
         		.get(`/url/${this.page_number}`)
         		.then((res) => {
           		setTimeout(() => {
             			if(res.data.length) {
               			this.articles = [...this.articles, ...res.data]
                           // 아래는 state를 다시 로드하기 위해 필요함
               			$state.loaded()
               			this.page_number++
               			// 끝 지정(No more data) - 데이터가 EACH_LEN개 미만이면 
               			if(res.data.length / EACH_LEN < 1) {
                 				$state.complete()
               			}
             			} else {
               			// 끝 지정(No more data)
               			$state.complete()
             			}
           		}, 400)
           		if (res.data.status == true) {
             			this.status = true
           		}
         		})
         		.catch(err => {
           		console.error(err);
         		})
       	},
       }
   }
   </script>
   ```



- `$state`의 초기화가 필요할 때

  ```javascript
  this.$refs.InfiniteLoading.stateChanger.reset(); 
  ```

  - `html`에서 `ref="InfiniteLoading"`를 걸어준 후 위와 같이 reset 시켜주면 된다.



## Reference

- [블로그 게시글](http://yoonbumtae.com/?p=2823)
- [vue-infinite-loading](https://peachscript.github.io/vue-infinite-loading/guide/)