<template>
    <div id="competitionItem d-flex flex-column align-items-center">
        <div class="compImage mb-3"></div>
        <p class="competitionTitle">안녕하세요 룰루</p>
        <p class="competitionDate">
            2020.07.15
            <br />~ 2020.10.03
        </p>
    </div>
</template>

<script>
export default {
    name: "competitionItem",
}
</script>
<style scoped>
.compImage{
    margin:0;
    height: 150px;
    width:100px;
    background-color: #000000;
}
.competitionTitle{
    margin:0;
    text-align:center;
    font-size:16px;
    font-weight:bold;
}
.competitionDate{
    text-align:center;
    margin:0;
    font-size: 11px;
}
</style>