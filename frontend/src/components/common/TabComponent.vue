<template>
  <div>
    <tabs
      :tabs="tabs"
      :currentTab="currentTab"
      :wrapper-class="'default-tabs'"
      :tab-class="'default-tabs__item'"
      :tab-active-class="'default-tabs__item_active'"
      :line-class="'default-tabs__active-line'"
      @onClick="handleClick"
    />
    <div class="content">
      <div v-if="currentTab === 'tab1'">
        Sed ut perspiciatis unde omnis iste natus error sit
        voluptatem accusantium doloremque laudantium, totam rem aperiam,
        eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae
        dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur
        aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione
        voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum
        quia dolor sit amet, consectetur, adipisci velit, sed quia non
        numquam eius modi tempora incidunt ut labore
        et dolore magnam aliquam quaerat voluptatem.
      </div>
      <div v-if="currentTab === 'tab2'">
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
        incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
        exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure
        dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
        Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt
        mollit anim id est laborum.
      </div>
      <div v-if="currentTab === 'tab3'">
        At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium
        voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint
        occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt
        mollitia animi, id est laborum et dolorum fuga.
      </div>
    </div>
  </div>
</template>

<script>
import Tabs from 'vue-tabs-with-active-line';
const TABS = [{
  title: 'Tab 1',
  value: 'tab1',
}, {
  title: 'Tab 2',
  value: 'tab2',
}, {
  title: 'Tab 3',
  value: 'tab3',
}];
export default {
  components: {
    Tabs,
  },
  data: () => ({
    tabs: TABS,
    currentTab: 'tab1',
  }),
  methods: {
    handleClick(newTab) {
      this.currentTab = newTab;
    },
  },
}
</script>

<style lang="scss">
.default-tabs {
  position: relative;
  margin: 0 auto;
  &__item {
    display: inline-block;
    margin: 0 5px;
    padding: 10px;
    padding-bottom: 8px;
    font-size: 16px;
    letter-spacing: 0.8px;
    color: gray;
    text-decoration: none;
    border: none;
    background-color: transparent;
    border-bottom: 2px solid transparent;
    cursor: pointer;
    transition: all 0.25s;
    &_active {
      color: black;
    }
    &:hover {
      border-bottom: 2px solid gray;
      color: black;
    }
    &:focus {
      outline: none;
      border-bottom: 2px solid gray;
      color: black;
    }
    &:first-child {
      margin-left: 0;
    }
    &:last-child {
      margin-right: 0;
    }
  }
  &__active-line {
    position: absolute;
    bottom: 0;
    left: 0;
    height: 2px;
    background-color: black;
    transition: transform 0.4s ease, width 0.4s ease;
  }
}
.content {
  margin-top: 30px;
  font-size: 20px;
}
</style>