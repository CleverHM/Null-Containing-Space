
<!--
    가입하기는 기본적인 폼만 제공됩니다
    기능명세에 따라 개발을 진행하세요.
    Sub PJT I에서는 UX, 디자인 등을 포함하여 백엔드를 제외하여 개발합니다.
 -->
<template>
  <div class="user join wrapC">
    <h4>가입하기</h4>
    <h5 class="mb-4">
    아래의 폼을 입력해주세요.
    </h5>

    <div class="input-with-label">
        <input v-model="nickName" id="nickname" placeholder="닉네임을 입력하세요." type="text" />
        <label for="nickname">닉네임</label>
    </div>


    <div class="form-wrap">
      <div class="input-with-label">
        <input v-model="email" id="email" placeholder="이메일을 입력하세요." type="text"/>
        <label for="email">이메일</label>
      </div>

      
      <div class="input-with-label">
        <input v-model="password" id="password" :type="passwordType" placeholder="비밀번호를 입력하세요." />
        <label for="password">비밀번호</label>
      </div>

      <div class="input-with-label">
        <input
          v-model="passwordConfirm"
          :type="passwordConfirmType"
          id="password-confirm"
          placeholder="비밀번호를 다시한번 입력하세요."
        />
        <label for="password-confirm">비밀번호 확인</label>
      </div>

      <!-- 비밀번호 찾기 !-->
      <div class="components-page">
        <SelectComponent :options="options" />
      </div>
      <div class="input-with-label">
        <input v-model="passwordAnswer" id="password" type="text" placeholder="비밀번호 질문의 답을 입력하세요." />
        <label for="passwordAnswer">비밀번호 질문</label>
      </div>




    </div>

    <button class="btn-bottom" @click="join">
    가입하기
    </button>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
import SelectComponent from "../../components/user/snsLogin/Select.vue";
import "../../assets/css/components.scss";

export default {
  components: {
      SelectComponent,
  },
  data: () => {
    return {
      email: "",
      password: "",
      passwordConfirm: "",
      passwordAnswer: "",
      nickName: "",
      isTerm: false,
      isLoading: false,
      error: {
        email: false,
        password: false,
        nickName: false,
        passwordConfirm: false,
        term: false
      },
      isSubmit: false,
      passwordType: "password",
      passwordConfirmType: "password",
      termPopup: false,
      options: [
        {
          value: "option1",
          title: "가장 좋아하는 프로그래밍 언어는?"
        },
        {
          value: "option2",
          title: "당신의 이름은?"
        }
      ],
    };
  },
  methods:{
    join(){
      let msg = "";
      http
      .post("/signup", {
        email : this.email,
        password : this.password,
        nickName : this.nickName
      })
      .then(({data}) => {
        if(data == "success") {
          msg = "complete";
        }
        alert(msg);
        this.moveLogin();
      });
    },
    moveLogin(){
      this.$router.push("/");
    }
  }
};
</script>


<style scoped>
</style>
