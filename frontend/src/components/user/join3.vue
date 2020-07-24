<template>
  <div id="join3" class="user join wrapC">
    <h5 class="mb-4">
     아래의 회원가입 폼을 작성하세요.
    </h5>
    <div class="input-with-label">
        <input v-model="user.email" :disabled="true" id="email" placeholder="example@xxxxx.com" type="text" />
        <label for="email">이메일</label>
    </div>
    <div class="form-wrap">
      <div class="input-with-label">
          <input v-model="user.password" @keyup="checkpassword" id="password" :type="passwordType" placeholder="＊＊＊＊＊＊＊" />
          <label for="password">비밀번호</label>
          <div class="Warning" v-if="error.password"><i class="fas fa-exclamation-triangle"></i> 영문,숫자 포함 8 자리이상이어야 합니다.</div>
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
      <!-- 이름 !-->
      <div class="input-with-label NameAge">
        <input v-model="user.name" id="name" placeholder="홍길동" type="text" />
        <label for="name">이름</label>
      </div>
        <!-- 성별 !-->
      <div>
        <div class="m-0">성별</div>
        <button class="btn-gender" :class="{'isClick' :male}" @click="selectmale" id="Male">남자</button>
        <button class="btn-gender" :class="{'isClick' : female}" @click="selectfemale" id="Female">여자</button>
      </div>
      
      <!-- 나이 !-->     
      <div class="input-with-label NameAge">
          <input @keyup="checkage" v-model="user.age" id="age" placeholder="25" type="text" />
          <label for="age">나이</label>
          <div class="Warning" v-if="error.age"><i class="fas fa-exclamation-triangle"></i>올바른 형식이 아닙니다.</div>
      </div>
      <!-- 휴대폰 번호 !-->
      <div class="input-with-label NameAge">
        <input @keyup="ChangeTelForm(user.tel)" v-model="user.tel" id="tel" placeholder="'-'를 제외하고 입력하세요." type="text" maxlength="13" />
        <label for="tel">휴대폰</label>
        <div class="Warning" v-if="error.tel"><i class="fas fa-exclamation-triangle"></i>올바른 형식이 아닙니다.</div>
      </div>

      <div class="input-with-label">
        <input @keyup="checknickname" v-model="user.nickname" id="nickname" placeholder="ex) 알골마스터" type="text" />
        <label for="nickname">닉네임</label>
        <div class="Warning" v-if="error.nickname"><i class="fas fa-exclamation-triangle"></i> 이미 존재하는 닉네임입니다.{{ user.nickname }}</div>
      </div>
    </div>

    

    <button class="btn-input" @click="join">가입하기</button>
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
      console.log("after = ", tmp)
      console.log("user.tel = ", this.user.tel)
    },
    checkpassword(){
      if (this.user.password.match(passwordReg) != null){
        this.error.password = false;
      } else {
        this.error.password = true;
      }
    },
    checkpasswordconfirm(){
      if (this.user.password === this.passwordConfirm){
        this.error.passwordConfirm = false;
      } else {
        this.error.passwordConfirm = true;
      }
    },
    checkage(){
      if (this.user.age.match(ageReg) != null){
        this.error.age = false;
      } else {
        this.error.age = true;
      }

    },
    checknickname(){

    },
    join(){
      console.log("hello")
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

