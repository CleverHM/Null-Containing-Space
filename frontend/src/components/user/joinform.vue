<template>
  <div id="JoinForm" ref="JoinForm">
      <h5 class="mb-4">
      아래의 회원가입 폼을 작성하세요.
      </h5>

      <!-- 이메일(수정불가) -->
      <div class="input-form" id="email-form">
          <label for="email">이메일</label>
          <input v-model="FormUser.email" 
          id="email"
          type="text"
          disabled />
      </div>

      <!-- 비밀번호 입력 -->
      <div class="input-form" id="password-form">
          <label for="password">비밀번호</label>
          <input v-model="FormUser.password" 
          @keyup="checkpassword"
          id="password"
          type="password"
          placeholder="********" />
          <div class="Warning" v-if="error.password"><i class="fas fa-exclamation-triangle"></i> 영문,숫자 포함 8 자리이상이어야 합니다.</div>
      </div>

      <!-- 비밀번호 중복 체크 -->
      <div class="input-form" id="passwordConfim-form">
          <label for="passwordConfirm">비밀번호 확인</label>
          <input v-model="passwordConfirm"
          @keyup="checkpasswordconfirm" 
          id="password-confirm"
          type="password"
          placeholder="********" />
          <div class="Warning" v-if="error.passwordConfirm"><i class="fas fa-exclamation-triangle"></i> 비밀번호가 일치하지 않습니다.</div>
      </div>

      <!-- 이름 -->
      <div class="input-form" id="name-form">
          <label for="email">이름</label>
          <input v-model="FormUser.name" 
          id="name"
          type="text"
          placeholder="홍길동" />
      </div>

      <!-- 성별 !-->
      <div id="gender-form">
        <div>성별</div>
        <button class="btn-gender" :class="{'isClick' : isMale}" @click="selectmale" id="Male">남자</button>
        <button class="btn-gender" :class="{'isClick' : isFemale}" @click="selectfemale" id="Female">여자</button>
      </div>

      <!-- 나이 !-->     
      <div class="input-form" id="age-form">
          <label for="email">나이</label>
          <input v-model="FormUser.age"
          @keyup="checkage" 
          id="age"
          placeholder="25"
          type="text" />
          <div class="Warning" v-if="error.age"><i class="fas fa-exclamation-triangle"></i>{{ error.age }}</div>
      </div>

      <!-- 휴대폰 번호 !-->
      <div class="input-form">
        <label for="tel">휴대폰</label>
        <input v-model="FormUser.tel"
        @keyup="ChangeTelForm(FormUser.tel)"
        id="tel"
        placeholder="'-'를 제외하고 입력하세요."
        type="text"
        maxlength="13" />
        <div class="Warning" v-if="error.tel"><i class="fas fa-exclamation-triangle"></i>올바른 형식이 아닙니다.</div>
      </div>

      <!-- 닉네임 -->
      <div class="input-form">
          <label for="nickname">닉네임</label>
          <input v-model="FormUser.nickname" 
          id="nickname"
          placeholder="ex) 알골마스터"
          type="text"/>
          <div class="Warning" v-if="error.nickname"><i class="fas fa-exclamation-triangle"></i>{{ error.nickname }}</div>
          <div class="Success" v-if="error.nicknameSuccess"><i class="fas fa-exclamation-triangle"></i>사용할 수 있는 닉네임입니다.</div>
          <button @click="isDuplicate">중복체크</button>
      </div>

      <button id="btn-join" @click="confirmForm">입력</button>
  </div>
</template>

<script>
import http from "@/util/http-common.js";

