<template>
    <div class="btn-group">
        <button class="navbtn">
            <b-icon-house-fill></b-icon-house-fill><br />
            <div class="name" @click="goProject"><small>프로젝트</small></div>
        </button>
        <button class="navbtn">
            <b-icon-file-text></b-icon-file-text><br />
            <div class="name"><small>SNS</small></div>
        </button>
        <button class="navbtn">
            <b-icon-bell-fill></b-icon-bell-fill><br />
            <div class="name"><small>알림</small></div>
        </button>
        <button class="navbtn">
            <b-icon-person-circle></b-icon-person-circle><br />
            <div class="name"><small>프로필</small></div>
        </button>
    </div>
</template>

<script>
export default {
    name: 'subnav2',
    methods: {
        goProject() {
            this.$router.push("/main")
        }
    }

}
</script>

<style scoped>
.btn-group{
    height: 50px;
    width: 100%;
    position: fixed;
    bottom: 0;
    background-color: #EDECEA;
}
.navbtn{
    padding-top: 3px;
    width: 25%;
}
</style>