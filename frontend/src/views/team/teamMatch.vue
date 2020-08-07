<template>
    <div>
        <Navbar></Navbar>
        <subNav></subNav>
        <div class="contentArea">
            <!-- 로딩 페이지 -->
            <div v-if="loadingOn">
                <img src="@/assets/images/lionLoading.gif" alt="" style="width:100%;">
            </div>

            <!-- 매칭된 화면 -->
            <div v-else>
                <div v-if="matchNodetail">
                    <!-- 매칭 간략화 화면 -->
                    <div class="memberImg-area">
                        <div class="d-flex flex-row justify-content-center">
                            <memberImg :memberData="memberData" :isLeader="false"></memberImg>
                            <memberImg :memberData="memberData" :isLeader="false"></memberImg>
                        </div>
                        <div class="d-flex flex-row justify-content-center">
                            <memberImg :memberData="memberData" :isLeader="false"></memberImg>
                            <memberImg :memberData="memberData" :isLeader="false"></memberImg>
                            <memberImg :memberData="memberData" :isLeader="false"></memberImg>
                        </div>
                    </div>
                    <div class="d-flex justify-content-center">
                        <button class="btnForm" @click="memberDetailOn">확인</button>
                        <button class="btnForm" @click="reMatchOn">다시 매칭</button>
                    </div>

                </div>

                <div v-else>
                    <div class="match-area">
                        <MatchUser></MatchUser>
                        <MatchUser></MatchUser>
                        <MatchUser></MatchUser>
                        <MatchUser></MatchUser>
                        <MatchUser></MatchUser>
                    </div>
                    <div class="d-flex justify-content-center">
                        <button class="btnForm" @click="reMatchOn">다시 팀원 매칭하기</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue'
import subNav from '../../components/common/subnav.vue'
import memberImg from '../../components/team/memberImg.vue'
import MatchUser from '../../components/team/MatchUser.vue'

export default {
    name: 'teamMatch',
    components: {
        Navbar,
        subNav,
        memberImg,
        MatchUser,
    },

    data() {
        return {
            loadingOn: true,
            matchNodetail: true,
            memberData: {
                file: null,
                nickname: '알골마스터',
            }
        }
    },

    created() {
        this.isLoading();
    },

    methods: {
        // 로딩하는가?
        isLoading() {
            if (this.loadingOn) {
                setTimeout(this.delayfinish, 4000);
            }
        },

        // 딜레이 화면
        delayfinish(){
            this.loadingOn = false;
        },

        // 디테일 화면 켜기
        memberDetailOn() {
            this.matchNodetail = false;
        },

        // 다시 매칭하기
        reMatchOn() {
            this.loadingOn = true;
            this.matchNodetail = true;
            this.isLoading();
        }

    }

}
</script>

<style scoped>
.contentArea {
    margin-top: 70px;
}

.btnForm {
    padding: 10px;
    margin: 10px 0px 20px 0px;
    border-radius: 20px;
    background-color: #ACCCC4;
    color: white;
    font-weight: bold;
    font-size: 17px;
}

</style>