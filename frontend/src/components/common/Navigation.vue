<template>
    <div class="fixed-top">
        <div class="Navi">
            <p v-if="currentRouteName ==='chat'">{{ teamName }}팀 채팅방</p>
            <p v-else-if="currentRouteName === 'followList'">{{ profileNickname }}님의 팔로우</p>
            <p v-else>Null 담은 공간</p>
            <div>
                <b-icon-arrow-left @click="goBack" class="arrow-left-icon icons" scale="1.3"></b-icon-arrow-left>
                <b-icon-search class="icons search-icon mx-4" scale="1.3" v-if="currentRouteName === 'FeedMain'" @click="goSearch"></b-icon-search>

                <!-- 프로필페이지 dropdown -->
                
                <input v-if="currentRouteName === 'profile'" type="checkbox" name="menu" class="toggleBox">
                <b-icon-list v-if="currentRouteName === 'profile'" class='list-icon'></b-icon-list>
                <div v-if="currentRouteName === 'profile'" class='menu'>
                    <div  class='menu-items'>
                        <li class="menu-item" @click="goMyPost"><b-icon-layout-text-sidebar-reverse scale="1.1" class="mr-2"/>작성한 글</li>
                        <li class="menu-item" @click="goMyLikePost"><b-icon-bookmarks-fill scale="1.1" class="mr-2"/>좋아요 글</li>

                        <li v-if="IsMe" class="menu-item" @click="goModifyAbility"><b-icon-person-bounding-box scale="1.1" class="mr-2"/>개발 능력 수정</li>
                        <li v-else class="menu-item" @click="teamJoinRequest"><b-icon-person-bounding-box scale="1.1" class="mr-2"/>팀원 요청</li>
                        <hr v-if="IsMe">
                        <li v-if="IsMe" class="menu-item" @click="logout"><b-icon-box-arrow-right scale="1.1" class="mr-2"/>로그아웃</li>
                    </div>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
import http from '@/util/http-common.js'
import $ from 'jquery'

const storage = window.sessionStorage;

$(document).click(e => {
    if (e.target.className != 'menu-item' && e.target.className != 'toggleBox') {
        if ($("input:checkbox[name='menu']").length != 0) $("input:checkbox[name='menu']")[0].checked = false;
    }
})

export default {
  name:"NavBar",
  props: [
      'subjectCheck', // LeaderForm에서 가지고있는 subject 정보
      'teamName',
      'profileNickname',
  ],
  created() {
    //   console.log(this.currentRouteName)
    // 로그인 안되어있을 시 로그인 창으로 이동
    if (!this.email || !this.nickname) {
        alert("로그인이 필요합니다.")
        this.$router.replace({name: 'Login'})
    }
  },
  computed: {
      currentRouteName() {
        return this.$route.name;
      },
      IsMe() {
          if (this.nickname == this.$route.params.nickname) return true
          else return false
      },
      
    
  },
  data() {
      return {
          showMenu: false,
          email: storage.User,
          nickname: storage.NickName,
          
      }
  },
  methods: {
        goBack(){
            
            if (this.currentRouteName === 'createTeam') {
                this.$router.replace({ name: 'LeaderForm', params: { beforeSubject: this.subjectCheck }});
            } else {
                this.$router.go(-1)
            }
        },
        goSearch(){
            this.$router.push({name: 'SearchMain'})
        },
        logout() {
            storage.clear()
            alert("로그아웃 완료")
            this.$router.push('/')

        },
        goModifyAbility() {
            this.$router.push({name: 'modifyAbility', params:{ nickname: storage.NickName}})
        },
        goMyPost() {
            this.$router.push({name: 'myPost', params:{ nickname: this.$route.params.nickname}})
        },
        goMyLikePost() {
            this.$router.push({name: 'myLikePost', params:{ nickname: this.$route.params.nickname}})
        },
      // 팀 가입 권유
        teamJoinRequest() {
            let formData = new FormData;
            formData.append("mynickname", storage.NickName)
            formData.append("tonickname", this.$route.params.nickname)
            
            http
            .post("/alarm/teamAlarm", formData)
            .then((res) => {
                alert(`${this.$route.params.nickname}님에게 팀원 요청을 보냈습니다.`)
            })
            .catch((err) => {
                console.log(err)
            })
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
    font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
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
.toggleBox ~ .menu {
    position: absolute;
    right: -200px;
    top: 50px;
    height: 0;
    opacity: 0;
    margin-top: 1px;
    overflow: hidden;
    background-color: #f7f7f7;
    border-left: 1px solid #dfdede;
    border-bottom: 1px solid #dfdede;
    color:#464545;
    transition: opacity 0.3s, right 0.6s, width 0.6s, height 0.4s;
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
.toggleBox{
    position: fixed;
    right: 20px;
    top: 0;
    height: 50px;
    z-index: 100;
    opacity: 0;
}
.toggleBox:checked ~ .menu{
    right: 0;
    width: 200px;
    height: 248px;
    opacity: 1;
}
</style>