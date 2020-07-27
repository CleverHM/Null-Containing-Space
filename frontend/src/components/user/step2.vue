<template>
  <div id="join2">
    <h5 class="mb-4">
    이메일로 받은 인증코드를 아래에 입력하세요.
    </h5>


    <div class="form-wrap">
      <div class="input-with-label">
        <input v-model="childauthNum" id="emailcode" @keyup.enter="confirmCode" placeholder="인증코드를 입력하세요." type="text" />
        <label for="emailcode">인증코드</label>
        <span id="ErrorMsg">{{ ErrorMessage }}</span>
      </div>
    </div>

    <button class="btn-input" @click="confirmCode">
    입력
    </button>
  </div>
</template>

<script>
import http from "@/util/http-common.js";

export default {
  props: {
    ErrorMessage: {
      type: String,
    }
  },
  data: () => {
    return {
      childauthNum: "",
    }
  },
  methods:{
   confirmCode() {
     this.$emit("ConfirmCode", this.childauthNum)
    }
  }
};
</script>


<style scoped>
#join2{
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
#ErrorMsg{
  color : #D91120;
}
</style>
