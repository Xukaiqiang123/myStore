<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商品列表页</title>

</head>
<body>
	<%@ include file="header.jsp"%>
	
   
<div class="panel panel-default" style="margin: 0 auto;width: 95%;">
	<div class="panel-heading">
	    <h3 class="panel-title"><span class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;商品列表</h3>
	</div>
	<div class="panel-body">
	   	   <!--列表开始-->
	    <div class="row" style="margin: 0 auto;">
	    	<c:forEach items="${gList}" var="g" varStatus="i">
		    	<div class="col-sm-3">
				    <div class="thumbnail">
				      <img src="${g.picture}" width="180" height="180"  alt="小米6" />
				      <div class="caption">
				        <h4>商品名称<a href="goods?method=getGoodsById&id=${g.id}">${g.name}</a></h4>
				        <p>热销指数
				        	<c:forEach begin="1" end="${g.star}">
				        		<img src="image/star_red.gif" alt="star"/>
				        	</c:forEach>
				        </p>
				         <p>上架日期:${g.pubDate}</p>
			             <p style="color:orange">价格:${g.price}</p>
				      </div>
				    </div>
				  </div>
	    	</c:forEach>
			  
		</div>
		<center>

			<nav aria-label="Page navigation">
				<ul class="pagination">
					<c:if test="${page.pageIndex == 1}">
						<li class="disabled">
						<span aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
						</span>
					</li>
					</c:if>
					<c:if test="${page.pageIndex > 1}">
						<li>
					<span>
					<a href="goods?method=getGoods&typeId=${param.typeId}&pageIndex=${page.pageIndex-1}" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
						</span>
						</li>
					</c:if>

					<c:forEach begin="1" end="${page.totalPages}" var="index">
						<c:if test="${page.pageIndex == index}">
							<li class="active"><a href="goods?method=getGoods&typeId=${param.typeId}&pageIndex=${index}">${index}</a></li>
						</c:if>

						<c:if test="${page.pageIndex != index}">
							<li><a href="goods?method=getGoods&typeId=${param.typeId}&pageIndex=${index}">${index}</a></li>
						</c:if>
					</c:forEach>

					<c:if test="${page.pageIndex < page.totalPages}">
					<li>
					<span>
					<a href="goods?method=getGoods&typeId=${param.typeId}&pageIndex=${page.pageIndex+1}" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
						</span>
					</li>
					</c:if>
					<c:if test="${page.pageIndex == page.totalPages}">
						<li class="disabled">
						<span aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
						</span>
						</li>
					</c:if>
				</ul>
			</nav>
		</center>
   	</div>
</div>
      <!-- 底部 -->
   <%@ include file="footer.jsp"%>
</body>
</html>