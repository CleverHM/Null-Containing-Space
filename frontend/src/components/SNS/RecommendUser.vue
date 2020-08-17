<template>
    <div id="RecommendUser">
        <router-link :to="{ name: 'profile', params: { nickname: userData.nickname }}">
            <div class="userImg">
                <img v-if="imgExist" :src="'data:image/png;base64, ' + userData.file" alt="user-image">
                <img v-if="!imgExist" src="@/assets/images/default_image.png" alt="user_default_image">
                <div class="userName d-flex justify-content-center">
                    {{ userData.nickname }}
                </div>
            </div>
        </router-link>
    </div>
</template>

<script>
export default {
    name: "RecommendUser",
    props: [
        "userData",
        "uid",
    ],
    
    data() {
        return {
            imgExist: false,
        }
    },
    
    created() {
        if (this.userData.file == null) {
            this.imgExist = false
        } else {
            this.imgExist = true
        }
    },

}
</script>

<style scoped>
#RecommendUser {
    display: relative;
}
.userImg {
    position: relative;
    width: 50px;
    height: 50px;
    background-color: grey;
    border-radius: 100%;
    overflow: hidden;
}

.userImg img {
    margin: 0;
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.userName {
    position: absolute;
    bottom: 1px;
    background-color: rgba( 247, 247, 247, 0.5 );
    width: 100%;
    color: #464545;
    font-size: 11px;
    white-space: nowrap; 
    overflow: hidden;
    text-overflow: clip;
    font-weight: bold;
}


</style>