<template>
  <div id="createFeed">
    <Navbar></Navbar>
    <subNav></subNav>
    <div class="feed-form">

      <!-- 제목 -->
      <div class="input-with-label">
        <input v-model="article.title" id="title" placeholder="제목을 입력하세요." type="text" />
        <label for="title">제목</label>
      </div>

      <!-- 본문 -->
      <div class="textarea-wrap">
        <div class="d-flex flex-row justify-content-between">
          <label>본문</label>
          <span>{{ article.content.length }}/</span>
        </div>
        <textarea class="d-flex flex-fill" v-model="article.content" placeholder="게시글 본문을 적어주세요."/>
      </div>

      <!-- 이미지 -->
      <div class="mx-1">
        <b-form-group label="이미지" label-for="file" label-cols-sm="2" label-size="sm" style="font-family: 'NanumBarunGothic', sans-serif; font-weight: bold;  font-size:13px;">
          <b-form-file 
            v-model="file"
            :state="Boolean(file)"
            id="file"
            ref="file"
            v-on:change="handleFileUpload()"
            accept="image/*"
            size="sm"
            style="border-color: #000;">
          </b-form-file>
        </b-form-group>
      </div>


      <!-- 해시태그 -->
      <div class="mx-1">
        <label for="tags-pills" style="font-family: 'NanumBarunGothic', sans-serif; font-weight: bold; font-size:13px;">Hash-tag</label>
        <b-form-tags
          input-id="tags-pills"
          :input-attrs="{ 'aria-describedby': 'tags-remove-on-delete-help' }"
          v-model="article.hashtags"
          tag-variant="info"
          tag-pills
          separator=" "
          placeholder="해시태그를 적어주세요."
          remove-on-delete
          class="mb-2"
        ></b-form-tags>
        <!-- <p>{{ article.hashtags }}</p> -->
      </div>

    </div>

    <!-- 작성 -->
    <div class="article-submit fixed-bottom d-flex justify-content-center align-content-center">
      <button @click="articleSubmit">작성하기</button>
    </div>
  </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue'
import subNav from '../../components/common/subnav.vue'
import http from "../../util/http-common.js";
import axios from 'axios';

export default {
  name: "CreateFeed",
  components: {
    Navbar,
    subNav,
  },
  data() {
    return {
      maxLength: 1000,
      file: null,
      article: {
        title: "",
        content: "",
        hashtags: [],
      },
    }
  },

  methods: {
    // 글 작성
    articleSubmit() {
    
      if (this.article.title === "") {
        this.errorMsg();
      } else {
        this.submitOn();
      }
    },

    errorMsg() {
      alert('제목을 입력하세요.')
    },

    submitOn() {
      console.log('submit');

      // 파일 axios 보내기
      let formData = new FormData();
      formData.append("file", this.file);

      formData.append("title", this.article.title);
      formData.append("content", this.article.content);
      formData.append("hashtags", this.article.hashtags);

      // 파일 업로드 axios 요청
      http
      .POST("/account/posting",
        formData,
        {
          headers: {
              'Content-Type': 'multipart/form-data'
          }
        }
      )
      .then(function(){
        console.log('SUCCESS!!');
      })
      .catch(function(){
        console.log('FAILURE!!');
      })
      
      // 이미지 제외 axios 요청
    //   http
    //   .POST("", {
    //     title: this.article.title,
    //     content: this.article.content,
    //     hashtags: this.article.hashtags
    //   })
    //   .then(({data}) => {
    //     if(data == "success") {
    //       console.log("complete");
    //     }
    //     this.moveFeed();
    //   });
    },

    moveFeed() {
      this.$router.push({ name: 'FeedMain' });
    },
    
    // 파일 업로드
    handleFileUpload() {
      // console.log(this.$refs.file.$refs.input.files[0])
      this.file = this.$refs.file.$refs.input.files[0];
      console.log(this.file)
    },

  },
  
  watch:{
    contents : function(value){
      let length = this.maxLength;
      value = value.length > length ? value.substr(0, length) : value;

      this.contents = value;
    }
  },
}
</script>

<style scoped>
.feed-form {
  margin-top: 85px;
  margin-bottom: 50px;
}

.input-with-label{
  width: 100%;
}

.textarea-wrap {
  width: 100%;
  float: left;
  position: relative;
  border: 1px solid #000;
  padding: 15px 15px 10px;
  margin-bottom: 10px;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  border-radius: 3px;
}

.textarea-wrap span {
  font-size: 0.857em;
  margin-top: 4px;
  color: #707070;
}

textarea {
    width: 100%;
    height: 100px;
    overflow: auto;
    resize: vertical;
    text-rendering: auto;
    color: -internal-light-dark(black, white);
    background-color: #f7f7f7;
    letter-spacing: normal;
    word-spacing: normal;
    text-transform: none;
    text-indent: 0px;
    text-shadow: none;
    display: inline-block;
    text-align: start;
    appearance: textarea;
    -webkit-rtl-ordering: logical;
    flex-direction: column;
    resize: auto;
    cursor: text;
    white-space: pre-wrap;
    overflow-wrap: break-word;
    margin: 0em;
    font: 400 13.3333px Arial;
    padding: 2px;
}

.textarea-wrap label {
  font-size: 13px;
  font-weight: bold;
}

.form-control {
    display: block;
    width: 100%;
    height: calc(1.5em + .75rem + 2px);
    padding: .375rem .75rem;
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: grey;
    background-color: #f7f7f7;
    background-clip: padding-box;
    border: 1px solid #000;
    border-radius: .25rem;
    transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
}
.badge-secondary {
    color: #fff;
    background-color: #ACCCC4;
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

</style>