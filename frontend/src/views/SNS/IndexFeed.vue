<template>
  <div class="feed newsfeed">
    <Navbar></Navbar>
    <subNav></subNav>
    <div class="wrapB">
      <div class="d-flex justify-content-end align-items-center">
        <!-- <button v-for="tag in clicktags" :key="tag" class="btn-sort" style="background-color: #ACCCC4;">{{ tag.value }}</button> -->
        
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

const clicktags = new Set();

export default {
  props: ["keyword"],
  name: "IndexFeed",

  components: { 
    SNSItem,
    Navbar,
    subNav,
  },

  methods: {
    articleSubmit() {
      this.$router.push("/feed/create");
    },
    tagAdd(inputValue) {
      // console.log(inputValue);
      clicktags.add(inputValue)
      console.log([...clicktags])
    }
  },


};
</script>


<style scoped>
.btn-sort{
    width: 70px;
    margin: 10px 5px 10px 5px;
    padding: 5px;
    border-radius: 4px;
    font-size: 14px;
    color: #f7f7f7;
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