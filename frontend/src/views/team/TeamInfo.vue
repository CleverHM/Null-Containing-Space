<template>
  <div id="teamInfo">
    <Navbar></Navbar>
    <subNav/>
      
    <div v-if="delayOn">
    </div>
    <div v-if="!delayOn">
      <div class="team-title d-flex justify-content-center">
        {{ teamData.title }}
      </div>

      <!-- 팀 / 명세서-->
      <div id="tabs">
        <div class="tab-button d-flex justify-content-center">
          <button v-for="(tab, index) in tabs"
            :class="{active: currentTab === index}"
            @click="currentTab = index"
            :key="tab">
            {{ tab }}
          </button>
        </div>
        <div class="tab-content">
          <div v-if="currentTab == 0" style="margin-top:20px;">

            <!-- 시작일 -->
            <div class="displaytags">
              프로젝트 진행 : {{ diffTime }}
            </div>
            
            <!-- 팀 정보 -->
            <div id="team" class="my-3">
              <div class="d-flex justify-content-center teamCnt"> 인원 ( {{ this.teamData.members.length + 1 }} / {{ this.teamData.cnt }} )</div>
              <div class="d-flex justify-content-start align-items-center">
                <div class="team-tags">
                  팀장
                </div>
                <div class="leader-info">
                    <memberImg :memberData="teamData.leaderNickname" :isLeader="true" class="ml-2"></memberImg>
                </div>
              </div>
              
              <div v-if="teamExist" class="memberArea d-flex justify-content-start align-items-center mt-4">
                <div class="team-tags">
                  팀원
                </div>
                <div class="member-info d-flex flex-row align-items-center">
                    <memberImg v-for="mem in teamData.members" :key="mem.nickname" :memberData="mem" :isLeader="false" class="ml-2"></memberImg>
                </div>
              </div>
            </div>

          </div>
          
          <div v-if="currentTab == 1">
            
            <!-- 프로젝트 명세서 -->
            <div class="team-spec">
                <specs :teamData="teamData"/>
            </div>
          
          </div>
        </div>
      </div>
      

      <!-- 팀원 매칭 버튼 -->
      <div class="submit-area d-flex justify-content-center">
        <button class="submit-button" @click="teamSignup">팀 가입 신청</button>
      </div>
    </div>
  </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue'
import subNav from '../../components/common/subnav.vue'
import specs from '../../components/team/Specification.vue'
import memberImg from '../../components/team/memberImg.vue'
import httpCommon from '../../util/http-common';
import http from "../../util/http-common.js";
import axios from 'axios';

const storage = window.sessionStorage;
var now = new Date(); // 현재 시간 받아오기


export default {
  name: "teamInfo",

  components: {
    Navbar,
    subNav,
    specs,
    memberImg,
  },

  props: [
    'teamId',
    ],

  data() {
    return {
      ifLeader: false,
      diffTime: '',
      teamExist: false,
      teamData: null,
      delayOn: true,
      tabs: [
        '프로젝트 팀 정보',
        '프로젝트 명세서',
      ],
      currentTab: 0,
    }
  },

  created() {
    setTimeout(this.delayfinish, 300);

    http
    .get(`/team/info/${this.teamId}`)
    .then((res) => {
      this.teamData = res.data.teamdate
      // 받아온 date 값이 string type 이므로 date type으로 변환 후 체크하는 methods 호출
      var postDate = new Date(this.teamData.createDate)
      this.diffTime = this.dateCheck(postDate);

      if (this.teamData.members.length == 0) {
        this.teamExist = false
      } else {
        this.teamExist = true
      }
    })
    .catch((err) => {
      console.log(err)
    })
    
  },

  methods: {

    // tab 이동
    handleClick(newTab) {
      this.currentTab = newTab;
    },

    // 날짜 체크
    dateCheck(date) {
      var diff = now - date
      var diff_sec = Math.floor(diff / 1000)
      var diff_min = Math.floor(diff_sec / 60)
      var diff_hour = Math.floor(diff_min / 60)
      var diff_day = Math.floor(diff_hour / 24)
      var diff_month = Math.floor(diff_day / 30)
      var diff_year = Math.floor(diff_month / 12)

      if (diff_year > 0) {
        var calyear = diff_year + '년'
        return calyear
      } else if (diff_month > 0) {
        var calmonth = diff_month + '달'
        return calmonth
      } else if (diff_day > 0) {
        var calday = diff_day + '일'
        return calday
      } else if (diff_hour > 0) {
        var calhour = diff_hour + '시간'
        return calhour
      } else if (diff_min > 0) {
        var calmin = diff_min + '분'
        return calmin
      } else if(diff_sec > 0) {
        var caltime = diff_sec + '초'
        return caltime
      } else {
        return '0초'
      }
    },

    delayfinish(){
      this.delayOn = false;
    },

    teamSignup() {
      let formData = new FormData;
      formData.append("mynickname", storage.getItem("NickName"))
      formData.append("tonickname", this.teamData.leaderNickname.nickname)

      http
      .post('/alarm/teamAlarm' , formData)
      .then((res) => {
        alert(`${this.teamData.leaderNickname.nickname}님에게 팀 가입 요청을 보냈습니다.`)
      })
      .catch((err) => {
        console.log(err)
      })
    }

  },

}
</script>

<style scoped>
#teamInfo {
    margin: 60px 10px 30px 10px;
}

*:focus { outline:none; }

.team-title {
  padding: 0px 10px 5px 10px;
  color: #464545;
  font-family:Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
  font-size: 25px;
  font-weight: bold;
}

#team {
  margin-bottom: 50px;
}

.content {
  margin-bottom: 70px;
}

.submit-button {
  position: fixed;
  bottom: 60px;
}

.submit-button {
    margin: 10px 0px 10px 0px;
    width: 170px;
    padding: 10px;
    color: white;
    background-color: #ACCCC4;
    border-radius: 10px;
}


.member-area {
  overflow: auto;
}

.team-tags {
  padding: 10px;
  font-size: 14px;
  font-weight: bold;
  color: #464545;
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
  float: left;
  width: 17%;
}
.member-info {
  display: inline;
  margin-bottom: 20px;
  width: 100%;
}

.member-info {
  display: inline;
  margin-bottom: 20px;
  width: 100%;
  overflow-x: scroll;
  white-space:nowrap;
}


.tab-content {
  margin-bottom: 70px;
  
}

.displaytags {
  padding: 10px;
  font-size: 14px;
  font-weight: bold;
  color: #464545;
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}


.teamCnt {
  margin-bottom: 20px;
  padding: 10px;
  font-size: 14px;
  font-weight: bold;
  color: #464545;
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}

.tab-button > button {
  padding: 10px 20px 10px 20px;
  width: 48%;
}

.tab-button > .active {
  border-bottom: 1.5px solid #464545;
}

</style>