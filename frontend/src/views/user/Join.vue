<template>
  <div id="JoinView">
      <div class="progress-container">
        <ul class="progressbar">
          <button id="Step1" :disable="!isActiveStep1" :class="{'active':isActiveStep1}">이메일 입력</button>
          <button id="Step2" :disable="!isActiveStep2" :class="{'active':isActiveStep2}">이메일 인증</button>
          <button id="Step3" :disable="!isActiveStep3" :class="{'active':isActiveStep3}">회원가입 폼 작성</button>
        </ul>
      </div>

      <div v-if="isActiveStep2">
        <div v-if="isActiveStep3">
          <Join3 @ConfirmJoin="Join" :user="user"></Join3>
        </div>
        <div v-else>
          <Join2 @ConfirmCode="Gostep3" @Resend="resend" :email="user.email" :ErrorMessage="ErrorMsg.auth"></Join2>
        </div>
      </div>

      <div v-else>
        <Join1 @ConfirmEmail="Gostep2" :ErrorMessage="ErrorMsg.email"></Join1>
      </div>

  </div>
</template>

<script>
import Join1 from '../../components/user/step1.vue'
import Join2 from '../../components/user/step2.vue'
import Join3 from '../../components/user/joinform.vue'
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
  methods:{
    Gostep2(email){
      this.user.email = email
      console.log(this.user.email, typeof(this.user.email))
      console.log(this.user, typeof(this.user))
      http
      .post('/auth/loginMailSend', 
        this.user.email,
      )
      .then((data) => {
        this.isActiveStep2 = true
      })  
      .catch((err) => {
        this.ErrorMsg.email = "이미 존재하는 이메일입니다."
      })
    },
    resend(email) {
      console.log(email)
      http
      .post('/auth/loginMailSend', 
        this.user.email,
      )
      .then((data) => {
        this.isActiveStep2 = true
        console.log("complete")
      })  
      .catch((err) => {
        this.ErrorMsg.email = "이미 존재하는 이메일입니다."
      })
    },
    Gostep3(authNum) {
      console.log(this.user.email)
      console.log(authNum)
      http
      .post('/auth/loginMailConfirm', 
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
        ability: [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
        age : this.user.age,
        email : this.user.email,
        gender : this.user.gender,
        name: this.user.name,
        nickname : this.user.nickname,
        password : this.user.password,
        tel : this.user.tel
      })
      .then(({data}) => {
        alert("회원가입 완료!");
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
#JoinView{
  width: 100vw;
}
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
  width: 33.3333%;
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

  border: 2px solid #E2DFD8;
  color: #E2DFD8;
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
  background: #E2DFD8;
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
border-color: #ACCCC4;
background: #ACCCC4;
color: white
}
.progressbar button.active:after{
 background: #ACCCC4;
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