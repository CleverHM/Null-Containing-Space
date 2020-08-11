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
    }
  },
  methods: {
    Initialize() {
      this.SearchData = ""
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

.serach-content {

}
</style>
