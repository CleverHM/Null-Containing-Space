<template>
  <div id="teamIn">
    <div class="team-title d-flex justify-content-center">
      {{ teamData.title }}
    </div>

    <!-- 팀 / 명세서-->
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

        <!-- 시작일 -->
        <div class="displaytags">
          프로젝트 진행 : {{ diffTime }}
        </div>
        
        <!-- 팀 정보 -->
        <div id="team" class="my-3">
          <div class="d-flex justify-content-start align-items-center">
            <div class="displaytags">
              팀장
            </div>
            <div class="team-member-area">
              <memberImg :memberData="teamData.leaderNickname" :isLeader="true" class="mx-2"></memberImg>
            </div>
          </div>
          
          <div class="d-flex justify-content-start align-items-center mt-4">
            <div class="displaytags">
              팀원
            </div>
            <div class="team-member-area d-flex flex-row align-items-center">
              <memberImg v-for="mem in teamData.members" :key="mem.nickname" :memberData="mem" :isLeader="false" class="ml-2"></memberImg>
            </div>
          </div>
        </div>

      </div>
      
      <div v-if="currentTab === 'tab2'">
        
        <!-- 프로젝트 명세서 -->
        <div class="team-spec">
            <specs :teamData="teamData"/>
        </div>
      
      </div>
    </div>
    

    <!-- 팀원 매칭 버튼 -->
    <div class="submit-area d-flex justify-content-center">
      <button v-if="ifLeader" class="submit-button" @click="teamFind">팀원 매칭하기</button>
    </div>
    
  </div>
</template>

<script>
import specs from './Specification.vue'
import memberImg from './memberImg.vue'
import Tabs from 'vue-tabs-with-active-line';

const storage = window.sessionStorage;
var now = new Date(); // 현재 시간 받아오기

const TABS = [{
    title: '프로젝트 팀 정보',
    value: 'tab1',
    },{
    title: '프로젝트 명세서',
    value: 'tab2',
    },
];

export default {
  name: "teamIn",

  components: {
    specs,
    memberImg,
    Tabs,
  },

  props: ['teamData'],

  data() {
    return {
      ifLeader: false,
      tabs: TABS,
      currentTab: 'tab1',
      diffTime: '',
    }
  },

  created() {
    if (storage.getItem("NickName") == this.teamData.leaderNickname.nickname) {
      this.ifLeader = true
    } else {
      this.ifLeader = false
    }

    // 받아온 date 값이 string type 이므로 date type으로 변환 후 체크하는 methods 호출
    var postDate = new Date(this.teamData.createDate)
    this.diffTime = this.dateCheck(postDate);

  },

  methods: {
    // 팀원찾기 페이지로 넘어가기
    teamFind() {
      console.log('매칭하기')
    },

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
        var calday = diff_day + '일='
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


  },

}
</script>

<style scoped>
.team-title {
  padding: 0px 10px 5px 10px;
  color: #464545;
  font-family:Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
  font-size: 25px;
  font-weight: bold;
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


</style>