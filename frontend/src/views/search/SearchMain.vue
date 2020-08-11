<template>
  <div id="SearchMain">
      <Navbar />
      <!-- 검색 창 -->
      <div class="search-bar">
        <label><b-icon-search /></label>
        <input type="text" v-model="SearchData" placeholder="검색어를 입력하세요.">
        <button v-if="SearchData" @click="Initialize"> x </button>
      </div>

      <!-- 검색 결과 -->
      <div class="search-content">
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
            <div class="content">
                <div v-if="isCurrent">
                   사람사람사람

                </div>
                <div v-else >
                  태그태그태그
                </div>
            </div>
      </div>

  </div>
</template>

<script>
import Navbar from '@/components/common/Navigation.vue'
export default {
  name: 'SearchMain',
  components: {
    Navbar,
  },
  data() {
    return {
      SearchData: "",
      isCurrent: true,
      currentTab: "사람",
    }
  },
  methods: {
    Initialize() {
      this.SearchData = ""
    },
    handleClick(event) {
          console.log(event.target.innerText)
          this.currentTab = event.target.innerText;
          if (this.currentTab == '사람') {
            this.isCurrent = true
          }
          else {
            this.isCurrent = false
          }        
        },
  },

}
</script>

<style scoped>
#SearchMain{
  padding: 0 10px;
}
.search-bar{
  display: flex;
  border-bottom: 1px solid #464545;
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
