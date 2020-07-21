<template>
    <div id="studyItem" class="mt-3 w-100">
        <p class="studyTitle">알고리즘 스터디 (3/5)</p>
        <p class="studySubject">스터디 범위 : List ~ BFS </p>
        <p class="studyDate"> 기간 : 2020.07.15 ~ 2020.08.31</p>
        <div class="mt-2 d-flex justify-content-end">
            <button class="btn-detail">상세페이지</button>
            <button class="btn-contact ml-4">컨택하기</button>
        </div>
        
    </div>
</template>

<script>
export default {
    name: "studyItem",
}
</script>

<style scoped>
p{
    margin : 0;
}
.studyTitle{
    margin-top : 5px;
    margin-bottom: 5px;
}
.studySubject{
    margin-left: 10px;
    font-size:14px;
}
.studyDate{
    margin-left: 10px;
    font-size: 14px;
}
.btn-detail{
    padding: 5px;
    font-size: 14px;
    background-color: #EDECEA;
    border-radius: 3px;
}
.btn-contact{
    padding: 5px;
    font-size: 14px;
    background-color: #ACCCC4;
    border-radius: 3px;

}
</style>