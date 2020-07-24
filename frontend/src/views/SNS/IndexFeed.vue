<template>
  <div class="feed newsfeed">
    <Navbar></Navbar>
    <subNav></subNav>
    <div class="wrapB">
      <div class="d-flex justify-content-end align-items-center">
        <button v-for="tag in clicktags" :key="tag" class="btn-sort" style="background-color: #ACCCC4;" @click="tagRemove">{{ tag }}</button>
      </div>

      <SNSItem @tag-add="tagAdd"/>
      <SNSItem @tag-add="tagAdd"/>
      <SNSItem @tag-add="tagAdd"/>
      <SNSItem @tag-add="tagAdd"/>
    </div>
    
    <!-- 작성 -->
    <div class="article-submit fixed-bottom d-flex justify-content-center align-content-center">
      <button @click="articleSubmit">작성하기</button>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import "../../components/css/feed/feed-item.scss";
import "../../components/css/feed/newsfeed.scss";
import SNSItem from "../../components/SNS/SNSItem.vue";
import Navbar from '../../components/common/Navigation.vue';
import subNav from '../../components/common/subnav.vue';



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
    }
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

.article-submit {
  height: 40px;
}

.article-submit button {
  color: white;
  width: 100%;
  background-color: #464545;
  font-size: 14px;
}

.wrapB {
  margin-bottom: 50px;
}

</style>