<template>
    <div style="top:0">
        <Navbar :showMenu="showMenu" @toggleShow="toggleMenu"></Navbar>
        <subNav></subNav>
        <div id="profile" @click="noshowMenu">
            <div id="baseProfile" style="height: 150px;">
                <!-- 프로필 이미지  -->
                <div class="profileImg">
                  <img src="@/assets/images/test.jpg">
                </div>
                <!-- 이름/팔로우 -->
                <div class="profileInfo">
                    <!-- 닉네임 -->
                    <div class="profileName">알골마스터</div>
                    <div class="follow">
                        <button class="follower">0<br>팔로워</button>
                        <button class="following">1<br>팔로잉</button>
                    </div>
                    <div class="profile-btns">
                        <button class="btn-follow" v-if="false">팔로우</button>
                        <button class="btn-follow" v-else @click="goUserModify">회원정보수정</button>
                    </div>
                </div>
            </div>

            <!-- 블로그 & 깃 !-->
            <button :class="[{'btn-on' : blogLink}, {'btn-off' : !blogLink}]">
            <i class="fab fa-blogger fa-2x"></i>
            <br><p>BLOG</p>
            </button>
            <button :class="[{'btn-on' : gitLink}, {'btn-off' : !gitLink}]">
            <i class="fab fa-git-square fa-2x"></i>
            <br><p>GIT</p>
            </button>


            <!-- 개발 능력 !-->
            <tabs
          :tabs="tabs"
          :currentTab="currentTab"
          :wrapper-class="'default-tabs'"
          :tab-class="'default-tabs__item'"
          :tab-active-class="'default-tabs__item_active'"
          :line-class="'default-tabs__active-line'"
          @onClick="handleClick"
        />
        <div class="content">
          <div v-if="currentTab === 'tab1'">
            
            <!-- 자기소개 !-->
            <div id="introduce" class="my-3">
            안녕하세요 알골마스터입니다. <br />
            잘지내보아요.<br/><br/>

            관심 분야<br/>
            ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
            </div>

            <!-- 태그 뱃지 !-->
            <TagBadge></TagBadge>
            <TagBadge></TagBadge>
            <TagBadge></TagBadge>

          </div>
          <div v-if="currentTab === 'tab2'">
            ability
          </div>
        
        </div>

    </div>
    </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue'
import subNav from '../../components/common/subnav.vue'
import TagBadge from '../../components/common/TagBadge.vue'
import TabComponent from '../../components/common/TabComponent.vue'
import Tabs from 'vue-tabs-with-active-line';
import http from "@/util/http-common.js";

const storage = window.sessionStorage;
console.log(storage.getItem("token"))
// var User = JSON.parse(storage.getItem('User'));
// console.log(User)
const TABS = [{
    title: 'Introduce',
    value: 'tab1',
    },{
    title: 'Ability',
    value: 'tab2',
    },
];

export default {
    name: "profile",
    components:{
        Navbar,
        subNav,
        TagBadge,
        Tabs,
    },
    created() {
      this.getUserInfo()
    },
    data : () => {
        return {
            // navigation dropdown
            showMenu: false,
            nickname: storage.NickName,
            blogLink : "dsdfsdfsfd",
            gitLink : "",
            tabs: TABS,
            currentTab: 'tab1',
        }
    },
    methods : {
        getUserInfo() {
          var InputData = new FormData();
          InputData.append("nickname", this.nickname)
          http
          .post("/account/myPage", InputData)
          .then((data) => {
            console.log(data)
          })
          .catch((err) => {
            console.log(err)
          })
        },
        handleClick(newTab) {
          this.currentTab = newTab;
        },
        noshowMenu() {
          this.showMenu = false;
        },
        toggleMenu(isshow) {
          this.showMenu = isshow;
        },
        goUserModify() {
          this.$router.push({name: 'UserModify'})
        }
    }


}

</script>

<style scoped>
#profile{
  margin: 70px 20px 50px 20px;
}
.baseProfile {
  position: relative;
}
.profileImg{
    float: left;
    height: 120px;
    width: 120px;
    background-color: black;
    border-radius: 100%;
    overflow: hidden;
}
.profileImg img {
  width: 100%;
  object-fit: cover;
}
.profileName{
    padding: 0 0 5px 135px;
    font-size: 20px;
    font-weight: bold;
}
.follow{
  padding-left: 135px;
  margin: 5px 0 10px 0;
}
.follow button {
  width: 50%;
}
.profile-btns {
  padding: 0 10px 0 135px;
}
.btn-follow{
    width: 100%;
    background-color: #464545;
    border-radius: 3px;
    color: #f7f7f7;

}
/* git, blog url */
.btn-on{
    width:50%;
    font-size: 18px;
    color: #464545
}
.btn-off{
    width:50%;
    font-size: 18px;
    color: #E2DFD8;
    
}
#introduce {
  white-space: normal;
  word-break: break-all;
}
.tabs {
  position: relative;
  top: 20px;
}

.tabs__item {
  display: inline-block;
  margin: 0 5px;
  padding: 10px;
  padding-bottom: 8px;
  font-size: 16px;
  letter-spacing: 0.8px;
  color: gray;
  text-decoration: none;
  border: none;
  background-color: transparent;
  border-bottom: 2px solid transparent;
  cursor: pointer;
  transition: all 0.25s;
}

.tabs__item_active {
  color: black;
}

.tabs__item:hover {
  border-bottom: 2px solid gray;
  color: black;
}

.tabs__item:focus {
  outline: none;
  border-bottom: 2px solid gray;
  color: black;
}

.tabs__item:first-child {
  margin-left: 0;
}

.tabs__item:last-child {
  margin-right: 0;
}

.tabs__active-line {
  position: absolute;
  bottom: 0;
  left: 0;
  height: 2px;
  background-color: black;
  transition: transform 0.4s ease, width 0.4s ease;
}
</style>
