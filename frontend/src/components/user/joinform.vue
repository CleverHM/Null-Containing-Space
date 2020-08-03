<template>
  <div id="JoinForm" class="user">
      <h5 class="mb-4">
      아래의 회원가입 폼을 작성하세요.
      </h5>

      <!-- 이메일(수정불가) -->
      <div class="input-form" id="email-form">
          <label for="email">이메일</label>
          <input v-model="user.email" 
          id="email"
          type="text"
          disabled />
      </div>

      <!-- 비밀번호 입력 -->
      <div class="input-form" id="password-form">
          <label for="password">비밀번호</label>
          <input v-model="user.password" 
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
          <input v-model="user.name" 
          id="name"
          type="text"
          placeholder="홍길동" />
      </div>

      <!-- 성별 !-->
      <div id="gender-form">
        <div>성별</div>
        <button class="btn-gender" :class="{'isClick' :male}" @click="selectmale" id="Male">남자</button>
        <button class="btn-gender" :class="{'isClick' : female}" @click="selectfemale" id="Female">여자</button>
      </div>
        
      <!-- 나이 !-->     
      <div class="input-form" id="age-form">
          <label for="email">나이</label>
          <input v-model="user.age"
          @keyup="checkage" 
          id="age"
          placeholder="25"
          type="text" />
      </div>

      <!-- 휴대폰 번호 !-->
      <div class="input-form">
        <label for="tel">휴대폰</label>
        <input v-model="user.tel"
        @keyup="ChangeTelForm(user.tel)"
        id="tel"
        placeholder="'-'를 제외하고 입력하세요."
        type="text"
        maxlength="13" />
        <div class="Warning" v-if="error.tel"><i class="fas fa-exclamation-triangle"></i>올바른 형식이 아닙니다.</div>
      </div>

      <!-- 닉네임 -->
      <div class="input-form">
          <label for="nickname">닉네임</label>
          <input v-model="user.nickname" 
          id="nickname"
          placeholder="ex) 알골마스터"
          type="text"/>
          <div class="Warning" v-if="error.nickname"><i class="fas fa-exclamation-triangle"></i>{{ error.nickname }}</div>
          <div class="Success" v-else><i class="fas fa-exclamation-triangle"></i>사용할 수 있는 닉네임입니다.</div>
          <button @click="isDuplicate">중복체크</button>
      </div>
   

    <button id="btn-join" @click="join">가입하기</button>
  </div>
</template>

<script>
import http from "@/util/http-common.js";

const passwordReg = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{6,20}$/
const ageReg = /^[1-9]{1}$|^[1-4]{1}[0-9]{1}$|^100$/


export default {
  name: 'Join3',
  props: {
    user:{
      type: Object,
      required: true,
    }
  },
  data() {
    return{
      tel: "",
      passwordConfirm: "",
      isTerm: false,
      isLoading: false,
      male: true,
      female : false,
      error: {
        password: false,
        nickname: false,
        passwordConfirm: false,
        age: false,
        tel: false,
      },
      isSubmit: false,
      passwordType: "password",
      passwordConfirmType: "password",
      termPopup: false,
    }
  },
  methods: {
    // 성별 선택
    selectmale() {
      this.male = true;
      this.female = false;
      this.user.gender = true;
    },
    selectfemale() {
      this.male = false;
      this.female = true;
      this.user.gender = false;
    },
    // 휴대폰 번호 체크 
     ChangeTelForm(inputNum){
      var phoneNum = inputNum.replace(/[^0-9]/g, '');
      console.log("before change", phoneNum)
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
      this.user.tel = tmp
    },
    // 비밀번호 체크
    checkpassword(){
      if (this.user.password.match(passwordReg) != null){
        this.error.password = false;
      } else {
        this.error.password = true;
      }
    },
    // 비밀번호확인 체크
    checkpasswordconfirm(){
      if (this.user.password === this.passwordConfirm){
        this.error.passwordConfirm = false;
      } else {
        this.error.passwordConfirm = true;
      }
    },
    // 나이 체크
    checkage(){
      if (this.user.age.match(ageReg) != null){
        this.error.age = false;
      } else {
        this.error.age = true;
      }

    },
    // 닉네임 중복 체크
    isDuplicate() {
      http
      .post("/account/nickNameDuplicate", this.user.nickname)
      .then((data) => {
        console.log(data.data)
        if (data.data.status) {
          this.error.nickname=""
        }
      })
      .catch((err) => {
        this.err.nickname="사용할 수 없는 닉네임입니다."
      })
    },
    // 회원 가입
    join(){
      console.log("hello")
      this.$emit("ConfirmJoin", this.user)
    }
  }
}
</script>

<style scoped>
#JoinForm{
    position: relative;
    width: 100%;
    padding: 70px 10px 50px 10px;
}
h5 {
   margin: 0 20px 30px 20px;
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
input[type="text"]:focus{
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
    top: 18px; 
    left: 13px;
}
#gender-form {
  width: 100%;
  margin: 0 20px 10px 20px;

}
.btn-gender{
  width: 45%;
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

