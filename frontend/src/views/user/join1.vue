<template>
  <div class="user join wrapC">
    <h4>가입하기</h4>
    <h5 class="mb-4">
    가입할 이메일 주소를 아래에 입력해주세요.
    </h5>


    <div class="form-wrap">
      <div class="input-with-label">
        <input v-model="email" id="email" placeholder="이메일을 입력하세요." type="text" />
        <label for="email">이메일</label>
      </div>
    </div>

    <button class="btn-bottom" @click="confirmEmail">
    입력
    </button>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
import "../../assets/css/components.scss";

export default {

  data: () => {
    return {
      email: "",
      options: [
        {
          value: "option1",
          title: "가장 좋아하는 프로그래밍 언어는?"
        },
        {
          value: "option2",
          title: "당신의 이름은?"
        }
      ],
    };
  },
  methods:{
    // 데이터베이스에 이메일이 있는지 확인하고 다음페이지로 이동시키기
    confirmEmail() {
        this.moveQuestion()
    },

    moveQuestion(){
      this.$router.push("/user/join/email/confirm");
    }
  }
};
</script>


<style scoped>
</style>
