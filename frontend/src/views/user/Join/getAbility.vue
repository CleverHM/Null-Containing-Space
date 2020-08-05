<template>
  <div class="css-slider">
      <input id="slide-1" type="radio" name="slides" checked>
      <section class="slide slide-one">
        <Join1></Join1>
        <nav>
          <label for="slide-2">입력</label>
        </nav>
      </section>

      <input id="slide-2" type="radio" name="slides">
      <section class="slide slide-two">
        <Join2></Join2>
        <nav>
          <label for="slide-3">입력</label>
        </nav>
      </section>

      <input id="slide-3" type="radio" name="slides">
      <section class="slide slide-three">
        <Join3 id="Element3" :User="User" />
        <nav>
          <label for="slide-4">입력</label>
        </nav>
      </section>

      <input id="slide-4" type="radio" name="slides">
      <section class="slide slide-four">
        <nav>
        <label for="slide-5">입력</label>
        </nav>
      </section>

      <input id="slide-5" type="radio" name="slides">
      <section class="slide slide-five">
        <h3>hello</h3>
        <nav>
        <label for="slide-1">입력</label>
        </nav>
      </section>

      <header>
        <label for="slide-1" id="slide-1" @click="gostep1"></label>
        <label for="slide-2" id="slide-2" @click="gostep2"></label>
        <label for="slide-3" id="slide-3" @click="gostep3"></label>
        <label for="slide-4" id="slide-4" @click="gostep4"></label>
        <label for="slide-5" id="slide-5" @click="gostep5"></label>
      </header>

  </div>  
</template>

<script>
import Join1 from '@/components/user/step1.vue'
import Join2 from '@/components/user/step2.vue'
import Join3 from '@/components/user/joinform.vue'
import http from "@/util/http-common.js"



export default {
  name: "Join",
  components: {
    Join1,
    Join2,
    Join3,
  },
  data() {
    console.log(this.$refs)
    return {
        User : {
        email: "",
        name: "",
        nickname: "",
        password: "",
        tel: "",
        gender: true,
        age: null,
      },
    }
  },

}
</script>

<style scoped>
.css-slider{
  width: 100vw;
  height: 100%;
  position: absolute;
  top: 0;
  /* overflow: hidden; */
  background: #f7f7f7;
  color: #464545;
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
  top:0;
  left: 100%;
  z-index: 10;
  padding: 8em 0px;
  background-color: #f7f7f7;
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

nav{
  position: fixed;
  bottom: 100px;
  z-index: 900;
  width: 100%;
}
nav label {
  font-size: 18px;
  color: #f7f7f7;
  background-color: #464545;
  width: 30%;
  padding: 10px 0;
}
.slide nav { opacity: 0; }
[id^="slide"]:checked + .slide nav{
  opacity: 1;
  -webkit-transition: all .5s .5s;
  transition: all .5s .5s;
}
header {
  position: absolute;
  top: 0;
  left: 0;
  z-index: 900;
  width: 100%;
}
header label {
  width: 15px;
  height: 15px;
  border-radius: 100%;
  background-color: #E2DFD8;
  margin: 20px 10px;
}
header label:hover {
  background: #464545;
}
input#slide-1:checked~header label#slide-1,
input#slide-2:checked~header label#slide-2,
input#slide-3:checked~header label#slide-3,
input#slide-4:checked~header label#slide-4,
input#slide-5:checked~header label#slide-5{
  background-color: #464545;
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