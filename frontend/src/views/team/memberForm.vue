<template>
    <div id="memberForm">
        <Navbar></Navbar>
        <subNav/>
        <div class="subject-part">
            <div class="subject-description">
                선호하는 프로젝트의 종류를 체크해주세요
            </div>
            <div id="subject-check" class="d-flex flex-column">
                <div class="check-part d-flex flex-row">
                    <input type="checkbox">
                    <div class="check-btn"></div>
                    <div>웹 기술 프로젝트</div>
                </div>
                <span>체크한 이름: {{ subjectCheck }}</span>
            </div>
        </div>
    </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue'
import subNav from '../../components/common/subnav.vue'

export default {
    name: "memberForm",
    components: {
        Navbar,
        subNav,
    },
    data() {
        return {
            subjectCheck: [],
        }
    }

}
</script>

<style scoped>
.subject-part {
    margin: 70px 10px 30px 10px;
    padding: 10px;
    background-color: white;
    border-radius: 5px;
}
.subject-description {
    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
    color: #464545;
    font-size: 14px;
    font-weight: bold;
}

.check-btn {
    width: 15px;
    height: 15px;
    border: 1px solid #464545;
    margin: 5px 10px 5px 0px;
}
</style>