<template>
  <div id="join1">
      <h5 class="mb-4" v-if="linkName==='Join'">
      {{ title.join }}
      </h5>
      <h5 class="mb-4" v-if="linkName==='findPassword'">
      {{ title.password }}
      </h5>
      <div class="form-wrap">
        <div class="input-with-label">
          <input @keyup.enter="confirmEmail" v-model="childemail" id="email" placeholder="이메일을 입력하세요." type="text" />
          <label for="email">이메일</label>
          <span id="ErrorMsg">{{ ErrorMessage }}</span>

        </div>
      </div>
      
      <button class="btn-input" @click="confirmEmail">입력</button>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
import "../../assets/css/components.scss";
// 이메일 체크 정규식
var EmailregExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
export default {
  props: {
    ErrorMessage:{
      type:String,
    }
  },
  created() {
    this.linkName = this.$route.name
    console.log(this.linkName)
  },
  data : () => {
    return {
      linkName: null,
      title : {
        join : "가입할 이메일 주소를 아래에 입력해주세요.",
        password : "가입한 이메일 주소를 아래에 입력해주세요.",
      },
      childemail: "",
    }
  },
  methods:{
    // 데이터베이스에 이메일이 있는지 확인하고 다음페이지로 이동시키기
    confirmEmail() {
        if (this.childemail.match(EmailregExp) != null){
          this.$emit("ConfirmEmail", this.childemail)
        }else {
          this.ErrorMessage = "이메일 형식이 올바르지 않습니다. 다시 입력해주세요."
        }
        
    },
  }
};
</script>


<style scoped>
#join1{
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
