<template>
    <div>
        <Navbar></Navbar>
        <subNav></subNav>
        <div id="ModifyForm">
            <!-- 이메일(수정불가) -->
            <div class="input-form" id="email-form">
                <label for="email">이메일</label>
                <input v-model="user.email" 
                id="email"
                type="text"
                disabled />
            </div>

            <!-- 닉네임 -->
            <div class="input-form">
                <label for="nickname">닉네임</label>
                <input v-model="user.nickname" 
                id="nickname"
                type="text"/>
                <div class="errorMsg" v-if="error.nickname">{{ error.nickname }}</div>
                <button @click="isDuplicate">중복체크</button>
            </div>

            <!-- git 주소 -->
            <div class="input-form">
                <label for="gitUrl">Git URL</label>
                <input v-model="user.gitUrl" 
                id="gitUrl"
                type="text"/>
            </div>

            <!-- blog 주소 -->
            <div class="input-form">
                <label for="blogUrl">Blog URL</label>
                <input v-model="user.blogUrl" 
                id="blogUrl"
                type="text"/>
            </div>
            <!-- 자기소개 -->
            <div class="textarea-form">
                <label for="blogUrl">자기소개</label>
                <textarea v-model="user.Intoduce"
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
            <button id="complete">수정</button>
        </div>
    </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue'
import subNav from '../../components/common/subnav.vue'


export default {
    name: 'ModifyUser',
    components: {
        Navbar,
        subNav,
    },
    data() {
        return {
            user : {
                email: "hello@naver.com",
                nickname: "알골마스터",
                password: "",
                gitUrl: "http://github.com/hello/",
                blogUrl: "http://hello.github.io/",
                Intoduce: "",
            },
            error: {
                nickname: "",
            },
        }
    },
    methods: {
        isDuplicate() {


        },
    },
}
</script>

<style scoped>
#ModifyForm{
    padding-top: 20px;
    width: 100vw;
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
</style>