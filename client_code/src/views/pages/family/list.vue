<template>
    <div class="family-list-container" style="padding: 20px;">
        <!-- 添加面包屑导航和返回按钮 -->
        <div class="breadcrumb-wrapper" style="margin-bottom: 20px;">
            <div class="bread_view">
                <el-breadcrumb separator="/" class="breadcrumb">
                    <el-breadcrumb-item :to="{ path: '/index' }">Index</el-breadcrumb-item>
                    <el-breadcrumb-item :to="{ path: '/index/yonghuCenter' }">User center</el-breadcrumb-item>
                    <el-breadcrumb-item>Family members</el-breadcrumb-item>
                </el-breadcrumb>
            </div>
            <div class="back_view">
                <el-button class="back_btn" type="primary" @click="handleBack">Back</el-button>
            </div>
        </div>

        <!-- 顶部搜索和添加区域 -->
        <div class="top-operation" style="margin-bottom: 20px;">
            <el-row :gutter="20">
                <el-col :span="16">
                    <el-input
                        v-model="searchForm.name"
                        placeholder="Please enter member name to search"
                        style="width: 200px; margin-right: 10px;"
                        clearable
                        @keyup.enter="handleSearch"
                    />
                    <el-button type="primary" @click="handleSearch">Search</el-button>
                    <el-button @click="resetSearch">Reset</el-button>
                </el-col>
                <el-col :span="8" style="text-align: right;">
                    <el-button type="success" @click="handleAdd">Add family members</el-button>
                </el-col>
            </el-row>
        </div>

        <!-- 数据表格 -->
        <el-table 
            :data="dataList" 
            border 
            style="width: 100%"
            v-loading="listLoading"
            element-loading-text="加载中..."
        >
            <el-table-column prop="yonghuzhanghao" label="User Account" width="120" />
            <el-table-column prop="yonghuxingming" label="User Name" width="120" />
            <el-table-column prop="relationship" label="Relationship" width="100" />
            <el-table-column prop="shoujihaoma" label="Phone Number" width="140" />
            <el-table-column prop="remark" label="Remark" min-width="200" show-overflow-tooltip />
            <el-table-column label="操作" width="220" fixed="right">
                <template #default="scope">
                    <el-button type="primary" link @click="handleEdit(scope.row)">Edit</el-button>
                    <el-button type="success" link @click="handleHealth(scope.row)">Health Status</el-button>
                    <el-button type="danger" link @click="handleDelete(scope.row)">Delete</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 分页 -->
        <div class="pagination-container" style="margin-top: 20px; text-align: right;">
            <el-pagination
                v-model:current-page="listQuery.page"
                v-model:page-size="listQuery.limit"
                :page-sizes="[10, 20, 30, 50]"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
            />
        </div>

        <!-- 添加/编辑对话框 -->
        <el-dialog
            :title="dialogTitle"
            v-model="dialogVisible"
            width="500px"
            :close-on-click-modal="false"
            @closed="handleDialogClosed"
        >
            <el-form :model="formData" :rules="rules" ref="formRef" label-width="100px">
                <el-form-item label="User Search" v-if="!formData.id">
                    <div style="display: flex; gap: 10px;">
                        <el-input 
                            v-model="searchUsername" 
                            placeholder="Please enter the user account"
                            style="flex: 1;"
                            @keyup.enter="searchUser"
                        />
                        <el-button type="primary" @click="searchUser">Search</el-button>
                    </div>
                </el-form-item>
                <template v-if="selectedUser">
                    <div style="margin-bottom: 20px; padding: 10px; background-color: #f5f7fa; border-radius: 4px;">
                        <div style="margin-bottom: 10px;">User information found:</div>
                        <div>User Acconut：{{selectedUser.yonghuzhanghao}}</div>
                        <div>User Name：{{selectedUser.yonghuxingming}}</div>
                        <div>Phone Number：{{selectedUser.shoujihaoma}}</div>
                    </div>
                </template>
                <el-form-item label="Relationship" prop="relationship">
                    <el-select v-model="formData.relationship" placeholder="Please select relationship " style="width: 100%;">
                        <el-option
                            v-for="item in relationshipOptions"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value"
                        />
                    </el-select>
                </el-form-item>
                <el-form-item label="Remark" prop="remark">
                    <el-input 
                        v-model="formData.remark" 
                        type="textarea" 
                        :rows="3" 
                        placeholder="Please enter remark"
                    />
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">Cancel</el-button>
                    <el-button type="primary" @click="handleSubmit">True</el-button>
                </span>
            </template>
        </el-dialog>

        <!-- 健康档案弹窗 -->
        <el-dialog
            title="HealthDialogVisible"
            v-model="healthDialogVisible"
            width="600px"
        >
            <div v-if="healthData" class="health-info">
                <div class="health-item">
                    <span class="label">User Name：</span>
                    <span class="value">{{healthData.yonghuxingming}}</span>
                </div>
                <div class="health-item">
                    <span class="label">Sex：</span>
                    <span class="value">{{healthData.xingbie}}</span>
                </div>
                <div class="health-item">
                    <span class="label">Date of Birth:：</span>
                    <span class="value">{{healthData.chushengriqi}}</span>
                </div>
                <div class="health-item">
                    <span class="label">Age：</span>
                    <span class="value">{{healthData.nianling}}</span>
                </div>
                <div class="health-item">
                    <span class="label">High：</span>
                    <span class="value">{{healthData.shengao}}</span>
                </div>
                <div class="health-item">
                    <span class="label">weight：</span>
                    <span class="value">{{healthData.tizhong}}</span>
                </div>
                <div class="health-item">
                    <span class="label">Blood style：</span>
                    <span class="value">{{healthData.xuexing}}</span>
                </div>
                <div class="health-item">
                    <span class="label">Medical history:</span>
                    <span class="value">{{healthData.jiwangbingshi}}</span>
                </div>
                <div class="health-item">
                    <span class="label">Family medical history:</span>
                    <span class="value">{{healthData.jiazubingshi}}</span>
                </div>
                <div class="health-item">
                    <span class="label">Allergy medication:</span>
                    <span class="value">{{healthData.guominyaowu}}</span>
                </div>
            </div>
            <div v-else class="no-data">
                There is currently no health record information available
            </div>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="healthDialogVisible = false">Close</el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>

