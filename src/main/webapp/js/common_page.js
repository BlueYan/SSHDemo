$(document).ready(function() {
	$(".page_btn").click(function(){
		$("[name='qo.currentPage']").val($(this).attr("data-page") || $("[name='qo.currentPage']").val());
		$("#searchForm").submit();
	});
	
	$("[name='qo.pageSize']").change(function(){
		$("#searchForm").submit();
	});
	
	$(".btn_input").click(function(){
		console.info("input click");
		window.location.href=$(this).attr("data-href");
	});
});