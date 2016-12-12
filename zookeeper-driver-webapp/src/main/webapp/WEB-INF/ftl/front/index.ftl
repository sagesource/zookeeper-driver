<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Zookeeper-Driver</title>

    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="${rc.contextPath}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${rc.contextPath}/static/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${rc.contextPath}/static/front/index/css/form-elements.css">
    <link rel="stylesheet" href="${rc.contextPath}/static/front/index/css/style.css">
</head>

<body>

<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1><strong>Zookeeper-Driver</strong> Connect Page</h1>

                    <div class="description">
                        <p>
                            欢迎使用Zookeeper-Driver,首次使用请配置连接信息!
                        </p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top">
                        <div class="form-top-left">
                            <h3>Connect To Zookeeper</h3>

                            <p>请选择要连接到的ZK服务:</p>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-lock"></i>
                        </div>
                    </div>
                    <div class="form-bottom">
                        <form role="form" action="" method="post" class="login-form">
                            <div class="form-group">
                                <label class="sr-only" for="form-address">ZK服务</label>
                                <select class="form-address form-control input-lg" name="form-address"
                                        id="form-address">
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-info">服务信息</label>
                                <input type="text" name="form-info" placeholder="zookeeper 服务信息"
                                       class="form-info form-control" id="form-info" readonly>
                            </div>
                            <button type="submit" class="btn">Connect!</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>


<!-- Javascript -->
<script type="application/javascript">
    var baseContext = "${rc.contextPath}";
</script>
<script src="${rc.contextPath}/static/js/function.js"></script>
<script src="${rc.contextPath}/static/js/api.js"></script>
<script src="${rc.contextPath}/static/js/jquery.min.js"></script>
<script src="${rc.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${rc.contextPath}/static/js/jquery.backstretch.min.js"></script>
<script src="${rc.contextPath}/static/front/index/js/scripts.js"></script>
<script src="${rc.contextPath}/static/front/index/js/operation.js"></script>

</body>

