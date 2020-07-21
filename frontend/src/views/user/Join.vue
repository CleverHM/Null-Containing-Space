<template>
  <div class="JoinView">
    <div class="progress-container">
      <ul class="progressbar">
        <li id="Step1" class="active">이메일 입력</li>
        <li id="Step2" :class="{'active':isActiveStep2}">이메일 인증</li>
        <li id="Step3" :class="{'active':isActiveStep3}">회원가입 폼 작성</li>
      </ul>
    </div>
    <div v-if="isActiveStep2">

      <div v-if="isActiveStep3">
        <Join3></Join3>
      </div>
      <div v-else>
        <Join2 @ConfirmCode="Gostep3"></Join2>
      </div>
    </div>
    <div v-else>
      <Join1 @ConfirmEmail="Gostep2"></Join1>
    </div>

  </div>
</template>

<script>
import Join1 from '../../components/user/join1.vue'
import Join2 from '../../components/user/join2.vue'
import Join3 from '../../components/user/join3.vue'
export default {
  name: 'JoinView',
  components: {
    Join1,
    Join2, 
    Join3,
  },
  data: () => {
    return {
      isActiveStep2 : false,
      isActiveStep3 : false,
    };
  },
  methods:{
    Gostep2(){
      this.isActiveStep2 = true;
    },
    Gostep3() {
      this.isActiveStep3 = true;
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
.progressbar li{
  float: left;
  width: 33.333%;
  position: relative;
  text-align: center;
}
.progressbar li:before{
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
.progressbar li:after{
  content: '';
  position: absolute;
  width:100%;
  height: 3px;
  background: #ACCCC4;
  top: 15px;
  left: -50%;
  z-index: -1;
}
.progressbar li:first-child:after{
content: none;
}
.progressbar li.active:before{
border-color: #3aac5d;
background: #3aac5d;
color: white
}
.progressbar li.active:after{
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