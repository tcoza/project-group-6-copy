import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import TutoringApp from '@/components/TutoringApp' //to remove
import SignIn from '@/components/Signin'
import Homepage from '@/components/Homepage'
import Students from '@/components/Students'
import Tutors from '@/components/Tutors'
import CourseSubjectSchool from '@/components/CourseSubjectSchool'
import Evaluations from '@/components/Evaluations'
import TutoringSessions from '@/components/TutoringSessions'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/hello',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/app',
      name: 'TutoringApp',
      component: SignIn
    },
    {
      path: '',
      name: 'Start',
      component: SignIn
    },
    {
      path: '/signin',
      name: 'SignIn',
      component: SignIn
    },
    {
      path: '/home',
      name: 'Homepage',
      component: Homepage
    },
    {
      path: '/students',
      name: 'Students',
      component: Students
    },
    {
      path: '/tutors',
      name: 'Tutors',
      component: Tutors
    },
    {
      path: '/css',
      name: 'CourseSubjectSchool',
      component: CourseSubjectSchool
    },
    {
      path: '/evaluations',
      name: 'Evaluations',
      component: Evaluations
    },
    {
      path: '/tutoringsessions',
      name: 'TutoringSessions',
      component: TutoringSessions
    }
  ]
})
