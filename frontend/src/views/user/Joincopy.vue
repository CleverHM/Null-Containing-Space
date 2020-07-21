<template>
  <div id="JoinCopy">
    <div class="progressbar">
        <div class="progress-track"></div>
          <div id="step1" class="progress-step">
            Step One
          </div>
          <div id="step2" class="progress-step">
            Step Two
          </div>            
          <div id="step3" class="progress-step">
            Complete
          </div>
        </div>

        <button onClick=next()>Next Step</button>
    </div>
</template>

<script>
</script>

<style scoped>
.progressbar{
  position: relative;
  display: flex;
}
.progress-track{
  position: absolute;
  top:5px;
  width:100px;
  height:5px;
  background-color:#C4BCB8;
  z-index: -1;
}
.progress-step{
  position: relative;
  width:100%;
  font-size: 12px;
  text-align: center;
}
.progress-step:last-child:after{
  display: none;
}
</style>