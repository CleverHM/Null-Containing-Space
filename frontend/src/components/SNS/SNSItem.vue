<template>
  <div id="SNSItem">
    <!-- user 부분 -->
    <div class="user-part d-flex flex-row align-items-center">
      <div class="user-img mr-2">
        <img v-if="!userImg" src="@/assets/images/default_image.png" alt="user_default_image" @click="goUserProfile">
        <img :src="'data:image/png;base64, ' + article.userFile" alt="user-image" @click="goUserProfile">
      </div>
      <div class="flex-column">
        <div class="user-name" style="color: #464545;" @click="goUserProfile">
            {{ article.userName }}
        </div>
        <div class="date-diff">{{ diffTime }}</div>
      </div>
    </div>

    <!-- SNS 이미지, 제목 부분 -->
    <div class="SNS-img">
      <router-link :to="{ name: 'FeedDetail', params: { postId: article.pid }}">
        <img :src="'data:image/png;base64, ' + article.file" alt="image" class="img-part">
      </router-link>
    </div>

    <!-- 제목 -->
    <div class="SNS-content">
      <router-link :to="{ name: 'FeedDetail', params: { postId: article.pid }}"><span class="title-part">{{ article.title }}</span></router-link>
    </div>

    <!-- 해시태그 -->
    <div class="hash-tags d-flex flex-wrap">
      <div v-for="hashtag in article.tags" :key="hashtag.id" @click="tagOn">
        {{ hashtag }}</div>
    </div>
    
    <!-- SNS 좋아요, 댓글수 부분  -->
    <div class="icon-part d-flex justify-content-around">
      <div>
        <b-icon icon="heart-fill" font-scale="1.2" :color="likeChange" @click="likeButton"></b-icon>
        <span>{{ article.likeCount }}</span>
      </div>
      <div>
        <b-icon icon="chat-square-fill" font-scale="1.2" class="style-icon"></b-icon>
        <span>{{ article.replyCount }}</span>
      </div>
    </div>

  </div>
</template>

<script>
import http from "../../util/http-common.js";
import axios from 'axios';

const storage = window.sessionStorage;
var now = new Date(); // 현재 시간 받아오기

export default {
  name: "SNSItemn",
  props: {
    article: Object,
  },

  computed: {
    // 좋아요 바뀌는 것 감지
    likeChange() {
      this.likeCheck();
      return this.likeColor
    },

  },

  created() {
    this.likeCheck();
    console.log(this.article)

    // 받아온 date 값이 string type 이므로 date type으로 변환 후 체크하는 methods 호출
    var postDate = new Date(this.article.date)
    this.diffTime = this.dateCheck(postDate);

    if (this.article.userFile == null) {
      this.userImg = false
    } else {
      this.userImg = true
    }
  },
  
  data() {
    return {
      likeColor: '',
      diffTime: '',
      userImg: false,
    }
  },

  methods: {
    // tag 클릭하면 화면 상단에 filtering 걸린 태그를 출력하기 위해서 상단 컴포넌트로 올려줌
    tagOn(event) {
      this.$emit('tag-add', event.target.innerText)
    },

    // 좋아요 체크
    likeCheck() {
      if (this.article.likeFlag) {
        this.likeColor = '#FF0000';
      } else {
        this.likeColor = '#C4BCB8';
      }
    },

    // 좋아요 누름
    likeButton(event) {
      
      let formData = new FormData();
      formData.append("email", storage.getItem("User"));
      formData.append("postid", this.article.pid);

      http
      .post('/like/post', formData)
      .then((res) => {
        this.article.likeCount = res.data.count
        this.article.likeFlag = res.data.flag
      })
      .catch((err) => {
        console.log(err)
      })
    },

    
    // 날짜 체크
    dateCheck(date) {
      var diff = now - date
      var diff_sec = Math.floor(diff / 1000)
      var diff_min = Math.floor(diff_sec / 60)
      var diff_hour = Math.floor(diff_min / 60)
      var diff_day = Math.floor(diff_hour / 24)
      var diff_month = Math.floor(diff_day / 30)
      var diff_year = Math.floor(diff_month / 12)

      if (diff_year > 0) {
        var calyear = diff_year + '년 전'
        return calyear
      } else if (diff_month > 0) {
        var calmonth = diff_month + '달 전'
        return calmonth
      } else if (diff_day > 0) {
        var calday = diff_day + '일 전'
        return calday
      } else if (diff_hour > 0) {
        var calhour = diff_hour + '시간 전'
        return calhour
      } else if (diff_min > 0) {
        var calmin = diff_min + '분 전'
        return calmin
      } else if(diff_sec > 0) {
        var caltime = diff_sec + '초 전'
        return caltime
      } else {
        return '0초 전'
      }
    },

    // 유저 사진, 닉네임 눌렀을 때 그 유저의 프로필 페이지로 보낸다.
    goUserProfile() {
      this.$router.push({ name: 'profile', params: { nickname: this.article.userName }});
    },

  },

}
</script>

<style scoped>
#SNSItem {
  background-color: white;
  border-radius: 5px;
  padding: 10px;
  margin-top: 5px;
  margin-bottom: 10px;
  width: 100%;
}

.user-img {
  display: block;
  background-color: #EDECEA;
  border-radius: 50%;
  width: 50px;
  height: 50px;
  overflow: hidden;
}

.user-img > img {
  width: 100%;
}

.user-name {
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  color: #464545;
  font-weight: bold;
  font-size: 16px;
}

.date-diff {
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  color: #C4BCB8;
  font-size: 14px;
}

.SNS-img > img {
  width: 100%;
  margin: 5px 0px 10px 0px;
}

.icon-part {
  margin: 5px 5px 0px 5px;
}

.icon-part > div > span {
  color: #C4BCB8;
  margin: 0px 0px 0px 7px;
}

.hash-tags {
  width: 100%;
  margin-bottom: 5px;
  cursor: pointer;
}

.hash-tags > div {
  background-color: #c6dfd6;
  border-radius: 20px;
  padding: 7px;
  font-size: 13px;
  margin-right: 10px;
  margin-bottom: 7px;
}

.style-icon {
  color: #C4BCB8;
}

.SNS-content {
  margin: 0px 0px 10px 5px;
  font-weight: bold;
  font-size: 16px;
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  width: 98%;
  overflow:hidden;
  text-overflow: ellipsis;
  white-space:nowrap;
}

.title-part {
  color: #464545; 
}

.img-part {
  width: 95%;
  margin: 10px;
  border-radius: 2px;
}

</style>