
function bodyLoaded(){
	//创建mainDiv
	var selectedClient=0;
	
	var mainDIV=document.createElement("div");
	mainDIV.id="mainDIV";
	$('body').append(mainDIV);
	
	//将mainDiv创建成layout
	$('#mainDIV').layout({
	    fit:true,
	    border:true
	});
	$('#mainDIV').layout('add', {
		region: 'north',
		minHeight: 100,
	    split: true
	});
	$('#mainDIV').layout('add', {
		region: 'south',
		minHeight: 50,
	    split: true
	});
	$('#mainDIV').layout('add', {
		region: 'west',
	    width: 150,
	    title: '客户端',
	    split: true,
	    tools: [{
	        iconCls:'icon-add',
	        handler:function(){
	        	$('#clientAccordion').accordion('add', {
					title: 'abc',
					selected: false
				});
	        }
	    },{
	        iconCls:'icon-remove',
	        handler:function(){removeClient(selectedClient);}
	    }]
	});
	$('#mainDIV').layout('add', {
		region: 'east',
	    width: 100,
	    title: '其它',
	    split: true
	});
	$('#mainDIV').layout('add', {
		region: 'center',
		minWidth: 800,
		minHeight: 600,
	    title: '任务中心'
	});
	
	//在west上添加accordion
	var clientAccordion=document.createElement("div");
	clientAccordion.id="clientAccordion";
	$('#mainDIV').layout('panel', 'west').append(clientAccordion);
	
	$('#clientAccordion').accordion({
		fit:true,
		onSelect: function(title,index){
			selectedClient=title;
		},
		onRemove: function(title,index){
			selectedClient=undefined;
		}
	});

	//根据client数量显示Accordion
	$.ajax({
		type: "GET",
		url : "/jdroutine/service/rclients/getClients",
		cache : false,
		dataType: "json",
		success : function(data) {
			var rClient=0;
			for (rClient in data){
				var clientTitle = data[rClient].id;
				$('#clientAccordion').accordion('add', {
					title: clientTitle,
					selected: false
				});
			}
		}
	});
}

function removeClient(selectedClient){
	alert(selectedClient);
//	$.ajax({
//		type: "DELETE",
//		url : "/jdroutine/service/rclients/remove/"+selectedClient,
//		cache : false,
//		dataType: "json",
//		success : function(data) {
//		}
//	});
	
	var selectedAccordion = $('#clientAccordion').accordion('getSelected');
//	openMessageDialog(dump_object($('#clientAccordion').accordion('options')));
//	openMessageDialog(dump_object($('#clientAccordion').accordion('panels')[0]));
	
	var selectedIndex = $('#clientAccordion').accordion('getPanelIndex', selectedAccordion);
	$('#clientAccordion').accordion('remove', selectedIndex);
}