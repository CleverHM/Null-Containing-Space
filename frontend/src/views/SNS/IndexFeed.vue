<template>
  <div class="feed newsfeed">
    <Navbar></Navbar>
    <subNav></subNav>
    <div class="wrapB">
      <div class="d-flex justify-content-end align-items-center">
        <button v-for="tag in clicktags" :key="tag" class="btn-sort" style="background-color: #ACCCC4;" @click="tagRemove">{{ tag }}</button>
      </div>

      <SNSItem v-for="article in articles" :article="article" :key="article.title" @tag-add="tagAdd"/>
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
    console.log('feed창')
    console.log(storage.getItem("User"))
    // var email = storage.getItem("User")
    // let formData = new FormData();
    // formData.append("email", storage.getItem("User"));

    // console.log(formData.get("email"))
    // let email = storage.getItem("User");
    http
    .post('/post/getPost', 
      storage.getItem("User")
    )
    .then((res) => {
      console.log(res.data)
      this.articles = res.data
      console.log('check')
      console.log(this.articles)
    })
    .catch((err) => {
      console.log(err)
    })
  },

  methods: {
    // 글 작성하기
    articleSubmit() {
      this.$router.push({ name: 'FeedCreate' });
    },

    // 태그 클릭하면 +. 중복은 제거
    tagAdd(inputValue) {
      // console.log(inputValue);
      if ( this.clicktags.indexOf(inputValue) < 0 ) {
        this.clicktags.push(inputValue)
      }
      // console.log([...this.clicktags])
    },

    // 태그 클릭하면 -
    tagRemove(event) {
      // console.log(event.target.innerText)
      this.clicktags.splice(this.clicktags.indexOf(event.target.innerText),1)
      // console.log([...this.clicktags])
    }

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
  bottom: 15px;
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