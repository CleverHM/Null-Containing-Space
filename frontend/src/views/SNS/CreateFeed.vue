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

      <!-- 내용 -->
      <!-- textarea -->
      <TextareaComponent
        inputValue="article.content"
        placeholder="게시글 내용을 적어주세요."
        label="내용"
        maxlength="1000"
      ></TextareaComponent>

      <!-- 이미지 -->

      <!-- 해시태그 -->

    </div>
  </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue'
import subNav from '../../components/common/subnav.vue'
import TextareaComponent from "../../components/common/Textarea";
import "../../assets/css/components.scss";

export default {
  name: "CreateFeed",
  components: {
    Navbar,
    subNav,
    TextareaComponent,
  },
  data() {
    return {
      maxLength: 1000,
      article: {
        title: '',
        content: '',
        hashtags: [],
        image: '',
      }
    }
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
}

.input-with-label{
  width: 100%;
}

.textarea-wrap {
  width: 100%;
}
</style>