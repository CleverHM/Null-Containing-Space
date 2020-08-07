<template>
    <div id="SubjectForm">
        <Navbar></Navbar>
        <subNav/>
        <div class="subject-part">
            <div class="subject-description">
                선호하는 프로젝트의 종류를 체크해주세요
            </div>
            <!-- 체크 박스 -->
            <div id="subject-check" class="d-flex flex-column">

                <div v-for="n in subjectLength"
                    :key="n-1"
                    :id="n-1"
                    class="check-btn d-flex flex-row"
                    @click="clickChange" >
                    <b-icon-check :class="{checkPartOn: isClick[n-1], checkPartOff: !isClick[n-1]}"></b-icon-check>
                    <div :class="{checkTextOn: isClick[n-1], checkTextOff: !isClick[n-1]}">{{ subjects[n-1] }}</div>
                </div>

                <!-- 없으면 실시간 반영이 안됨... -->
                <span style="display:none">{{ subjectCheck }}</span>
            </div>
            <div class="d-flex justify-content-center">
                <button v-if="!leader" class="submit-button" @click="moveMain">팀원 등록하기</button>
                <button v-if="leader" class="submit-button" @click="moveLeader">팀 세부사항 기입하기</button>
            </div>
        </div>
    </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue'
import subNav from '../../components/common/subnav.vue'
import http from "../../util/http-common.js";
import axios from 'axios';

const storage = window.sessionStorage;

export default {
    name: "SubjectForm",
    components: {
        Navbar,
        subNav,
    },

    props: [
        'beforeSubject'
        ],
        
    data() {
        return {
            subjectCheck: 0,
            subjects: [
                '웹 기술 프로젝트',
                '웹 디자인 프로젝트',
                'IOT 프로젝트',
            ],
            subjectLength: 0,
            isClick: [],
            leader: false,
        }
    },

    created() {
        
        if (this.$route.name === 'SubjectForm') {
            this.leader = false
        } else {
            this.leader = true
        }

        this.subjectLength = this.subjects.length

        // 처음에 subject 개수만큼 채워두기
        var step;
        for (step = 0 ; step < this.subjectLength; step++) {
            this.isClick.push(false)
        }
        if (this.beforeSubject == undefined) {
            this.isClick[0] = true
            this.subjectCheck = 1
        } else {
            this.isClick[this.beforeSubject - 1] = true
            this.subjectCheck = this.beforeSubject
        }
    },

    methods: {
        clickChange(event) {
            // id 값을 받아온다. 만약 자식 요소라면 부모요소로 접근해서 id값 가져오기
            var idx = event.target.id
            if (!event.target.id) {
                idx = event.target.parentElement.id
            }


            this.isClick[idx] = !this.isClick[idx]

            // id 값과 동일한 subjects의 인덱스 값이 해당 부분의 value이므로 받아서 넣어준다. 1부터 시작하므로 + 1을 넣어줌
            if (this.isClick[idx]) {
                var beforeidx = this.subjectCheck;
                this.isClick[beforeidx - 1] = false
                this.subjectCheck = Number(idx) + 1
            } else {
                this.subjectCheck = Number(idx) + 1
            }
        },

        // 팀원 시작 등록 후 메인으로
        moveMain() {
            let formData = new FormData();
            formData.append("nickname", storage.getItem("NickName"));
            formData.append("preferProject", this.subjectCheck);

            http
            .post('/team/memberRegister', formData)
            .then((res) => {
                this.$router.push({ name: 'Main' })
            })
            .catch((err) => {
                console.log(err)
            })

        },

        moveLeader() {
            this.$router.push({ name: 'createTeam', params: { subjectCheck: this.subjectCheck }});
        }

    }

}
</script>

<style scoped>
.subject-part {
    margin: 70px 10px 30px 10px;
    padding: 10px;
    background-color: white;
    border-radius: 5px;
    min-height: 500px;
}
.subject-description {
    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
    color: #464545;
    font-size: 14px;
    font-weight: bold;
    margin: 20px 0px 20px 0px;
}

.subject-check {
    margin: 30px 20px 30px 0px;
}

.check-btn {
    width: 90%;
    height: 30px;
    border: 1px solid #EDECEA;
    border-radius: 5px;
    margin: 10px 0px 10px 0px;
    overflow: hidden;
}
/* 
.check-btn:hover {
    cursor: pointer;
    border: 1px solid #ACCCC4;
}
.check-btn:hover > .checkPartOff {
    color: #464545;
} */

.checkPartOn {
    display: block;
    width: 30px;
    height: 100%;
    background-color: #ACCCC4;
    color: white;
}

.checkPartOff {
    display: block;
    width: 30px;
    height: 100%;
    background-color: #EDECEA;
    color: white;
}

.checkTextOff {
    display: inline;
    line-height: 30px;
    margin-left: 15px;
    color: #868686;
}
.checkTextOn {
    display: inline;
    line-height: 30px;
    margin-left: 15px;
    color: #464545;
}

.submit-button {
    margin-top: 20px;
    padding: 10px;
    color: white;
    background-color: #ACCCC4;
    border-radius: 10px;
}

</style>