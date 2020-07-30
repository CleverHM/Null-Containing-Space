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
        <b-img id="img" :src="imgReceive"></b-img>
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
        <b-icon icon="heart-fill" font-scale="1.2" :color="sns.like_color" @click="likeButton"></b-icon>
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
export default {
  name: "SNSItemn",
  props: {
    article: Object,
  },

  computed: {
    imgReceive() {
      return this.baseUrl + this.article.fileName;
    },
  },

  created() {
    this.likeCheck();
  },
  
  data() {
    return {
      // 'https://cdn.pixabay.com/photo/2020/07/10/20/45/sparrow-5392119__340.jpg',
      baseUrl: "../../../../../../../../s03p12d105/SNS_Backend/src/main/resources/static/images",
      imgUrl: '',
      sns: {
        username: '알골마스터',
        data: '9시간 전',
        imgurl: "",
        title: '.....ABCDEFGHIJK',
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
        liked: false,
        like_color: '',
      }
    }
  },

  methods: {
    // tag 클릭하면 화면 상단에 filtering 걸린 태그를 출력하기 위해서 상단 컴포넌트로 올려줌
    tagOn(event) {
      // console.log(event.target.innerText)
      this.$emit('tag-add', event.target.innerText)
    },

    // // 클릭 시 해당 article의 detail 페이지로 넘어감
    // detailOn(event) {
    //   // console.log(event)
    //   this.$emit('go-detail', this.article.pid);
    // },

    // 좋아요 체크
    likeCheck() {
      if (this.sns.liked) {
        this.sns.like_color = '#FF3300';
      } else {
        this.sns.like_color = '#C4BCB8';
      }
    },

    // 좋아요 누름
    likeButton(event) {
      // console.log('liked')
      if (this.sns.liked) {
        this.sns.liked = false;
        this.sns.like_color = '#C4BCB8';
      } else {
        this.sns.liked = true;
        this.sns.like_color = '#FF3300';
      }
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

</style>