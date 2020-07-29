<template>
  <div class="user" id="login">
    <div class="wrapC">
      <Logo :component="component" />

      <div class="input-with-label">
        <input
          v-model="email"
          v-bind:class="{error : error.email, complete:!error.email&&email.length!==0}"
          @keyup="checkEmailForm"
          @keyup.enter="onLogin"
          id="email"
          placeholder="이메일을 입력하세요."
          type="text"
        />
        <label for="email">이메일</label>
        <div class="error-text" v-if="error.email">{{error.email}}</div>
      </div>

      <div class="input-with-label">
        <input
          v-model="password"
          type="password"
          v-bind:class="{error : error.password, complete:!error.password&&password.length!==0}"
          id="password"
          @keyup="checkPasswordForm"
          @keyup.enter="onLogin"
          placeholder="비밀번호를 입력하세요."
        />
        <label for="password">비밀번호</label>
        <div class="error-text" v-if="error.password">{{error.password}}</div>
      </div>
      <button
        class="btn btn--back btn--login"
        @click="onLogin"
        :disabled="!isSubmit"
        :class="{disabled : !isSubmit}"
      >로그인</button>

      <div class="sns-login">
        <div class="text">
          <p>SNS 간편 로그인</p>
          <div class="bar"></div>
        </div>

        <kakaoLogin :component="component" />
        <GoogleLogin :component="component" />
      </div>
      <div class="add-option">
        <div class="text">
          <p>혹시</p>
          <div class="bar"></div>
        </div>
        <div class="wrap">
          <p>비밀번호를 잊으셨나요?</p>
          <router-link to="/find/password" class="btn--text">비밀번호 찾기</router-link>
        </div>
        <div class="wrap">
          <p>아직 회원이 아니신가요?</p>
          <router-link to="/user/join" class="btn--text">가입하기</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "../../components/css/user.scss";
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import Logo from "../../components/user/Logo.vue";
import KakaoLogin from "../../components/user/snsLogin/Kakao.vue";
import GoogleLogin from "../../components/user/snsLogin/Google.vue";
import UserApi from "../../api/UserApi";
import http from "../../util/http-common.js";

const storage = window.sessionStorage;

export default {
  components: {
    Logo,
    KakaoLogin,
    GoogleLogin
  },
  data: () => {
    return {
      email: "",
      password: "",
      passwordSchema: new PV(),
      error: {
        email: false,
        password: false
      },
      isSubmit: false,
    };
  },
  created() {
    this.component = this;

    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(100)
      .has()
      .digits()
      .has()
      .letters();
  },
  methods: {
    setInfo(status, token, info) {
      this.status = status
      this.token = token
      this.info = info
    },
    checkEmailForm() {
      if (this.email.length >= 0 && !EmailValidator.validate(this.email)){
        this.error.email = "이메일 형식이 아닙니다.";
      } else {
        this.error.email = ""
      }
        
    },
    checkPasswordForm() {
       if (
        this.password.length >= 0 &&
        !this.passwordSchema.validate(this.password)
      ){
        this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
      } else {
        this.error.password = ""
      }
        
      
      this.isSubmit = true;
      Object.values(this.error).map(v => {
        if (v) this.isSubmit = false;
      });
    },

    // 로그인 요청 - 토큰 받아서 local storage에 넣기
    onLogin() {
      if (this.isSubmit) {

        storage.setItem("token", "");
        storage.setItem("User", "");
        
        let msg = "";
        
        http
        .post("/account/login", {
          email : this.email,
          password : this.password,
        },
        )
        .then((res) => {
          console.log(res)
          if(res.data.status) {
            console.log(res.data.email);
            msg = "로그인되었습니다.";
            // storage에 받아온 데이터 집어넣기
            storage.setItem("token", res.data.token)
            console.log(storage)
            var User = {
              "email" : res.data.email,
              "nickname" : res.data.nickname,
            }
            console.log(User, typeof(User))
            storage.setItem("User", res.data.email)
          }
          alert(msg);
          this.moveFeed();
        })
        .catch((err) => {
          console.log(err)
          this.error.password = "로그인 정보가 일치하지 않습니다. 다시 입력하세요.";
        })
        
    
    }
  },

  moveFeed(){
        this.$router.push("/feed");
  },
},
 
};
</script>


