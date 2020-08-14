import Vue from 'vue'
import VueGoodWizard from 'vue-good-wizard'
import App from './App.vue'
import VueRouter from 'vue-router';
import routes from './routes'
import store from './vuex/store'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import { library } from '@fortawesome/fontawesome-svg-core'
import { fas } from '@fortawesome/free-solid-svg-icons'
import { far } from '@fortawesome/free-regular-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import VueEllipseProgress from 'vue-ellipse-progress';
import UploadImage from 'vue-upload-image';
import $ from 'jquery'


//fontawesome
library.add(fas)
library.add(far)
Vue.component('font-awesome-icon', FontAwesomeIcon)
Vue.config.productionTip = false

// step progress bar (Join page)
Vue.use(VueGoodWizard)

Vue.use(VueRouter)
// Bootstrap
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

// vueEllipse
Vue.use(VueEllipseProgress)

// vue uploadimage
Vue.component('upload-image', UploadImage)

const router = new VueRouter({
    mode:'history',
    routes,
});

new Vue({
    router,
    store,
    render: h => h(App),
}).$mount('#app');


