<template>
  <div id="createFeed">
    <Navbar></Navbar>
    <div class="feed-form">
      <!-- 제목 -->
      <div class="inputForm">
        <input 
        v-model="article.title" 
        id="title" 
        placeholder="제목(3글자 이상 입력해주세요)" 
        maxlength="50" 
        type="text" />
      </div>

      <!-- 본문 -->
      <div class="textareaForm">
        <div class="textlength">{{ article.content.length }}/</div>
        <textarea class="d-flex flex-fill" v-model="article.content" placeholder="게시글 본문을 적어주세요."/>
      </div>

      <!-- 이미지 -->
      <div class="mx-1">
        <b-form-group style="font-family: 'NanumBarunGothic', sans-serif; font-weight: bold;  font-size:13px;">
          <b-form-file 
            v-model="article.file"
            :placeholder="image.placeholder"
            :state="image.state"
            id="file"
            ref="file"
            v-on:change="handleFileUpload()"
            accept="image/*"
            size="sm"
            style="border-color: #000;">
          </b-form-file>
        </b-form-group>
      </div>
      <div v-if="updateCheck">
        <!-- 임시로 원래 들어간 이미지 띄우기 -->
        <img :src="'data:image/png;base64, ' + updateImage" alt="image" class="img-part" style="width: 50px;">
      </div>

      <!-- 해시태그 -->

      <!-- 해시태그 작성 -->
      <div class="mx-1 mb-2">
        <input v-model="hashtag"
         @keyup.space="addhashtag(hashtag)" 
         @keyup.enter="addhashtag(hashtag)" 
         @keyup.delete="removetag(hashtag)" 
         placeholder="해시태그를 입력해주세요." 
         type="text" />
      </div>
      <div class="displaytags" v-if="article.hashtags.length">
      입력 태그 : 
      <button class="btn-tags" v-for="tag in article.hashtags" :key="tag" @click="tagRemove">{{ tag }}</button>
      </div>
      

    </div>

    <!-- 작성 -->
    <div class="article-submit fixed-bottom d-flex justify-content-center align-content-center">
      <button @click="articleSubmit" :disabled="isDisabled">작성하기</button>
    </div>

    <!-- 작성 누른 후 로딩 시간 -->
    <div :class="{loadingContainer: isDisabled, loadingOff: !isDisabled}">
      <div :class="{loading: isDisabled, loadingOff: !isDisabled}">
      </div>
      <div :class="{loadingText: isDisabled, loadingOff: !isDisabled}">업로드 중입니다</div>
    </div>
  </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue'
import http from "../../util/http-common.js";
import axios from 'axios';

const storage = window.sessionStorage;

