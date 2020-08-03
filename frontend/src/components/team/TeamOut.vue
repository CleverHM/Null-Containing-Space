<template>
  <div id="teamOut">
    <div class="part-teamOut">
        <div class="out-main d-flex flex-column justify-content-center align-items-center">
            <div>
                <b-icon-file-earmark-text 
                    font-scale="3"/>
            </div>
            <div>
                진행중인 프로젝트가 없습니다.
            </div>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "teamOut",

}
</script>

<style scoped>

.part-teamOut {
    margin: 60px 10px 30px 10px;
}

.out-main {
    height: 200px;
}
.out-main > div {
    margin-top: 10px;
    margin-bottom: 10px;
}

</style>