<template>
    <div v-if="isLoading"><Navbar /></div>
    <div v-else class="myPostView">
        <Navbar />
        <myPostItem :post="post" v-for="post in Posts" :key="post.pid"></myPostItem>
    </div>
</template>

<script>
import Navbar from '@/components/common/Navigation.vue'
import myPostItem from '@/components/user/myPostItem.vue'
import http from '@/util/http-common.js'
export default {
    name: 'myPost',
    components : {
        Navbar,
        myPostItem,
    },
    props: [
        'nickname',
    ],
    created() {
        var InputData = new FormData()
        InputData.append("nickname", this.nickname)
        http.post("/post/myLikePost", InputData)
        .then(({data}) => {
            this.Posts = data
            console.log(this.Posts)
        })
        this.Loading()
    },
    data() {
        return {
            isLoading: true,
            Posts: [],
        }
    },
    methods: {
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
</style>