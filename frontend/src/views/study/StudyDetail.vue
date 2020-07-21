<template>
  <div id="studyDetail">
    <Navbar></Navbar>
    <subNav></subNav>
    <div class="study-area">
        <h4 class="studyTitle">알고리즘 스터디</h4>   
        <p class="created-data d-flex justify-content-end mr-2">작성일: 2020-07-15</p> 
        <!-- 탭 사용: 스터디 정보 | 참여 인원-->
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
                1번 탭
            </div>
            <div v-if="currentTab === 'tab2'">
                <vue-ellipse-progress 
                    :data="circles"                    
                    :progress="progress"
                    :angle="-90"
                    color="#ACCCC4"
                    :colorFill="colorFillGradient"
                    emptyColor="#EDECEA"
                    :emptyColorFill="emptyColorFillGradient"                      
                    :size="300"
                    :thickness="10"
                    emptyThickness="10%"
                    lineMode="in 10"
                    :legend="true"
                    :legendValue="180"
                    legendClass="legend-custom-style"
                    dash="60 0.9"
                    animation="default 1000 400"
                    :noData="false"
                    :loading="false"                      
                    fontColor="#E2DFD8"
                    :half="false"
                    :gap="10"
                    dot="10 #ACCCC4"
                    fontSize="5rem">
                    
                    <span slot="legend-value">/20</span>
                    <p slot="legend-caption">현황</p>
                    
                    </vue-ellipse-progress>
            </div>
        </div>

    </div>


  </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue'
import subNav from '../../components/common/subnav.vue'
import Tabs from 'vue-tabs-with-active-line';

const TABS = [{
    title: '스터디 정보',
    value: 'tab1',
    }, {
    title: '참여 인원',
    value: 'tab2',
}];

export default {
  name: "studyDetail",
  components: {
    Navbar,
    subNav,
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
.study-area {
    margin-top: 10px;
    margin: 10px;
    padding: 20px;
    background-color: white;
}
.content > div {
    width: 50%;
}
</style>