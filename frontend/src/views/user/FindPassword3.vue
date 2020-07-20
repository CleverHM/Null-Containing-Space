<template>
  <div class="user join wrapC">
    <h5 class="mb-4">
    임시 비밀번호가 발급되었습니다.
    <br />로그인 후 회원정보 수정에서 비밀번호를 변경해 주세요.
    </h5>

    <div class="form-wrap">
      <p>임시 비밀번호</p>
      <p>{{ tempPassword }}</p>
    </div>

    <button class="btn-bottom" @click="moveLogin">
    로그인 하러 가기
    </button>
  </div>
</template>

<script>
export default {

  data: () => {
      return {
          // 일단 password로 해놓고 나중에 알고리즘으로 임시비밀번호 발급하기
          tempPassword : "password",
      }

  },
  methods:{
   moveLogin() {
       this.$router.push("/")
   }
  }
};
</script>


<style scoped>
</style>
