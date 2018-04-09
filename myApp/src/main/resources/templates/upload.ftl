<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
</head>

<body>
<form action="/base/upload" method="POST" enctype="multipart/form-data">
    <p>单文件上传：</><br/>
    <input type="file" name="file1"/>
    <input type="submit" value = "上传"/>
</form>
<div>
    ===============================================================================
</div>
<form method="POST" enctype="multipart/form-data"
      action="/base/upload">
    <p>多文件上传：</>
    <p>文件1：<input type="file" name="file" /></p>
    <p>文件2：<input type="file" name="file" /></p>
    <p><input type="submit" value="上传" /></p>
</form>
<div>
    ===============================================================================
</div>
<a href="/testDownload">下载</a>

</body>
</html>