<template>
    <div class="fixed-top">
        <div class="Navi">
            <p>{{ currentRouteName }}</p>
            <div>
                <b-icon-envelope-open v-if="currentRouteName === 'FeedMain'" class="envelop-open-icon icons" scale="1.3"></b-icon-envelope-open>
                <b-icon-arrow-left @click="goBack" v-if="currentRouteName != 'FeedMain'" class="arrow-left-icon icons" scale="1.3"></b-icon-arrow-left>
                <b-icon-search class="icons search-icon mx-4" scale="1.3"></b-icon-search>
                <b-icon-person-fill v-if="currentRouteName != 'profile'" class="icons person-icon" scale="1.3" @click="goProfile"></b-icon-person-fill>

                <b-icon-list v-if="currentRouteName === 'profile'" @click='toggleShow' class='list-icon'></b-icon-list>
                    <div v-if='showMenu' class='menu'>
                        <div class='menu-item'>
                            <li class="menu-user m-3">알골마스터</li>
                            <hr>
                            <li><b-icon-question-circle-fill scale="1.1" class="mr-2"/>QnA</li>
                            <li><b-icon-bookmarks-fill scale="1.1" class="mr-2"/>좋아요 글</li>
                            <li><b-icon-person-bounding-box scale="1.1" class="mr-2"/>회원 등급</li>
                            <hr>
                            <li><b-icon-box-arrow-right scale="1.1" class="mr-2"/>로그아웃</li>
                            <li><b-icon-pencil scale="1.1" class="mr-2"/>회원정보 수정</li>
                        </div>
                    </div>
                <!-- <b-icon-question-circle-fill v-if="currentRouteName === 'profile'" class="icons question-circle-icon" scale="1.3"></b-icon-question-circle-fill> -->
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

  data() {
      return {
          showMenu: false
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
      goPageDropdown() {
          this.$router.push("/page")
      },

      
      toggleShow: function() {
          this.showMenu = !this.showMenu;
      },

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
.list-icon{
    position: fixed;
    right:20px;
    top:0;
    height:50px;
    color: #464545;
}
.menu {
    float: right;
    width: 250px;
    margin-top: 1px;
    background-color: #f7f7f7;
    border-left: 1px solid #dfdede;
    border-bottom: 1px solid #dfdede;
    color:#464545;
}
.menu-user {
    font-weight: bold;
}
.menu-item > li {
    margin: 25px 20px 25px 20px;
    font-family: Arial, Helvetica, sans-serif;
}
.question-circle-icon{
    position: fixed;
    right:20px;
    top:0;
    height:50px;
    color: #464545;
}
</style>