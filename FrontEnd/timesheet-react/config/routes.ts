export default [
  {
    path: '/user',
    layout: false,
    routes: [
      {
        path: '/user',
        routes: [
          {
            name: 'login',
            path: '/user/login',
            component: './user/Login',
          },
        ],
      },
    ],
  },
  {
    path: '/welcome',
    name: 'welcome',
    icon: 'smile',
    component: './Welcome',
  },
  {
    path: '/admin',
    name: 'admin',
    icon: 'crown',
    access: 'canAdmin',
    component: './Admin',
    routes: [
      {
        path: '/admin/sub-page',
        name: 'sub-page',
        icon: 'smile',
        component: './Welcome',
      },
    ],
  },
  // summary
  {
    path: '/summary',
    name: 'Summary',
    icon: 'FileOutlined',
    component: './Summary/Summary',
  },
  // timesheet
  {
    path: '/timesheet',
    name: 'Timesheet',
    icon: 'EditOutlined',
    component: './Timesheet/Timesheet',
  },
  {
    path: '/timesheet/:weekEnding',
    component: './Timesheet/Timesheet',
  },
  // profile
  {
    path: '/profile',
    name: 'profile',
    icon: 'UserOutlined',
    component: './Profile/Profile',
  },

  {
    name: 'list.table-list',
    icon: 'table',
    path: '/list',
    component: './TableList',
  },
  {
    path: '/',
    redirect: '/welcome',
  },
  {
    component: './404',
  },
];
