<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <jsp:include page="/inc.jsp"/>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h1>SSM Demo</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button class="btn btn-primary" id="btnAddEmp">新增</button>
            <button class="btn btn-danger" id="btnDelEmp">删除</button>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover">
                <tr>
                    <th>序号</th>
                    <th>编号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>Email</th>
                    <th>部门</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${page.list}" var="e" varStatus="stat">
                    <tr>
                        <td>${stat.count}</td>
                        <td>${e.id}</td>
                        <td>${e.name}</td>
                        <td>${e.gender eq 'M' ?'男':'女'}</td>
                        <td>${e.email}</td>
                        <td>${e.dept.name}</td>
                        <td>
                            <button type="button" class="btn btn-sm btn-primary" aria-label="Left Align">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true" ></span>编辑
                            </button>
                            <button type="button" class="btn btn-sm btn-danger" aria-label="Left Align">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            当前第<span class="label label-info">${page.pageNum} / ${page.pages}</span>页&nbsp;&nbsp;


            总记录<span class="label label-info">${page.total}</span>条

        </div>
        <div class="col-md-6">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li>
                        <a href="${ctx}/emp/list?pageNo=1" aria-label="Previous">
                            <span aria-hidden="true">首页</span>
                        </a>
                    </li>
                    <c:if test="${page.hasPreviousPage}">
                    <li>
                        <a href="${ctx}/emp/list?pageNo=${page.prePage}" aria-label="Previous">
                            <span aria-hidden="true">上一页</span>
                        </a>
                    </li>
                    </c:if>
                    <c:forEach items="${page.navigatepageNums}" var="n">
                        <li class="<c:if test="${n eq page.pageNum}">active</c:if>" >
                            <a href="${ctx}/emp/list?pageNo=${n}">${n}</a>
                        </li>
                    </c:forEach>
                    <c:if test="${page.hasNextPage}">
                    <li>
                        <a href="${ctx}/emp/list?pageNo=${page.nextPage}" aria-label="Next">
                            <span aria-hidden="true">下一页</span>
                        </a>
                    </li>
                    </c:if>
                    <li>
                        <a href="${ctx}/emp/list?pageNo=${page.pages}" aria-label="Next">
                            <span aria-hidden="true">末页</span>
                        </a>
                    </li>
                </ul>
            </nav>

        </div>


    </div>
</div>


<!-- 编辑Employee Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">员工信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="empForm">

                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="name" placeholder="姓名" name="name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="inputEmail3" placeholder="helloz@qq.com" name="email">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" value="M"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" value="F"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">部门</label>
                        <div class="col-sm-5">
                            <select class="form-control" id="deptOption" name="deptid">

                            </select>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="btnSave">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script>
    $(function(){

        $("#btnSave").click(function(){
            var url = "${ctx}/emp"
            $.post(url,$("#empForm").serialize(),function(res){
                alert(res.msg);
                if(res.success){
                    $('#myModal').modal('hide');
                    window.location.href="${ctx}/emp/list?pageNo=${page.pages}"
                }
            },'json')
        })

        $("#btnAddEmp").click(function () {
            getDept();
            $('#myModal').modal({
                backdrop:'static'
            })
        });


    })


    function getDept() {
        $.getJSON("${ctx}/dept/getData",function(res){
            var option = '<option value="">--请选择--</option>';
            $("#deptOption").empty();
            $.each(res.data.list,function(){
                option+='<option value="'+this.id+'">'+this.name+'</option>';
            })
            $("#deptOption").append(option);
        })

    }
</script>
</body>
</html>