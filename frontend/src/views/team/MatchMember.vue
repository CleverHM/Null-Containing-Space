<template>
    <div id="MacthMember">
        <Navbar></Navbar>
        <subNav></subNav>

        <div class="match-area">
            <MatchUser v-for="member in members" :key="member.nickname" :userData="member"></MatchUser>
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
    props: ['members']
}
</script>

<style scoped>

*:focus { outline:none; }

</style>