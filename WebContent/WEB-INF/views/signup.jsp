<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create An Account</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>

<style>
html, body {
	height: 100%;
}

body {
	font-size: 14px;
	display: flex;
	flex-direction: column;
}

.true-body {
	height: 100%;
	background-color: #f8f9fa;
	padding-top: 5%;
	padding-left: 5%;
	padding-top: 5%;
}

.answers {
	height: 500px;
	overflow-y: scroll;
}

#signup-container h2 {
	text-align: center;
}

#signup-container .btn-login {
	background-color: #FF5964;
	color: #fff;
}

#signup-container h4 {
	color: black;
}

.footer {
	flex-shrink: 0;
	padding: 1rem;
	background-color: #efefef;
	text-align: center;
	bottom: 0;
}

.vl {
	top: 0;
	height: 100%;
	border-left: 3px solid black;
	width: 3px;
	border-left: 3px solid black;
}

/*over-writing Bootstrap colors */
.navbar {
	background-color: #dee2e6 !important;
}

.scroll-spy-navbar-brand {
	color: black !important;
	text-shadow: white !important;
}

.mini-nav {
	background-color: #f8f9fa !important;
}

#signup-container * a {
	text-align: center;
}
</style>

</head>
<body>

	<jsp:include page="generalHeader.jsp" />

	<div class="true-body">
		<div class="row justify-content-md-center">
			<div class="col-md-auto">
				<div id='signup-container' title="Sign-Up Form" align="center">
					<form:form action="signup" method="POST" modelAttribute="newLogin">
						<h2>
							<img src="<c:url value = "/images/logo.png"/>">
						</h2>
						<div class="error" role="alert">
							<c:if test="${alertCode != null}">
								<!-- StatusCodes with code of 2000+ are success codes -->
								<c:set
									value="${alertCode.getCode() < 2000 ? 'alert-danger' : 'alert-success'}"
									var="alertStyle" />
								<div class="alert ${alertStyle} col-md-10">
									<c:out value="${alertCode.toString()}" />
								</div>
							</c:if>
						</div>
						<div class="form-group">
							<form:input class="form-control col-md-10" id="login"
								path="username" placeholder="Username" required="true" />
						</div>
						<div class="form-group">
							<form:input class="form-control col-md-10" id="login"
								type="password" path="password" placeholder="Password"
								required="true" />
						</div>
						<div class="form-group">
							<input class="form-control col-md-10" id="login" type="password"
								name="password2" placeholder="Re-type Password" required="true" />
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<div class="col-md-5">
								<input id='btn-login' class=" btn btn-primary active"
									type="submit" value="Create Account">
							</div>
							<div class="col-md-1"></div>
							<div class="col-md-3">
								<input id='btn-reset' class=" btn btn-danger active"
									type="reset" value="Reset">
							</div>
						</div>
						<div class="form-group row">
							<div class="col-md-12">
								<a href="login">I have a login</a>
							</div>
						</div>
					</form:form>
				</div>
			</div>
			<div class="col-md-auto pr-0">
				<div class="vl"></div>
			</div>
			<div class="col-md-auto">
				<nav id="create-account-faq" class="navbar flex-column mini-nav">
				<span class="navbar-brand scroll-spy-navbar-brand">FAQ</span> <nav
					class="nav flex-column nav-fill"> <a class="nav-link"
					href="#item-1">This seems short...</a> <a class="nav-link"
					href="#item-2">Really?</a> <a class="nav-link" href="#item-3">
					I don't want your spam!</a> <a class="nav-link" href="#item-4"> So
					what should I do?</a> </nav> </nav>
			</div>
			<div class="col-md-3 answers pl-0">
				<div id="scroll-spy" data-spy="scroll"
					data-target="#create-account-faq" data-offset="0">
					<h4 id="item-1">This seems... short.</h4>
					<p>It is! And that is the beauty of it! There are so many
						websites you have accounts for. Creating another one tends to be a
						pain. Usually they ask for your name, your e-mail address,
						sometimes even your location. Who wants to give that information
						away, and right at the start of your budding relationship? Uh-huh,
						no way! We don't like it, and we get if you don't either.</p>
					<p>But that's why our registration form is simple. Just type in
						a username you like (unfortunately it does have to be different
						from the ones we have), and the desired password you want (no
						forced digits, special characters, uppercase or lowercase letters
						needed!). And you'll be on your way to your own Health and
						Wellness Tracker account.</p>
					<br>
					<h4 id="item-2">Really?</h4>
					<p>
						Yes, really... but with a disclaimer (sorry, but I have to be a
						bit Jiminy Cricket). While we don't agree with giving away
						personal identifiers (name, location, e-mail address) to any Tom,
						Harry, or Dick, it can <b><i>sometimes</i></b> prove to be
						beneficial. For example, recovering your account is made possible
						and more secure by including an e-mail address. Otherwise, there
						would be no sure way to know the person requesting the password
						change is you.
					</p>
					<p>And that example is also a bit of a warning. We won't be
						able to help if you forget or lose your password if you do not
						provide your e-mail address. Everything else a profile asks for
						(including ours) is truly unnecessary.</p>
					<br>
					<h4 id="item-3">I don't want your spam!</h4>
					<p>
						I know! I get it too (literally). If you provide your e-mail
						address, we promise (cross our digital AND physical hearts) you
						will <b>not</b> receive promotional e-mails from us. We will not
						use your e-mail address in any way that has not been explicitly
						requested by you.
					</p>
					<br>
					<h4 id="item-4">So what should I do?</h4>
					<p>Whatever you feel comfortable doing. Whether its providing
						your name, e-mail address, or nothing at all. It is your decision,
						and we respect it.</p>
					<br>
					<p>Thank you for using Health & Wellness Tracker.</p>
					<br>
				</div>
				<div class="mr-auto">
					<span>Sincerely,<br> Kirstie Rodriguez<br> Founder
						of HWT, Inc.
					</span>
				</div>
			</div>

		</div>
	</div>
	<jsp:include page="footer.jsp" />
	<script>
		$('#pageNameHeader').html(document.title);
	</script>
</body>
</html>