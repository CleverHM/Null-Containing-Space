<template>
  <div id="teamIn">
    <div class="team-spec">
        <specs :teamData="teamData"/>
    </div>
    <div class="submit-area d-flex justify-content-center">
      <button class="submit-button" @click="teamFind">팀원 매칭하기</button>
    </div>
  </div>
</template>

<script>
import specs from './Specification.vue'

export default {
  name: "teamIn",

  components: {
    specs,
  },

  props: ['teamData'],

  methods: {
    // 팀원찾기 페이지로 넘어가기
    teamFind() {
      console.log('매칭하기')
    },


  },

}
</script>

<style scoped>

.submit-button {
    margin: 10px 0px 10px 0px;
    padding: 10px;
    color: white;
    background-color: #ACCCC4;
    border-radius: 10px;
}
</style>