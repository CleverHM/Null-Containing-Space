<template>
  <div id="teamIn">
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
        <!-- 팀 정보 탭 -->
        <div v-if="currentTab == 0" style="margin-left:10px; margin-top:20px;">

          <!-- 시작일 -->
          <div class="displaytags">
            프로젝트 진행 : {{ diffTime }}
          </div>
          
          <!-- 팀 정보 -->
          <div id="team" class="my-3">
            <div class="d-flex justify-content-center teamCnt"> 인원 ( {{ this.teamData.members.length + 1 }} / {{ this.teamData.cnt }} )</div>
            <div class="d-flex justify-content-start align-items-center">
              <div class="displaytags">
                팀장
              </div>
              <div class="team-member-area">
                <memberImg :memberData="teamData.leaderNickname" :isLeader="true" class="mx-2"></memberImg>
              </div>
            </div>
            
            <div v-if="teamExist" class="d-flex justify-content-start align-items-center mt-4">
              <div class="displaytags">
                팀원
              </div>
              <div class="team-member-area d-flex flex-row align-items-center">
                  <memberImg v-for="mem in teamData.members" :key="mem.nickname" :memberData="mem" :isLeader="false" class="ml-2"></memberImg>
              </div>
            </div>
          </div>

        </div>
      
        <!-- 명세서 탭 -->
        <div v-if="currentTab == 1">
          
          <!-- 프로젝트 명세서 -->
          <div class="team-spec">
              <specs :teamData="teamData"/>
          </div>
        
        </div>
      
        <!-- 설정 탭 -->
        <div v-if="currentTab == 2" class="settingArea d-flex flex-column">
          
          <!-- 진행 기간 - 연결 X -->
          <button>
            <b-icon-calendar-check class="mr-2"/>
            {{ startDate }} 
            <span class="mx-2"> ~ </span> 
            {{ nowDate }} 
            <span class="mx-2" style="font-size: 13px; color: #C4BCB8;"> (진행 중) </span>
          </button>
          
          <!-- 실시간 채팅 -->
          <button>
            <b-icon-chat-dots class="mr-2"/>
            실시간 채팅
          </button>
          
          <!-- 팀 탈퇴 (팀장은 불가능)-->
          <button
            :class="{ noClick: ifLeader }"
            @click="modalLeave">
            <b-icon-person-dash class="mr-2"/>
            프로젝트 탈퇴
            <span v-if="ifLeader" style="float: right; font-size: 13px;">(팀원만 가능)</span>
          </button>

          <!-- 명세서 수정 (팀장만 가능) -->
          <button style="border: none;"
            :class="{ noClick: !ifLeader }">
            <b-icon-card-checklist class="mr-2"/>
            명세서 수정
            <span v-if="!ifLeader" style="float: right; font-size: 13px;">(팀장만 가능)</span>
          </button>
          
          <!-- 프로젝트 종료 (팀장만 가능)-->
          <button style="border: none;"
            :class="{ noClick: !ifLeader }"
            @click="modalExit">
            <b-icon-door-open class="mr-2"/>
            프로젝트 종료
            <span v-if="!ifLeader" style="float: right; font-size: 13px;">(팀장만 가능)</span>
          </button>

        </div>

        <!-- 모달 -->
        <div v-if="showModal" @close="showModal = false">
            <div class="modal-mask">
              <div class="modal-wrapper">
                <div class="modal-container">

                  <div class="modal-header d-flex justify-content-start align-items-center">
                    <b-icon-exclamation-circle-fill style="color:red"/>
                    <span class="ml-2">경고</span>
                  </div>

                  <div class="modal-body">
                    {{ modal.message[modal.cnt] }}
                  </div>

                  <div class="modal-footer d-flex justify-content-end">
                    <button class="modal-default-button" @click="closeModal">
                      취소
                    </button>
                    <button v-if="!ifLeader" class="modal-button" @click="teamLeave">
                      팀 탈퇴
                    </button>
                    <button v-if="ifLeader" class="modal-button" @click="teamExit">
                      프로젝트 종료
                    </button>
                  </div>
                </div>
              </div>
            </div>
        </div>

      </div>
    </div>
    

    <!-- 팀원 매칭 버튼 -->
    <div class="submit-area d-flex justify-content-center">
      <button v-if="ifLeader" class="submit-button" @click="teamMatchGo">팀원 매칭하기</button>
    </div>
    
  </div>
</template>

<script>
import specs from './Specification.vue'
import memberImg from './memberImg.vue'
import http from "../../util/http-common.js";
import axios from 'axios';

const storage = window.sessionStorage;
var now = new Date(); // 현재 시간 받아오기


