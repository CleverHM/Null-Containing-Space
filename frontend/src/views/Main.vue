<template>
    <div id="Main">
        <Navbar></Navbar>
        <subNav></subNav>
        <!-- 공모전 !-->
        <div id="competition" class="mx-2">
            <h5 class="pt-3">공모전</h5>
            <div id="compItems" class="d-flex">
                <competitionItem></competitionItem>
                <competitionItem></competitionItem>
                <competitionItem></competitionItem>
            </div>
        </div>
        <hr>
        <!-- 스터디 !-->
        <div id="study" class="mx-2">
            <div class="d-flex justify-content-between align-items-center">
                <h5 class="m-0">스터디</h5>
                <button class="btn-study">스터디 등록하기</button>
            </div>
            <div id="studyItems" class="d-flex flex-column">
                <studyItem></studyItem>
                <studyItem></studyItem>
                <studyItem></studyItem>
                <studyItem></studyItem>
                
            </div>
        </div>

    </div>
</template>

<script>
import Navbar from '../components/common/Navigation.vue'
import subNav from '../components/common/subnav.vue'
import competitionItem from "../components/main/competitionItem.vue"
import studyItem from "../components/main/studyItem.vue"

export default {
  name:"main",
  components: {
      Navbar,
      subNav,
      competitionItem,
      studyItem,
  },
};
</script>

<style scoped>
#competition{

}

#study{
    
}
.btn-study{
    padding: 7px;
    border-radius: 4px;
    font-size: 15px;
    background-color: #464545;
    color: #f7f7f7;

}
</style>