<template>
    <div v-if="isLoading"></div>
    <div v-else style="top:0">
        <Navbar />
        <subNav></subNav>

        <div id="profile">
            <div id="baseProfile" style="height: 150px;">
                <!-- 프로필 이미지  -->
                <div class="profileImg">
                  <img v-if="User.profileURL" :src="'data:image/png;base64, ' + User.profileURL" alt="image" class="img-part">
                  <img v-else src="@/assets/images/default_image.png">
                </div>
                <!-- 이름/팔로우 -->
                <div class="profileInfo">
                    <!-- 닉네임 -->
                    <div class="profileName">{{ User.nickname }}</div>
                    <div class="follow">
                        <button class="follower" @click="goFollowList">{{ User.followercount }}<br>팔로워</button>
                        <button class="following" @click="goFollowList">{{ User.followingcount }}<br>팔로잉</button>
                    </div>
                    <div class="profile-btns">
                        <button class="btn-follow" v-if="isMe" @click="goUserModify">회원정보수정</button>
                        <button class="btn-follow" v-else-if="isFollow" @click="follow">팔로우 취소</button>
                        <button class="btn-follow" v-else @click="follow">팔로우</button>
                    </div>
                </div>
            </div>

            <!-- 블로그 & 깃 !-->
            <button v-if="User.blogURL" @click="goBlog" class="btn-on">
              <i class="fab fa-blogger fa-2x"></i>
              <br><p>BLOG</p>
            </button>
            <button v-else class="btn-off" disabled>
              <i class="fab fa-blogger fa-2x"></i>
              <br><p>No BLOG</p>
            </button>

            <button v-if="User.GitURL" @click="goGit" class="btn-on">
              <i class="fab fa-git-square fa-2x"></i>
              <br><p>GIT</p>
            </button>
            <button v-else class="btn-off" disabled>
              <i class="fab fa-git-square fa-2x"></i>
              <br><p>No GIT</p>
            </button>

            <!-- 개발 능력 !-->
            <nav class="default-tabs">
              <div class="default-tabs-item" :class="{'tabs-item_active':isCurrent}" @click="handleClick">
                <button> Introduce </button> <!-- active tab -->
                <div class="default-tabs-active-line"></div>
              </div>
              <div class="default-tabs-item" :class="{'tabs-item_active':!isCurrent}" @click="handleClick">
                <button> Ability </button>
                <div class="default-tabs-active-line"></div>
              </div>
            </nav>
            
            <div class="content">
              <div v-if="currentTab === 'Introduce'">
                <!-- 자기소개 !-->
                <div id="introduce" class="my-3" v-if="User.Introduce">
                {{ User.Introduce }}
                </div>
                <div id="introduce" class="my-3" v-else>
                자기소개가 없습니다. <br>
                회원정보수정에서 자기소개를 추가해주세요.
                </div>

                <!-- 태그 뱃지 !-->
                {{ User.tag }}
                <TagBadge :tag="tag" v-for="tag in User.tags" :key="tag"></TagBadge>

              </div>
              <div v-if="currentTab === 'Ability'" >
                <div class="ability-header">
                    <div class="ability-kind">종류</div>
                    <div class="ability-level">능력</div>
                  
                </div>
                <profileAbility :abilityname="abilities[n]" :ability="User.ability[n]" :id="n" :key="n" v-for="n in 15"></profileAbility>
                
              </div>
            </div>

        </div>
    </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue'
import subNav from '../../components/common/subnav.vue'
import TagBadge from '../../components/common/TagBadge.vue'
import profileAbility from '../../components/user/profileAbility.vue'
import http from "@/util/http-common.js";

const storage = window.sessionStorage;

