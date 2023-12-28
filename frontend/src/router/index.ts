import { createRouter, createWebHistory } from 'vue-router';
import SubjectList from '../views/SubjectList.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'subject-list',
      component: SubjectList
    }
  ]
});

export default router;
