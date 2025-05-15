import { createRouter, createWebHashHistory } from 'vue-router'
import index from '../views'
import home from '../views/pages/home.vue'
import login from '../views/pages/login.vue'
import newsList from '@/views/pages/news/list'
import systemintroList from '@/views/pages/systemintro/list'
import systemintroDetail from '@/views/pages/systemintro/formModel'
import systemintroAdd from '@/views/pages/systemintro/formAdd'
import yonghuList from '@/views/pages/yonghu/list'
import yonghuDetail from '@/views/pages/yonghu/formModel'
import yonghuAdd from '@/views/pages/yonghu/formAdd'
import yonghuRegister from '@/views/pages/yonghu/register'
import yonghuCenter from '@/views/pages/yonghu/center'
import jiankangdanganList from '@/views/pages/jiankangdangan/list'
import jiankangdanganDetail from '@/views/pages/jiankangdangan/formModel'
import jiankangdanganAdd from '@/views/pages/jiankangdangan/formAdd'
import jiankangshujuList from '@/views/pages/jiankangshuju/list'
import jiankangshujuDetail from '@/views/pages/jiankangshuju/formModel'
import jiankangshujuAdd from '@/views/pages/jiankangshuju/formAdd'
import yongyaoguanliList from '@/views/pages/yongyaoguanli/list'
import yongyaoguanliDetail from '@/views/pages/yongyaoguanli/formModel'
import yongyaoguanliAdd from '@/views/pages/yongyaoguanli/formAdd'
import jiankangmubiaoList from '@/views/pages/jiankangmubiao/list'
import jiankangmubiaoDetail from '@/views/pages/jiankangmubiao/formModel'
import jiankangmubiaoAdd from '@/views/pages/jiankangmubiao/formAdd'
import jiankangshipuList from '@/views/pages/jiankangshipu/list'
import jiankangshipuDetail from '@/views/pages/jiankangshipu/formModel'
import jiankangshipuAdd from '@/views/pages/jiankangshipu/formAdd'
import storeupList from '@/views/pages/storeup/list'
import jianshenjihuaList from '@/views/pages/jianshenjihua/list'
import jianshenjihuaDetail from '@/views/pages/jianshenjihua/formModel'
import jianshenjihuaAdd from '@/views/pages/jianshenjihua/formAdd'
import jiankangzhishiList from '@/views/pages/jiankangzhishi/list'
import jiankangzhishiDetail from '@/views/pages/jiankangzhishi/formModel'
import jiankangzhishiAdd from '@/views/pages/jiankangzhishi/formAdd'
import syslogList from '@/views/pages/syslog/list'
import syslogDetail from '@/views/pages/syslog/formModel'
import syslogAdd from '@/views/pages/syslog/formAdd'
import popupRemindList from '@/views/pages/popupRemind/list'
import popupRemindDetail from '@/views/pages/popupRemind/formModel'
import popupRemindAdd from '@/views/pages/popupRemind/formAdd'
import onlineMessageList from '@/views/pages/onlineMessage/list'
import onlineMessageDetail from '@/views/pages/onlineMessage/formModel'
import onlineMessageAdd from '@/views/pages/onlineMessage/formAdd'
import jiankangzhuanjiaList from '@/views/pages/jiankangzhuanjia/list'
import jiankangzhuanjiaDetail from '@/views/pages/jiankangzhuanjia/formModel'
import jiankangzhuanjiaAdd from '@/views/pages/jiankangzhuanjia/formAdd'
import chatMessageList from '@/views/pages/chatMessage/list'
import chatMessageDetail from '@/views/pages/chatMessage/formModel'
import chatMessageAdd from '@/views/pages/chatMessage/formAdd'
import chatFriendList from '@/views/pages/chatFriend/list'
import chatFriendDetail from '@/views/pages/chatFriend/formModel'
import chatFriendAdd from '@/views/pages/chatFriend/formAdd'
import yinshijiluList from '@/views/pages/yinshijilu/list'
import yinshijiluDetail from '@/views/pages/yinshijilu/formModel'
import yinshijiluAdd from '@/views/pages/yinshijilu/formAdd'
import familyList from '@/views/pages/family/list.vue'

