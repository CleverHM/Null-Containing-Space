<template>
  <div id="SNSItem">
    <!-- user 부분 -->
    <div class="user-part d-flex flex-row align-items-center">
      <div class="user-img"></div>
      <div class="flex-column">
        <div class="user-name">{{ article.userName }}</div>
        <div class="date-diff">{{ article.date }}</div>
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
        <span>0</span>
      </div>
    </div>

  </div>
</template>

<script>
import http from "../../util/http-common.js";
import axios from 'axios';

const storage = window.sessionStorage;

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
    // console.log(this.article.file)
    this.imgUrl = this.article.file
  },
  
  data() {
    return {
      // 'https://cdn.pixabay.com/photo/2020/07/10/20/45/sparrow-5392119__340.jpg',
      baseUrl: "../../../../../../../../s03p12d105/SNS_Backend/src/main/resources/static/images",
      likeColor: '',
    }
  },

  methods: {
    // tag 클릭하면 화면 상단에 filtering 걸린 태그를 출력하기 위해서 상단 컴포넌트로 올려줌
    tagOn(event) {
      // console.log(event.target.innerText)
      this.$emit('tag-add', event.target.innerText)
    },

    // 좋아요 체크
    likeCheck() {
      if (!this.article.likeFlag) {
        this.likeColor = '#FF0000';
      } else {
        this.likeColor = '#C4BCB8';
      }
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
  background-color: #C4BCB8;
  border-radius: 50%;
  width: 50px;
  height: 50px;
}

.user-name {
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  color: #464545;
  font-weight: bold;
  font-size: 16px;
}

.date-diff {
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