<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<title>Schedule to Reddit</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/resources/datetime-picker.css" />">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="<c:url value="/resources/datetime-picker.js" />"></script>

</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="info">Schedule to Reddit</a>
    </div>
    
    <p class="navbar-text navbar-right">Logged in as <b><c:out value="${username}"/></b>&nbsp;&nbsp;&nbsp;</p>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="posts">My Scheduled Posts</a></li>
        <li><a href="post">Post to Reddit</a></li>
        <li class="active"><a href="postSchedule">Schedule Post to Reddit</a></li>
      </ul>
      
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<div class="container">
<h1>Schedule Post to Reddit</h1>
<form action="schedule" method="post">
<div class="row">
<div class="form-group">
    <label class="col-sm-3">Title</label>
    <span class="col-sm-9"><input name="title" placeholder="title" class="form-control" required/></span>
</div>
<br><br>
<div class="form-group">
    <label class="col-sm-3">Url</label>
    <span class="col-sm-9"><input name="url" placeholder="url" class="form-control" required/></span>
</div>
<br><br>  
<div class="form-group">
    <label class="col-sm-3">Subreddit</label>
    <span class="col-sm-9"><input name="sr" placeholder="Subreddit" class="form-control" required/></span>
</div>
<br><br>
<div class="col-sm-3">
<input type="checkbox" name="sendreplies" value="true"/> Send replies to my inbox
</div>
<br><br>

<label class="col-sm-3">Submission Date</label>
<span class="col-sm-9"><input type="text" name="date" class="form-control"></span>
    <script type="text/javascript">
        $(function(){
            $('*[name=date]').appendDtpicker({"inline": true});
        });
    </script>

    <br><br>
    
    <button type="submit" class="btn btn-primary">Schedule</button>
   </div>
</form>
</div>
</body>
</html>