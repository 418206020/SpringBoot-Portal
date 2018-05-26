$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'device/device/list',
        datatype: "json",
        colModel: [
			{ label: 'MAC标识', name: 'devMacid', index: "devMacid", width: 120, key: true },
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

var setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "menuId",
			pIdKey: "parentId",
			rootPId: -1
		},
		key: {
			url:"nourl"
		}
	},
	check:{
		enable:true,
		nocheckInherit:true
	}
};
var ztree;

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
			vm.getMenuTree(null);
		},
		update: function () {
			var roleId = getSelectedRow();
			if(roleId == null){
				return ;
			}

			vm.showList = false;
            vm.title = "修改";
            vm.getMenuTree(roleId);
		},
		del: function () {
			var roleIds = getSelectedRows();
			if(roleIds == null){
				return ;
			}

			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "device/device/delete",
                    contentType: "application/json",
				    data: JSON.stringify(roleIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getRole: function(roleId){
            $.get(baseURL + "device/device/info/"+roleId, function(r){
            	vm.device = r.device;

                //勾选角色所拥有的菜单
    			var menuIds = vm.device.menuIdList;
    			for(var i=0; i<menuIds.length; i++) {
    				var node = ztree.getNodeByParam("menuId", menuIds[i]);
    				ztree.checkNode(node, true, false);
    			}
    		});
		},
		saveOrUpdate: function () {
            if(vm.validator()){
                return ;
            }

			//获取选择的菜单
			var nodes = ztree.getCheckedNodes(true);
			var menuIdList = new Array();
			for(var i=0; i<nodes.length; i++) {
				menuIdList.push(nodes[i].menuId);
			}
			vm.device.menuIdList = menuIdList;

			var url = vm.device.roleId == null ? "device/device/save" : "device/device/update";
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
		getMenuTree: function(roleId) {
			//加载菜单树
			$.get(baseURL + "device/menu/list", function(r){
				ztree = $.fn.zTree.init($("#menuTree"), setting, r);
				//展开所有节点
				ztree.expandAll(true);

				if(roleId != null){
					vm.getRole(roleId);
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