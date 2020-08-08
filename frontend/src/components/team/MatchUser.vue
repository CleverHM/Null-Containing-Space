<template>
  <div id="MatchUser">
      
    <div class="user-area d-flex flex-row">
        <!-- 유저 이미지 -->
        <div class="img-area d-flex align-content-center">
            <img v-if="imgExist" :src="'data:image/png;base64, ' + userData.file" alt="user-image">
            <img v-if="!imgExist" src="@/assets/images/default_image.png" alt="user_default_image">
        </div>

        <!-- 유저 정보 -->
        <div class="info-area">

            <!-- 유저 이름 -->
            <div class="texttags">
                {{ userData.nickname }}
            </div>

            <!-- 선택한 주제 -->
            <div class="texttags">
                {{ subjectCheck }}
            </div>

            <!-- 버튼 영역 -->
            <div class="btn-area d-flex justify-content-between">
                <button style="background-color: #E2DFD8;" @click="goUserProfile">팀원 정보보기</button>
                <button style="background-color: #ACCCC4;">팀원 요청</button>
            </div>

        </div>
    </div>
  </div>
</template>

<script>
export default {
    name: "MatchUser",
    props: ['userData'],

    data() {
        return {
            userImg: false,
            userData2: {
                nickname: '알골마스터',
                file: null,
                preferProject: 1,
            },
            basic: {
                subjects: [
                    '웹 기술 프로젝트',
                    '웹 디자인 프로젝트',
                    'IOT 프로젝트',
                ],
            },
            subjectCheck: '',
            imgExist: false,  
        }
    },

    created() {
        if (this.userData.file == null) {
            this.imgExist = false
        } else {
            this.imgExist = true
        }

        var num = Number(this.userData.preferProject) - 1
        this.subjectCheck = this.basic.subjects[num]
    },

    methods: {
        goUserProfile() {
            this.$router.push({ name: 'profile', params: { nickname: this.userData.nickname }})
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
  margin: 7px;
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