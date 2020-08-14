<template>
  <div id="join1">
      <h5 class="mb-4" v-if="linkName==='step1'">
      {{ title.join }}
      </h5> 
      <h5 class="mb-4" v-if="linkName==='findPassword'">
      {{ title.findpassword }}
      </h5>
      <h5 class="mb-4" v-if="linkName==='modifyPassword'">
      {{ title.modifypassword }}
      </h5>
      <div class="input-form" v-if="linkName === 'modifyPassword'">
          <input @keyup.enter="confirmPassword" v-model="password" id="password" placeholder="********" type="password" />
          <label for="password">현재 비밀번호</label>
          <div id="ErrorMsg" style="text-align: left;">{{ ErrorMessage }}</div>
      </div>

      <div class="input-form" v-else>
          <input @keyup.enter="confirmEmail" v-model="email" id="email" placeholder="이메일을 입력하세요." type="text" />
          <label for="email">이메일</label>
          <div id="ErrorMsg" style="text-align: left;">{{ ErrorMessage }}</div>
      </div>

      <button v-if="linkName === 'modifyPassword'" class="btn-input" @click="confirmPassword">입력</button>
      <button v-else class="btn-input" @click="confirmEmail">입력</button>
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
      password: "",
      title : {
        join : "가입할 이메일 주소를 아래에 입력해주세요.",
        findpassword : "가입한 이메일 주소를 아래에 입력해주세요.",
        modifypassword : "현재 비밀번호를 아래에 입력해주세요.",
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
              .then(() => {
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
              http.get(`/account/emailDuplicate/${this.email}`)
              .then(() => {
                  this.$emit("CompleteStep1", this.email)
              })
              .catch(() => {
                  this.ErrorMessage = "이미 존재하는 이메일입니다. 다른 이메일을 입력해주세요."
              })
          }else {
            this.ErrorMessage = "이메일 형식이 올바르지 않습니다. 다시 입력해주세요."
          }
        }      
    },

    confirmPassword() {
      var InputData = new FormData()
      InputData.append("email", window.sessionStorage.User)
      InputData.append("NewPassword", this.password)
      http.post("/account/findPasswordModify", InputData)
      .then(({data}) => {
        if (data.status) this.ErrorMessage = "다시 입력해주세요."
        else {
          this.$emit("Complete1")
        }
      })

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
input[type="text"], 
input[type="password"]{
    padding-left: 100px;
    width: 100%;
    border: 1px solid #464545;
    border-radius: 3px;
}
input[type="text"]:focus,
input[type="password"]:focus{
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