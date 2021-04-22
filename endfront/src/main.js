import Vue from 'vue'
import App from './App.vue'
import VueCountryCode from "vue-country-code-select";
import Element from 'element-ui'
Vue.use(Element)
// or
import {
  Select,
  Button
  // ...
} from 'element-ui'

import './plugins/element.js'
import router from './router'
// fade/zoom 等
import 'element-ui/lib/theme-chalk/base.css';
// collapse 展开折叠
import CollapseTransition from 'element-ui/lib/transitions/collapse-transition';

Vue.component(CollapseTransition.name, CollapseTransition)
Vue.component(Select.name, Select)
Vue.component(Button.name, Button)
Vue.config.productionTip = false
Vue.use(VueCountryCode);
new Vue({
  router,
  render: h => h(App),

}).$mount('#app')
