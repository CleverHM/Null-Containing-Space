<template>
  <div id="detailFeed">
    <div class="wrapB">
      <Navbar></Navbar>
      <subNav></subNav>

      <!-- 수정삭제 부분 -->
      <div v-if="udOn" class=" ud-part">
        <li><b-icon-pencil class="mr-3"></b-icon-pencil>수정</li>
        <li @click="deletePost"><b-icon-trash class="mr-3"></b-icon-trash>삭제</li>
      </div>

      <div class="feedpage">
        <!-- title 부분 -->
        <div class="d-flex justify-content-between align-items-center">
          <div class="page-title">
            {{ article.title }}
          </div>
          <div>
            <b-icon-three-dots-vertical @click="udButton"></b-icon-three-dots-vertical>
          </div>
        </div>

        <!-- user 부분 -->
        <div class="user-part d-flex flex-row align-items-center">
          <div class="user-img"></div>
          <div class="user-name">{{ article.userNickname }}</div>
          <div class="user-created-at">{{ article.date }}</div>
          <div class="user-count">
            <span>조회수</span>
            <span class="ml-2">{{ article.viewCount }}</span>
          </div>
        </div>

        <!-- SNS 이미지 -->
        <div class="SNS-img">
          <img :src="'data:image/png;base64, ' + article.file" alt="image" class="img-part">
        </div>

        <!-- content 부분 -->
        <div class="page-content">
          {{ article.content }}
        </div>

        <!-- 좋아요 부분 -->
        <div class="icon-part d-flex justify-content-center">
          <div>
            <b-icon icon="heart-fill" font-scale="1.2" :color="likeChange" @click="likeButton"></b-icon>
            <span class="ml-2">{{ article.likeCount }}</span>
          </div>
        </div>
        
        <hr>
        <!-- 해시태그 -->
        <div class="hash-tags d-flex flex-wrap">
          <div v-for="hashtag in article.tags" :key="hashtag.id">
            {{ hashtag }}</div>
        </div>

        <!-- 댓글 part -->
        <div class="comment-part">
          <Comment></Comment>
          <Comment></Comment>
        </div>

        <!-- 댓글 작성창 -->
        <div class="fixed-bottom comment-add d-flex align-items-center">
          <input v-model="comment.content" type="text"
            placeholder="댓글을 작성해주세요."
            class="flex-fill"
            style="border:none;"
            @keyup.enter="commentOn"/>
          <button class="px-3" @click="commentOn">
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
import http from "../../util/http-common.js";
import axios from 'axios';

const storage = window.sessionStorage;

export default {
  name: "detailFeed",
  props: ["postId"],
  components: {
    Navbar,
    subNav,
    Comment,
  },

  computed: {
    // 좋아요 바뀌는 것 감지
    likeChange() {
      this.likeCheck();
      return this.likeColor
    },

  },
  
  data() {
    return {
      imgUrl: 'https://cdn.pixabay.com/photo/2020/07/10/20/45/sparrow-5392119__340.jpg',
      likeColor: '',
      udOn: false,
      article: {
        content: "",
        data: "",
        file: "",
        likeCount: 0,
        likeFlag: 0,
        pid: 0,
        tags: [],
        title: "",
        userEmail:"",
        userNickname: "",
        viewCount: 0,
      },
      comment: {
        content: "",
      },
    }
  },

  created() {
    this.dataReceive();
    this.likeCheck();
  },

  methods: {
    // 좋아요 체크
    likeCheck() {
      console.log(this.article.likeFlag)
      if (this.article.likeFlag) {
        this.likeColor = '#FF0000';
      } else {
        this.likeColor = '#C4BCB8';
      }
    },

    // 댓글 작성 버튼 눌림
    commentOn() {
      console.log(this.comment.content)
      if (this.comment.content === "") {
        this.errorMsg();
      } else {
        this.commentSubmit()
      }
    },

    dataReceive() {
      // console.log(this.postId)
      let formData = new FormData();
      formData.append("email", storage.getItem("User"));
      formData.append("pid", this.postId);


      http
      .post('/post/postDetail', formData)
      .then((res) => {
        // console.log(res.data)
        // 받아온 데이터를 집어 넣기
        this.article = res.data
        console.log('check')
        console.log(this.article)
      })
      .catch((err) => {
        console.log(err)
      })
    },
    
    // 에러메세지
    errorMsg() {
      alert("댓글 내용을 입력해주세요.")
    },

    // 댓글 작성
    commentSubmit() {
      console.log("comment submit!")
      
      http
      .post('url', {
        content: this.comment.content,
        email: storage.getItem("User"),
      })
      .then((res) => {
        console.log('SUCCESS!!');
        // this.$router.push(`/feed/${article.pid}/detail`);
      })
      .catch((err) => {
        console.log(err);
        console.log('ERROR!!');
      })
    },

    // 좋아요 누름
    likeButton(event) {
      // console.log('liked')
      // console.log(storage.getItem("User"))
      
      let formData = new FormData();
      formData.append("email", storage.getItem("User"));
      formData.append("postid", this.article.pid);

      http
      .post('/like/post', formData)
      .then((res) => {
        // console.log(res.data)
        this.article.likeCount = res.data.count
        this.article.likeFlag = res.data.flag
      })
      .catch((err) => {
        console.log(err)
      })
    },
    // 수정, 삭제 버튼
    udButton(event) {
      var requestUser = storage.getItem("User")
      if (requestUser === this.article.userEmail) {
        this.udOn = !this.udOn;
      }
      // console.log(this.udOn)
    },

    // 글 삭제
    deletePost() {
      
      http
      .post("/post/postDelete",
        this.article.pid
      )
      .then((res) => {
        console.log('delete')
        console.log(res.data)
        this.$router.push({ name: 'FeedMain' });
      })
      .catch((err) => {
        console.log(err)
      })

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

.ud-part {
  position: float;
  float: right;
  background-color: #f7f7f7;
}
.ud-part > li {
  margin: 20px;
}

.img-part {
  width: 95%;
  margin: 10px;
  border-radius: 2px;
}
</style>