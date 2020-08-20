<template>
    <div>
        <Navbar />
        <subnav />
        <div class="tag-header">
            <div class="tag-image"><b-icon-tag /></div>
            <div class="tag-info">
                <div class="tag-name-count">
                    <div class="tag-name">#{{tag.hash}}</div>
                    <div class="tag-count">(게시물 {{ Results.length }} 개)</div>
                </div>
                <button v-if="!isFollow" class="tag-follow-btn" @click="followtag">팔로우</button>
                <button v-else class="tag-unfollow-btn" @click="followtag">팔로우 취소</button>

            </div>
        </div>
        <hr>
        <div class="tag-content">
            <myPostItem :post="post" v-for="post in Results" :key="post.pid" />
        <infinite-loading 
        @infinite="infiniteHandler" 
        ref="InfiniteLoading"
        spinner="waveDots">
        <div slot="no-results" class="null-area d-flex justify-content-center align-items-center align-content-center flex-column">
            <b-icon-x-circle scale="1.5" font-scale="1.5" class="mb-4"/>
            게시글이 존재하지 않습니다.
        </div>
        <div slot="no-more" style="display: none;"></div>
        </infinite-loading>
        </div>
        


    </div>
</template>

<script>
import Navbar from '@/components/common/Navigation.vue'
import subnav from '@/components/common/subnav.vue'
import myPostItem from '@/components/search/searchPostItem.vue'
import http from '@/util/http-common.js'
import InfiniteLoading from 'vue-infinite-loading';

const storage = window.sessionStorage;

export default {
    name: 'TagResult',
    props: [
        'tag'
    ],
    components: {
        Navbar,
        subnav,
        myPostItem,
        InfiniteLoading,
    },
    created() {
        this.isFollow = this.tag.flag
        this.Loading()
    },
    data() {
        return {
            Results: [],
            isFollow: true,
            limit: 1,
        }
    },
    methods: {
        Loading() {
              if (this.isLoading) {
                  setTimeout(this.delayfinish, 250);
              }
        },
          // 딜레이 화면
        delayfinish(){
              this.isLoading = false;
        },

        infiniteHandler($state) {
            const EACH_LEN = 10
            
            http.get(`/post/hashall/${storage.getItem("NickName")}/${this.limit}`, {
                params: {
                    hashtag: this.tag.hash + ''
                }
            })
            .then(({data}) => {
                setTimeout(() => {
                    if(data.hashfeeddata.length) {
                    this.Results = [...this.Results, ...data.hashfeeddata]
                    if (this.limit == 1) { this.isFollow = data.status }
                    $state.loaded()
                    this.limit++
                    if(data.hashfeeddata.length / EACH_LEN < 1) {
                        $state.complete()
                        }
                    } else {
                        $state.complete()
                    }
                }, 300)
            })
        },

        followtag() {
            var InputData = new FormData()
            InputData.append("nickname", storage.NickName)
            InputData.append("tagname", this.tag.hash)
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

.tag-content {
    margin-left: 10px;
}

.null-area {
  width: 100%;
  height: 400px;
  font-size: 15px;
  color: #464545;
  font-weight: bold;
}
</style>