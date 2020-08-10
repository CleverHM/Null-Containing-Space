<template>
    <div v-if="User" class="follow-item">
        <div class="follow-img" @click="goProfile">
            <img v-if="User.file" :src="'data:image/png;base64, ' + User.file" alt="image">
            <img v-else src="@/assets/images/default_image.png">
        </div>
        <div class="follow-nickname" @click="goProfile">{{ User.nickname }}</div>
        <button class="follow-button">팔로우</button>
    </div>
</template>

<script>
export default {
    name: 'followItem',
    props: [
        'User',
    ],
    created() {
        console.log(this.User)
    },
    methods: {
        goProfile() {
            this.$router.push({ name: 'profile', params: { nickname: this.User.nickname }});
        },
    }

}
</script>

<style scoped>
.follow-item {
    position: relative;
    height: 60px;
    padding: 5px 10px;
    margin-bottom: 10px;
}
.follow-img, .follow-nickname, .follow-button{
    cursor: pointer;
}
.follow-img {
    position: absolute;
    width: 50px;
    height: 50px;
    background-color: #464545;
    border-radius: 100%;
    overflow: hidden;
}
.follow-img img {
    width: 100%;
    height: 100%;
    
}
.follow-nickname {
    position: absolute;
    left: 80px;
    top: 15px;
}
.follow-button {
    position: absolute;
    top: 15px;
    right: 15px;
    background-color: #464545;
    color: #f7f7f7;
    padding: 3px 7px;
    border-radius: 5px;
    font-size: 16px;

}
</style>