<template>
    <div id="followList">
        <Navbar />
        <!-- 개발 능력 !-->
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
                <followItem />
                
              </div>
            </div>

    </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue'
import followItem from '@/components/user/followItem.vue'
import http from "@/util/http-common.js";

export default {
    name: "followList",
    props: [
        'PageName',
        'nickname',
    ],
    components: {
        Navbar,
        followItem
    },
    created() {
        // 팔로잉 : 1, 팔로워 ; 2
        if (this.PageName == 'follower') {
            this.isCurrent = true
            this.currentTab = '팔로워'
            this.followFlag = 2
        }
        else {
            this.isCurrent = false
            this.currentTab = '팔로잉'
            this.followFlag = 1
        }
        var InputData = new FormData()
        InputData.append("nickname", this.nickname)
        InputData.append("flag", this.followFlag)
        console.log(this.nickname, this.followFlag)
        http.post("/follow/user/list", InputData)
        .then(({data}) => {
            for (var idx in data){
                this.List.push(data[idx])
            }
        })
        

    },
    data() {
        return {
            isCurrent: true,
            currentTab: "",
            followFlag : 1,
            List: [],
        }
    },
    methods : {
        handleClick(event) {
          console.log(event.target.innerText)
          this.currentTab = event.target.innerText;
          this.isCurrent = !this.isCurrent
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