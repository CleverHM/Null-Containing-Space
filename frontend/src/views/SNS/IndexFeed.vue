<template>
  <div class="feed newsfeed">
    <Navbar></Navbar>
    <subNav></subNav>
    <div class="wrapB">
      <div class="d-flex justify-content-end align-items-center">
        <button v-for="tag in clicktags" :key="tag" class="btn-sort" style="background-color: #ACCCC4;" @click="tagRemove">{{ tag }}</button>
      </div>

      <!-- 출력될 피드 게시물 - SNSItem으로 article 객체 데이터 넘겨줌 -->
      <SNSItem v-for="article in articles" :article="article" :key="article.pid" @tag-add="tagAdd"/>
    </div>
    <!-- 작성 -->
    <button class="createArticle" @click="articleSubmit"><b-icon-pencil-square></b-icon-pencil-square></button>

    <!-- 무한스크롤 -->
    <infinite-loading @infinite="infiniteHandler" spinner="waveDots">
      <div slot="no-more" style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px;">목록의 끝입니다 :)</div>
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
import http from "../../util/http-common.js";
import axios from 'axios';
import InfiniteLoading from 'vue-infinite-loading';

const storage = window.sessionStorage;

export default {
  props: ["keyword"],
  name: "IndexFeed",

  components: { 
    SNSItem,
    Navbar,
    subNav,
    InfiniteLoading,
  },
  data() {
    return {
      clicktags: [],
      articles: null,
      limit: 1,   // 무한스크롤 위한 page 번호
    }
  },
  

  created() {
    this.bringList();

    async function getTopicFromApi() {
          try {
              const init = await fetch(`/api/idol/uwasa/pages/0`, {method: "GET"})
              const data = await init.json()
              return data
          } catch(exc) {
              console.error(exc)
          }
      }
      getTopicFromApi().then(data => {
          console.log("fromAPI", data)
          this.topicData = data
      })
  },

  methods: {
    // 피드 가져오기 (해시태그 x)
    bringList() {
      http
      .post('/post/getPost', 
        storage.getItem("User")
      )
      .then((res) => {
        // console.log(res.data)
        this.articles = res.data
      })
      .catch((err) => {
        console.log(err)
      })
    },

    // 해시태그 있을 때 피드 가져오기
    bringListHash() {
      let formData = new FormData();
      formData.append("email", storage.getItem("User"));
      formData.append("hashtag", this.clicktags);

      console.log('해시', this.clicktags)

      http
      .post('/post/getHashtagPost', 
        formData
      )
      .then((res) => {
        // console.log(res.data)
        this.articles = res.data
      })
      .catch((err) => {
        console.log(err)
      })
    },

    // 글 작성하기
    articleSubmit() {
      this.$router.push({ name: 'FeedCreate' });
    },

    // 태그 클릭하면 +. 중복은 제거
    tagAdd(inputValue) {
      if ( this.clicktags.indexOf(inputValue) < 0 ) {
        this.clicktags.push(inputValue)
      }
      this.bringListHash();
    },

    // 태그 클릭하면 -
    tagRemove(event) {
      this.clicktags.splice(this.clicktags.indexOf(event.target.innerText),1)
      if (this.clicktags.length === 0) {
        this.bringList();
      } else {
        this.bringListHash();
      }
    },

  },


};
</script>


<style scoped>
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

.wrapB {
  margin-bottom: 50px;
}

</style>