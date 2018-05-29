$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'device/device/list',
        datatype: "json",
        colModel: [
            { label: '编号', name: 'id', index: "id", width: 45, key: true },
			{ label: 'MAC标识', name: 'devMacid', index: "devMacid", width: 120},
			{ label: '设备中文名称', name: 'devNameZh', index: "devNameZh", width: 105 },
			{ label: '设备英文名称', name: 'devNameEn', index: "devNameEn", width: 105 },
			{ label: 'MC用户', name: 'userId', index: "userId", width: 20 },
			{ label: '开关状态', name: 'statusSwitch',width: 40,
                formatter: function(value, options, row){
                     return value === 1 ?
                        '<span class="label label-info">OPEN</span>' :
                        '<span class="label label-warning">CLOSE</span>';
                }
            },
            { label: '设备状态', name: 'devStatus',width: 40,
                formatter: function(value, options, row){
                     return value === 1 ?
                        '<span class="label label-success">正常</span>' :
                        '<span class="label label-warning">危险</span>';
                }
            },
			{ label: '电量%', name: 'electricity', index: "electricity", width: 40 }
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
		device:{}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.device = {};
		},
		update: function () {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			$.get(baseURL + "device/device/info/"+id, function(r){
                            vm.showList = false;
                            vm.title = "修改";
                            vm.device = r.device;
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
        				    url: baseURL + "device/device/delete",
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
			var url = vm.device.id == null ? "device/device/save" : "device/device/update";
            			$.ajax({
            				type: "POST",
            			    url: baseURL + url,
                            contentType: "application/json",
            			    data: JSON.stringify(vm.device),
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
                postData:{'devMacid': vm.q.devMacid},
                page:page
            }).trigger("reloadGrid");
		},
        validator: function () {
            if(isBlank(vm.device.devMacid)){
                alert("devMacid不能为空");
                return true;
            }
        }
	}
});