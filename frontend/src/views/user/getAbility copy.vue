<template>
  <div class="css-slider">
    <input id="slide-1" type="radio" name="slides" checked>

    <section class="slide slide-one">
      <Join1 @ConfirmEmail="Gostep2" :ErrorMessage="ErrorMsg.email"></Join1>
      <nav>
        <!-- <label for="slide-3" class="prev">&#10094;</label> -->
        <label for="slide-2" class="next">다음 &#10095;</label>
      </nav>
    </section>

    <input id="slide-2" type="radio" name="slides">
    <section class="slide slide-two">
          <Join2 @ConfirmCode="Gostep3" @Resend="resend" :email="user.email" :ErrorMessage="ErrorMsg.auth"></Join2>
      <nav>
        <label for="slide-1" class="prev">&#10094; 이전</label>
        <label for="slide-3" class="next">다음 &#10095;</label>
      </nav>
    </section>

    <input id="slide-3" type="radio" name="slides">
    <section class="slide slide-three">
      <Join3 @ConfirmJoin="Join" :user="user"></Join3>
      <nav>
        <label for="slide-2" class="prev">&#10094; 이전</label>
        <label for="slide-4" class="next">다음 &#10095;</label>
      </nav>
    </section>

    <input id="slide-4" type="radio" name="slides">
    <section class="slide slide-four">
      <h3>아래의 언어들을 평가해주세요.</h3>
      <checkAbility :ability="ability" v-for="ability in abilities" :key="ability.id"></checkAbility>
      <nav>
        <label for="slide-3" class="prev">&#10094; 이전</label>
        <label for="slide-5" class="next">다음 &#10095;</label>
      </nav>
    </section>

    <input id="slide-5" type="radio" name="slides">
    <section class="slide slide-four">
      <h3>감사합니다. <br>
      </h3>
      <nav>
        <label for="slide-4" class="prev">&#10094; 이전</label>
        <label for="slide-1" class="next">다음 &#10095;</label>
      </nav>
    </section>

    <header>
      <label for="slide-1" id="slide-1"></label>
      <label for="slide-2" id="slide-2"></label>
      <label for="slide-3" id="slide-3"></label>
      <label for="slide-4" id="slide-4"></label>
      <label for="slide-5" id="slide-5"></label>
    </header>
  </div>
</template>

<script>
import Join1 from '../../components/user/step1.vue'
import Join2 from '../../components/user/step2.vue'
import Join3 from '../../components/user/joinform.vue'
import http from "../../util/http-common.js";
import checkAbility from '../../components/user/checkAbility.vue';
export default {
    name: 'getAbility2',
    components: {
      checkAbility,
      Join1,
      Join2,
      Join3,
    },
     data() {
        return {
            abilities: [
                { name: 'html',
                  id: '1',
                  score: 2},
                { name: 'css',
                  id: '2',
                  score: 2},
                { name: 'JavaScript',
                  id: '3' ,
                  score: 2},
                { name: 'cpp',
                  id: '4',
                  score: 2 },
                { name: 'java',
                  id: '5' ,
                  score: 2},
                { name: 'Python',
                    id: '6' ,
                    score: 2},
                { name: 'php',
                    id: '7' ,
                    score: 2},
                { name: 'sql',
                    id: '8' ,
                    score: 2},
                { name: 'nosql',
                    id: '9' ,
                    score: 2},
                { name: 'spring',
                    id: '10',
                    score: 2 },
                { name: 'django',
                    id: '11',
                    score: 2 },
                { name: 'bootstrap',
                    id: '12',
                    score: 2 },
                { name: 'vue',
                    id: '13',
                    score: 2 },
                { name: 'react',
                    id: '14',
                    score: 2 },
              ],
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
    }
    },
    methods: {
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
    }

}
</script>

<style>
/* 전체 화면 style */
.css-slider {
  height: 100vh;
  width: 100vw;
  position: absolute;
  top: 0;
  overflow: hidden;
  background: #fff;
  color: #000;
  text-align: center;
}
label {
  cursor: pointer;
  display: inline-block;
}

.slide {
  height: 100%;
  width: 100%;
  max-width: 1920px;
  position: absolute;
  top: 0;
  left: 100%;
  z-index: 10;
  padding: 8em 0px;
  background-color: #c6dfd6;
  background-size: cover;
  -webkit-transition: left 0s .75s;
  transition: left 0s .75s;
}
[id^="slide"]:checked + .slide {
  left: 0;
  z-index: 100;
  -webkit-transition: left .65s ease-out;
  transition: left .65s ease-out;
}

header {
  position: absolute;
  top: 0;
  left: 0;
  z-index: 900;
  width: 100%;
}
header label {
  background-color: rgba(255, 255, 255, 0.8);
  border-radius: 50%;
  width: 15px;
  height: 15px;
  margin: 20px 10px;
}
header label:hover { background: #2e353b; }

nav {
  position: fixed;
  bottom: 60px;
  /* margin-top: -42px; */
  z-index: 900;
  width: 100%;
}
nav label {
  font-size: 20px;
  padding: 10px;
  color: #f7f7f7;
  width: 30%;
  background-color: #464545;
  }


.prev {
  position: absolute;
  left: 10px;
}

.next {
  position: absolute;
  right: 10px;
}

.slide .prev, .slide .next { opacity: 0; }
[id^="slide"]:checked + .slide .prev,
 [id^="slide"]:checked + .slide .next {
  opacity: 1;
  -webkit-transition: all .5s .5s;
  transition: all .5s .5s;
}

.slide h3 {
  opacity: 0;
  -webkit-transform: translateY(100%);
  -ms-transform: translateY(100%);
  transform: translateY(100%);
  -webkit-transition: -webkit-transform .5s .5s, opacity .5s;
  transition: transform .5s .5s, opacity .5s;
}

input#slide-1:checked~header label#slide-1,
input#slide-2:checked~header label#slide-2,
input#slide-3:checked~header label#slide-3,
input#slide-4:checked~header label#slide-4,
input#slide-5:checked~header label#slide-5{
  background:#2e353b
  }

[id^="slide"]:checked + .slide h3 {
  opacity: 1;
  -webkit-transform: translateY(0);
  -ms-transform: translateY(0);
  transform: translateY(0);
  -webkit-transition: all .5s .5s;
  transition: all .5s .5s;
}
</style>