export default {
  name: "CreateFeed",
  props: ["pId"],
  components: {
    Navbar,
  },
  computed: {
    // image 파일을 보여줘야하는가?
    updateCheck() {
      this.imageInformation();
      if (this.article.file) {
        return false
      } else if (this.$route.name === 'FeedUpdate') {
        return true
      } else {
        return false
      }
    },
  },

  data() {
    return {
      maxLength: 1000,
      article: {
        title: "",
        content: "",
        hashtags: [],
        file: null,
      },
      // 이미지 상태 관련
      image: {
        state: false,
        placeholder: '',
      },
      updateImage: '',
      notchangeImage: false, // 게시글 수정에서 이미지가 업데이트 되었는지 여부
      hashtag: "",
      isDisabled: false,
    }
  },

  created() {
    this.isDisabled = false;
    if (this.$route.name === 'FeedUpdate') {
      this.bringPost(this.pId);
    }
  },

  methods: {
    // 이미지 정보 상태 체크
    imageInformation() {
      // 파일이 들어갔을 때 / 게시글 수정으로 들어왔을 때 / 전부 아닐 때
      if (this.article.file) {
        this.image.state = true
        this.image.placeholder = '이미지를 등록해주세요'
        this.notchangeImage = false
      } else if (this.$route.name === 'FeedUpdate') {
        this.image.state = true
        this.image.placeholder = 'Before_Image.png'
        this.notchangeImage = true
      } else {
        this.image.state = false
        this.image.placeholder = '이미지를 등록해주세요. (없으면 작성 불가)'
        this.notchangeImage = false
      }
    },

    // 글 수정일 경우 처음 기존 데이터 가져오기
    bringPost() {
      var postId = parseInt(this.pId) // string -> 정수 변환

      http
      .get(`/post/modifyData/${postId}`)
      .then((res) => {
        // 직접 값 집어넣기
        this.article.title = res.data.title
        this.article.content = res.data.content
        this.article.hashtags = res.data.tags
        this.updateImage = res.data.file

      })
      .catch((err) => {
        console.log(err)
      })
    },

    // 글 작성
    articleSubmit() {
      this.isDisabled = true;
      if (this.article.title === "") {
        this.errorMsg();
        this.isDisabled = false;
      } else if (this.$route.name === 'FeedUpdate') {
        // console.log('글 수정')
        this.submitModify();
      } else {
        this.submitOn();
      }
    },

    errorMsg() {
      alert('제목을 입력하세요.')
    },

    // 수정 사항을 저장하기 위한 요청 2단계로 나눔
    submitModify() {
      var postId = parseInt(this.pId)

      let formData = new FormData();
      formData.append("email", storage.getItem("User"));
      formData.append("title", this.article.title);
      formData.append("content", this.article.content);
      formData.append("hashtags", this.article.hashtags);

      // 이미지 변화가 없을 때 / 있을 때
      if (this.notchangeImage) {
        
        http
        .put(`/post/isFalse/${postId}`, formData)
        .then((res) => {
          this.$router.replace({ name: 'FeedDetail', params: { postId: postId }});
        })
        .catch((err) => {
          console.log(err)
        })

      } else {

        formData.append("files", this.article.file);

        http
        .put(`/post/isTrue/${postId}`, 
          formData,
          {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
          }
        )
        .then((res) => {
          this.$router.replace({ name: 'FeedDetail', params: { postId: postId }});
        })
        .catch((err) => {
          console.log(err)
        })

      }

    },

    submitOn() {
      
      // 파일 axios 보내기
      let formData = new FormData();
      formData.append("files", this.article.file);
      formData.append("email", storage.getItem("User"));
      formData.append("title", this.article.title);
      formData.append("content", this.article.content);
      formData.append("hashtags", this.article.hashtags);
      // 파일 업로드 axios 요청
      http
      .post("/post/create",
        formData,
        {
          headers: {
              'Content-Type': 'multipart/form-data'
          }
        }
      )
      .then((res) => {
        this.moveFeed();
      })
      .catch((err) => {
        console.log(err)
      })
          
    },

    moveFeed() {
      this.$router.push({ name: 'FeedMain' });
    },
    addhashtag(hashtag) {
      hashtag = hashtag.replace(/(\s*)/g, "")
      if (hashtag && hashtag !="" && hashtag != " ") {
        var isDouble = this.article.hashtags.find(function(n){
          return hashtag === n
        })
      // console.log(isDouble)
      if (!isDouble) {
        this.article.hashtags.push(hashtag)
      }
      }
      
      this.hashtag = ""

    },
    // 해시태그 작성중 backspace키 누르면
    removetag(hashtag) {
      if (!hashtag) {
        this.article.hashtags.pop()
      }
      
    },
    // 태그 클릭하면 -
    tagRemove(event) {
      this.article.hashtags.splice(this.article.hashtags.indexOf(event.target.innerText),1)
    },
    
    // 파일 업로드
    handleFileUpload() {
      this.file = this.$refs.file.$refs.input.files[0];
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
.inputForm{
  margin: 0px 5px 10px 5px;
  
}
input {
  padding: 0 10px 0 10px;
  width: 100%;
  height: 40px;
  border: none;
  border-bottom: 1px solid black;
}
input::placeholder {
  font-size: 13px;
}
.textareaForm{
  margin: 0px 5px 10px 5px;
  border: none;
}
.textareaForm textarea {
  padding: 0 10px 0 10px;
}
.textlength{
  position: absolute;
  right: 0;
  margin-right: 10px;
}
.hashtags::placeholder{
   color: red;
}
.btn-tags{
  border-radius: 10px;
  margin: 3px;
  padding: 1px 5px 1px 5px;
  background-color: #C4BCB8;
  font-weight:bold;
  color: #f7f7f7;
}
.displaytags{
  padding: 0px 10px 0 10px;
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

.loadingOff {
  display: none;
}

/** BEGIN CSS **/
@keyframes rotate-loading {
    0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
    100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
}

@-moz-keyframes rotate-loading {
    0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
    100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
}

@-webkit-keyframes rotate-loading {
    0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
    100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
}

@-o-keyframes rotate-loading {
    0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
    100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
}

@keyframes rotate-loading {
    0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
    100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
}

@-moz-keyframes rotate-loading {
    0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
    100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
}

@-webkit-keyframes rotate-loading {
    0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
    100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
}

@-o-keyframes rotate-loading {
    0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
    100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
}

@keyframes loading-text-opacity {
    0%  {opacity: 0}
    20% {opacity: 0}
    50% {opacity: 1}
    100%{opacity: 0}
}

@-moz-keyframes loading-text-opacity {
    0%  {opacity: 0}
    20% {opacity: 0}
    50% {opacity: 1}
    100%{opacity: 0}
}

@-webkit-keyframes loading-text-opacity {
    0%  {opacity: 0}
    20% {opacity: 0}
    50% {opacity: 1}
    100%{opacity: 0}
}

@-o-keyframes loading-text-opacity {
    0%  {opacity: 0}
    20% {opacity: 0}
    50% {opacity: 1}
    100%{opacity: 0}
}
.loadingContainer {
  margin: 50px 0px 50px 0px;
  position: fixed;
  z-index: 500;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, .4);
  display: table;
  transition: opacity .3s ease;
}

.loading {
  position: absolute;
  top: 45%;
  left: 50%;
  margin-top:-50px; margin-left:-50px; 
  height: 100px;
  width: 100px;
  border-radius: 100%;
  border: 2px solid transparent;
  border-color: transparent #ACCCC4 transparent #ACCCC4;
  -moz-animation: rotate-loading 1.5s linear 0s infinite normal;
  -moz-transform-origin: 50% 50%;
  -o-animation: rotate-loading 1.5s linear 0s infinite normal;
  -o-transform-origin: 50% 50%;
  -webkit-animation: rotate-loading 1.5s linear 0s infinite normal;
  -webkit-transform-origin: 50% 50%;
  animation: rotate-loading 1.5s linear 0s infinite normal;
  transform-origin: 50% 50%;
}

.loadingContainer:hover .loading {
  border-color: transparent #464545 transparent #464545;
}
.loadingContainer:hover .loading,
.loadingContainer .loading {
  -webkit-transition: all 0.5s ease-in-out;
  -moz-transition: all 0.5s ease-in-out;
  -ms-transition: all 0.5s ease-in-out;
  -o-transition: all 0.5s ease-in-out;
  transition: all 0.5s ease-in-out;
}

.loadingText {
  position: absolute;
  top: 45%;
  left: 50%;
  transform:translate(-50%, -50%);
  -moz-animation: loading-text-opacity 2s linear 0s infinite normal;
  -o-animation: loading-text-opacity 2s linear 0s infinite normal;
  -webkit-animation: loading-text-opacity 2s linear 0s infinite normal;
  animation: loading-text-opacity 2s linear 0s infinite normal;
  color: white;
  font-size: 12px;
  font-weight: bold;
  opacity: 0;
  text-align: center;
  text-transform: uppercase;
  width: 100px;
}

</style>