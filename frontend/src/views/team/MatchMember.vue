<template>
    <div id="MacthMember">
        <Navbar></Navbar>
        <subNav></subNav>
        <div class="displaytags d-flex justify-content-center">
            아래 인재들과 함께 프로젝트를 진행해보세요.
        </div>

        <!-- 팀 / 명세서-->
        <div id="tabs">
        <div class="tab-button d-flex justify-content-center">
            <button v-for="(tab, index) in tabs"
            :class="{active: currentTab === index}"
            @click="currentTab = index"
            :key="tab">
            {{ tab }}
            </button>
        </div>
        <div class="tab-content">
            <div v-if="currentTab == 0" style="margin-left:10px; margin-top:20px;">
                <div class="match-area">
                    <MatchUser v-for="member in team.members1" :key="member.nickname" :userData="member"></MatchUser>
                </div>
            </div>

            <div v-if="currentTab == 1" style="margin-left:10px; margin-top:20px;">
                <div class="match-area">
                    <MatchUser v-for="member in team.members2" :key="member.nickname" :userData="member"></MatchUser>
                </div>
            </div>
        </div>
        </div>

        <div class="d-flex justify-content-center">
            <button class="btnForm" @click="reMatchOn">다시 팀원 매칭하기</button>
        </div>
    </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue'
import subNav from '../../components/common/subnav.vue'
import MatchUser from '../../components/team/MatchUser.vue'

const storage = window.sessionStorage;

export default {
    name: "MatchMember",
    components: {
        Navbar,
        subNav,
        MatchUser,
    },
    props: ['members'],
    data() {
        return {
            team: {
                members: null,
                members1: null,
                members2: null,
            },
            tabs: [
            '같은 주제 선택',
            '다른 주제 선택',
            ],
            currentTab: 0,
        }
    },

    created() {
        if (this.members == undefined) {
            this.team.members = JSON.parse(storage.getItem("members"))
        } else {
            this.team.members = this.members
        }
        this.team.members1 = this.team.members.slice(0, 5)
        this.team.members2 = this.team.members.slice(5, 10)
    },

    methods: {
        // 다시 매칭하기
        reMatchOn() {
            this.$router.push({ name: 'teamMatch' })
        },
        
        // tab 이동
        handleClick(newTab) {
            this.currentTab = newTab;
        },


    }
}
</script>

<style scoped>

*:focus { outline:none; }


.btnForm {
    padding: 10px;
    margin: 10px 0px 20px 0px;
    border-radius: 20px;
    background-color: #ACCCC4;
    color: white;
    font-weight: bold;
    font-size: 17px;
}


.displaytags {
    margin-top: 70px;   
    padding: 10px;
    font-size: 14px;
    font-weight: bold;
    color: #464545;
    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}


.tab-button > button {
  padding: 10px 20px 10px 20px;
  font-size: 14px;
}

.tab-button > .active {
  border-bottom: 1.5px solid #464545;
}

</style>