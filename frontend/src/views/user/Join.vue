<template>
  <div class="JoinView">
    <div class="progress-container">
      <ul class="progressbar">
        <li id="Step1" class="active">이메일 입력</li>
        <li id="Step2" :class="{'active':isActiveStep2}">이메일 인증</li>
        <li id="Step3" :class="{'active':isActiveStep3}">회원가입 폼 작성</li>
      </ul>
    </div>
    <div v-if="isActiveStep2">

      <div v-if="isActiveStep3">
        <Join3 @ConfirmJoin="Join" :user="user"></Join3>
      </div>
      <div v-else>
        <Join2 @ConfirmCode="Gostep3" :authNum="authNum" :ErrorMessage="PasswordErrorMsg"></Join2>
      </div>
    </div>
    <div v-else>
      <Join1 @ConfirmEmail="Gostep2" :email="user.email"></Join1>
    </div>

  </div>
</template>

<script>
import Join1 from '../../components/user/join1.vue'
import Join2 from '../../components/user/join2.vue'
import Join3 from '../../components/user/join3.vue'
import http from "../../util/http-common.js";
import axios from 'axios';
export default {
  name: 'JoinView',
  components: {
    Join1,
    Join2, 
    Join3,
  },
  data: () => {
    return {
      user : {},
      authNum : "",
      isActiveStep2 : false,
      isActiveStep3 : false,
      PasswordErrorMsg : "",
      // 회원가입 폼 확인
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
      termPopup: false
    };
  },
  created(){
    this.user.email = ""
    this.user.nickname = ""
    this.user.password = ""
  },
  methods:{
    Gostep2(email){
      this.user.email = email
      console.log(this.user.email, typeof(this.user.email))
      console.log(this.user, typeof(this.user))
      http
      .post('/account/loginMailSend', 
        this.user.email,
      )
      .then((data) => {
        console.log(data)
      })


      this.isActiveStep2 = true;
    },
    Gostep3(authNum) {
      console.log(this.user.email)
      console.log(authNum)
      http
      .post('/account/loginMailConfirm', 
        {
          "auth_email": this.user.email,
          "auth_number": authNum,
        }
        )
      .then((data) => {
        this.isActiveStep3 = true;
      })
      .catch((err) => {
        this.PasswordErrorMsg = "인증번호가 일치하지 않습니다. 다시 입력해 주세요."
      })
      
    },
    Join(user, passwordConfirm){
      this.user = user
      console.log(this.user)
      let msg = "";
      http
      .post("/account/signup", {
        email : this.user.email,
        nickname : this.user.nickname,
        password : this.user.password
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
    
  },
}
</script>

<style scoped>
.progress-container{
  width:100%;
  position: absolute;
  top:30px;
  z-index: 1;
}
.progressbar{
  counter-reset: step;
}
.progressbar li{
  float: left;
  width: 33.333%;
  position: relative;
  text-align: center;
}
.progressbar li:before{
  content:counter(step);
  counter-increment: step;
  width:30px;
  height:30px;

  border: 2px solid #ACCCC4;
  color: #ACCCC4;
  display: block;
  margin: 0 auto 10px auto;
  border-radius: 50%;
  line-height: 27px;
  background:  #f7f7f7;
  text-align: center;
  font-weight: bold;
}
.progressbar li:after{
  content: '';
  position: absolute;
  width:100%;
  height: 3px;
  background: #ACCCC4;
  top: 15px;
  left: -50%;
  z-index: -1;
}
.progressbar li:first-child:after{
content: none;
}
.progressbar li.active:before{
border-color: #3aac5d;
background: #3aac5d;
color: white
}
.progressbar li.active:after{
 background: #3aac5d;
}


#step1, #step2, #step3{
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
</style>