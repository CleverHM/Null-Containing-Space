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

const storage = window.sessionStorage;

export default {
  props: ["keyword"],
  name: "IndexFeed",

  components: { 
    SNSItem,
    Navbar,
    subNav,
  },
  data() {
    return {
      clicktags: [],
      articles: null,
    }
  },

  created() {
    this.bringList();
  },

  methods: {
    // 피드 가져오기 (해시태그 x)
    bringList() {
      http
      .post('/post/getPost', 
        storage.getItem("User")
      )
      .then((res) => {
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

      http
      .post('/post/getHashtagPost', 
        formData
      )
      .then((res) => {
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