<template>
    <div class="fixed-top">
        <div class="Navi">
            <b-icon-envelope-open v-if="currentRouteName === 'Main'" class="envelope-open-icon" scale="1.3"></b-icon-envelope-open>
            <b-icon-arrow-left v-if="currentRouteName === 'profile'" class="arrow-left-icon" scale="1.3"></b-icon-arrow-left>
            <p>{{ currentRouteName }}</p>
            <div>
            <b-icon-search class="search-icon mx-4" scale="1.3"></b-icon-search>
            <b-icon-person-fill v-if="currentRouteName != 'profile'" class="person-icon" scale="1.3"></b-icon-person-fill>
            <b-icon-question-circle-fill v-if="currentRouteName === 'profile'" class="question-circle-icon" scale="1.3"></b-icon-question-circle-fill>
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
.envelope-open-icon, .envelope-close-icon{
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