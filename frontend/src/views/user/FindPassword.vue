<template>
  <div class="JoinView">
    <div class="progress-container">
      <ul class="progressbar">
        <button id="Step1" :disable="!isActiveStep1" :class="{'active':isActiveStep1}">이메일 입력</button>
        <button id="Step2" :disable="!isActiveStep2" :class="{'active':isActiveStep2}">이메일 인증</button>
        <button id="Step3" :disable="!isActiveStep3" :class="{'active':isActiveStep3}">새 비밀번호 입력</button>
      </ul>
    </div>

    <div v-if="isActiveStep2">
      <div v-if="isActiveStep3">
        <Password3 @ConfirmJoin="Join" :user="user"></Password3>
      </div>
      <div v-else>
        <Password2 @ConfirmCode="Gostep3" @Resend="resend" :email="user.email" :ErrorMessage="ErrorMsg.auth"></Password2>
      </div>
    </div>

    <div v-else>
      <Password1 @ConfirmEmail="Gostep2" :ErrorMessage="ErrorMsg.email"></Password1>
    </div>

  </div>
</template>

<script>
import Password1 from '../../components/user/step1.vue'
import Password2 from '../../components/user/step2.vue'
import Password3 from '../../components/user/passwordform.vue'
import http from "../../util/http-common.js";
import axios from 'axios';


export default {
  name: 'FindPassword',
  components: {
    Password1,
    Password2, 
    Password3,
  },
  data: () => {
    return {
      user : {
        email: "",
        name: "",
        nickname: "",
        password: "",
        tel: "",
        gender: true,
        age: null,
      },
      authNum : "",
      isActiveStep1 : true,
      isActiveStep2 : false,
      isActiveStep3 : false,
      ErrorMsg: {
        email: "",
        auth: "",
        password: "",
      },
      passwordType: "password",
      passwordConfirmType: "password",
    };
  },
  created(){
    this.user.email = ""
    this.user.name = ""
    this.user.nickname = ""
    this.user.password = ""
    this.user.tel = ""
    this.user.gender = true
    this.user.age = null
  },
  methods:{
    Gostep2(email){
      this.user.email = email
      console.log(this.user.email, typeof(this.user.email))
      console.log(this.user, typeof(this.user))
      http
      .post('/account/passwordUpdateMailSend', 
        this.user.email,
      )
      .then((data) => {
        console.log(data)
        this.isActiveStep2 = true;
      })
      .catch((err) => {
        this.ErrorMsg.email = "존재하지 않는 이메일입니다."
      })
    },
      resend(email) {
      console.log(email)
      http
      .post('/account/loginMailSend', 
        this.user.email,
      )
      .then((data) => {
        alert("인증번호가 재전송되었습니다.")
      })  
      .catch((err) => {
        console.log(err)
      })
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
        this.ErrorMsg.auth = "인증번호가 일치하지 않습니다. 다시 입력해 주세요."
      })
      
    },
    Join(user){
      this.user = user
      console.log(this.user)
      let msg = "";
      http
      .post("/account/signup", {
        age : this.user.age,
        email : this.user.email,
        gender : this.user.gender,
        name: this.user.name,
        nickname : this.user.nickname,
        password : this.user.password,
        tel : this.user.tel
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
.progressbar button{
  float: left;
  width: 33.333%;
  position: relative;
  text-align: center;
  border: 0;
  outline: 0;
  cursor: default;
}
.progressbar button.active{
  cursor: pointer;
}
.progressbar button:before{
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
.progressbar button:after{
  content: '';
  position: absolute;
  width:100%;
  height: 3px;
  background: #ACCCC4;
  top: 15px;
  left: -50%;
  z-index: -1;
}
.progressbar button:first-child:after{
content: none;
}
button.active{
  cursor : hand;
}
.progressbar button.active:before{
border-color: #3aac5d;
background: #3aac5d;
color: white
}
.progressbar button.active:after{
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