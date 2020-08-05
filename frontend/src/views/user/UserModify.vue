<template>
    <div>
        <Navbar></Navbar>
        <subNav></subNav>
        <div id="ModifyForm">
            <!-- 프로필 사진 -->
            <div class="image-form" id="image-form">
                <label for="profileimg">
                    <div class="image-box">
                        <img v-if="previewImg.preview" :src="previewImg.preview">
                        <img v-else-if="User.profileURL" :src="'data:image/png;base64, ' + User.profileURL" alt="image" class="img-part">
                        <img v-else src="@/assets/images/profile_default.png">
                    </div>
                    <br>
                    <!-- 프로필사진 변경 -->
                </label>
                <input
                id="profileimg"
                type="file"
                ref="files"
                @change="imageUpload" />
            </div>
            
        
            <!-- 이메일(수정불가) -->
            <div class="input-form" id="email-form">
                <label for="email">이메일</label>
                <input v-model="User.email" 
                id="email"
                type="text"
                disabled />
            </div>

            <!-- 닉네임 -->
            <div class="input-form">
                <label for="nickname">닉네임</label>
                <input v-model="User.nickname" 
                id="nickname"
                type="text"/>
                <div class="errorMsg" v-if="error.nickname"><i class="fas fa-exclamation-triangle"></i>{{ error.nickname }}</div>
                <div class="Success" v-else><i class="fas fa-exclamation-triangle"></i>사용할 수 있는 닉네임입니다.</div>

                <button @click="isDuplicate">중복체크</button>
            </div>

            <!-- git 주소 -->
            <div class="input-form">
                <label for="gitUrl">Git URL</label>
                <input v-model="User.gitUrl" 
                id="gitUrl"
                type="text"/>
            </div>

            <!-- blog 주소 -->
            <div class="input-form">
                <label for="blogUrl">Blog URL</label>
                <input v-model="User.blogUrl" 
                id="blogUrl"
                type="text"/>
            </div>
            <!-- 자기소개 -->
            <div class="textarea-form">
                <label for="blogUrl">자기소개</label>
                <textarea v-model="User.Intoduce"
                id="Introduce" />
            </div>
            <!-- 비밀번호 수정 버튼 -->
            <div class="input-form" id="password-form">
                <label for="email">비밀번호 수정</label>
                <input
                id="password"
                type="text"
                disabled />
                <button>비밀번호 수정</button>
            </div>
            <!-- 회원탈퇴 버튼 -->
            <div class="input-form" id="delete-form">
                <label for="nickname">회원탈퇴</label>
                <input 
                id="delete"
                type="text"
                disabled/>
                <button>회원탈퇴</button>
            </div>
            <!-- 수정 완료 버튼 -->
            <button id="complete" @click="Modify">수정</button>
        </div>
    </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue'
import subNav from '../../components/common/subnav.vue'
import http from "@/util/http-common.js";

const storage = window.sessionStorage;
export default {
    name: 'ModifyUser',
    components: {
        Navbar,
        subNav,
    },
    created() {
        this.getInfo()
    },
    data() {
        return {
            User : {
                email: storage.User,
                nickname: null,
                blogURL: null,
                GitURL: null,
                Introduce: null,
                profileURL: null,
                password: "gpffhdn1234"
            },
            error: {
                nickname: "",
            },
            previewImg: {
                file: "",
                preview: "",
            },
        }
    },
    methods: {
        getInfo() {
            this.User.nickname = storage.NickName;
            var InputData = new FormData();
            InputData.append("nickname", this.User.nickname)
            http
            .post("/account/myPage", InputData)
            .then(({data}) => {
                this.User.nickname = data.nickname
                this.User.Introduce = data.intro
                this.User.profileURL = data.file
                this.User.blogURL = data.blogaddr
                this.User.GitURL = data.gitaddr
            })
            .catch((err) => {
            console.log(err)
            })
        },
        isDuplicate() {
            http
            .post("/account/nickNameDuplicate", this.User.nickname)
            .then((data) => {
                console.log(data.data)
                if (data.data.status) {
                this.error.nickname=""
                }
            })
            .catch((err) => {
                this.err.nickname="사용할 수 없는 닉네임입니다."
            })
        },
        imageUpload() {
            console.log(this.$refs.files.files);
            this.previewImg = {
                file: this.$refs.files.files[0],
                preview: URL.createObjectURL(this.$refs.files.files[0]),
            }
            
            console.log(this.previewImg);
            // console.log(this.filesPreview);
        },
        Modify() {
            var InputData = new FormData()
            console.log(InputData)
            InputData.append("profile", this.previewImg.file)
            InputData.append("email", this.User.email)
            InputData.append("nickname", this.User.nickname)
            InputData.append("blog", this.User.blogURL)
            InputData.append("git", this.User.GitURL)
            InputData.append("intro", this.User.Introduce)
            InputData.append("password", this.User.password)
            http
            .post("/account/modify", InputData)
            .then(({data}) => {
                console.log(data)
                storage.NickName = this.User.nickname
                this.$router.push({name:'profile'})
            })
            .catch((err) => {
                console.log(err)
            })
        },
    },
}
</script>

<style scoped>
#ModifyForm{
    padding-top: 20px;
    width: 100vw;
}
.image-form{
    padding: 10px 0 10px 0;
    text-align: center; 
}
.image-box {
    height: 140px;
    width: 140px;
    border-radius: 100%;
    overflow: hidden;
}
.image-box img {
    margin: 0;
    width: 100%;
    height: 100%;
    object-fit: cover;
}
.image-form label {
    display: inline-block;
    padding: 0 0 0 0;
    background-color: #f7f7f7;
    color: #464545;
    vertical-align: middle;
    font-size: 15px;
    cursor: pointer;
    border-radius: 5px;
}
.image-form input[type='file'] {
    position: absolute;
    width: 0;
    height: 0;
    padding: 0;
    overflow: hidden;
    border: 0;
}

.input-form {
    position: relative;
    margin: 0 20px 30px 20px;
    height: 50px;
}
input[type="text"]{
    padding-left: 80px;
    width: 100%;
    border: 1px solid #464545;
    border-radius: 3px;
}
input[type="text"]:focus{
    border: 0;
    border-bottom: 1.2px solid #464545;
}
.input-form button{
    padding: 4px 6px 4px 6px;
    position: absolute;
    background-color: #464545;
    border-radius: 3px;
    color: #f7f7f7;
    bottom: 8.5px;
    right: 11px;
}
.input-form label {
    position: absolute;
    padding: 0;
    margin: 0;
    font-size: 13px;
    font-weight: bold;
    top: 18px; 
    left: 13px;
}
.textarea-form{
    position: relative;
    margin: 0 20px 20px 20px;
}
.textarea-form textarea{
    resize: none;
    background-color: #EDECEA;
    border: 1px solid #464545;
    border-radius: 3px;
    width: 100%;
    height: 100px;
}
#email-form input {
    color: #C4BCB8;
}
#password, #delete{
    border: 0;
}
#delete-form > button,
#password-form > button{
    background-color: #D52602;
}
#delete-form > label,
#password-form > label{
    font-size: 16px;
}
#complete{
    width: 100%;
    background-color: #464545;
    color: #f7f7f7;
    height: 50px;
    border-radius: 3px;

}
.Success{
  font-size: 13px;
  color: green;
}
</style>