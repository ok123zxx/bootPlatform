<!DOCTYPE html>
<html>
<head>
    <title>下划线与驼峰命名法互相转换</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="layui/layui.js"></script>

    <!-- 引入 Bootstrap -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- HTML5 Shiv 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
    <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

    <script type="application/javascript">
        var small = false;
        function sendData(type,str){
            $.ajax({
                url:"/base/"+type+"/?str="+str+"&small="+small,
                type:"post",
                success:function(data){
                    $("textarea[name="+type+"Result]").val(data);
                }
            });
        }
        $(function () {
            $("#camel2UndelineBtn").click(function () {
                var str = $("textarea[name=camel2Undeline]").val();
                if(str.length != 0){
                    sendData("camel2Undeline",str);
                }
            });
            $("#undeline2CamelBtn").click(function () {
                var str = $("textarea[name=undeline2Camel]").val();
                if(str.length != 0){
                    sendData("undeline2Camel",str);
                }
            });

            $("textarea").on("keydown",function (event) {
                if(event.keyCode==13){
                    $(this).parent().parent().find("button").click();
                    return false;
                }
            });

            $("#small").click(function () {
                small = !small;
            });
        });


    </script>
    <style>
        textarea{
            resize: none;
        }
    </style>

    <script>
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?b354bf8e2015c68d1867036bbf0dea8f";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    下划线与驼峰命名法互相转换 <small>测试版</small>
                </h1>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <div class="col-sm-5">
                    <textarea name="camel2Undeline" class="form-control" placeholder="驼峰转下划线"></textarea>
                </div>
                <div class="col-sm-2">
                    <button type="button" class="btn btn-default" id="camel2UndelineBtn">转换</button>
                </div>
                <div class="col-sm-5">
                    <textarea name="camel2UndelineResult" class="form-control" placeholder="结果" readonly></textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-5">
                    <textarea name="undeline2Camel" class="form-control" placeholder="下划线转驼峰"></textarea>
                </div>
                <div class="col-sm-2">
                    <button type="button" class="btn btn-default" id="undeline2CamelBtn">转换</button>
                    <br>
                    <input type="checkbox" id="small"><label for="small">首字母小写</label>
                </div>
                <div class="col-sm-5">
                    <textarea name="undeline2CamelResult" class="form-control" placeholder="结果" readonly></textarea>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>

