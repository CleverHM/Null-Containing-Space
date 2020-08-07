<template>
  <div id="join3" class="user join wrapC">
    <h5 class="mb-4">
     새 비밀번호를 입력하세요.
    </h5>
    <div class="form-wrap">
      <div class="input-with-label">
          <input v-model="password" @keyup="checkpassword" id="password" :type="passwordType" placeholder="＊＊＊＊＊＊＊" />
          <label for="password">새 비밀번호</label>
          <div class="Warning" v-if="error.password"><i class="fas fa-exclamation-triangle"></i> 영문,숫자 포함 6~20자리를 입력하세요.</div>
      </div>
      
      <div class="input-with-label">
        <input
          v-model="passwordConfirm"
          :type="passwordConfirmType"
          @keyup="checkpasswordconfirm" 
          id="password-confirm"
          placeholder="＊＊＊＊＊＊＊"
        />
        <label for="password-confirm">비밀번호 확인</label>
        <div class="Warning" v-if="error.passwordConfirm"><i class="fas fa-exclamation-triangle"></i> 비밀번호가 일치하지 않습니다.</div>
      </div>
    </div>
    

    <button class="btn-input" @click="updatePassword">변경하기</button>
  </div>
</template>

<script>
import http from "@/util/http-common.js";

const passwordReg = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{6,20}$/

export default {
  name: 'passwordForm',
  props: {
    email: {
      type: String,
    }
  },
  data() {
    return{
      password: "",
      passwordConfirm: "",
      error: {
        password: false,
        passwordConfirm: false,
      },
      isSubmit: false,
      passwordType: "password",
      passwordConfirmType: "password",
      termPopup: false,
    }
  },
  methods: {
    checkpassword(){
      if (this.password.match(passwordReg) != null){
        this.error.password = false;
      } else {
        this.error.password = true;
      }
    },
    checkpasswordconfirm(){
      if (this.password === this.passwordConfirm){
        this.error.passwordConfirm = false;
      } else {
        this.error.passwordConfirm = true;
      }
    },
    updatePassword(){
      var InputData = new FormData()
      InputData.append("email", this.email)
      InputData.append("NewPassword", this.password)
      http.post("account/findPasswordModify", InputData)
      .then(() => {
        this.$emit("Complete3")
      })
    }
  }
}
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
.Warning{
  font-size: 13px;
  color: red;
}
/* 폰번호 style */
.dropdown-menu.radio .dropdown-item { 
	position:relative;
	overflow:hidden; 
	cursor:pointer; }

.dropdown-menu.radio input { 
	visibility: hidden; 
	position:absolute; left: -30px; }

.dropdown-menu.radio i { 
	font-weight:normal; font-style:normal; 
	display:block; }

.dropdown-item.active {
  background-color:#555; color:#fff;
}

</style>

