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
const storage=window.sessionStorage;
export default {
    name: 'subnav',
    data() {
        return {
            nickname: storage.getItem("NickName"),
        }
    },
    methods: {
        goProject() {
            this.$router.push({name: 'Main'}).catch(()=>{})
        },
        goFeed() {
            this.$router.push({name: 'FeedMain'}).catch(()=>{})
        },
        goNotice() {
            this.$router.push({name: 'Notice'}).catch(()=>{})
        },
        goProfile() {
            this.$router.push({ name: 'profile', params: { postId: this.nickname }}).catch(()=>{})
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
    color: #464545;
    padding-top: 3px;
    width: 25%;
}
</style>