<script setup>
import { ref, reactive, onMounted, getCurrentInstance } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useRouter } from 'vue-router'

const { proxy } = getCurrentInstance()

const router = useRouter()

// 基础信息
const tableName = 'jiatingchengyuan'
const formName = 'Family members'

// 数据列表相关
const dataList = ref([])
const total = ref(0)
const listLoading = ref(false)
const listQuery = ref({
    page: 1,
    limit: 10,
    sort: 'id',
    order: 'desc'
})

// 搜索相关
const searchForm = reactive({
    name: ''
})

// 表单相关
const dialogVisible = ref(false)
const dialogTitle = ref('')
const formRef = ref(null)
const formData = reactive({
    id: '',
    userId: '',  // 关联的用户ID
    yonghuzhanghao: '', // 用户账号
    yonghuxingming: '', // 用户姓名
    shoujihaoma: '', // 联系电话
    relationship: '', // 关系
    remark: '', // 备注
    chengyuanzhanghao: '' // 当前登录用户账号
})

// 关系选项
const relationshipOptions = [
    { value: 'Father', label: '父亲' },
    { value: 'Mother', label: '母亲' },
    { value: 'Son', label: '儿子' },
    { value: 'daughter', label: '女儿' },
    { value: 'Wife', label: '配偶' },
    { value: 'GrandFather', label: '祖父' },
    { value: 'GrandMother', label: '祖母' },
    { value: 'Other', label: '其他' }
]

// 表单验证规则
const rules = {
    relationship: [{ required: true, message: 'Please select relationship', trigger: 'change' }],
    remark: [{ required: false, message: 'Please enter remark', trigger: 'blur' }]
}

// 用户搜索相关
const searchUsername = ref('')
const selectedUser = ref(null)

// 添加 loading 状态
const loading = ref(false)

// 添加静态测试数据
const mockUsers = [
    {
        id: 1,
        yonghuzhanghao: 'zhangsan',
        yonghuxingming: '张三',
        shoujihaoma: '13800138000'
    },
    {
        id: 2,
        yonghuzhanghao: 'lisi',
        yonghuxingming: '李四',
        shoujihaoma: '13800138001'
    }
]

const mockFamilyList = [
    {
        id: 1,
        userId: 1,
        yonghuzhanghao: 'zhangsan',
        yonghuxingming: '张三',
        shoujihaoma: '13800138000',
        relationship: '父亲',
        remark: '这是一条测试数据'
    }
]

