<template>
    <div class="fixed-top">
        <div class="Navi">
            <p>{{ currentRouteName }}</p>
            <div>
                <b-icon-envelope-open v-if="currentRouteName === 'FeedMain'" class="envelop-open-icon icons" scale="1.3"></b-icon-envelope-open>
                <b-icon-arrow-left @click="goBack" v-if="currentRouteName != 'FeedMain'" class="arrow-left-icon icons" scale="1.3"></b-icon-arrow-left>
                <b-icon-search class="icons search-icon mx-4" scale="1.3"></b-icon-search>
                <b-icon-person-fill v-if="currentRouteName != 'profile'" class="icons person-icon" scale="1.3" @click="goProfile"></b-icon-person-fill>
                <b-icon-question-circle-fill v-if="currentRouteName === 'profile'" class="icons question-circle-icon" scale="1.3"></b-icon-question-circle-fill>
            </div>
        </div>

    </div>
</template>

<script>
export default {
  name:"NavBar",
  computed: {
      currentRouteName() {
        return this.$route.name;
      }
  },
  methods: {
      goBack(){
          console.log("hello")
          this.$router.go(-1)
      },
      goProfile(){
          this.$router.push("/user/profile")
      },
      goSearch(){

      },
      goNotice() {

      },
  }
};
</script>

<style scoped>
.Navi{
    background-color: #ACCCC4;
    height: 50px;

}
.Navi p{
    margin: 0;
    line-height:50px;
    text-align : center;
    font-weight: bold;
    font-size: 1.11em;
}
.icons{
    cursor: pointer;
}
.envelop-open-icon, .envelop-close-icon{
    position: fixed;
    left:20px;
    top:0;
    height:50px;
}
.arrow-left-icon{
    position: fixed;
    left:20px;
    top:0;
    height:50px;
    color: #464545;
}
.search-icon{
    position: fixed;
    right:35px;
    top:0;
    height:50px;
    color: #464545;
}
.person-icon{
    position: fixed;
    right:20px;
    top:0;
    height:50px;
    color: #464545;
}
.question-circle-icon{
    position: fixed;
    right:20px;
    top:0;
    height:50px;
    color: #464545;
}
</style>