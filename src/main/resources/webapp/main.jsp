
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>查询</title>
    <!--引入jquery-->
    <script type="text/javascript" src="../static/js/jquery.js"></script>
    <!--声明js代码-->
    <script type="text/javascript">
        $(function () {
            //获取tbody对象
            var tb = $("#tb");

            $("#btn").click(function () {
                $.post("select", function (data) {
                    var date = data;
                    for(var i=0;i<data.length;i++){
                        tb.append("  <tr>\n" +
                            "        <td>"+data[i].id+"</td>\n" +
                            "        <td>"+data[i].ip+"</td>\n" +
                            "        <td>"+data[i].filename+"</td>\n" +
                            "        <td>"+

                            +data[i].file+


                            +"</td>\n" +
                            "    </tr>");
                    }
                })

            })

        })

    </script>


</head>
<body>

<div>
    <input type="button" id="btn" value="查询">
</div>

<table cellpadding="10" cellspacing="0" border="1px">
    <thead>
    <tr>
        <td>编号</td>
        <td>ip地址</td>
        <td>文件名</td>
        <td>文件</td>
    </tr>

    </thead>
    <tbody id="tb"></tbody>
</table>


</body>

</html>