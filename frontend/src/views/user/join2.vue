<template>
  <div class="user join wrapC">
    <h4>가입하기</h4>
    <h5 class="mb-4">
    이메일로 받은 인증코드를 아래에 입력하세요.
    </h5>


    <div class="form-wrap">
      <div class="input-with-label">
        <input v-model="email" id="email" placeholder="인증코드를 입력하세요." type="text" />
        <label for="email">인증코드</label>
      </div>
    </div>

    <button class="btn-bottom" @click="moveJoin">
    입력
    </button>
  </div>
</template>

<script>
import http from "@/util/http-common.js";

export default {

  data: () => {
      return {
          // 일단 password로 해놓고 나중에 알고리즘으로 임시비밀번호 발급하기
          tempPassword : "password",
      }

  },
  methods:{
   moveJoin() {
       this.$router.push("/user/join")
   }
  }
};
</script>


<style scoped>
</style>
