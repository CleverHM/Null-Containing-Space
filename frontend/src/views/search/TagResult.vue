<template>
    <div>
        <Navbar />
        <subnav />
        <div class="tag-header">
            <div class="tag-image"><b-icon-tag /></div>
            <div class="tag-info">
                <div class="tag-name-count">
                    <div class="tag-name">#{{tag}}</div>
                    <div class="tag-count">(게시물 {{ Results.length }} 개)</div>
                </div>
                <button v-if="!isFollow" class="tag-follow-btn" @click="followtag">팔로우</button>
                <button v-else class="tag-unfollow-btn" @click="followtag">팔로우 취소</button>

            </div>
        </div>
        <hr>
        <div class="tag-content">
            <myPostItem :post="post" v-for="post in Results" :key="post.pid" />
        </div>


    </div>
</template>

<script>
import Navbar from '@/components/common/Navigation.vue'
import subnav from '@/components/common/subnav.vue'
import myPostItem from '@/components/user/myPostItem.vue'
import http from '@/util/http-common.js'

export default {
    name: 'TagResult',
    props: [
        'tag'
    ],
    components: {
        Navbar,
        subnav,
        myPostItem,
    },
    created() {
        var InputData = new FormData()
        InputData.append("email", window.sessionStorage.User)
        InputData.append("hashtag", this.tag)
        http.post("/post/getHashtagPostAll", InputData)
        .then(({data}) => {
            this.Results = data.hashfeeddata
            this.isFollow = data.status
        })
        this.Loading()
    },
    data() {
        return {
            Results: [],
            isFollow: true,
        }
    },
    methods: {
        Loading() {
              if (this.isLoading) {
                  setTimeout(this.delayfinish, 100);
              }
        },
          // 딜레이 화면
        delayfinish(){
              this.isLoading = false;
        },
        followtag() {
            var InputData = new FormData()
            InputData.append("nickname", window.sessionStorage.NickName)
            InputData.append("tagname", this.tag)
            http.post("/follow/tag", InputData)
            .then(({data}) => {
                this.isFollow = data
            })
        },
    }
}
</script>

<style scoped>
.tag-header{
    width: 100%;
    position: relative;
    display: flex;
}
.tag-image, .tag-info {
    display: inline-block;
}
.tag-image{
    position: relative;
    width: 100px;
    height: 100px;
}
.tag-image svg{
    position: absolute;
    left: 25px;
    top: 25px;
    width: 50px;
    height: 50px;
}
.tag-info{
    position: relative;
    flex: 1;
}
.tag-name-count{
    position: absolute;
    top: 20px;
}
.tag-name {
    display: inline;
    margin: 15px;
    font-weight: bold;
    font-size: 22px;
    text-align: center;
}
.tag-count{
    display: inline;
}
.tag-follow-btn{
    position: absolute;
    bottom: 10px;
    margin: 0px 2%;
    width: 96%;
    height: 25px;
    border: 0;
    border-radius: 5px;
    outline: 0;
    background-color: #464545;
    color: #f7f7f7;
}
.tag-unfollow-btn{
    position: absolute;
    bottom: 10px;
    margin: 0px 2%;
    width: 96%;
    height: 25px;
    border: 0;
    border-radius: 5px;
    outline: 0;
    background-color: #464545;
    color: #f7f7f7;
}
hr{
    margin: 0;
    padding: 0;
}
</style>