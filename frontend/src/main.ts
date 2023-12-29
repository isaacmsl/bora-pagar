import './assets/main.css';

import { createApp } from 'vue';
import { createPinia } from 'pinia';
import App from './App.vue';
import router from './router';
import 'vuetify/styles';
import { createVuetify } from 'vuetify';
import vue3GoogleLogin from 'vue3-google-login';

const GOOGLE_LOGIN_CLIENT_ID = '384114633752-bdes1tkaej8ah8mlps8ungf18k1976ls.apps.googleusercontent.com';

const app = createApp(App);
const vuetify = createVuetify({
  theme: {
    defaultTheme: 'customDarkTheme',
    themes: {
      customDarkTheme: {
        dark: true,
        colors: {
          background: '#1D2030',
          surface: '#FFFFFF',
          primary: '#2591D7',
          'primary-darken-1': '#0E6BA8',
          secondary: '#0A2472',
          'secondary-darken-1': '#001C55',
          error: '#B00020',
          info: '#2196F3',
          success: '#4CAF50',
          warning: '#FB8C00'
        }
      }
    }
  },
  icons: {
    defaultSet: 'mdi'
  }
});

app.use(vue3GoogleLogin, {
  clientId: GOOGLE_LOGIN_CLIENT_ID
});
app.use(createPinia());
app.use(router);
app.use(vuetify);

app.mount('#app');