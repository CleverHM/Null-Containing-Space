<template>
    <div id="leaderForm">
        <Navbar></Navbar>
        <subNav/>
        <div class="team-form">
            <!-- 프로젝트 이름 -->
            <div class="inputForm">
                <input v-model="team.title" id="title" placeholder="프로젝트 이름(3글자 이상 입력해주세요)" type="text" />
            </div>

            <!-- 프로젝트 기술 -->
            <div class="team-use">
                프로젝트 사용 기술
            </div>

            <!-- 해시태그 작성 -->
            <div v-for="n in 4" :key="n" :id="n-1" class="mx-1 mb-2">
                <input v-model="hash[n-1]"
                    @keyup.space="addhashtag(hash[n-1], $event)" 
                    @keyup.enter="addhashtag(hash[n-1], $event)" 
                    @keyup.delete="removetag(hash[n-1], $event)" 
                    :placeholder="hashPlace[n-1]" 
                    type="text" />
                <div class="displaytags">
                    {{ hashcate[n-1] }} : 
                    <button class="btn-tags" v-for="tag in team.hashtags[n-1]" :key="tag" @click="tagRemove">{{ tag }}</button>
                </div>
            </div>
            

        </div>

    </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue'
import subNav from '../../components/common/subnav.vue'

export default {
    name: "leaderForm",

    components: {
        Navbar,
        subNav,
    },

    data() {
        return {
            team: {
                title: '',
                // front - back - db - frame 순
                hashtags: [
                    [], [], [], [],
                ],
                content: '',
            },
            hash: [
                '', '', '', '',
            ],
            hashPlace: [
                'Frontend 기술을 입력해주세요',
                'Backend 기술을 입력해주세요',
                'DataBase 기술을 입력해주세요',
                'Framework 기술을 입력해주세요',
            ],
            hashcate: [
                'Frontend', 'Backend', 'DataBase','Fremework',
            ],
        }
    },

    methods: {
        // 해시태그 추가하기
        addhashtag(hashtag, event) {
            var idx = event.currentTarget.parentElement.id

            hashtag = hashtag.replace(/(\s*)/g, "")
            if (hashtag && hashtag !="" && hashtag != " ") {
                var isDouble = this.team.hashtags[idx].find(function(n){
                    return hashtag === n
                    })
                console.log(isDouble)
                if (!isDouble) {
                    this.team.hashtags[idx].push(hashtag)
                }
            }
            
            this.hash[idx] = ""
        },

        // 해시태그 작성중 backspace키 눌러서 빼기
        removetag(hashtag, event) {
            var idx = event.currentTarget.parentElement.id
            if (!hashtag) {
                this.team.hashtags[idx].pop()
            }
        },

        // 태그 클릭해서 빼기
        tagRemove(event) {
            var idx = event.target.parentElement.parentElement.id
            this.team.hashtags[idx].splice(this.team.hashtags[idx].indexOf(event.target.innerText),1)
        },
        
    }

}
</script>

<style scoped>
.team-form {
    margin-top: 55px;
    margin-bottom: 50px;
}

.input-with-label{
    width: 100%;
}
.inputForm{
    margin: 0px 5px 10px 5px;
}

.team-use {
    margin: 20px 10px 10px 10px;
    color: #464545;
    font-size: 15px;
    font-family: Arial, Helvetica, sans-serif;
    font-weight: bold;
}

input {
    padding: 0 10px 0 10px;
    width: 100%;
    height: 40px;
    border: none;
    border-bottom: 1px solid black;
}

input::placeholder {
    font-size: 13px;
    font-weight: normal;
    color: #464545;
}


.btn-tags{
    border-radius: 10px;
    margin: 3px;
    padding: 1px 5px 1px 5px;
    background-color: #C4BCB8;
    font-weight:bold;
    color: #f7f7f7;
}

.displaytags{
    padding: 10px;
    font-size: 14px;
    font-weight: bold;
    color: #464545;
    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}

</style>