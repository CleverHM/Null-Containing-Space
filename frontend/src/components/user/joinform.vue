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
      </div>

      <!-- 휴대폰 번호 !-->
      <div class="input-form">
        <label for="tel">휴대폰</label>
        <input v-model="FormUser.tel"
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
          <input v-model="FormUser.nickname" 
          id="nickname"
          placeholder="ex) 알골마스터"
          type="text"/>
          <div class="Warning" v-if="error.nickname"><i class="fas fa-exclamation-triangle"></i>{{ error.nickname }}</div>
          <div class="Success" v-if="error.nicknameSuccess"><i class="fas fa-exclamation-triangle"></i>사용할 수 있는 닉네임입니다.</div>
          <button @click="isDuplicate">중복체크</button>
      </div>

      <button id="btn-join" @click="join">가입하기</button>
  </div>
</template>

<script>
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