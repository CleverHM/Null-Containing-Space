<template>
    <div id="css-slider">
        <section class="slide slide-four">
          <h5>당신의 개발능력을 수정해주세요.</h5>
          <table>
            <tr class="tableHeader">
              <th> 개발 능력 </th>
              <th>상</th>
              <th>중</th>
              <th>하</th>
            </tr>
            <Join4 :ability="ability" v-for="ability in abilities" :key="ability.id" @getAbility="saveAbility"></Join4>
          </table>
          
          <button id="btn-join" @click="Join">입력</button>
        </section>

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
                { name: 'cpp',
                    id: 0},
                { name: 'Java',
                    id: 1},
                { name: 'Python',
                    id: 2},
                { name: 'php',
                    id: 3},
                { name: 'html',
                    id: 4},
                { name: 'css',
                    id: 5},
                { name: 'JavaScript',
                    id: 6},
                { name: 'SQL',
                    id: 7},
                { name: 'noSQL',
                    id: 8},
                { name: 'Spring',
                    id: 9},
                { name: 'Django',
                    id: 10},
                { name: 'BootStrap',
                    id: 11},
                { name: 'Vue',
                    id: 12},
                { name: 'React',
                    id: 13},
                { name: 'Algorithm',
                    id: 14},
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
  padding: 2em 0px;
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