const passwordReg = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{6,20}$/
const ageReg = /^[1-9]{1}$|^[1-4]{1}[0-9]{1}$|^100$/
export default {
  name: 'Join3',
  props: {
    User: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      FormUser: this.User,
      passwordConfirm: "",
      isMale: true,
      isFemale: false,
      error: {
        password: false,
        nickname: false,
        nicknameSuccess: false,
        passwordConfirm: false,
        age: false,
        tel: false,
      },
      isSubmit: false,

    }
  },
  methods: {
    // 비밀번호 체크
    checkpassword() {
      if (this.FormUser.password.match(passwordReg) != null){
        this.error.password = false;
      } else {
        this.error.password = true;
      }
    },
    // 비밀번호 확인 체크
    checkpasswordconfirm() {
      if (this.FormUser.password === this.passwordConfirm){
        this.error.passwordConfirm = false;
      } else {
        this.error.passwordConfirm = true;
      }
    },
    // 성별 선택 - 남자
    selectmale() {
      this.isMale = true;
      this.isFemale = false;
      this.FormUser.gender = true;
    },
    // 성별 선택 - 여자
    selectfemale() {
      this.isMale = false;
      this.isFemale = true;
      this.FormUser.gender = false;
    },
    // 나이 체크
    checkage() {
      if (this.FormUser.age.match(ageReg) != null){
        this.error.age = false;
      } else {
        this.error.age = "올바른 형식이 아닙니다.";
      }
    },
    // 휴대폰번호 체크
    ChangeTelForm(inputNum){
      var phoneNum = inputNum.replace(/[^0-9]/g, '');
      var tmp = '';
      if (phoneNum.length < 4){
        tmp = phoneNum
      } else if (phoneNum.length < 7){
        tmp += phoneNum.substr(0, 3);
        tmp += '-'
        tmp += phoneNum.substr(3);
      } else if (phoneNum.length < 11) {
        tmp += phoneNum.substr(0, 3);
        tmp += '-'
        tmp += phoneNum.substr(3, 3);
        tmp += '-'
        tmp += phoneNum.substr(6);
      }else {
        tmp += phoneNum.substr(0, 3);
        tmp += '-'
        tmp += phoneNum.substr(3, 4);
        tmp += '-'
        tmp += phoneNum.substr(7);
      }
      this.FormUser.tel = tmp
    },
    // 닉네임체크
    isDuplicate() {
      http
      .post("/account/nickNameDuplicate", this.FormUser.nickname)
      .then((data) => {
        if (data.data.status) {
          this.error.nicknameSuccess="사용할 수 있는 닉네임입니다."
          this.error.nickname=false
        }
      })
      .catch((err) => {
        this.error.nickname="사용할 수 없는 닉네임입니다."
        this.error.nicknameSuccess=false;
      })
    },
    // 최종 회원가입양식 확인
    confirmForm() {
      if (this.FormUser.password && this.passwordConfirm && this.FormUser.name &&
           this.FormUser.age && this.FormUser.tel && this.FormUser.nickname){
            
            // 다시 확인
            this.checkpassword()
            this.checkpasswordconfirm()
            this.checkage()
            this.isDuplicate()


            if (this.error.nickname || !this.error.nicknameSuccess || this.error.password || this.error.passwordConfirm){
                alert("다시 입력해주세요.")
            } else {
                this.$emit("CompleteStep3", this.FormUser)
            }
      } else {
        alert("필수사항이 입력되지않았습니다. 다시 입력해주세요.")
      }
      

    },
  }
}
</script>

<style scoped>
#JoinForm{
    position: relative;
    width: 100%;
    padding: 0 10px 50px 10px;
}
.input-form {
    position: relative;
    margin: 0 20px 30px 20px;
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
.input-form button{
    padding: 4px 6px 4px 6px;
    position: absolute;
    background-color: #464545;
    border-radius: 3px;
    color: #f7f7f7;
    bottom: 8.5px;
    right: 11px;
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
#gender-form {
  margin: 0 20px 10px 20px;
  padding: 0 40px 0 0;
  width: 100%;

}
#gender-form > div {
  text-align: left;
  margin-bottom: 10px;
}
.btn-gender{
  width: 50%;
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
  text-align: left;
  font-size: 13px;
  color: red;
}
.Success{
  font-size: 13px;
  color: green;
}
#btn-join{
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
</style>