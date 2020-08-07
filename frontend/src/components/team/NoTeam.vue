<template>
  <div id="NoTeam">
      <div>
          선호하는 주제: {{ subjectCheck }}
      </div>
      <div v-if="noMatch">
        해당하는 팀이 없습니다.
      </div>
      <TeamUser v-for="team in userData.teamdates" 
        :key="team.leaderNickname.nickname" 
        :teamData="team"/>
  </div>
</template>

<script>
import TeamUser from './TeamUser.vue'

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
          noMatch: false,
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

    },
}
</script>

<style>

</style>