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
              <div v-if="isCurrent">
                    <notice1 />
                    <notice1 />
                

              </div>
              <div v-else >
                    <notice1 />
                    <notice1 />
                    <notice1 />
                
              </div>
            </div>

    </div>
</template>

<script>
import Navbar from '../components/common/Navigation.vue'
import subNav from '../components/common/subnav.vue'
import notice1 from '../components/notice/notice1.vue'
export default {
    name: 'notice',
    components: {
        Navbar,
        subNav,
        notice1,
    },
    data() {
        return {
            isCurrent: true,
            currentTab: "",
        }
    },
    methods: {
        handleClick(event) {
            this.currentTab = event.target.innerText;
            if (this.currentTab == 'SNS') {
                this.isCurrent = false
                this.followFlag = 1
            }
            else {
                this.isCurrent = true
                this.currentTab = '프로젝트'
                this.followFlag = 2
            }
            console.log(this.isCurrent)
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
  margin-top: 30px;
  font-size: 20px;
}
#introduce {
  white-space: normal;
  word-break: break-all;
}
</style>