<template>
    <div id="css-slider">
        <div v-if="isLoading"></div>
        <section class="slide slide-four" v-else>
          <h5>당신의 개발능력을 수정해주세요.</h5>
          <table>
            <tr class="tableHeader">
              <th> 개발 능력 </th>
              <th>상</th>
              <th>중</th>
              <th>하</th>
            </tr>
            <ability :abilityLevel="modifyAbility[n-1]" :abilityName="abilities[n-1]" v-for="n in 15" :key="n-1" @getAbility="saveAbility"></ability>
          </table>
          
          <button id="btn-join" @click="Modify">수정</button>
        </section>

    </div>
</template>

<script>
import ability from '@/components/user/checkAbility.vue'
import http from "@/util/http-common.js"


export default {
    name: 'modifyAbility',
    components: {
        ability,
    },
    props: [
      'nickname'
    ],
    created() {
      var InputData = new FormData()
      console.log(this.nickname)
      InputData.append("nickname", this.nickname)
      http.post("/account/abilityInfo", InputData)
      .then(({data}) => {
        this.modifyAbility = data
        console.log(this.modifyAbility)
      })
      this.Loading();
      
      
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
            modifyAbility: [2, 2, 2, 2, 2,
                            2, 2, 2, 2, 2,
                            2, 2, 2, 2, 2],
            isLoading: true,
        }

    },
    methods: {
      Loading() {
            if (this.isLoading) {
                setTimeout(this.delayfinish, 100);
            }
      },
        // 딜레이 화면
      delayfinish(){
            this.isLoading = false;
      },
      saveAbility(name, level){
        for (var i=0; i<15; i++){
          var ability = this.abilities[i]
          if (ability === name){
            this.modifyAbility[i] = level
          }
        }
      },
      Modify() {
        var InputData = new FormData()
        InputData.append("nickname", this.nickname)
        InputData.append("ability", this.modifyAbility)
        http.post("/account/abilityModify", InputData)
        .then(({data}) => {
          this.$router.push({ name: 'profile', params: { nickname: this.nickname }});
        })

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