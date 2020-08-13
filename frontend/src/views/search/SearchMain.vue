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

const storage = window.sessionStorage

export default {
  name: 'SearchMain',
  components: {
    Navbar,
    subnav,
    peopleItem,
    tagItem,
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
      console.log(storage.istagTab)
      if (storage.ispeopleTab) {
        this.isCurrent = true;
        this.Results = JSON.parse(storage.peopleResult)
      }
      else if (storage.istagTab) {
        this.isCurrent = false;
        console.log(storage.tagResult)
        console.log(JSON.parse(storage.tagResult))
        this.Results = JSON.parse(storage.tagResult)
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
    }
  },
  watch: {
    SearchData(data) {
      if (!data) this.isEnter = false
    },
  },
  methods: {
  
    Initialize() {
      this.SearchData = ""
      this.isEnter = false
    },
    handleClick(event) {
          console.log(event.target.innerText)
          this.currentTab = event.target.innerText;
          if (this.currentTab == '사람') {
            this.isCurrent = true
            if(this.SearchData) this.searchPeople()
          }
          else {
            this.isCurrent = false
            if(this.SearchData) this.searchTag()
            this.Loading()
          }        
    },
    search() {
      this.isLoading = true;
      this.isEnter = true;
    if(this.SearchData) {
      // 검색할땐 기존 스토리지에 있는 결과는 삭제
      storage.SearchData = this.SearchData
      storage.removeItem('peopleResult')
      storage.removeItem('tagResult')
    
      this.isCurrent = true;
      this.searchPeople()
      this.Loading()
    }
      

    },
    searchPeople() {
      // 스토리지에서 사람 탭의 활성화 정보 담기
      storage.ispeopleTab = true;
      storage.removeItem("istagTab")
      // 스토리지에 사람탭의 결과가 있으면
      if (storage.peopleResult) {
        this.Results = JSON.parse(storage.peopleResult)
      }
      // 스토리지에 사람탭의 결과가 없으면 axios로 불러오기
      else {
        var InputData = new FormData()
        InputData.append("search", this.SearchData)
        InputData.append("mynickname", window.sessionStorage.NickName)
        http.post("/search/user", InputData)
        .then(({data}) => {
          this.Results = data;
          storage.peopleResult = JSON.stringify(data)        
        })
      }
      
    },
    searchTag() {
      // 스토리지에서 tab정보 활성화, 사람탭정보 비활성화
      storage.istagTab = true;
      storage.removeItem("ispeopleTab")
      // 스토리지에 태그결과가 있으면
      if (storage.tagResult) this.Results = JSON.parse(storage.tagResult)
      // 스토리지에 태그결과가 없으면
      else {
        var InputData = new FormData()
        InputData.append("hashtag", this.SearchData)
        http.post("/search/hashtag", InputData)
        .then(({data}) => {
          this.Results = data;
          console.log(this.Results)
          storage.tagResult = JSON.stringify(data)
        })
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
</style>
