$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'customer/customer/list',
        datatype: "json",
        colModel: [
			{ label: '编号', name: 'id', index: "id", width: 45, key: true },
			{ label: '手机号码', name: 'mobile', index: "mobile", width: 65},
			{ label: '客户名称', name: 'username', sortable: false, index: "username", width: 105 },
			{ label: '性别', name: 'sex', width: 30, sortable: false,
			    formatter: function(value, options, row){
                    return value === '1' ?
                        '<span>男</span>' :
                        '<span>女</span>';
                }
            },
			{ label: '微信识别码', name: 'wechatId', sortable: false, width: 100 },
			{ label: '邮箱', name: 'email', sortable: false, width: 100 },
			{ label: '创建时间', name: 'createTime', index: "create_time", width: 80}
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page",
            rows:"limit",
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			mobile: null
		},
		showList: true,
		title:null,
		customer:{}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.customer = {};
		},
		update: function () {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			$.get(baseURL + "customer/customer/info/"+id, function(r){
                            vm.showList = false;
                            vm.title = "修改";
                            vm.customer = r.customer;
                        });
		},
		del: function () {
        			var ids = getSelectedRows();
        			if(ids == null){
        				return ;
        			}

        			confirm('确定要删除选中的记录？', function(){
        				$.ajax({
        					type: "POST",
        				    url: baseURL + "customer/customer/delete",
                            contentType: "application/json",
        				    data: JSON.stringify(ids),
        				    success: function(r){
        						if(r.code == 0){
        							alert('操作成功', function(){
        								vm.reload();
        							});
        						}else{
        							alert(r.msg);
        						}
        					}
        				});
        			});
        		},
		saveOrUpdate: function () {
            if(vm.validator()){
                return ;
            }
			var url = vm.customer.id == null ? "customer/customer/save" : "customer/customer/update";
            			$.ajax({
            				type: "POST",
            			    url: baseURL + url,
                            contentType: "application/json",
            			    data: JSON.stringify(vm.customer),
            			    success: function(r){
            			    	if(r.code === 0){
            						alert('操作成功', function(){
            							vm.reload();
            						});
            					}else{
            						alert(r.msg);
            					}
            				}
            			});
		},
	    reload: function () {
	    	vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'mobile': vm.q.mobile},
                page:page
            }).trigger("reloadGrid");
		},
        validator: function () {
            if(isBlank(vm.customer.mobile)){
                alert("mobile不能为空");
                return true;
            }
        }
	}
});