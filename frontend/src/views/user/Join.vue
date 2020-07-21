<template>
  <div class="JoinView">
    <div class="progress-container">
      <ul class="progressbar">
        <li class="active">이메일 입력</li>
        <li class="active">Step2</li>
        <li>Step3</li>
      </ul>
    </div>

    <div id="step1">
      <h5 class="mb-4">
      가입할 이메일 주소를 아래에 입력해주세요.
      </h5>
      <div class="form-wrap">
        <div class="input-with-label">
          <input v-model="email" id="email" placeholder="이메일을 입력하세요." type="text" />
          <label for="email">이메일</label>
        </div>
      </div>
      <button class="btn-input" @click="next()">입력</button>
    </div>

    <div id="step2">
      <h5 class="mb-4">
      가입할 이메일 주소를 아래에 입력해주세요.
      </h5>
      <div class="form-wrap">
        <div class="input-with-label">
          <input v-model="email" id="email" placeholder="이메일을 입력하세요." type="text" />
          <label for="email">이메일</label>
        </div>
      </div>
      <button class="btn-input" @click="next()">입력</button>
    </div>

    <div id="step3">
      <h5 class="mb-4">
      가입할 이메일 주소를 아래에 입력해주세요.
      </h5>
      <div class="form-wrap">
        <div class="input-with-label">
          <input v-model="email" id="email" placeholder="이메일을 입력하세요." type="text" />
          <label for="email">이메일</label>
        </div>
      </div>
      <button class="btn-input" @click="next()">입력</button>
    </div>

  </div>
</template>

<script>
export default {
  name: 'JoinView',
  methods:{
    next(){
      this.$router.push('/')
    }
  }
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