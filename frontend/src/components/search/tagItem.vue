<template>
    <div v-if="tag" class="follow-item" @click="goTagResult">
        <div class="follow-img">
            <b-icon-tag />
        </div>
        <div class="follow-nickname">#{{tag}}</div>
            <!-- <myPostItem></myPostItem> -->
    </div>
</template>

<script>
import myPostItem from '@/components/user/myPostItem.vue'
import http from '@/util/http-common.js'


export default {
    name: 'followItem',
    props: [
        'tag',
    ],
    components: {
        // myPostItem,
    },
    created() {
        console.log(this.tag)
    },
    methods: {  
        goTagResult() {
            this.$router.push({name: 'TagResult', params: { tag: this.tag}})
        },
        
    }

}
</script>

<style scoped>
.follow-item {
    position: relative;
    height: 60px;
    padding: 10px 10px 20px 10px;
    /* border-bottom: 1px solid #E2DFD8; */
}
.follow-img, .follow-nickname, .follow-button{
    cursor: pointer;
}
.follow-img {
    position: absolute;
    width: 50px;
    height: 50px;
    background-color: #f7f7f7;
    margin-bottom: 10px;
    border-radius: 100%;
    overflow: hidden;
}
.follow-img svg {
    position: absolute;
    width: 25px;
    height: 25px;
    left: 12.5px;
    top: 12.5px;
    margin-bottom: 10px;
    
}
.follow-nickname {
    position: absolute;
    left: 80px;
    top: 20px;
    margin-bottom: 10px;
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
    border: 0;
    outline: 0;

}
.unfollow-button {
    position: absolute;
    top: 15px;
    right: 15px;
    background-color: #C4BCB8;
    color: #f7f7f7;
    padding: 3px 7px;
    border-radius: 5px;
    font-size: 16px;
    border: 0;
    outline: 0;

}
hr {
    margin-top: 55px;
}
</style>