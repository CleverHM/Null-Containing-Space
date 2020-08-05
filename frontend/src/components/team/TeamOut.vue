<template>
  <div>
    <div class="part-teamOut">
        <div class="out-main d-flex flex-column justify-content-center align-items-center">
            <div>
                <b-icon-file-earmark-text 
                    font-scale="3"/>
            </div>
            <div>
                진행중인 프로젝트가 없습니다
            </div>
        </div>
        <div class="button-part d-flex justify-content-around">
            <button class="teamCreate" @click="moveLeader">팀장으로 시작</button>
            <button class="teamSignup" @click="moveMember">팀원으로 시작</button>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "teamOut",
  methods: {

      moveLeader() {
        this.$router.push({ name: 'LeaderForm'});
      },
      
      moveMember() {
        this.$router.push({ name: 'MemberForm'});
      },

  }

}
</script>

<style scoped>

.part-teamOut {
    margin: 60px 10px 30px 10px;
}

.out-main {
    height: 400px;
}
.out-main > div {
    margin-top: 10px;
    margin-bottom: 10px;
}

.button-part > button {
    padding: 10px;
    color: white;
    border-radius: 5px;
}

.teamCreate {
    background-color: #ACCCC4;
}

.teamSignup {
    background-color: #C4BCB8;
}

</style>