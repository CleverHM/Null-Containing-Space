<template>
    <div class="fixed-top">
        <div class="Navi">
            <p>{{ currentRouteName }}</p>
            <div>
                <b-icon-arrow-left @click="goBack" v-if="currentRouteName != 'Main'" class="arrow-left-icon icons" scale="1.3"></b-icon-arrow-left>
                <b-icon-search class="icons search-icon mx-4" scale="1.3" v-if="currentRouteName === 'FeedMain'" @click="goSearch"></b-icon-search>

                <!-- 프로필페이지 dropdown -->
                <b-icon-list v-if="currentRouteName === 'profile'" @click='toggleShow' class='list-icon'></b-icon-list>
                    <div v-if='showMenu' class='menu'>
                        <div class='menu-items'>
                            <li class="menu-user m-3">
                                알골마스터
                                </li>
                            <hr>
                            <!-- <li class="menu-tiem"><b-icon-question-circle-fill scale="1.1" class="mr-2"/>QnA</li> -->
                            <li class="menu-tiem" @click="goMyPost"><b-icon-layout-text-sidebar-reverse scale="1.1" class="mr-2"/>작성한 글</li>
                            <li class="menu-tiem" @click="goMyLikePost"><b-icon-bookmarks-fill scale="1.1" class="mr-2"/>좋아요 글</li>
                            <li class="menu-tiem" @click="goModifyAbility"><b-icon-person-bounding-box scale="1.1" class="mr-2"/>개발 능력 수정</li>
                            <hr>
                            <li class="menu-tiem" @click="logout"><b-icon-box-arrow-right scale="1.1" class="mr-2"/>로그아웃</li>
                            <!-- <li class="menu-tiem" @click="goModifyUser"><b-icon-pencil scale="1.1" class="mr-2"/>회원정보 수정</li> -->
                        </div>
                    </div>
                <!-- <b-icon-question-circle-fill v-if="currentRouteName === 'profile'" class="icons question-circle-icon" scale="1.3"></b-icon-question-circle-fill> -->
            </div>
        </div>

    </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
  name:"NavBar",
  props: [
      'subjectCheck', // LeaderForm에서 가지고있는 subject 정보
  ],
  computed: {
      currentRouteName() {
        return this.$route.name;
      }
  },
  data() {
      return {
          showMenu: false,
      }
  },
  methods: {
      goBack(){
          if (this.currentRouteName === 'createTeam') {
              this.$router.push({ name: 'LeaderForm', params: { beforeSubject: this.subjectCheck }});
          } else {
              this.$router.go(-1)

          }
      },
      goSearch(){
          this.$router.push({name: 'SearchMain'})
      },
      logout() {
          storage.clear()
          alert("로그아웃")
          this.$router.push('/')

      },
      toggleShow() {
          this.showMenu = !this.showMenu
      },
      goModifyAbility() {
          this.$router.push({name: 'modifyAbility', params:{ nickname: storage.NickName}})
      },
      goMyPost() {
          this.$router.push({name: 'myPost', params:{ nickname: storage.NickName}})
      },
      goMyLikePost() {
          this.$router.push({name: 'myLikePost', params:{ nickname: storage.NickName}})
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
.arrow-left-icon{
    position: fixed;
    left:20px;
    top:0;
    height:50px;
    color: #464545;
}
.search-icon{
    position: fixed;
    right: 0;
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
    cursor: pointer;
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
.menu-items > li {
    margin: 25px 20px 25px 20px;
    font-family: Arial, Helvetica, sans-serif;
    cursor: pointer;
}
.question-circle-icon{
    position: fixed;
    right:20px;
    top:0;
    height:50px;
    color: #464545;
}
</style>