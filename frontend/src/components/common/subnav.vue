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
            if (this.$route.name == 'Main') this.$router.go()
            else this.$router.push({name: 'Main'}).catch(()=>{})
        },
        goFeed() {
            if (this.$route.name == 'FeedMain') this.$router.go()
            else this.$router.push({name: 'FeedMain'}).catch(()=>{})
        },
        goNotice() {
            if (this.$route.name == "Notice") this.$router.go()
            else this.$router.push({name: 'Notice'}).catch(()=>{})
        },
        goProfile() {
            if (this.$route.name =='profile') this.$router.push({ name: 'profile', params: { nickname: this.nickname }}).catch(() => {})
            else this.$router.push({ name: 'profile', params: { nickname: this.nickname }})
            
            
            
        },
        
    }

}
</script>

<style scoped>
.btn-group{
    z-index: 1000;
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
    border: 0;
    outline: 0;
}
</style>