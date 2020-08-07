<template>
  <div id="join1">
      <h5 class="mb-4" v-if="linkName==='step1'">
      {{ title.join }}
      </h5>
      <h5 class="mb-4" v-if="linkName==='findPassword'">
      {{ title.password }}
      </h5>
      <div class="input-form">
          <input @keyup.enter="confirmEmail" v-model="email" id="email" placeholder="이메일을 입력하세요." type="text" />
          <label for="email">이메일</label>
          <div id="ErrorMsg" style="text-align: left;">{{ ErrorMessage }}</div>
      </div>
      
      <button class="btn-input" @click="confirmEmail">입력</button>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
// 이메일 체크 정규식
var EmailregExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
export default {
  created() {
    this.linkName = this.$route.name
  },
  data : () => {
    return {
      linkName: null,
      email: "",
      title : {
        join : "가입할 이메일 주소를 아래에 입력해주세요.",
        password : "가입한 이메일 주소를 아래에 입력해주세요.",
      },
      ErrorMessage: "",
      
    }
  },
  methods:{
    // 데이터베이스에 이메일이 있는지 확인하고 다음페이지로 이동시키기
    confirmEmail() {
        // 비밀번호 찾기 페이지
        if (this.linkName == "findPassword"){
          if (this.email.match(EmailregExp) != null){
              http
              .post("/auth/passwordUpdateMailSend", this.email)
              .then(({data}) => {
                  this.$emit("Complete1", this.email)
              })
              .catch(() => {
                  this.ErrorMessage = "존재하지 않는 이메일입니다. 다른 이메일을 입력해주세요."
              })

          }else {
            this.ErrorMessage = "이메일 형식이 올바르지 않습니다. 다시 입력해주세요."
          }
        // 회원가입
        }else {
          if (this.email.match(EmailregExp) != null){
              http
              .post("/account/emailDuplicate", this.email)
              .then(({data}) => {
                  this.$emit("CompleteStep1", this.email)
              })
              .catch((err) => {
                  this.ErrorMessage = "이미 존재하는 이메일입니다. 다른 이메일을 입력해주세요."
              })
          }else {
            this.ErrorMessage = "이메일 형식이 올바르지 않습니다. 다시 입력해주세요."
          }
        }      
    },
  }
};
</script>


<style scoped>
#join1{
  position: absolute;
  width:100%;
  top:134px;
  padding: 0px 30px 30px 30px;
}
.input-form {
    position: relative;
    height: 50px;
}
input[type="text"]{
    padding-left: 100px;
    width: 100%;
    border: 1px solid #464545;
    border-radius: 3px;
}
input[type="text"]:focus{
    border: 0;
    border-bottom: 1.2px solid #464545;
}

.input-form label {
    position: absolute;
    padding: 0;
    margin: 0;
    font-size: 13px;
    font-weight: bold;
    top: 17px; 
    left: 13px;
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
#ErrorMsg{
  color : #D91120;
}

</style>