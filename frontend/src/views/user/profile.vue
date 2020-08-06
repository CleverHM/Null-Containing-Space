<template>
    <div style="top:0">
        <Navbar :showMenu="showMenu" @toggleShow="toggleMenu"></Navbar>
        <subNav></subNav>
        <div id="profile" @click="noshowMenu">
            <div id="baseProfile" style="height: 150px;">
                <!-- 프로필 이미지  -->
                <div class="profileImg">
                  <img v-if="User.profileURL" :src="'data:image/png;base64, ' + User.profileURL" alt="image" class="img-part">
                  <img v-else src="@/assets/images/profile_default.png">
                </div>
                <!-- 이름/팔로우 -->
                <div class="profileInfo">
                    <!-- 닉네임 -->
                    <div class="profileName">{{ User.nickname }}</div>
                    <div class="follow">
                        <button class="follower">{{ User.followercount }}<br>팔로워</button>
                        <button class="following">{{ User.followingcount }}<br>팔로잉</button>
                    </div>
                    <div class="profile-btns">
                        <button class="btn-follow" v-if="isMe" @click="goUserModify">회원정보수정</button>
                        <button class="btn-follow" v-else>팔로우</button>
                    </div>
                </div>
            </div>

            <!-- 블로그 & 깃 !-->
            <button v-if="User.blogURL" class="btn-on">
              <i class="fab fa-blogger fa-2x"></i>
              <br><p>BLOG</p>
            </button>
            <button v-else class="btn-off" disabled>
              <i class="fab fa-blogger fa-2x"></i>
              <br><p>No BLOG</p>
            </button>

            <button v-if="User.GitURL" class="btn-on">
              <i class="fab fa-git-square fa-2x"></i>
              <br><p>GIT</p>
            </button>
            <button v-else class="btn-off" disabled>
              <i class="fab fa-git-square fa-2x"></i>
              <br><p>No GIT</p>
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
            <div id="introduce" class="my-3" v-if="User.Introduce">
            {{ User.Introduce }}
            </div>
            <div id="introduce" class="my-3" v-else>
            자기소개가 없습니다. <br>
            회원정보수정에서 자기소개를 추가해주세요.
            </div>

            <!-- 태그 뱃지 !-->
            <TagBadge></TagBadge>
            <TagBadge></TagBadge>
            <TagBadge></TagBadge>

          </div>
          <div v-if="currentTab === 'tab2'">
            {{ User.ability }}
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
    props: [
      'nickname'
    ],
    created() {
      this.getUserInfo()
    },
    computed: {
      isMe() {
        return storage.NickName === this.User.nickname
      }
    },
    data : () => {
        return {
            User: {
              nickname: null,
              followingcount: 0,
              followercount: 0,
              blogURL: null,
              GitURL: null,
              Introduce: null,
              profileURL: null,
              ability: null,
            },
            // navigation dropdown
            showMenu: false,
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
          .then(({data}) => {
              this.User.nickname = data.nickname
              this.User.Introduce = data.intro
              this.User.profileURL = data.file
              this.User.followingcount = data.followingCnt
              this.User.followercount = data.followerCnt;
              this.User.blogURL = data.blogaddr
              this.User.GitURL = data.gitaddr
              this.User.ability = data.abt
              console.log(this.User)
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
    background-color: #f7f7f7;
    border-radius: 100%;
    overflow: hidden;
}
.profileImg img {
  margin: 0;
  width: 100%;
  height: 100%;
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
.btn-off:visited{
  border: 0;
}
.btn-off:active{
  border: 0;
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
