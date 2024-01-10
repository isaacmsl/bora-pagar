import { createRouter, createWebHistory } from 'vue-router';
import SubjectList from '../views/SubjectList.vue';
import SearchUsers from '../views/SearchUsers.vue';
import SubjectsUser from '@/views/SubjectsUser.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'subject-list',
      component: SubjectList
    },
    {
      path: '/search-users',
      name: 'search-users',
      component: SearchUsers
    },
    {
      path: '/subjects-user/:googleId',
      name: 'subjects-user',
      component: SubjectsUser
    },
  ]
});

export default router;
