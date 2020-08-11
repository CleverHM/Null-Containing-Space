<template>
    <div id="css-slider">
        <section class="slide slide-four">
          <h5>당신의 개발능력을 평가해주세요.</h5>
          <table>
            <tr class="tableHeader">
              <th> 개발 능력 </th>
              <th>상</th>
              <th>중</th>
              <th>하</th>
            </tr>
            <Join4 :abilityLevel="2" :abilityName="ability" v-for="ability in abilities" :key="ability" @getAbility="saveAbility"></Join4>
          </table>
          
          <button id="btn-join" @click="Join">입력</button>
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
import http from "@/util/http-common.js"


export default {
    name: 'step4',
    components: {
        Join4,
    },
    props: {
      User: {
        type: Object,
      }
    },
    data() {
        return {
            abilities: [
                'cpp',
                'Java',
                'Python',
                'php',
                'html',
                'css',
                'JavaScript',
                'SQL',
                'noSQL',
                'Spring',
                'Django',
                'BootStrap',
                'Vue',
                'React',
                'Algorithm',
            ],
        }

    },
    methods: {
      saveAbility(name, level){
        for (var i in this.abilities){
          var ability = this.abilities[i]
          if (ability.name === name){
            this.User.ability[i] = level
          }
        }
      },
      Join() {
        console.log(this.User)
        http.post("/account/signup", this.User)
        .then(({data}) => {
          this.$router.push({name: 'step5'}) 
        })

      },
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
#css-slider{
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
.slide h5 {
  text-align: center;
  margin-bottom: 25px;
}
header {
  text-align: center;
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
table {
  padding-bottom: 50px;
  display: inline-block;
  font-size: 19px;
}
tr {
  text-align: left;
  border-bottom: 1px soild #464545;
}
th, td {
  padding: 20px;
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