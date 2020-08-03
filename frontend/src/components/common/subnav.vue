<template>
    <div class="btn-group">
        <button class="navbtn" @click="goProject">
            <b-icon-house-fill></b-icon-house-fill><br />
            <div class="name"><small>프로젝트</small></div>
        </button>
        <button class="navbtn" @click="goFeed">
            <b-icon-file-text></b-icon-file-text><br />
            <div class="name"><small>SNS</small></div>
        </button>
        <button class="navbtn" @click="goNotice">
            <b-icon-bell-fill></b-icon-bell-fill><br />
            <div class="name"><small>알림</small></div>
        </button>
        <button class="navbtn" @click="goProfile">
            <b-icon-person-circle></b-icon-person-circle><br />
            <div class="name"><small>프로필</small></div>
        </button>
    </div>
</template>

<script>
export default {
    name: 'subnav',
    methods: {
        goProject() {
            this.$router.push("/main")
        },
        goFeed() {
            this.$router.push("/feed")
        },
        goNotice() {
            this.$router.push("/notice")
        },
        goProfile() {
            this.$router.push("/user/profile")
        },
        
    }

}
</script>

<style scoped>
.btn-group{
    height: 50px;
    width: 100%;
    position: fixed;
    bottom: 0;
    opacity: 1;
    background-color: #EDECEA;
}
.navbtn{
    padding-top: 3px;
    width: 25%;
}
</style>