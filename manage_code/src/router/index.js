	import {
		createRouter,
		createWebHashHistory
	} from 'vue-router'
	import news from '@/views/news/list'
	import discussjiankangzhishi from '@/views/discussjiankangzhishi/list'
	import jiankangdangan from '@/views/jiankangdangan/list'
	import jiankangshuju from '@/views/jiankangshuju/list'
	import jiankangshipu from '@/views/jiankangshipu/list'
	import syslog from '@/views/syslog/list'
	import jiankangzhuanjia from '@/views/jiankangzhuanjia/list'
	import storeup from '@/views/storeup/list'
	import users from '@/views/users/list'
	import jiankangzhishi from '@/views/jiankangzhishi/list'
	import jiankangmubiao from '@/views/jiankangmubiao/list'
	import systemintro from '@/views/systemintro/list'
	import onlineMessage from '@/views/onlineMessage/list'
	import yongyaoguanli from '@/views/yongyaoguanli/list'
	import chat from '@/views/chat/list'
	import yonghu from '@/views/yonghu/list'
	import jianshenjihua from '@/views/jianshenjihua/list'
	import discussjiankangshipu from '@/views/discussjiankangshipu/list'
	import yinshijilu from '@/views/yinshijilu/list'
	import popupRemind from '@/views/popupRemind/list'
	import config from '@/views/config/list'
	import usersCenter from '@/views/users/center'
	import jiankangzhuanjiaRegister from '@/views/jiankangzhuanjia/register'
	import jiankangzhuanjiaCenter from '@/views/jiankangzhuanjia/center'

export const routes = [{
		path: '/login',
		name: 'login',
        meta: { title: 'Login' },
		component: () => import('../views/login.vue')
	},{
		path: '/',
		name: '首页',
        meta: { title: 'Index' },
		component: () => import('../views/index'),
		children: [{
			path: '/',
			name: 'home',
			component: () => import('../views/HomeView.vue'),
			meta: {
				affix: true,
                title: 'Index'
			}
		}, {
			path: '/updatepassword',
			name: 'updatepassword',
            meta: { title: 'Change Password' },
			component: () => import('../views/updatepassword.vue')
		}

		,{
			path: '/usersCenter',
			name: 'usersCenter',
            meta: { title: 'Administrator personal center' },
			component: usersCenter
		}
		,{
			path: '/jiankangzhuanjiaCenter',
			name: 'jiankangzhuanjiaCenter',
            meta: { title: 'Health expert personal center' },
			component: jiankangzhuanjiaCenter
		}
		,{
			path: '/news',
			name: 'news',
            meta: { title: 'News and Information' },
			component: news
		}
		,{
			path: '/discussjiankangzhishi',
			name: 'discussjiankangzhishi',
            meta: { title: 'Health knowledge review - review' },
			component: discussjiankangzhishi
		}
		,{
			path: '/jiankangdangan',
			name: 'jiankangdangan',
            meta: { title: 'Health records' },
			component: jiankangdangan
		}
		,{
			path: '/jiankangshuju',
			name: 'jiankangshuju',
            meta: { title: 'health data' },
			component: jiankangshuju
		}
		,{
			path: '/jiankangshipu',
			name: 'jiankangshipu',
            meta: { title: 'Healthy recipes ' },
			component: jiankangshipu
		}
		,{
			path: '/syslog',
			name: 'syslog',
            meta: { title: 'Operate log' },
			component: syslog
		}
		,{
			path: '/jiankangzhuanjia',
			name: 'jiankangzhuanjia',
            meta: { title: 'Health expert' },
			component: jiankangzhuanjia
		}
		,{
			path: '/storeup',
			name: 'storeup',
            meta: { title: 'My collection' },
			component: storeup
		}
		,{
			path: '/users',
			name: 'users',
            meta: { title: 'Admin' },
			component: users
		}
		,{
			path: '/jiankangzhishi',
			name: 'jiankangzhishi',
            meta: { title: 'Health knowledge' },
			component: jiankangzhishi
		}
		,{
			path: '/jiankangmubiao',
			name: 'jiankangmubiao',
            meta: { title: 'Health goals' },
			component: jiankangmubiao
		}
		,{
			path: '/systemintro',
			name: 'systemintro',
            meta: { title: 'Website Introduction' },
			component: systemintro
		}
		,{
			path: '/onlineMessage',
			name: 'onlineMessage',
            meta: { title: 'Online message' },
			component: onlineMessage
		}
		,{
			path: '/yongyaoguanli',
			name: 'yongyaoguanli',
            meta: { title: 'Medication management' },
			component: yongyaoguanli
		}
		,{
			path: '/chat',
			name: 'chat',
            meta: { title: 'Customer service chat' },
			component: chat
		}
		,{
			path: '/yonghu',
			name: 'yonghu',
            meta: { title: 'User' },
			component: yonghu
		}
		,{
			path: '/jianshenjihua',
			name: 'jianshenjihua',
            meta: { title: 'Fitness plan' },
			component: jianshenjihua
		}
		,{
			path: '/discussjiankangshipu',
			name: 'discussjiankangshipu',
            meta: { title: 'Healthy recipe reviews-reviews' },
			component: discussjiankangshipu
		}
		,{
			path: '/yinshijilu',
			name: 'yinshijilu',
            meta: { title: 'Food record' },
			component: yinshijilu
		}
		,{
			path: '/popupRemind',
			name: 'popupRemind',
            meta: { title: 'Medication reminder' },
			component: popupRemind
		}
		,{
			path: '/config',
			name: 'config',
            meta: { title: 'Carousel image' },
			component: config
		}
		]
	},
	{
		path: '/jiankangzhuanjiaRegister',
		name: 'jiankangzhuanjiaRegister',
        meta: { title: 'Health expert registration' },
		component: jiankangzhuanjiaRegister
	},
]

const router = createRouter({
	history: createWebHashHistory(process.env.BASE_URL),
	routes
})

export default router
