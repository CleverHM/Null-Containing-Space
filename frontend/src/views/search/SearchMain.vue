<template>
  <div v-if="isLoading">
    <Navbar />
    <subnav />
    <!-- 검색 창 -->
      <div class="search-bar">
        <label><b-icon-search /></label>
        <input name="searchData" type="text" v-model="SearchData" placeholder="검색어를 입력하세요." @keyup.enter="search" autofocus>
        <button v-if="SearchData" @click="Initialize"> x </button>
      </div>


      
  </div>
  <div v-else id="SearchMain">
      <Navbar />
      <subnav />
      <!-- 검색 창 -->
      <div class="search-bar">
        <label><b-icon-search /></label>
        <input id="SearchBar" type="text" v-model="SearchData" placeholder="검색어를 입력하세요." @keyup.enter="search" autofocus>
        <button v-if="SearchData" @click="Initialize"> x </button>
      </div>

      <!-- 검색 결과 -->
      <div v-if="isEnter" class="search-content">
          <!-- 결과 탭  -->
            <nav class="default-tabs">
              <div class="default-tabs-item" :class="{'tabs-item_active':isCurrent}" @click="handleClick">
                <button> 사람 </button> <!-- active tab -->
                <div class="default-tabs-active-line"></div>
              </div>
              <div class="default-tabs-item" :class="{'tabs-item_active':!isCurrent}" @click="handleClick">
                <button> 태그 </button>
                <div class="default-tabs-active-line"></div>
              </div>
            </nav>
            <!-- 검색 결과 -->
            <div class="content">
                <div v-if="isCurrent">
                   <peopleItem :User="user" v-for="user in Results" :key="user.nickname"/>

                </div>
                <div v-else >
                  <tagItem :tag="tag" v-for="tag in Results" :key="tag"/>
                </div>
                <infinite-loading 
                @infinite="infiniteHandler" 
                ref="InfiniteLoading"
                spinner="waveDots">
                <div slot="no-results" class="null-area d-flex justify-content-center align-items-center align-content-center flex-column">
                  <b-icon-x-circle scale="1.5" font-scale="1.5" class="mb-4"/>
                  검색 결과가 존재하지 않습니다.
                </div>
                <div slot="no-more" style="display: none;"></div>
              </infinite-loading>
            </div>
      </div>

  </div>
</template>

<script>
import Navbar from '@/components/common/Navigation.vue'
import subnav from '@/components/common/subnav.vue'
import peopleItem from '@/components/search/peopleItem.vue'
import tagItem from '@/components/search/tagItem.vue'
import http from '@/util/http-common.js'
import InfiniteLoading from 'vue-infinite-loading';

const storage = window.sessionStorage

export default {
  name: 'SearchMain',
  components: {
    Navbar,
    subnav,
    peopleItem,
    tagItem,
    InfiniteLoading,
  },
  created() {
    var data = storage.SearchData
    if (!data) {
      this.SearchData = ""
    } else {
      this.SearchData = data
      // 탭 활성화
      this.isEnter = true;
      // 탭 내용 활성화
      if (storage.searchTab == true || storage.searchTab == undefined) {
        this.isCurrent = true;
      }
      else if (storage.searchTab == false) {
        this.isCurrent = false;
      }
    }

  },
  data() {
    return {
      SearchData: "",
      isCurrent: true,
      currentTab: "사람",
      isEnter: false,
      Results: [],
      isLoading: false,
      limit: 1,
    }
  },
  watch: {
    SearchData(data) {
      if (!data) {
        this.isEnter = false
        storage.searchTab = true
      }
    },
  },
  methods: {
  
    Initialize() {
      this.SearchData = ""
      this.isEnter = false
    },
    handleClick(event) {
      if (this.currentTab != event.target.innerText) {
        this.currentTab = event.target.innerText;
        this.limit = 1;
        this.Results = [];
        this.$refs.InfiniteLoading.stateChanger.reset(); 
        
        if (this.currentTab == '사람') {
          this.isCurrent = true
        }
        else {
          this.isCurrent = false
        }
        storage.searchTab = this.isCurrent;
      }     
    },
    search() {
      this.isLoading = true;
      this.isEnter = true;
      if(this.SearchData) {
        // 검색할땐 기존 스토리지에 있는 결과는 삭제
        storage.SearchData = this.SearchData
      
        this.isCurrent = true;
        this.limit = 1;
        this.Results = [];
        this.Loading()
      }
    },
    searchPeople($state) {
      const EACH_LEN = 10

      var InputData = new FormData()
      InputData.append("search", this.SearchData)
      InputData.append("mynickname", window.sessionStorage.NickName)
      InputData.append("pagenum", this.limit)

      http.post("/search/user", InputData)
      .then(({data}) => {
        setTimeout(() => {
          if(data.length) {
            this.Results = [...this.Results, ...data]
            $state.loaded()
            this.limit++
            if(data.length / EACH_LEN < 1) {
              $state.complete()
            }
          } else {
            $state.complete()
          }
        }, 300)
      })
    },
    searchTag($state) {
      const EACH_LEN = 10

      var InputData = new FormData()
      InputData.append("hashtag", this.SearchData)
      InputData.append("pagenum", this.limit)

      http.post("/search/hashtag", InputData)
      .then(({data}) => {
        setTimeout(() => {
          if(data.length) {
            this.Results = [...this.Results, ...data]
            $state.loaded()
            this.limit++
            if(data.length / EACH_LEN < 1) {
              $state.complete()
            }
          } else {
            $state.complete()
          }
        }, 300)
      })
    },
    infiniteHandler($state) {
      if (this.isCurrent == true) {
        this.searchPeople($state);
      } else {
        this.searchTag($state);
      }
    },
    Loading() {
      this.isLoading = true;
        if (this.isLoading) {
            setTimeout(this.delayfinish, 200);
        }
        },
    // 딜레이 화면
    delayfinish(){
        this.isLoading = false;
    },
  },

}
</script>

<style scoped>
.search-bar{
  display: flex;
  border-bottom: 1px solid #464545;
  margin: 0px 10px;
}
.search-bar > label {
  line-height: 50px;
  margin: 0 0 0 10px;

}
.search-bar > input {
  height: 50px;
  flex: 1;
  border: 0;
  

}
.search-bar > button {
  height: 50px;
  font-size: 20px;
  padding: 0 10px 0 10px;
  border: 0;
  outline: 0;
}
/* 검색 결과 */
.default-tabs {
  position: relative;
  padding: 15px 0 0 0 ;
  margin: 0px 10px;
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
  margin: 5px 10px 0 10px;
  font-size: 20px;
}
#introduce {
  white-space: normal;
  word-break: break-all;
}
.null-area {
  width: 100%;
  height: 400px;
  font-size: 15px;
  color: #464545;
  font-weight: bold;
}
</style>
