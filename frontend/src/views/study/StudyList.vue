<template>
  <div id="studyList">
    <Navbar></Navbar>
    <subNav></subNav>
    <!-- 스터디 !-->
    <div id="study" class="m-2">
        <div class="d-flex justify-content-end align-items-center">
            <button class="btn-study"
        </div>
        <!-- 스터디 리스트 출력 (임의로 4개만) -->
        <div id="studyItems" class="d-flex flex-column">
            <studyItem></studyItem>
            <studyItem></studyItem>
            <studyItem></studyItem>
            <studyItem></studyItem>
            
        </div>

        <div class="fixed-bottom d-flex justify-content-center mb-3">
            <button class="btn-study">스터디 등록하기</button></div>
    </div>
  </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue'
import subNav from '../../components/common/subnav.vue'
import studyItem from "../../components/main/studyItem.vue"

export default {
  name: "studyList",
  components: {
    Navbar,
    subNav,
    studyItem,
  },
}
</script>

<style scoped>

.btn-study{
    padding: 7px;
    border-radius: 4px;
    font-size: 15px;
    background-color: #464545;
    color: #f7f7f7;

}
</style>