// 获取当前登录用户账号
const getCurrentUserAccount = () => {
    const frontName = proxy.$toolUtil.storageGet('frontName')
    console.log('获取到的用户账号:', frontName)
    return frontName || ''
}

// 获取数据列表
const getList = () => {
    listLoading.value = true
    let params = JSON.parse(JSON.stringify(searchForm))
    if (searchForm.name && searchForm.name != '') {
        params.yonghuxingming = '%' + searchForm.name + '%'
    }
    
    proxy.$http({
        url: '/jiatingchengyuan/page',
        method: 'get',
        params: params
    }).then(res => {
        listLoading.value = false
        if (res.data.code === 0) {
            const pageData = res.data.data
            // 获取当前登录用户账号
            const currentUser = proxy.$toolUtil.storageGet('frontName')
            // 筛选出当前用户的家庭成员数据
            const filteredList = (pageData.list || []).filter(item => 
                item.chengyuanzhanghao === currentUser
            )
            dataList.value = filteredList
            total.value = filteredList.length
        } else {
            dataList.value = []
            total.value = 0
            ElMessage.error(res.data.msg || 'Failed to retrieve data')
        }
    }).catch(error => {
        console.error('Failed to retrieve data:', error)
        dataList.value = []
        total.value = 0
        ElMessage.error(error.response?.data?.error || 'Failed to retrieve data')
    })
}

// 分页处理
const handleSizeChange = (val) => {
    console.log('每页显示数量改变:', val)
    listQuery.value.limit = val
    listQuery.value.page = 1  // 重置到第一页
    getList()
}

const handleCurrentChange = (val) => {
    console.log('当前页码改变:', val)
    listQuery.value.page = val
    getList()
}

// 搜索处理
const handleSearch = () => {
    console.log('执行搜索，搜索条件:', searchForm)
    listQuery.value.page = 1  // 搜索时重置到第一页
    getList()
}

// 重置搜索
const resetSearch = () => {
    searchForm.name = ''
    listQuery.value.page = 1
    getList()
}

// 搜索用户
const searchUser = async () => {
    console.log('开始搜索用户:', searchUsername.value)
    if (!searchUsername.value) {
        ElMessage.warning('请输入用户账号')
        return
    }
    try {
        console.log('发送搜索请求...')
        const res = await proxy.$http.get('/yonghu/page', {
            params: {
                yonghuzhanghao: searchUsername.value
            }
        })
        console.log('搜索结果:', res.data)
        if (res.data.code === 0 && res.data.data.list && res.data.data.list.length > 0) {
            selectedUser.value = res.data.data.list[0]
            // 更新表单数据
            formData.userId = selectedUser.value.id
            formData.yonghuzhanghao = selectedUser.value.yonghuzhanghao
            formData.yonghuxingming = selectedUser.value.yonghuxingming
            formData.shoujihaoma = selectedUser.value.shoujihaoma
            console.log('用户数据已更新:', formData)
        } else {
            console.log('未找到用户')
            ElMessage.warning('No relevant users found')
            selectedUser.value = null
            // 清空相关表单数据
            formData.userId = ''
            formData.yonghuzhanghao = ''
            formData.yonghuxingming = ''
            formData.shoujihaoma = ''
        }
    } catch (error) {
        console.error('搜索用户失败：', error)
        ElMessage.error('No relevant users found')
    }
}

// 添加成员
const handleAdd = () => {
    dialogTitle.value = 'Add family members'
    dialogVisible.value = true
    selectedUser.value = null
    searchUsername.value = ''
    
    // 清空所有表单数据
    Object.keys(formData).forEach(key => {
        formData[key] = ''
    })
    
    // 设置当前登录用户账号
    const currentUser = getCurrentUserAccount()
    console.log('当前获取到的用户账号:', currentUser)
    formData.chengyuanzhanghao = currentUser
    
    // 输出完整的表单数据
    console.log('添加时的完整表单数据:', formData)
}

// 编辑成员
const handleEdit = (row) => {
    dialogTitle.value = 'Edit family members'
    dialogVisible.value = true
    Object.keys(formData).forEach(key => {
        formData[key] = row[key]
    })
    
    // 设置当前登录用户账号
    const currentUser = getCurrentUserAccount()
    console.log('当前获取到的用户账号:', currentUser)
    formData.chengyuanzhanghao = currentUser
    
    // 输出完整的表单数据
    console.log('编辑时的完整表单数据:', formData)
}

