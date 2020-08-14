<template>
  <div id="followList">
    <Navbar />
      <nav class="default-tabs">
        <div class="default-tabs-item" :class="{'tabs-item_active':isCurrent}" @click="handleClick">
          <button> 팔로워 </button> <!-- active tab -->
          <div class="default-tabs-active-line"></div>
        </div>
        <div class="default-tabs-item" :class="{'tabs-item_active':!isCurrent}" @click="handleClick">
          <button> 팔로잉 </button>
          <div class="default-tabs-active-line"></div>
        </div>
      </nav>
      
      <div class="content">
        <div v-if="isCurrent">
          
          <followItem :User="User" v-for="User in List" :key="User.nickname"/>

        </div>
        <div v-else >
          <followItem :User="User" v-for="User in List" :key="User.nickname"/>
          
        </div>
        
        <!-- 무한스크롤 -->
        <infinite-loading 
          @infinite="infiniteHandler" 
          ref="InfiniteLoading"
          spinner="waveDots">
          <div slot="no-results" style="font-size: 14px; padding: 10px 0px; margin-top:20px;">아직 {{ currentTab }}(이/가) 존재하지 않습니다.</div>
          <div slot="no-more" style="display: none;"></div>
        </infinite-loading>
      </div>

  </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue'
import followItem from '@/components/user/followItem.vue'
import http from "@/util/http-common.js";
import InfiniteLoading from 'vue-infinite-loading';

const storage = window.sessionStorage;

export default {
  name: "followList",
  props: [
    'PageName',
    'nickname',
  ],
  components: {
    Navbar,
    followItem,
    InfiniteLoading,
  },
  created() {
    this.getfollow();
  },
  data() {
    return {
      isCurrent: true,
      currentTab: '팔로워',
      limit: 1,
      followFlag : 1,
      List: [],
    }
  },
  methods : {
    getfollow() {
      if (this.PageName == 'following') {
          this.isCurrent = false
          this.currentTab = '팔로잉'
          this.followFlag = 1
      }
      else {
        this.isCurrent = true
        this.currentTab = '팔로워'
        this.followFlag = 2
        }
    },

    dataReceive($state) {
      const EACH_LEN = 10

      http.get(`/follow/user/${this.nickname}/${this.followFlag}/${this.limit}`)
      .then((res) => {
        setTimeout(() => {
          if(res.data.length) {
            this.List = [...this.List, ...res.data]
            $state.loaded()
            this.limit++
            if(res.data.length / EACH_LEN < 1) {
              $state.complete()
            }
          } else {
            // 끝 지정(No more data)
            $state.complete()
          }
        }, 400)

      })
        
    },

    // 무한 스크롤
    infiniteHandler($state) {
      this.dataReceive($state);
    },

    handleClick(event) {
      if (this.currentTab != event.target.innerText) {
        this.currentTab = event.target.innerText;
        this.$refs.InfiniteLoading.stateChanger.reset(); 
        this.List = []
        this.limit = 1
        if (this.currentTab == '팔로잉') {
          this.isCurrent = false
          this.followFlag = 1
        }
        else {
          this.isCurrent = true
          this.followFlag = 2
        }
      }
    },

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
  /* margin-top: 30px; */
  font-size: 20px;
}
#introduce {
  white-space: normal;
  word-break: break-all;
}

</style>