const routes = [{
		path: '/',
		redirect: '/index/home'
	},
	{
		path: '/index',
		component: index,
		children: [{
			path: 'home',
			component: home
		}
		, {
			path: 'newsList',
			component: newsList
		}
		, {
			path: 'systemintroList',
			component: systemintroList
		}, {
			path: 'systemintroDetail',
			component: systemintroDetail
		}, {
			path: 'systemintroAdd',
			component: systemintroAdd
		}
		, {
			path: 'yonghuList',
			component: yonghuList
		}, {
			path: 'yonghuDetail',
			component: yonghuDetail
		}, {
			path: 'yonghuAdd',
			component: yonghuAdd
		}
		, {
			path: 'yonghuCenter',
			component: yonghuCenter
		}
		, {
			path: 'jiankangdanganList',
			component: jiankangdanganList
		}, {
			path: 'jiankangdanganDetail',
			component: jiankangdanganDetail
		}, {
			path: 'jiankangdanganAdd',
			component: jiankangdanganAdd
		}
		, {
			path: 'jiankangshujuList',
			component: jiankangshujuList
		}, {
			path: 'jiankangshujuDetail',
			component: jiankangshujuDetail
		}, {
			path: 'jiankangshujuAdd',
			component: jiankangshujuAdd
		}
		, {
			path: 'yongyaoguanliList',
			component: yongyaoguanliList
		}, {
			path: 'yongyaoguanliDetail',
			component: yongyaoguanliDetail
		}, {
			path: 'yongyaoguanliAdd',
			component: yongyaoguanliAdd
		}
		, {
			path: 'jiankangmubiaoList',
			component: jiankangmubiaoList
		}, {
			path: 'jiankangmubiaoDetail',
			component: jiankangmubiaoDetail
		}, {
			path: 'jiankangmubiaoAdd',
			component: jiankangmubiaoAdd
		}
		, {
			path: 'jiankangshipuList',
			component: jiankangshipuList
		}, {
			path: 'jiankangshipuDetail',
			component: jiankangshipuDetail
		}, {
			path: 'jiankangshipuAdd',
			component: jiankangshipuAdd
		}
        , {
            path: 'storeupList',
            component: storeupList
        }
		, {
			path: 'familyList',
			component: familyList
		}
		, {
			path: 'jianshenjihuaList',
			component: jianshenjihuaList
		}, {
			path: 'jianshenjihuaDetail',
			component: jianshenjihuaDetail
		}, {
			path: 'jianshenjihuaAdd',
			component: jianshenjihuaAdd
		}
		, {
			path: 'jiankangzhishiList',
			component: jiankangzhishiList
		}, {
			path: 'jiankangzhishiDetail',
			component: jiankangzhishiDetail
		}, {
			path: 'jiankangzhishiAdd',
			component: jiankangzhishiAdd
		}
		, {
			path: 'syslogList',
			component: syslogList
		}, {
			path: 'syslogDetail',
			component: syslogDetail
		}, {
			path: 'syslogAdd',
			component: syslogAdd
		}
		, {
			path: 'popupRemindList',
			component: popupRemindList
		}, {
			path: 'popupRemindDetail',
			component: popupRemindDetail
		}, {
			path: 'popupRemindAdd',
			component: popupRemindAdd
		}
		, {
			path: 'onlineMessageList',
			component: onlineMessageList
		}, {
			path: 'onlineMessageDetail',
			component: onlineMessageDetail
		}, {
			path: 'onlineMessageAdd',
			component: onlineMessageAdd
		}
		, {
			path: 'jiankangzhuanjiaList',
			component: jiankangzhuanjiaList
		}, {
			path: 'jiankangzhuanjiaDetail',
			component: jiankangzhuanjiaDetail
		}, {
			path: 'jiankangzhuanjiaAdd',
			component: jiankangzhuanjiaAdd
		}
		, {
			path: 'chatMessageList',
			component: chatMessageList
		}, {
			path: 'chatMessageDetail',
			component: chatMessageDetail
		}, {
			path: 'chatMessageAdd',
			component: chatMessageAdd
		}
		, {
			path: 'chatFriendList',
			component: chatFriendList
		}, {
			path: 'chatFriendDetail',
			component: chatFriendDetail
		}, {
			path: 'chatFriendAdd',
			component: chatFriendAdd
		}
		, {
			path: 'yinshijiluList',
			component: yinshijiluList
		}, {
			path: 'yinshijiluDetail',
			component: yinshijiluDetail
		}, {
			path: 'yinshijiluAdd',
			component: yinshijiluAdd
		}
		]
	},
	{
		path: '/login',
		component: login
	}
	,{
		path: '/yonghuRegister',
		component: yonghuRegister
	}
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})

export default router
