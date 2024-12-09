<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="parts/header.jsp" %>
<body>
<div class="container">
    <form class="form-horizontal" method="post">
        <fieldset>

            <!-- Form Name -->
            <legend>Edit user:</legend>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="login">Login</label>
                <div class="col-md-4">
                    <input
                            id="login"
                            name="login"
                            type="text"
                            value="${requestScope.user.login}"
                            placeholder="your login"
                            class="form-control input-md"
                            required="">
                    <span class="help-block">min 3 symbols</span>
                </div>
            </div>

            <!-- Password input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="password">Password</label>
                <div class="col-md-4">
                    <input id="password"
                           name="password"
                           type="password"
                           value="${requestScope.user.password}"
                           placeholder="your password"
                           class="form-control input-md"
                           required="">
                    <span class="help-block">min 8 symb</span>
                </div>
            </div>


            <!-- Select Basic -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="role">Role</label>
                <div class="col-md-4">
                    <select id="role" name="role" class="form-control">
                        <c:forEach var="role" items="${applicationScope.roles}">
                            <option value="${role}" ${role==requestScope.user.role?"selected":""}>${role}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <!-- Button (Double) -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="create">Operation</label>
                <div class="col-md-8">
                    <c:if test="${requestScope.user==null}">
                        <button id="create" name="create" class="btn btn-success">Create</button>
                    </c:if>
                    <c:if test="${requestScope.user!=null}">
                        <button id="update" name="update" class="btn btn-primary">Update</button>
                    </c:if>

                </div>
            </div>

        </fieldset>
    </form>
</div>
<%@include file="parts/footer.jsp" %>


