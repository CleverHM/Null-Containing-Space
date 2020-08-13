<template>
  <div id="IndexFeed">
    <Navbar></Navbar>
    <subNav></subNav>
    <div>
      <div v-if="status" class="recommendArea">
        <div class="text-tags my-2">
          팔로우가 없습니다. 아래 유저는 어떤가요?
        </div> 
        <div class="d-flex justify-content-start">
          <RecommendUser v-for="userData in userList" :key="userData.nickname" :userData="userData" class="mx-2"/>
        </div>
      </div>
      <div class="d-flex justify-content-end align-items-center">
        <button v-for="tag in clicktags" :key="tag" class="btn-sort" style="background-color: #ACCCC4;" @click="tagRemove">{{ tag }}</button>
      </div>

      <!-- 출력될 피드 게시물 - SNSItem으로 article 객체 데이터 넘겨줌 -->
      <SNSItem v-for="article in articles" :article="article" :key="article.pid" @tag-add="tagAdd"/>
    </div>
    <!-- 작성 -->
    <button class="createArticle" @click="articleSubmit"><b-icon-pencil-square></b-icon-pencil-square></button>

    <!-- 무한스크롤 -->
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
import { mapState } from "vuex";
import "../../components/css/feed/feed-item.scss";
import "../../components/css/feed/newsfeed.scss";
import SNSItem from "../../components/SNS/SNSItem.vue";
import Navbar from '../../components/common/Navigation.vue';
import subNav from '../../components/common/subnav.vue';
import RecommendUser from '../../components/SNS/RecommendUser';
import http from "../../util/http-common.js";
import axios from 'axios';
import InfiniteLoading from 'vue-infinite-loading';

const storage = window.sessionStorage;

export default {
  name: "IndexFeed",
  props: ["keyword"],

  components: { 
    SNSItem,
    Navbar,
    subNav,
    InfiniteLoading,
    RecommendUser,
  },
  data() {
    return {
      clicktags: [],
      articles: [],
      hashExist: false,
      limit: 1,   // 무한스크롤 위한 page 번호
      postExist: true,
      status: false,
      userList: [],
    }
  },

  created() {
    // this.bringList();
  },

  methods: {
    // 피드 가져오기 (해시태그 x)
    bringList($state) {
      const EACH_LEN = 3

      let formData = new FormData;
      formData.append("email", storage.getItem("User"));
      formData.append("pagenum", this.limit);

      http
      .post('/post/getPost', formData)
      .then((res) => {
        setTimeout(() => {
          if(res.data.feeddata.length) {
            this.articles = this.articles.concat(res.data.feeddata)
            $state.loaded()
            this.limit = this.limit + 1
            // 끝 지정(No more data) - 데이터가 EACH_LEN개 미만이면 
            if(res.data.feeddata.length / EACH_LEN < 1) {
              $state.complete()
            }
          } else {
            // 끝 지정(No more data)
            $state.complete()
          }
        }, 400)
        if (res.data.status == true) {
          this.status = true
          this.userReco();
        }
      })
      .catch(err => {
        console.error(err);
      })
    },

    // 해시태그 있을 때 피드 가져오기
    bringListHash($state) {
      const EACH_LEN = 3

      let formData = new FormData();
      formData.append("email", storage.getItem("User"));
      formData.append("hashtag", this.clicktags);
      formData.append("pagenum", this.limit);
      // console.log('해시', this.clicktags)

      http
      .post('/post/getHashtagPost', 
        formData
      )
      .then((res) => {
        setTimeout(() => {
          if(res.data.hashfeeddata.length) {
            this.articles = this.articles.concat(res.data.hashfeeddata)
            $state.loaded()
            this.limit = this.limit + 1
            // console.log("after", this.articles, this.limit)
            // 끝 지정(No more data) - 데이터가 EACH_LEN개 미만이면 
            if(res.data.hashfeeddata.length / EACH_LEN < 1) {
              $state.complete()
            }
          } else {
            // 끝 지정(No more data)
            $state.complete()
          }
        }, 400)
      })
      .catch((err) => {
        console.log(err)
      })
    },

    // 무한 스크롤
    infiniteHandler($state) {
      if (this.hashExist == false) {
        this.bringList($state);
      } else {
        this.bringListHash($state);
      }
      
    },

    
    // 글 작성하기
    articleSubmit() {
      this.$router.push({ name: 'FeedCreate' });
    },

    // 태그 클릭하면 +. 중복은 제거
    tagAdd(inputValue) {
      this.hashExist = true

      if ( this.clicktags.indexOf(inputValue) < 0 ) {
        this.clicktags.push(inputValue)
        this.articles = []
        this.limit = 1

        // $state 초기화
        this.$refs.InfiniteLoading.stateChanger.reset(); 
      }
    },

    // 태그 클릭하면 -
    tagRemove(event) {
      this.clicktags.splice(this.clicktags.indexOf(event.target.innerText),1)
      this.articles = []
      this.hashExist = true


      this.limit = 1
      
      if (this.clicktags.length === 0) {
        this.hashExist = false
      } 
      
      // $state 초기화
      this.$refs.InfiniteLoading.stateChanger.reset(); 
    },

    // 유저 추천 받기
    userReco() {
      let formData = new FormData;
      formData.append('nickname', storage.getItem("NickName"))
      
      http
      .post('/account/recommendUser', formData)
      .then((res) => {
        this.userList = res.data
      })
      .catch((err) => {
        console.log(err)
      })


    }

  },


};
</script>


<style scoped>
#IndexFeed {
  margin: 50px 0px 30px 0px;
}
.btn-sort{
    margin: 10px 5px 10px 5px;
    height: 25px;
    padding: 5px;
    border-radius: 20px;
    font-size: 12px;
    color: #464545;
}
.createArticle {
  position: fixed;
  bottom: 65px;
  right: 30px;
  height: 50px;
  width: 50px;
  border-radius: 100%;
  color: white;
  background-color: #464545;
  font-size: 14px;
}

.recommendArea {
  margin: 20px 0px 20px 0px;
  padding: 5px 0px 15px 0px;
  border-bottom: 1px solid #464545;
}

.text-tags {
  margin-left: 10px;
  font-size: 13px;
  color: #464545;
}

</style>