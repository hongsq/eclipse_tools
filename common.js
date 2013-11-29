/**
 * 将字符串变成json对象
 * @param jsonString
 * @returns
 */
function stringToJson(jsonString){
    var json = (new Function("return " + jsonString))();
    return json;
}

function dump_object(inputObject) {
	var output = "";
	for ( var property in inputObject) {
		output = output + "<br/> " + property + " : " + inputObject[property];
	}
	return output;
}

function openMessageDialog(message) {
	var dialogDiv = document.createElement("div");
	dialogDiv.id = "dialogDiv";
	$('body').append(dialogDiv);

	$('#dialogDiv').dialog({
		title : 'Message Dialog',
		width : 400,
		height : 200,
		content : message,
		closed : false,
		cache : false,
		modal : true,
		onClose : function(){
			$('#dialogDiv').empty();
			$('#dialogDiv').remove();
		},
		onDestroy : function(){
			$('#dialogDiv').empty();
			$('#dialogDiv').remove();
		}
	});
	//alert(message);
}