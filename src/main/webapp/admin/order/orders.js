var app=new Vue({
        el:'#app',
        data:{
            ordersl:[],
            orders:[],
            searchStartTime:'',
            searchEndTime:'',
            searchName:'',
            searchStatus:'',
            type:{},
            dialogVisible:false,
            //分页
            pagesizes:[5,10,20],
            pageSize:5,
            pageNumber:1,
            total:10,
        },
        methods:{
            clear(){
                this.searchStartTime='';
                   this.searchEndTime='';
                    this.searchName='';
                    this.searchStatus=''
            },
            query(){
                axios.post('/order/getOrders',{
                        pageSize:this.pageSize,
                    pageNumber: this.pageNumber,
                        searchStartTime: this.searchStartTime,
                        searchEndTime: this.searchEndTime,
                        searchStatus:this.searchStatus,
                    searchName:this.searchName
                }) .then((res)=>{
                    console.log(res);
                    this.orders=res.data.list;
                        this.orders=res.data.list;
                        this.pageNumber=res.data.pageNum;
                        this.total=res.data.total;
                }) .catch(function (reason) {
                })
            },
            //点单对话框显示
            handleEdit(index, row) {
                console.log(index, row);
                this.ordersl=row.orderItems;
                this.dialogVisible=true;
            },
            fire(row){
                axios.get("/order/updateState/3/"+row.oid).then((res)=>{
                    this.$message({
                        message: '发货成功',
                        type: 'success'
                    });
                    this.query();
                }).catch(()=>{
                    this.$message({
                        message: '发货失败',
                        type: 'error'
                    });
                })
            },


            handleSizeChange(val) {
                this.pageSize=val;
               this.handleCurrentChange(1);
            },
            handleCurrentChange(val) {
                this.pageNumber=val;
                this.query();
            },

            /**
             * 对话框关闭
             * @param done
             */
            handleClose(done) {
                this.$confirm('确认关闭？')
                    .then(_ => {
                        done();
                    })
                    .catch(_ => {});
            }
        },

        mounted:function () {
            this.query();
        }

    })