export default {
    name: "profile",
    components:{
        Navbar,
        subNav,
        TagBadge,
        profileAbility,
    },
    created() {
      this.nickname = storage.getItem("NickName")
      this.pagenickname = this.$route.params.nickname
      this.getUserInfo()
      this.Loading();
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
              tags: [],
            },
            isMe: false,
            isFollow: false,
            isCurrent: true,
            currentTab: 'Introduce',
            abilities: [
                { name: 'cpp',
                    id: 0},
                { name: 'Java',
                    id: 1},
                { name: 'Python',
                    id: 2},
                { name: 'php',
                    id: 3},
                { name: 'html',
                    id: 4},
                { name: 'css',
                    id: 5},
                { name: 'JavaScript',
                    id: 6},
                { name: 'SQL',
                    id: 7},
                { name: 'noSQL',
                    id: 8},
                { name: 'Spring',
                    id: 9},
                { name: 'Django',
                    id: 10},
                { name: 'BootStrap',
                    id: 11},
                { name: 'Vue',
                    id: 12},
                { name: 'React',
                    id: 13},
                { name: 'Algorithm',
                    id: 14},
            ],
            lenAbility: 15,
            isLoading: true,
        }
    },
    methods : {
        Loading() {
              if (this.isLoading) {
                  setTimeout(this.delayfinish, 100);
              }
        },
          // 딜레이 화면
        delayfinish(){
              this.isLoading = false;
        },
        getUserInfo() {
          var InputData = new FormData();
          InputData.append("nickname", this.nickname)
          InputData.append("pageNickname", this.pagenickname)
          http
          .post("/account/myPage", InputData)
          .then(({data}) => {
              console.log(data)
              this.User.nickname = data.nickname
              this.User.Introduce = data.intro
              this.User.profileURL = data.file
              this.User.followingcount = data.followingCnt
              this.User.followercount = data.followerCnt;
              this.User.blogURL = data.blogaddr
              this.User.GitURL = data.gitaddr
              this.User.ability = data.abt
              this.User.tags = data.tag
              this.isMe = data.me
              this.isFollow = data.follow
          })
          .catch((err) => {
            console.log(err)
          })
        },
        follow() {
          var InputData = new FormData()
          InputData.append("From", this.nickname)
          InputData.append("To", this.pagenickname)
          http.post("/follow/user", InputData)
          .then(({data}) => {
            this.isFollow = data.flag
            this.User.followingcount = data.followingCnt
            this.User.followercount = data.followerCnt
            console.log(this.isFollow)
          })
          .catch(() => {})

        },
        handleClick(event) {
          console.log(event.target.innerText)
          this.currentTab = event.target.innerText;
          this.isCurrent = !this.isCurrent
        },
        goUserModify() {
          this.$router.push({name: 'UserModify'})
        },
        goFollowList(event) {
          this.$router.push({name: 'followList', params: { PageName: event.target.className, nickname: this.User.nickname}})
        },
        goBlog() {
          window.open(this.User.blogURL)
        },
        goGit() {
          window.open(this.User.GitURL)
        },
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
  border: 0;
  outline: 0;
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
    color: #464545;
    border: 0;
    outline: 0;
}
.btn-off{
    width:50%;
    font-size: 18px;
    color: #E2DFD8;   
    border: 0;
    outline: 0;
}
#introduce {
  white-space: pre-wrap;
  word-break: break-all;
}

.default-tabs {
  position: relative;
  /* margin: 0 auto; */
}
.default-tabs-item {
    display: inline-block;
    padding-bottom: 8px;
    font-size: 16px;
    letter-spacing: 0.8px;
    color: gray;
    text-decoration: none;
    text-align: center;
    border: none;
    background-color: transparent;
    border-bottom: 2px solid transparent;
    cursor: pointer;
    -webkit-transition: all 0.25s;
    transition: all 0.25s;
    width: 50%;
}
.tabs-item_active{
      outline: none;
      border-bottom: 2px solid gray;
      color: black;
}
.default-tabs-item button {
  cursor: pointer;
  border: 0;
  outline: 0;
}
.default-tabs-item_active {
      color: black;
}
.default-tabs-item:hover {
      border-bottom: 2px solid gray;
      color: black;
}
.default-tabs-item:focus {
      outline: none;
      border-bottom: 2px solid gray;
      color: black;
}
.default-tabs-item:visited{
      outline: none;
      border-bottom: 2px solid gray;
      color: black;
}
.default-tabs-item:first-child {
      margin-left: 0;
}
.default-tabs-item:last-child {
      margin-right: 0;
}

.content {
  margin-top: 30px;
  font-size: 20px;
}

/* ability tab */
.ability-header{
  border-bottom: 1px solid #464545;
}
.ability-kind, .ability-level{
  padding-bottom: 10px;
  font-weight: bold;
  display: inline-block;
  width: 50%;
  text-align: center;
}
</style>