// 删除成员
const handleDelete = (row) => {
    ElMessageBox.confirm('Are you sure you want to delete this family member?', 'Tip', {
        type: 'warning'
    }).then(async () => {
        try {
            const res = await proxy.$http.post('/jiatingchengyuan/delete', [row.id])
            if (res.data.code === 0) {
                ElMessage.success('Delete Success')
                getList()
            } else {
                ElMessage.error(res.data.msg || 'Fail to delete')
            }
        } catch (error) {
            console.error('删除失败：', error)
            ElMessage.error('Fail to delete')
        }
    }).catch(() => {})
}

// 提交表单
const handleSubmit = async () => {
    if (!formRef.value) return
    
    // 添加新成员时的额外验证
    if (!formData.id && !formData.userId) {
        ElMessage.warning('Please search and select the user first')
        return
    }

    // 确保设置了当前登录用户账号
    const currentUser = getCurrentUserAccount()
    console.log('提交时获取到的用户账号:', currentUser)
    formData.chengyuanzhanghao = currentUser

    console.log('准备提交的完整数据:', formData)

    await formRef.value.validate(async (valid) => {
        if (valid) {
            try {
                const url = formData.id 
                    ? '/jiatingchengyuan/update'
                    : '/jiatingchengyuan/save'
                const res = await proxy.$http.post(url, formData)
                if (res.data.code === 0) {
                    ElMessage.success(formData.id ? 'Modified successfully' : 'Add successfully')
                    dialogVisible.value = false
                    getList() // 刷新列表
                } else {
                    ElMessage.error(res.data.msg || 'Fail to submit')
                }
            } catch (error) {
                console.error('提交失败：', error)
                ElMessage.error('Fail to submit')
            }
        } else {
            console.log('Fail')
            return false
        }
    })
}

// 添加对话框关闭处理方法
const handleDialogClosed = () => {
    // 清空搜索和选择的用户数据
    searchUsername.value = ''
    selectedUser.value = null
    // 重置表单
    formRef.value?.resetFields()
    // 清空所有表单数据
    Object.keys(formData).forEach(key => {
        formData[key] = ''
    })
}

// 返回按钮处理函数
const handleBack = () => {
    router.push('/index/yonghuCenter')
}

// 健康档案相关
const healthDialogVisible = ref(false)
const healthData = ref(null)

// 查看健康档案
const handleHealth = async (row) => {
    try {
        const res = await proxy.$http.get('/jiankangdangan/getByYonghuzhanghao', {
            params: {
                yonghuzhanghao: row.yonghuzhanghao
            }
        })
        if (res.data.code === 0 && res.data.data) {
            healthData.value = res.data.data
            healthDialogVisible.value = true
        } else {
            ElMessage.warning('The users health profile was not found')
        }
    } catch (error) {
        console.error('获取健康档案失败：', error)
        ElMessage.error('Failed to obtain health records')
    }
}

// 页面加载时获取数据
onMounted(() => {
    console.log('Page loading, starting to retrieve data')
    getList()
})
</script>

<style scoped>
.family-list-container {
    background-color: #fff;
    border-radius: 4px;
    box-shadow: 0 2px 12px 0 rgba(0,0,0,0.1);
}

.top-operation {
    padding: 15px;
    background-color: #f5f7fa;
    border-radius: 4px 4px 0 0;
}

.el-table {
    margin-top: 15px;
}

:deep(.el-dialog__body) {
    padding: 20px 40px;
}

.dialog-footer {
    text-align: right;
    margin-top: 20px;
}

.breadcrumb-wrapper {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 0;
}

.bread_view {
    display: flex;
    align-items: center;
}

.back_view {
    margin-left: 20px;
}

.back_btn {
    padding: 9px 15px;
}

.health-info {
    padding: 20px;
}

.health-item {
    margin-bottom: 15px;
    line-height: 24px;
}

.health-item .label {
    display: inline-block;
    width: 100px;
    color: #606266;
    font-weight: bold;
}

.health-item .value {
    color: #333;
}

.no-data {
    text-align: center;
    color: #909399;
    padding: 30px 0;
}
</style> 