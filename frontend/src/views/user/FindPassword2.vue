<template>
  <div class="user join wrapC">
    <h4>비밀번호 찾기</h4>
    <h5 class="mb-4">
    가입시 선택한 질문의 답을 입력해주세요.
    </h5>

    <div class="form-wrap">
      <!-- 비밀번호 찾기 !-->
      <div class="components-page">
        <SelectComponent :options="options" />
      </div>

      <div class="input-with-label">
        <input v-model="passwordAnswer" id="password" type="text" placeholder="비밀번호 질문의 답을 입력하세요." />
        <label for="passwordAnswer">비밀번호 질문</label>
      </div>
    </div>

    <button class="btn-bottom" @click="findPassword">
    비밀번호 찾기
    </button>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
import SelectComponent from "../../components/user/snsLogin/Select";
import "../../assets/css/components.scss";

export default {
  components: {
      SelectComponent,
  },
  data: () => {
    return {
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
   // 비밀번호 질문과 답변이 일치하는지 확인하고 임시비밀번호 발급 페이지로 이동
   findPassword() {
       this.moveTempPassword()
   },
   moveTempPassword() {
       this.$router.push("/find/password")
   }
  }
};
</script>


<style scoped>
</style>
