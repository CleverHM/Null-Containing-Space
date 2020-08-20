<template>
    <div>
        <Navbar />
        <subNav />
        <nav class="default-tabs">
              <div class="default-tabs-item" :class="{'tabs-item_active':isCurrent}" @click="handleClick">
                <button> 프로젝트 </button> <!-- active tab -->
                <div class="default-tabs-active-line"></div>
              </div>
              <div class="default-tabs-item" :class="{'tabs-item_active':!isCurrent}" @click="handleClick">
                <button> SNS </button>
                <div class="default-tabs-active-line"></div>
              </div>
            </nav>
            
            <div class="content">
              <!-- 프로젝트 알람 -->
              <div v-if="isCurrent">
                <!-- 알람이 없을 때 -->
                <!-- <div v-if="!teamExist" class="null-area d-flex justify-content-center align-items-center align-content-center flex-column">
                  <b-icon-envelope-open scale="1.5" font-scale="1.5" class="mb-4"/>
                  새로운 소식이 없습니다.
                </div> -->

                <!-- 알람이 있을 때 -->
                <div>
                  <TeamAlarm v-for="teamData in teamList" :key="teamData.aid" :teamData="teamData" />
                </div>
                  

              </div>

              <!-- feed 알람 -->
              <div v-else >
                <!-- 알람이 없을 때 -->
                <!-- <div v-if="!snsExist" class="null-area d-flex justify-content-center align-items-center align-content-center flex-column">
                  <b-icon-envelope-open scale="1.5" font-scale="1.5" class="mb-4"/>
                  새로운 소식이 없습니다.
                </div> -->

                <!-- 알람이 있을 때 -->
                <div>
                    <SNSAlarm v-for="snsData in snsList" :key="snsData.id" :snsData="snsData"/>
                </div>
              </div>
                
              <!-- 무한스크롤 -->
              <infinite-loading 
                @infinite="infiniteHandler" 
                ref="InfiniteLoading"
                spinner="waveDots">
                <div slot="no-results" class="null-area d-flex justify-content-center align-items-center align-content-center flex-column">
                  <b-icon-envelope-open scale="1.5" font-scale="1.5" class="mb-4"/>
                  새로운 소식이 없습니다.
                </div>
                <div slot="no-more" style="font-size: 14px; padding: 10px 0px;">더 이상 알림이 없습니다.</div>
              </infinite-loading>

            </div>

    </div>
</template>

<script>
import Navbar from '../components/common/Navigation.vue'
import subNav from '../components/common/subnav.vue'
import TeamAlarm from '../components/notice/TeamAlarm.vue'
import SNSAlarm from '../components/notice/SNSAlarm.vue'
import http from "../util/http-common.js";
import axios from 'axios';
import InfiniteLoading from 'vue-infinite-loading';

const storage = window.sessionStorage;

export default {
  name: 'notice',
  components: {
    Navbar,
    subNav,
    TeamAlarm,
    SNSAlarm,
    InfiniteLoading,
  },
  props: [
    'tapId'
  ],
  data() {
    return {
      isCurrent: true,
      currentTab: "",
      snsList: [],
      teamList: [],
      teamLimit: 1,
      snsLimit: 1,
    }
  },

  created() {
    this.currentHandle();
    
  },

  methods: {
    // axios 요청으로 알림 정보 받아오기
    teamReceive($state) {
      const EACH_LEN = 10

      http
      .get(`/alarm/team/${storage.getItem("NickName")}/${this.teamLimit}`)
      .then((res) => {
        setTimeout(() => {
          if(res.data.teamalarm.length) {
            this.teamList = [...this.teamList, ...res.data.teamalarm]
            $state.loaded()
            this.teamLimit++
            if(res.data.teamalarm.length / EACH_LEN < 1) {
              $state.complete()
            }
          } else {
            // 끝 지정(No more data)
            $state.complete()
          }
        }, 400)
      })
      .catch((err) => {
      })

    },

    snsReceive($state) {
      const EACH_LEN = 10

      http
      .get(`/alarm/sns/${storage.getItem("NickName")}/${this.snsLimit}`)
      .then((res) => {
        setTimeout(() => {
          if(res.data.snsalarm.length) {
            this.snsList = [...this.snsList, ...res.data.snsalarm]
            $state.loaded()
            this.snsLimit++
            if(res.data.snsalarm.length / EACH_LEN < 1) {
              $state.complete()
            }
          } else {
            // 끝 지정(No more data)
            $state.complete()
          }
        }, 400)
      })
      .catch((err) => {
      })
    },

    // 무한 스크롤
    infiniteHandler($state) {
      if (this.isCurrent == true) {
        this.teamReceive($state);
      } else {
        this.snsReceive($state);
      }
    },

    // 탭 구현
    handleClick(event) {
      if (this.currentTab != event.target.innerText) {
        this.currentTab = event.target.innerText;
        this.$refs.InfiniteLoading.stateChanger.reset(); 
        if (this.currentTab == 'SNS') {
            this.isCurrent = false
            this.snsLimit = 1
            this.snsList = []
            storage.setItem("alarmTab", 2)
        }
        else {
            this.isCurrent = true
            this.currentTab = '프로젝트'
            this.teamLimit = 1
            this.teamList = []
            storage.setItem("alarmTab", 1)
        }
      }
    },

    currentHandle() {
      if (storage.getItem("alarmTab") == 1) {
        this.isCurrent = true
        this.currentTab = '프로젝트'
      } else {
        this.isCurrent = false
        this.currentTab == 'SNS'
      }
    }
  }
}
</script>

<style scoped>
.default-tabs {
  position: relative;
  padding: 10px 0 0 0 ;
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
  margin-top: 0px;
  font-size: 20px;
}
#introduce {
  white-space: normal;
  word-break: break-all;
}

.null-area {
  width: 100%;
  height: 500px;
  font-size: 15px;
  color: #464545;
  font-weight: bold;
}

</style>