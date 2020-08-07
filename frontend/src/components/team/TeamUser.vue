<template>
  <div id="TeamUser">
    <div class="user-area d-flex flex-row">
        <!-- 유저 이미지 -->
        <div class="img-area d-flex align-content-center">
            <img v-if="imgExist" :src="'data:image/png;base64, ' + teamData.leaderNickname.file" alt="user-image">
            <img v-if="!imgExist" src="@/assets/images/default_image.png" alt="user_default_image">
        </div>

        <!-- 유저 정보 -->
        <div class="info-area">

            <!-- 유저 이름 -->
            <div class="texttags">
                팀장: {{ teamData.leaderNickname.nickname }}
            </div>

            <!-- 버튼 영역 -->
            <div class="btn-area d-flex justify-content-center">
                <button style="background-color: #ACCCC4;" @click="goTeamInfo">팀 정보보기</button>
            </div>

        </div>
    </div>
  </div>
</template>

<script>
export default {
    name: "TeamUser",
    props: ["teamData"],
    data() {
        return {
            imgExist: false,  
        }
    },

    created() {
        if (this.teamData.leaderNickname.file == null) {
            this.imgExist = false
        } else {
            this.imgExist = true
        }

    },

    methods: {
        goTeamInfo() {
            this.$router.push({ name: 'TeamInfo', params: { teamId: this.teamData.teamid }})
        }
    },

}
</script>

<style scoped>
.user-area {
    margin: 15px 10px 15px 10px;
    padding: 15px;
    border-radius: 20px;
    /* background-color: rgb(252, 252, 252); */
    border: 2px solid white;
}

.img-area {
    display: inline-block;
    margin-top: 5px;
    margin-bottom: 5px;
    width: 90px;
    height: 90px;
    border-radius: 100%;
    background-color: grey;
    overflow: hidden;
}

.img-area > img {
    width: 100%;
}

.info-area {
    width: 65%;
    margin-left: 20px;
}


.texttags {
  margin: 10px;
  font-size: 14px;
  font-weight: bold;
  color: #464545;
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}

.btn-area {
    margin-left: 5px;
}

.btn-area > button {
    padding: 6px;
    color: white;
    border-radius: 10px;
}

</style>