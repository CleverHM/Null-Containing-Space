<template>
    <div class="css-slider">
        <section class="slide slide-four">
          <Join4 :ability="ability" v-for="ability in abilities" :key="ability.id"></Join4>
          <button id="btn-join">입력</button>
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
import Join4 from '@/components/user/checkAbility.vue'

export default {
    name: 'step4',
    components: {
        Join4,
    },
    data() {
        return {
            abilities: [
                { name: 'html',
                  id: '1',
                  score: 2},
                { name: 'css',
                  id: '2',
                  score: 2},
                { name: 'JavaScript',
                  id: '3' ,
                  score: 2},
                { name: 'cpp',
                  id: '4',
                  score: 2 },
                { name: 'java',
                  id: '5' ,
                  score: 2},
                { name: 'Python',
                    id: '6' ,
                    score: 2},
                { name: 'php',
                    id: '7' ,
                    score: 2},
                { name: 'sql',
                    id: '8' ,
                    score: 2},
                { name: 'nosql',
                    id: '9' ,
                    score: 2},
                { name: 'spring',
                    id: '10',
                    score: 2 },
                { name: 'django',
                    id: '11',
                    score: 2 },
                { name: 'bootstrap',
                    id: '12',
                    score: 2 },
                { name: 'vue',
                    id: '13',
                    score: 2 },
                { name: 'react',
                    id: '14',
                    score: 2 },
            ],
        }

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
header div#slide-4{
  background-color: #464545;
}
#btn-join{
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