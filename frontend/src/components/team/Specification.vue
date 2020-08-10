<template>
  <div id="specification">
    <div class="specification-area">
      <!-- 프로젝트 기술 -->
      <div class="team-use">

        <div class="subject-area d-flex justify-content-center">
          {{ subjectCheck }}
        </div>
        <div class="specs-area">
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
        </div>

        <div style="border-bottom: 1px solid #464545;" class="mb-3"></div>

        <div class="content-area">
          프로젝트 요약
          <div class="page-content" v-html="teamContent" style="font-weight: lighter;">
          </div>
        </div>
      
      </div>

    </div>
  </div>
</template>

<script>

export default {
    name: "specification",
    props: ['teamData'],

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
          subjects: [
              '웹 기술 프로젝트',
              '웹 디자인 프로젝트',
              'IOT 프로젝트',
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
        subjectCheck: '',
      }
    },

    created() {
      var c = 0;
      var checkLen = 0;

      var num = Number(this.teamData.preferProject) - 1
      this.subjectCheck = this.basic.subjects[num]

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

.team-use {
  margin: 20px 10px 10px 10px;
  color: #464545;
  font-size: 15px;
  font-family: Arial, Helvetica, sans-serif;
  font-weight: bold;
}

.subject-area {
  border-bottom: 1px solid #464545;
  padding: 10px;
  font-size: 14px;
  font-weight: bold;
  color: #464545;
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}

.displaytags {
  display: inline-block;
  width: 25%;
  padding: 10px;
  font-size: 14px;
  font-weight: bold;
  color: #464545;
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}

.specs-area {
  margin: 20px 0px 5px 0px;
  overflow:auto;
}

.button-area {
  float: right;
  display: inline;
  width: 70%;
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

.content-area {
  padding: 10px;
  font-size: 14px;
  font-weight: bold;
  color: #464545;
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}

.page-content {
  margin: 5px;
  padding: 7px;
  border: 1px solid #E2DFD8;
  border-radius: 10px;
  word-wrap: wrap;
  word-break: break-all;
}


</style>