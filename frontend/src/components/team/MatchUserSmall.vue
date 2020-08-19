<template>
    <div id="MatchUserSmall">
        <div class="userImg">
            <img v-if="imgExist" :src="'data:image/png;base64, ' + userData.profile" alt="user-image">
            <img v-if="!imgExist" src="@/assets/images/default_image.png" alt="user_default_image">
            <div class="userName d-flex justify-content-center">
                {{ userData.nickname }}
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "MatchUserSmall",
    props: ["userData"],
    
    data() {
        return {
            imgExist: false,
        }
    },
    
    created() {
        if (this.userData.profile == null) {
            this.imgExist = false
        } else {
            this.imgExist = true
        }
    },

}
</script>

<style scoped>
#MatchUserSmall {
    display: inline-block;
}
.userImg {
    position: relative;
    width: 70px;
    height: 70px;
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
    bottom: 5px;
    background-color: rgba( 247, 247, 247, 0.5 );
    width: 100%;
    color: #464545;
    font-size: 14px;
    white-space: nowrap; 
    overflow: hidden;
    text-overflow: clip;
}

</style>