export default {
  name: "teamIn",

  components: {
    specs,
    memberImg,
  },

  props: [
    'teamData',
    ],

  data() {
    return {
      ifLeader: false,
      diffTime: '',
      teamExist: false,
      tabs: [
        '팀 정보',
        '명세서',
        '설정',
      ],
      currentTab: 0,
      startDate: null,
      nowDate: null,
      showModal: false,
      modal: {
        cnt: 0,
        message: [
          '정말 팀을 탈퇴하시겠습니까?',
          '정말 팀을 종료하시겠습니까?',
        ],
      }
    }
  },

  created() {

    if (storage.getItem("NickName") == this.teamData.leaderNickname.nickname) {
      this.ifLeader = true
    } else {
      this.ifLeader = false
    }

    // 받아온 date 값이 string type 이므로 date type으로 변환 후 체크하는 methods 호출
    var postDate = new Date(this.teamData.createDate);
    this.diffTime = this.dateCheck(postDate);

    // 프로젝트 시작 날짜 yyyy-mm-nn 순
    this.startDate = this.teamData.createDate.slice(0,10)

    // 오늘 날짜
    let year = now.getFullYear().toString(); // 년도
    let month = (now.getMonth() + 1).toString();  // 월
    let date = now.getDate().toString();  // 날짜
    this.nowDate = year + '-' + (month[1] ? month : '0' + month[0]) + '-' + (date[1] ? date : "0" + date[0]);

    if (this.teamData.members.length == 0) {
      this.teamExist = false
    } else {
      this.teamExist = true
    }

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

    // 프로젝트 팀 탈퇴
    teamLeave() {
      if (this.ifLeader == false) {
        let formData = new FormData;
        formData.append("nickname", storage.getItem("NickName"))

        http
        .post('/team/leave', formData)
        .then((res) => {
          alert('프로젝트 팀에서 탈퇴하셨습니다.')
          this.$router.go({ name: 'Main' }).catch(()=>{})
        })
        .catch((err) => {
          console.log(err)
        })
      }
    },

    // 프로젝트 종료
    teamExit() {
      if (this.ifLeader == true) {
        let formData = new FormData;
        formData.append("nickname", storage.getItem("NickName"))

        http
        .post('/team/exit', formData)
        .then((res) => {
          alert('프로젝트가 종료되었습니다.')
          this.$router.go({ name: 'Main' }).catch(()=>{})
        })
        .catch((err) => {
          console.log(err)
        })
      }
    },

    // 팀 매칭 페이지로 이동
    teamMatchGo() {
      this.$router.push({ name: 'teamMatch' })
    },

    // 유저 페이지 이동
    goUserProfile() {
      console.log('ㅠㅠㅠㅠ')
    },

    // modal - 팀 탈퇴
    modalLeave() {
      if (this.ifLeader == false) {
        this.showModal = true;
        this.modal.cnt = 0;
      }
    },

    // modal - 팀 종료
    modalExit() {
      if (this.ifLeader == true) {
        this.showModal = true;
        this.modal.cnt = 1;
      }
    },

    // modal 닫기
    closeModal() {
      this.showModal = false;
    },

  },

}
</script>

<style scoped>
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

.tab-content {
  margin-bottom: 70px;
}

.submit-button {
  position: fixed;
  bottom: 60px;
}

.submit-button {
    margin: 10px 0px 10px 0px;
    padding: 10px;
    color: white;
    background-color: #ACCCC4;
    border-radius: 10px;
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
  width: 32%;
}

.tab-button > .active {
  border-bottom: 1.5px solid #464545;
}

.settingArea {
  margin: 30px 7px 10px 7px;
}

.settingArea > button {
  padding: 12px;
  width: 100%;
  font-size: 15px;
  margin-top: 2px;
  margin-bottom: 2px;
  border-bottom: 1px solid white;
  text-align: start;
  color: #464545;
}

.noClick {
  color: #cec8c5 !important;
}

/* modal */
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, .5);
  display: table;
  transition: opacity .3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-container {
  width: 300px;
  margin: 0px auto;
  padding: 20px 30px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
  transition: all .3s ease;
  font-family: Helvetica, Arial, sans-serif;
}

.modal-header {
  margin-top: 0;
  color: red;
}

.modal-body {
  margin: 20px 0;
}

.modal-default-button {
  padding: 5px;
  border-radius: 10px;
  color: white;
  background-color: #C4BCB8;
}

.modal-button {
  padding: 5px;
  border-radius: 10px;
  color: white;
  background-color: red;
}

.modal-enter {
  opacity: 0;
}

.modal-leave-active {
  opacity: 0;
}

.modal-enter .modal-container,
.modal-leave-active .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}

</style>