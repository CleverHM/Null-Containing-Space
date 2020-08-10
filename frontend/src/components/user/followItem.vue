<template>
    <div class="follow-item">
        <div class="follow-img"></div>
        <div class="follow-nickname">{{ User.nickname }}</div>
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

}
</script>

<style scoped>
.follow-item {
    position: relative;
    height: 60px;
    padding: 5px 10px;
    margin-bottom: 10px;
}
.follow-img {
    position: absolute;
    width: 50px;
    height: 50px;
    background-color: #464545;
    border-radius: 100%;
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