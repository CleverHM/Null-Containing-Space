<template>
    <div class="Modal-container">
        <input id="Modal-toggle" type="checkbox">
        <button>Click me</button>


        <div class="Modal-backdrop">
            <div class="Modal-content">
                <label class="Modal-close" for="Modal-toggle">x</label>
                <h2>Modal title</h2>
                <hr />
                <p>Modal Content</p>
                <label class="Modal-close close-button" for="Modal-toggle">OK</label>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Modal',
}
</script>

<style scoped>
.Modal-container {
  margin: 0 auto;
  padding-top: 60px;
  position: relative;
  width: 160px;
}
.Modal-container button {
  display: block;
  margin: 0 auto;
  color: #fff;
  width: 160px;
  height: 50px;
  line-height: 50px;
  background: #446CB3;
  font-size: 22px;
  border: 0;
  border-radius: 3px;
  box-shadow: 0 5px 5px -5px #333;
}
.Modal-container .Modal-backdrop {
  height: 0;
  width: 0;
  opacity: 0;
  overflow: hidden;
  transition: opacity 0.2s ease-in;
}
.Modal-container #Modal-toggle {
  position: absolute;
  left: 0;
  top: 0;
  height: 100%;
  width: 100%;
  margin: 0;
  opacity: 0;
  cursor: pointer;
}


.Modal-container #Modal-toggle:checked {
  width: 100vw;
  height: 100vh;
  position: fixed;
  left: 0;
  top: 0;
  z-index: 9;
  opacity: 0;
}
.Modal-container #Modal-toggle:checked ~ .Modal-backdrop {
  background-color: rgba(0, 0, 0, 0.6);
  width: 100vw;
  height: 100vh;
  position: fixed;
  left: 0;
  top: 0;
  z-index: 9;
  pointer-events: none;
  opacity: 1;
}
.Modal-container #Modal-toggle:checked ~ .Modal-backdrop .Modal-content {
  background-color: #fff;
  max-width: 400px;
  width: 100%;
  height: 280px;
  padding: 10px 30px;
  position: absolute;
  left: calc(50% - 200px);
  top: 12%;
  border-radius: 4px;
  z-index: 999;
  pointer-events: auto;
  cursor: auto;
  box-shadow: 0 3px 7px rgba(0, 0, 0, 0.6);
}

@media (max-width: 400px) {

.Modal-container #Modal-toggle:checked ~ .Modal-backdrop .Modal-content { left: 0; }
}

.Modal-container #Modal-toggle:checked ~ .Modal-backdrop .Modal-content .Modal-close {
  color: #666;
  position: absolute;
  right: 2px;
  top: 0;
  padding-top: 7px;
  background: #fff;
  font-size: 16px;
  width: 25px;
  height: 28px;
  font-weight: bold;
  text-align: center;
  cursor: pointer;
}

.Modal-container #Modal-toggle:checked ~ .Modal-backdrop .Modal-content .Modal-close.close-button {
  top: initial;
  bottom: 20px;
  right: 20px;
  background: #4CAF50;
  color: #fff;
  width: 50px;
  border-radius: 2px;
  font-size: 14px;
  font-weight: normal;
}

.Modal-container #Modal-toggle:checked ~ .Modal-backdrop .Modal-content .Modal-close.button:hover {
  color: #fff;
  background: #1E824C;
}

.Modal-container #Modal-toggle:checked ~ .Modal-backdrop .Modal-content .Modal-close:hover { color: #333; }
</style>