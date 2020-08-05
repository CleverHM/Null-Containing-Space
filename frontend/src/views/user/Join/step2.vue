<template>
    <div class="css-slider">
        <section class="slide slide-one">
            <Join2></Join2>
        </section>

        <header>
            <div for="slide-1" id="slide-1" @click="gostep1"></div>
            <div for="slide-2" id="slide-2" @click="gostep2"></div>
            <div for="slide-3" id="slide-3" @click="gostep3"></div>
            <div for="slide-4" id="slide-4" @click="gostep4"></div>
            <div for="slide-5" id="slide-5" @click="gostep5"></div>
        </header>
    </div>
</template>

<script>
import Join2 from '@/components/user/step2.vue'

export default {
    name: 'step1',
    components: {
        Join2,
    },
    methods: {
    gostep1() {
      this.$router.push({name: 'step1'}).catch(()=>{})
    },
    gostep2() {
      this.$router.push({name: 'step2'}).catch(()=>{})
    },
    gostep3() {
      this.$router.push({name: 'step3'}).catch(()=>{})
    },
    gostep4() {
      this.$router.push({name: 'step4'}).catch(()=>{})
    },
    gostep5() {
      this.$router.push({name: 'step5'}).catch(()=>{})
    },
  },
    
}
</script>

<style scoped>
.css-slider{
  width: 100vw;
  height: 100vh;
  position: absolute;
  top: 0;
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
.slide {
  left: 0;
  z-index: 100;
  -webkit-transition: left .65s ease-out;
  transition: left .65s ease-out;
}

header {
  position: absolute;
  top: 0;
  left: 0;
  z-index: 900;
  width: 100%;
}
header div {
  cursor: pointer !important;
  display: inline-block;
  width: 15px;
  height: 15px;
  border-radius: 100%;
  background-color: #E2DFD8;
  margin: 20px 10px;
}
header div:hover {
  background: #464545;
}
header div#slide-2{
  background-color: #464545;
}
</style>