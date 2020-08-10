<template>
    <div v-if="isLoading"></div>
    <div v-else>
        <Navbar />
        <myPostItem></myPostItem>
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
        http.post("/post/myPost", InputData)
        .then(({data}) => {
            console.log(data)
        })
        this.Loading()
    },
    data() {
        return {
            isLoading: true,
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

    }    
}
</script>

<style scoped>

</style>