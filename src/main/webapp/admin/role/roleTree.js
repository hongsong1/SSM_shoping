
var vm=new Vue({
        el:"#app",
        data:{
            formInline:{},
            typename:'',

            dialogRole:false,
            treeKeys:[],
            dialogResource:false,
            roleId:'',
            pagesizes:[3,6,9],
            pageSize:3,
            pageNum:1,
            total:10,
            getTreeKeys:[],
            roles:[],
            treeData: [],
            defaultProps:{
                children: 'children',
                label: 'label'
            },
            ruleForm:{
                roleName:'',
                status: '',
                description: '',
                grade:''
            },
            rules: {
                roleName: [
                    { required: true, message: '请输入活动名称', trigger: 'blur' },
                    {  max: 8, message: ' 长度不能大于 8 个字符', trigger: 'blur' }
                ],
                status: [
                    { required: true, message: '请选择活动区域', trigger: 'change' }
                ],

                description: [
                    {  max: 50, message: ' 长度不能大于50个字符', trigger: 'blur' }
                ],
            }
        },

        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.post('/updateRole',
                            this.ruleForm
                            ).then((res)=>{
                              this.ruleForm={};
                                  this.dialogRole=false;
                            this.$message({
                                message: '恭喜你，修改成功',
                                type: 'success'
                            });
                        }).catch((res)=>{
                            console.log(res);
                            this.$message({
                                message: '修改失败',
                                type: 'error'
                            });
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },

            handleSizeChange(val) {
                this.pageSize=val;
                this. getRole();
            },
            handleCurrentChange(val) {
                this.pageNum=val;
                this. getRole();
            },
            getRole(){
                axios.get('/getRole',{
                    params: {
                        typename:this.typename,
                        pageNum:this.pageNum,
                        pageSize:this.pageSize
                    }
                }).then((res)=>{
                    this.roles=res.data.list;
                    this.total=res.data.total;
                    this.pageNum=res.data.pageNum;
                })
            },

            handleEdit(index, row) {
                this.dialogRole=true;
                this.ruleForm=row;
                console.log(index, row);

            },
            handleDelete(index, row) {
                axios.get('/delRole',{
                    params:{
                        id:row.id,
                        status:row.status
                    }
                }).then((res)=>{
                    this.getRole();
                    if(res.data==500){
                        this.$message({
                            message: '权限不足修改失败',
                            type: 'error'
                        });
                    }else{
                        this.$message({
                            message: '恭喜你，修改成功',
                            type: 'success'
                        });
                    }

                }).catch((res)=>{
                    this.$message({
                        message: '修改失败',
                        type: 'error'
                    });
                })
            },
            getResource: function (index, row) {
                this.dialogResource = true;
                this.roleId = row.id;
                console.log(row.id);
                axios.get('/getRid', {
                    params: {
                        roleId: this.roleId
                    }
                }).then((res) => {
                    console.log(res);
                    this.$refs.tree.setCheckedKeys([]);
                    this.treeKeys = res.data;
                    console.log(this.treeKeys);
                }).catch((res) => {
                    this.$message('出错了');
                })
            },

            //对话框关闭
            handleClose(done) {
                this.$confirm('确认关闭？')
                    .then(_ => {
                        this.ruleForm={};
                        done();
                    })
                    .catch(_ => {});
            },

            getCheckedKeys() {
                console.log(this.$refs.tree.getCheckedKeys());
            },
            setCheckedKeys() {
                this.$refs.tree.setCheckedKeys([1,2,3,9,5]);
            },
            resetChecked() {
                this.$refs.tree.setCheckedKeys([]);
            },
            getTree(){
                axios.get('/getTree').then((res)=>{
                    this.treeData=res.data;
                }).catch((res)=>{
                    this.$message('加载失败');
                })
            },
            getKey(){
                this.getTreeKeys=this.$refs.tree.getCheckedKeys();
                axios.post('/setResource',
                    {
                        roleId:this.roleId,
                        keys:this.getTreeKeys
                    }
                ).then((res)=>{
                    this.$message("修改成功");
                    this.dialogResource=false;
                })
            }



        },


        mounted:function () {
            this.getRole();
            this.getTree();
        }

    })
