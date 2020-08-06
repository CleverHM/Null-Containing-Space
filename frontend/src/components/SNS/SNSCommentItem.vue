<template>
  <div id="sns-comment">
    <!-- user 부분 -->
    <div class="user-part d-flex justify-content-between align-items-center mb-2">
      <div class="d-flex align-items-center">
        <div class="comment-img">
            <img v-if="!userImg" src="@/assets/images/default_image.png" alt="user_default_image">
            <img :src="'data:image/png;base64, ' + reply.file" alt="user-image">
        </div>
        <div class="comment-name ml-2">{{ reply.who }}</div>
      </div>
      <div class="d-flex flex-row">
        <div class="comment-date">{{ diffTime }}</div>
        <b-icon-x v-if="userCheck" class="ml-2" @click="commentDelete"></b-icon-x>
      </div>
    </div>
    <!-- 내용 -->
    <div class="comment-content">
      {{ reply.content }}
    </div>
  </div>
</template>

<script>
import http from "../../util/http-common.js";
import axios from 'axios';

const storage = window.sessionStorage;
var now = new Date(); // 현재 시간 받아오기


export default {
  name: "SNSComment",
  props: {
    reply: Object,
  },

  data() {
    return {
      diffTime: "",
      userCheck: false,
      userImg: false,
    }
  },

  created() {
    // 받아온 시간(string) - date (형식 변환)
    var postDate = new Date(this.reply.createData)
    this.diffTime = this.dateCheck(postDate);
    if (this.reply.email === storage.getItem("User")) {
      this.userCheck = true;
    }

    if (this.reply.file == null ) {
      this.userImg = false
    } else {
      this.userImg = true
    }
  },

  methods: {
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

    // 댓글 삭제
    commentDelete() {
      http
      .post('/reply/delete', this.reply.rid)
      .then((res) =>{
        this.$emit('delete-reply')
      })
      .catch((err) => {
        console.log(err)
      })
    }


  },

}
</script>

<style scoped>

#sns-comment {
  margin: 15px 3px 0px 3px;
}

.comment-img {
  border-radius: 100%;
  width: 30px;
  height: 30px;
  overflow: hidden;
}

.comment-img > img {
  width: 100%;
}

.comment-name {
  font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
  font-size: 13px;
  font-weight: bold;
}

.comment-date {
  font-size: 12px;
  color: #E2DFD8;
}

.comment-content {
  font-size: 14px;
  color: #464545;
  width: 98%;
  white-space: normal;
  word-break: break-all;
}
</style>