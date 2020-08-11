<template>
  <div id="NoTeam">
    <div class="d-flex justify-content-center subjectArea">
        현재 <span class="subjectDesign mx-2">{{ subjectCheck }}</span> 를 선택 중입니다
    </div>

    <div v-if="noMatch" class="teamDes d-flex justify-content-center align-items-center align-content-center flex-column" style="min-height: 400px;">
      <b-icon-file-earmark-break-fill scale="4" class="mb-5" style="color:#464545;"></b-icon-file-earmark-break-fill>
      해당 주제로 프로젝트를 진행 중인 팀이 없습니다
    </div>

    <div v-if="!noMatch">
      <div class="teamDes d-flex justify-content-center">해당 주제로 프로젝트를 진행 중인 팀 목록입니다</div>
      <TeamUser v-for="team in userData.teamdates" 
        :key="team.leaderNickname.nickname" 
        :teamData="team"/>
    </div>

    <button class="closeTeam" @click="closeTeam">팀원 시작 취소</button>
  
  </div>
</template>

<script>
import TeamUser from './TeamUser.vue'
import http from "../../util/http-common.js";
import axios from 'axios';

const storage = window.sessionStorage;

export default {
    name: "NoTeam",
    props: ["userData"],
    components: {
        TeamUser,
    },
    data() {
        return {
          basic: {
              subjects: [
              '웹 기술 프로젝트',
              '웹 디자인 프로젝트',
              'IOT 프로젝트',
            ],
          },
          subjectCheck: '',
          noMatch: true,
        }
    },

    created() {
      var num = Number(this.userData.preferProject) - 1
      this.subjectCheck = this.basic.subjects[num]

      if (this.userData.teamdates.length == 0) {
          this.noMatch = true
      } else {
          this.noMatch = false
      }
    },
    
    methods: {
      closeTeam() {
        let formData = new FormData;
        formData.append("nickname", storage.getItem("NickName"))

        http
        .post('/team/memberCancel', formData)
        .then((res) => {
          this.$router.go({ name: 'Main' }).catch(()=>{})
        })
        .catch((err) => {
          console.log(err)
        })
      },
    },
}
</script>

<style scoped>
#noTeam {
  margin: 60px 10px 50px 10px;
}

.subjectArea {
  border: 2px dashed #ACCCC4;
  padding:10px;
  font-size: 15px;
  color: #C4BCB8;
  margin-top: 20px;
  margin-bottom: 20px;
}

.subjectDesign {
  font-weight: bold;
  color: #464545;
}

.teamDes {
  font-size: 15px;
  margin: 10px;
}

.closeTeam {
  position: fixed;
  bottom: 70px;
  left: 50%;
  transform:translate(-50%, 0%);
  padding: 7px;
  color: white;
  background-color:#C4BCB8;
  border-radius: 10px;
}

</style>