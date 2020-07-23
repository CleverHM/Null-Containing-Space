
<!--
    가입하기는 기본적인 폼만 제공됩니다
    기능명세에 따라 개발을 진행하세요.
    Sub PJT I에서는 UX, 디자인 등을 포함하여 백엔드를 제외하여 개발합니다.
 -->
<template>
  <div id="join3" class="user join wrapC">
    <h5 class="mb-4">
     아래의 회원가입 폼을 작성하세요.
    </h5>
    <div class="input-with-label">
        <input v-model="user.email" :disabled="true" id="email" placeholder="이메일을 입력하세요." type="text" />
        <label for="email">이메일</label>
    </div>
    <div class="form-wrap">
      <div class="input-with-label">
          <input v-model="user.password" id="password" :type="passwordType" placeholder="비밀번호를 입력하세요." />
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
      <div class="input-with-label NameAge">
        <input v-model="user.name" id="name" placeholder="이름을 입력하세요." type="text" />
        <label for="nickname">이름</label>
      </div>

      <!-- 성별 !-->
      <div>
        <p class="m-0">성별</p>
        <button class="btn-gender" :class="{'isClick' :male}" @click="selectmale" id="Male">남자</button>
        <button class="btn-gender" :class="{'isClick' : female}" @click="selectfemale" id="Female">여자</button>
      </div>
      
      <!-- 이름 & 나이 !-->     
      <div class="input-with-label NameAge">
          <input v-model="user.nickname" id="nickname" placeholder="나이를 입력하세요." type="text" />
          <label for="nickname">나이</label>
      </div>



      <div class="input-with-label">
        <input v-model="user.nickname" id="nickname" placeholder="닉네임을 입력하세요." type="text" />
        <label for="nickname">닉네임</label>
      </div>
    </div>

    <label>
      <input v-model="isTerm" type="checkbox" id="term" />
      <span>약관을 동의합니다.</span>
    </label>

    <span @click="termPopup=true">약관보기</span>

    <button class="btn-input" @click="join">가입하기</button>
  </div>
</template>

<script>
import http from "@/util/http-common.js";

export default {
  props: {
    user:{
      type:Object,
      required: true
    }
  },
  data: () => {
    return {
      passwordConfirm: "",
      isTerm: false,
      isLoading: false,
      male: true,
      female : false,
      error: {
        email: false,
        password: false,
        nickname: false,
        passwordConfirm: false,
        term: false
      },
      isSubmit: false,
      passwordType: "password",
      passwordConfirmType: "password",
      termPopup: false,

    };
  },
  methods:{
    selectmale() {
      this.male = true;
      this.female = false;
    },
    selectfemale() {
      this.male = false;
      this.female = true;

    },
    join(){
      
      this.$emit("ConfirmJoin", this.user, this.passwordConfirm)
    }
  }
};
</script>

<style scoped>
#join3{
  position: absolute;
  width:100%;
  top:134px;
  padding: 0px 30px 30px 30px;
}
.input-with-label{
  width: 100%;
}

.btn-input{
  position: fixed;
  bottom:0;
  left: 0;
  background-color: #464545;
  height: 50px;
  border-radius: 3px;
  color: #f7f7f7;
  font-weight: bold;
  width:100%;
}
#email:hover{
  border: 1px solid #000;
}
.gender-buttons{
  background-color: #f7f7f7;
  margin-bottom: 16px;
  border: 1px solid;
  
}
.btn-gender{
  width:50%;
  height: 50px;
  border: 1px solid #464545;
  margin-bottom: 16px;
    
}

.isClick{
  background-color: #464545;
  color: #f7f7f7;
  margin-left: -0px;
}

</style>

