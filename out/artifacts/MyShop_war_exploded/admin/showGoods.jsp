<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/DatePicker.js"></script>
<title>商品列表</title>

</head>
<body>
<div class="row" style="width:98%;margin-left: 1%;">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				会员列表
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<div class="form-group form-inline">
							<span>商品名称</span>
							<input type="text" id="name" class="form-control">
						</div>
					</div>
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<div class="form-group form-inline">
							<span>上架时间</span>
							<input type="text" readonly="readonly"  id="pubDate" class="form-control" onclick="setday(this)">
						</div>
					</div>
					<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
						<button type="button" class="btn btn-primary" id="search" onclick="searchGoods()"><span class="glyphicon glyphicon-search"></span></button>
					</div>
				</div>
				<div style="height: 400px;overflow: scroll;">
					<table id="tb_list" class="table table-striped table-hover table-bordered">
						<tr>
							<td>序号</td><td>商品名称</td><td>价格</td><td>上架时间</td><td>类型</td><td>操作</td>
						</tr>
						<c:forEach items="${goodsList}" var="goods" varStatus="i">
							<tr>
								<td>${i.count}</td>
								<td>${goods.name}</td>
								<td>${goods.price}</td>
								<td>${goods.pubDate}</td>
								<td>${goods.goodsType.name}</td>
								<td><button class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal${i.count}">修改</button>&nbsp;&nbsp;
									<button onclick="deleteGoods(${goods.id})">删除</button> &nbsp;
									<a tabindex="0" id="example${goods.id}" class="btn btn-primary btn-xs"
									role="button" data-toggle="popover"
									data-trigger="focus"
									data-placement="left"
									data-content="${goods.info}">描述</a>
									<script type="text/javascript">
										$(function(){
											$("#example${goods.id}").popover();
										})
									</script>
								</td>
							</tr>

							<!-- 弹出模态框 -->

							<div class="modal fade" tabindex="-1" role="dialog" id="myModal${i.count}">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">
												<span>&times;</span>
											</button>
											<h4 class="modal-title">修改商品</h4>
										</div>
										<form action="goods?method=updateGoods" method="post" class="form-horizontal">
											<div class="motal-body">
												<div class="form-group">
													<label class="col-sm-2 control-label">商品名称</label>
													<div class="col-sm-10">
														<input type="hidden" name="id" value="${goods.id}">
														<input type="text" name="name" class="form-control" value="${goods.name}">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">生产日期</label>
													<div class="col-sm-10">
														<input type="date" name="pubDate" class="form-control" value="${goods.pubDate}">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">商品单价</label>
													<div class="col-sm-10">
														<input type="text" name="price" class="form-control" value="${goods.price}">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">商品热度</label>
													<div class="col-sm-10">
														<input type="number" min="1" max="5" name="star" class="form-control" value="${goods.star}">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">商品描述</label>
													<div class="col-sm-10">
														<input type="text" name="info" class="form-control" value="${goods.info}">
													</div>
												</div>

											</div>
											<div class="motal-footer">
												<button type="submit" class="btn btn-primary">修改</button>
											</div>
										</form>
									</div>
								</div>
							</div>
						</c:forEach>
					</table>
				</div>
			</div>
			
		</div>
	</div>
</div>
<script type="text/javascript">
	function searchGoods() {
		var name = $("#name").val();
		var level = $("#pubDate").val();
		window.location.href="goods?method=searchGoods&name="+name+"&pubDate="+level;
	}
	function deleteGoods(id){
		window.location.href="goods?method=deleteGoods&id="+id;
	}
</script>
</body>
</html>