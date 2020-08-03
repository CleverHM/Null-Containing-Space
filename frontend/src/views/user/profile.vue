<template>
    <div style="top:0;">
        <Navbar></Navbar>
        <subNav></subNav>

        <div id="baseProfile" class="mb-4">
            <div class="d-flex align-items-center">
                <div id="profileImg"></div>
                <div class="ml-3 flex-fill">
                    <p class="mb-1 profileName">sdfsdf</p>
                    <div class="follow mb-2">
                        <button class="follower">0<br>팔로워</button>
                        <button class="following">1<br>팔로잉</button>
                    </div>
                    <button class="btn-follow">팔로우</button>
                </div>
            </div>
        </div>

        <!-- 블로그 & 깃 !-->
        <button :class="[{'btn-on' : blogLink}, {'btn-off' : !blogLink}]">
        <i class="fab fa-blogger fa-2x"></i>
        <br><p>BLOG</p>
        </button>
        <button :class="[{'btn-on' : gitLink}, {'btn-off' : !gitLink}]">
        <i class="fab fa-git-square fa-2x"></i>
        <br><p>GIT</p>
        </button>

        <!-- 자기소개 !-->
<pre id="introduce" class="my-3">
안녕하세요 알골마스터입니다.
잘지내보아요.

관심 분야
↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
</pre>

        <!-- 태그 뱃지 !-->
        <TagBadge></TagBadge>
        <TagBadge></TagBadge>
        <TagBadge></TagBadge>


        <!-- 공모전 / SNS 관리 !-->
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
        Sed ut perspiciatis unde omnis iste natus error sit
        voluptatem accusantium doloremque laudantium, totam rem aperiam,
        eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae
        dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur
        aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione
        voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum
        quia dolor sit amet, consectetur, adipisci velit, sed quia non
        numquam eius modi tempora incidunt ut labore
        et dolore magnam aliquam quaerat voluptatem.
      </div>
      <div v-if="currentTab === 'tab2'">
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
        incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
        exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure
        dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
        Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt
        mollit anim id est laborum.
      </div>
      <div v-if="currentTab === 'tab3'">
        At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium
        voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint
        occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt
        mollitia animi, id est laborum et dolorum fuga.
      </div>
    </div>


    </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue'
import subNav from '../../components/common/subnav.vue'
import TagBadge from '../../components/common/TagBadge.vue'
import TabComponent from '../../components/common/TabComponent.vue'
import Tabs from 'vue-tabs-with-active-line';

const storage = window.sessionStorage;
console.log(storage.getItem("token"))
// var User = JSON.parse(storage.getItem('User'));
// console.log(User)
const TABS = [{
    title: 'Tab 1',
    value: 'tab1',
    }, {
    title: 'Tab 2',
    value: 'tab2',
    }, {
    title: 'Tab 3',
    value: 'tab3',
}];

export default {
    name: "profile",
    components:{
        Navbar,
        subNav,
        TagBadge,
        Tabs,
    },
    data : () => {
        return {
            
            blogLink : "dsdfsdfsfd",
            gitLink : "",
            tabs: TABS,
            currentTab: 'tab1',
        }
    },
    methods : {
         handleClick(newTab) {
        this.currentTab = newTab;
        },
    }


}

</script>

<style scoped>
#profileImg{
    height: 120px;
    width: 120px;
    background-color: black;
}
.profileName{
    font-size: 20px;
    font-weight: bold;
}
.follower{
    width:50%;
}
.following{
    width:50%;

}
.btn-follow{
    width:100%;
    background-color: #464545;
    border-radius: 3px;
    color: #f7f7f7;

}
.btn-on{
    width:50%;
    font-size: 18px;
    color: #464545
}
.btn-off{
    width:50%;
    font-size: 18px;
    color: #E2DFD8;
    
}

.tabs {
  position: relative;
  top: 20px;
}

.tabs__item {
  display: inline-block;
  margin: 0 5px;
  padding: 10px;
  padding-bottom: 8px;
  font-size: 16px;
  letter-spacing: 0.8px;
  color: gray;
  text-decoration: none;
  border: none;
  background-color: transparent;
  border-bottom: 2px solid transparent;
  cursor: pointer;
  transition: all 0.25s;
}

.tabs__item_active {
  color: black;
}

.tabs__item:hover {
  border-bottom: 2px solid gray;
  color: black;
}

.tabs__item:focus {
  outline: none;
  border-bottom: 2px solid gray;
  color: black;
}

.tabs__item:first-child {
  margin-left: 0;
}

.tabs__item:last-child {
  margin-right: 0;
}

.tabs__active-line {
  position: absolute;
  bottom: 0;
  left: 0;
  height: 2px;
  background-color: black;
  transition: transform 0.4s ease, width 0.4s ease;
}
</style>