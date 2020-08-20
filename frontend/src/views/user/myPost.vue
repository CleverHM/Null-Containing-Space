<template>
    <div>
        <Navbar />
        <div v-if="isLoading"></div>

        <div v-else class="myPostView">
            <myPostItem :post="post" v-for="post in Posts" :key="post.pid"></myPostItem>
        </div>

        <infinite-loading 
        @infinite="infiniteHandler" 
        ref="InfiniteLoading"
        spinner="waveDots">
            <div slot="no-results" class="null-area d-flex justify-content-center align-items-center align-content-center flex-column">
                <b-icon-file-earmark-break scale="1.5" font-scale="1.5" class="mb-4"/>
                작성한 글이 없습니다.
            </div>
            <div slot="no-more" style="font-size: 14px; padding: 10px 0px;">더 이상 작성한 글이 존재하지 않습니다.</div>
        </infinite-loading>

    </div>
</template>

<script>
import Navbar from '@/components/common/Navigation.vue'
import myPostItem from '@/components/user/myPostItem.vue'
import http from '@/util/http-common.js'
import InfiniteLoading from 'vue-infinite-loading'


export default {
    name: 'myPost',
    components : {
        Navbar,
        myPostItem,
        InfiniteLoading,
    },
    props: [
        'nickname',
    ],
    created() {
        this.Loading()
    },
    data() {
        return {
            isLoading: true,
            Posts: [],
            limit: 1,
        }
    },
    methods: {

         infiniteHandler($state) {
            var EACH_LEN = 10;

            http.get(`/post/upload/${this.nickname}/${this.limit}`)
            .then((res) => {
                setTimeout(() => {
                if(res.data.length) {
                    this.Posts = [...this.Posts, ...res.data]
                    $state.loaded()
                    this.limit++
                    if(res.data.length / EACH_LEN < 1) {
                        $state.complete()
                    }
                } else {
                    // 끝 지정(No more data)
                    $state.complete()
                }
                }, 400)
            })
            .catch((err) => {
                if (err.response.status == 404) {
                this.$router.replace({ name: 'NotFound' })
                }
            })
        },

        Loading() {
              if (this.isLoading) {
                  setTimeout(this.delayfinish, 200);
              }
        },
          // 딜레이 화면
        delayfinish(){
              this.isLoading = false;
        },

    }    
}
</script>

<style scoped>
:root {
    --rootwidth: 0;
}
.myPostView{
    padding: 5px;
}
.null-area {
  width: 100%;
  height: 500px;
  font-size: 15px;
  color: #464545;
  font-weight: bold;
}
</style>