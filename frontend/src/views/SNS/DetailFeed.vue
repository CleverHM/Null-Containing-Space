<template>
  <div id="detailFeed">
    <div class="wrapB">
      <Navbar></Navbar>
      <subNav></subNav>
      <div class="feedpage">
        <!-- title 부분 -->
        <div class="page-title">
          {{ article.title }}
        </div>

        <!-- user 부분 -->
        <div class="user-part d-flex flex-row align-items-center">
          <div class="user-img"></div>
          <div class="user-name">{{ article.username }}</div>
          <div class="user-created-at">{{ article.created_at }}</div>
          <div class="user-count">
            <span>조회수</span>
            <span class="ml-2">0</span>
          </div>
        </div>

        <!-- SNS 이미지, 제목 부분 -->
        <div class="SNS-img">
          <b-img :src="imgUrl" fluid alt="Fluid image" style="border-radius:2px;"></b-img>
        </div>

        <!-- content 부분 -->
        <div class="page-content">
          {{ article.content }}
        </div>

        <!-- 좋아요 부분 -->
        <div class="icon-part d-flex justify-content-center">
          <div>
            <b-icon icon="heart-fill" font-scale="1.2" :color="like_color" @click="likeButton"></b-icon>
            <span class="icon-heart-data">0</span>
          </div>
        </div>
        
        <hr>
        <!-- 해시태그 -->
        <div class="hash-tags d-flex flex-wrap">
          <div v-for="hashtag in article.hashtags" :key="hashtag.id">
            {{ hashtag.name }}</div>
        </div>

        <!-- 댓글 part -->
        <div class="comment-part">
          <Comment></Comment>
          <Comment></Comment>
        </div>

        <!-- 댓글 작성창 -->
        <div class="fixed-bottom comment-add d-flex align-items-center">
          <input v-model="commentData" type="text"
            placeholder="댓글을 작성해주세요."
            class="flex-fill"
            style="border:none;"/>
          <button class="px-3">
            작성
          </button>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue'
import subNav from '../../components/common/subnav.vue'
import Comment from '../../components/SNS/SNSCommentItem.vue'

export default {
  name: "detailFeed",
  components: {
    Navbar,
    subNav,
    Comment,
  },
  
  data() {
    return {
      imgUrl: 'https://cdn.pixabay.com/photo/2020/07/10/20/45/sparrow-5392119__340.jpg',
      like_color: '',
      liked: false,
      article: {
        username: '알골마스터',
        created_at: '2020-07-15',
        title: '.....ABCDEFGHIJK',
        content: 'Korean Lorem Ipsum in Hangul script is a mix of all Korean chars according to common frequency. Suggestions for improvement are welcome. Korean Lorem Ipsum in Hangul script is a mix of all Korean chars according to common frequency. Suggestions for improvement are welcome. Korean Lorem Ipsum in Hangul script is a mix of all Korean chars according to common frequency. Suggestions for improvement are welcome. Korean Lorem Ipsum in Hangul script is a mix of all Korean chars according to common frequency. Suggestions for improvement are welcome.',
        hashtags: [
          { name: 'Python',
            id: '1' },
          { name: 'Algorithm',
            id: '2' },
          { name: 'JavaScript',
            id: '3' },
          { name: 'Django',
            id: '4' },
          { name: 'Vue.js',
            id: '5' },
        ],
      },
      commentData: '',
    }
  },

  created() {
    if (this.liked) {
      this.like_color = '#FF3300';
    } else {
      this.like_color = '#C4BCB8';
    }
  },

  methods: {
    commentOn() {
    },
    
    // 좋아요 누름
    likeButton(event) {
      // console.log('liked')
      if (this.liked) {
        this.liked = false;
        this.like_color = '#C4BCB8';
      } else {
        this.liked = true;
        this.like_color = '#FF3300';
      }
    },
  }
}


</script>

<style scope>
.feedpage {
  margin: 85px 5px 55px 5px;
  padding: 10px;
  background-color: white;
  border-radius: 10px;
}

.user-img {
  background-color: #C4BCB8;
  border-radius: 50%;
  width: 30px;
  height: 30px;
}

.page-title {
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  color: #464545;
  font-weight: bold;
  font-size: 20px;
}

.user-part > div {
  margin-top: 5px;
  margin-right: 13px;
}

.user-name {
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  color: #464545;
  font-weight: bold;
  font-size: 14px;
}

.user-created-at {
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  color: #E2DFD8;
  font-size: 14px;
}

.user-count span {
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  color: #E2DFD8;
  font-size: 14px;
}

/* 페이지 밖으로 나가지 않도록 하기 */
.page-content {
  margin-top: 5px;
  width: 98%;
  white-space: normal;
  word-break: break-all;
}

.SNS-img > img {
  width: 100%;
  margin: 5px 0px 5px 0px;
}

.icon-part {
  margin-top: 10px;
}

.icon-heart-data {
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  color: #E2DFD8;
  margin-left: 10px;
}

.comment-add {
  width: 100%;
  background-color: #f7f7f7;
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
}

.hash-tags {
  width: 100%;
  margin-bottom: 5px;
}

.hash-tags > div {
  background-color: #c6dfd6;
  border-radius: 20px;
  padding: 7px;
  font-size: 13px;
  margin-right: 10px;
  margin-bottom: 7px;
}


</style>