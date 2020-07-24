<template>
    <div class="commentFeed">
        <Navbar></Navbar>
        <subNav></subNav>
        <div class="wrapB">
      
        <!-- 게시글 -->
        <div class="article-part">
            aaaa
        </div>
        
        <!-- 댓글 part -->
        <div class="comment-part">
            <Comment></Comment>
            <hr>
            <Comment></Comment>
        </div>

        <div class="fixed-bottom comment-add d-flex align-items-center">
            <input type="text"
                placeholder="댓글을 작성해주세요."
                class="flex-fill"
                v-model="commentData"
                style="border:none;"/>
            <button class="px-3">
                작성
            </button>
        </div>
    
      
        </div>
    </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue';
import subNav from '../../components/common/subnav.vue';
import Comment from '../../components/SNS/SNSCommentItem.vue'

export default {
  name: "commentFeed",
  components: { 
    Navbar,
    subNav,
    Comment,
  },

  date() {
    return {
        commentData: '',
    }
  },

  methods: {
    commentOn() {
    },
  },

}
</script>

<style scoped>
  .article-part {
    margin-top: 95px;
    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
    font-size: 16px;
    font-weight: bold;
  }
</style>