<template>
    <div id="profile" class="px-4" style="top:0;">
        <Navbar></Navbar>

        <div id="baseProfile" class="mb-4">
            <div class="d-flex align-items-center">
                <div id="profileImg"></div>
                <div class="ml-3 flex-fill">
                    <p class="mb-1 profileName">알골마스터</p>
                    <div class="follow mb-2">
                        <button class="follower">0<br>팔로워</button>
                        <button class="following">1<br>팔로잉</button>
                    </div>
                    <button class="btn-follow">팔로우</button>
                </div>
            </div>
        </div>

        <!-- 블로그 & 깃 !-->
        <button :class="[{'btn-on' : blogLink}, {'btn-off' : !blogLink}]">
        <i class="fab fa-blogger fa-2x"></i>
        <br><p>BLOG</p>
        </button>
        <button :class="[{'btn-on' : gitLink}, {'btn-off' : !gitLink}]">
        <i class="fab fa-git-square fa-2x"></i>
        <br><p>GIT</p>
        </button>

        <!-- 자기소개 !-->
<pre id="introduce" class="my-3">
안녕하세요 알골마스터입니다.
잘지내보아요.

관심 분야
↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
</pre>

        <!-- 태그 뱃지 !-->
        <TagBadge></TagBadge>
        <TagBadge></TagBadge>
        <TagBadge></TagBadge>

    </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue'
import TagBadge from '../../components/common/TagBadge.vue'
export default {
    name: "profile",
    components:{
        Navbar,
        TagBadge,
    },
    data : () => {
        return {
            blogLink : "dsdfsdfsfd",
            gitLink : "",
        }
    },

}

</script>

<style scoped>
#profileImg{
    height: 120px;
    width: 120px;
    background-color: black;
}
.profileName{
    font-size: 20px;
    font-weight: bold;
}
.follower{
    width:50%;
}
.following{
    width:50%;

}
.btn-follow{
    width:100%;
    background-color: #464545;
    border-radius: 3px;
    color: #f7f7f7;

}
.btn-on{
    width:50%;
    font-size: 18px;
    color: #464545
}
.btn-off{
    width:50%;
    font-size: 18px;
    color: #E2DFD8;
    
}
</style>