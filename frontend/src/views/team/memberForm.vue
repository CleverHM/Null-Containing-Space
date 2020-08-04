<template>
    <div id="memberForm">
        <Navbar></Navbar>
        <subNav/>
        <div class="subject-part">
            <div class="subject-description">
                선호하는 프로젝트의 종류를 체크해주세요
            </div>
            <div id="subject-check" class="d-flex flex-column">
                
                <div v-for="n in subjectLength"
                    :key="n-1"
                    :id="n-1"
                    class="check-btn d-flex flex-row"
                    @click="isClick[n-1] = !isClick[n-1]" >
                    <b-icon-check :class="{checkPartOn: isClick[n-1], checkPartOff: !isClick[n-1]}"></b-icon-check>
                    <div :class="{checkTextOn: isClick[n-1], checkTextOff: !isClick[n-1]}">{{ subjects[n-1] }} {{ isClick[n-1] }} {{ n-1 }}</div>
                </div>

                <!-- <div class="check-btn d-flex flex-row" @click="clickChange0">
                    <b-icon-check :class="iconChange0"></b-icon-check>
                    <div :class="textChange0">{{ subjects[0] }}</div>
                </div>

                <div class="check-btn d-flex flex-row" @click="clickChange1">
                    <b-icon-check :class="iconChange1"></b-icon-check>
                    <div :class="textChange1">{{ subjects[1] }}</div>
                </div>

                <div class="check-btn d-flex flex-row" @click="clickChange2">
                    <b-icon-check :class="iconChange2"></b-icon-check>
                    <div :class="textChange2">{{ subjects[2] }}</div>
                </div> -->


                <span>체크한 이름: {{ subjectCheck }}</span>
            </div>
        </div>
    </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue'
import subNav from '../../components/common/subnav.vue'

export default {
    name: "memberForm",
    components: {
        Navbar,
        subNav,
    },
    data() {
        return {
            subjectCheck: [],
            subjects: [
                '웹 기술 프로젝트',
                '웹 디자인 프로젝트',
                'IOT 프로젝트',
            ],
            subjectLength: 3,
            isClick: [
                false, false, false, false, false,
                false, false, false, false, false,
            ]
        }
    },
    methods: {
        clickChange(event) {
            var idx = event.target.id
            console.log(idx)
            console.log(this.isClick[idx])
            this.isClick[idx] = !this.isClick[idx]
            console.log(this.isClick[idx])
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
}
.subject-description {
    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
    color: #464545;
    font-size: 14px;
    font-weight: bold;
}

.check-btn {
    width: 80%;
    height: 30px;
    border: 1px solid #EDECEA;
    border-radius: 5px;
    margin: 10px 0px 10px 0px;
    overflow: hidden;
}

.check-btn:hover {
    cursor: pointer;
    border: 1px solid #ACCCC4;
}
.check-btn:hover > .checkPartOff {
    color: #464545;
}

.checkPartOff {
    display: block;
    width: 30px;
    height: 100%;
    background-color: #EDECEA;
    color: white;
}
.checkPartOn {
    display: block;
    width: 30px;
    height: 100%;
    background-color: #ACCCC4;
    color: white;
}


.checkTextOff {
    display: inline;
    line-height: 30px;
    margin-left: 15px;
    color: #C4BCB8;
}
.checkTextOn {
    display: inline;
    line-height: 30px;
    margin-left: 15px;
    color: #464545;
}
</style>