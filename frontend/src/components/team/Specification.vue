<template>
  <div id="specification">
    <div class="spec-area">
      <div class="team-title d-flex justify-content-center">
        {{ teamData.title }}
      </div>

      <!-- 프로젝트 기술 -->
      <div class="team-use">
          프로젝트 사용 기술

        <div v-for="n in 4" :key="n-1" :id="n + '/'" class="mx-1 mb-2">
          <div class="displaytags">
              {{ basic.techName[n-1] }}
          </div>
          <div class="button-area">
              <div v-for="m in basic.techLen[n-1]"
                  :key="basic.techTrue[n-1][m-1]"
                  :id="m + ','"
                  class="checkOn">
                  {{ basic.techTrue[n-1][m-1] }}
              </div>
          </div>
        </div>

        
        <div class="page-content" v-html="teamContent" style="font-weight: lighter;">
        </div>
      
      </div>


      <div class="team-member-area d-flex flex-row align-items-center">
        <memberImg :memberData="teamData.leaderNickname" :isLeader="true" class="mx-2"></memberImg>
        <memberImg v-for="mem in teamData.members" :key="mem.nickname" :memberData="mem" :isLeader="false" class="ml-2"></memberImg>
      </div>


    </div>
  </div>
</template>

<script>
import memberImg from './memberImg.vue'

export default {
    name: "specification",
    props: ['teamData'],
    components: {
      memberImg,
    },

    data() {
      return {
        basic: {
          techs: [
            'cpp', 'java', 'python', 'php',
            'html', 'css', 'javascript',
            'sql', 'nosql', 
            'spring', 'django', 'bootstrap', 'vue', 'react', 
            'algo',
          ],
          techDiv: [
            3, 6, 8, 13, 14
          ],
          techTrue: [
            [], [], [], [], []
          ],
          techLen: [],
          techName: [
            'Backend', 'Frontend', 'DataBase', 'Fremework',
          ]
        },
        teamContent: '',
      }
    },

    created() {
      var c = 0;
      var checkLen = 0;

      // 각 영역마다 true인 값 넣어주기
      for (let i = 0; i < this.basic.techs.length; i++) {
        if (this.teamData.preTech[i]) {
          checkLen = checkLen + 1;
          this.basic.techTrue[c].push(this.basic.techs[i]);
        }
        if (i === this.basic.techDiv[c]) {
          this.basic.techLen.push(checkLen)
          c = c + 1;
          checkLen = 0;
        }
      }
      // 프로젝트 요약 부분 줄바꿈 적용
      this.teamContent = this.teamData.intro.replace(/(?:\r\n|\r|\n)/g, '<br/>');
    
    },

    methods: {

    },
}
</script>

<style scoped>
.team-title {
  padding: 0px 10px 5px 10px;
  color: #464545;
  font-family:Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
  font-size: 25px;
  font-weight: bold;
}


.team-use {
  margin: 20px 10px 10px 10px;
  color: #464545;
  font-size: 15px;
  font-family: Arial, Helvetica, sans-serif;
  font-weight: bold;
}

.displaytags {
  width: 30%;
  padding: 10px;
  font-size: 14px;
  font-weight: bold;
  color: #464545;
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}

.button-area {
  width: 98%;
  padding: 0px 10px 5px 10px;
  margin-bottom: 5px;
}

.checkOn {
  display: inline-block;
  margin: 5px;
  padding: 0px 5px 5px 5px;
  border-radius: 10px;
  font-size: 13px;
  font-weight: bold;
  color: white;
  background-color: #ACCCC4;
}

.team-member-area {
  display: inline;
}


</style>