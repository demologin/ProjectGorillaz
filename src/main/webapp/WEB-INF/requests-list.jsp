<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="parts/header.jsp" %>


    <nav class="navbar navbar-expand-md bg-body" style="position: fixed;width: 100%;">
        <div class="container-fluid"><a class="navbar-brand" href="#">Brand</a><button data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navcol-1"><span class="visually-hidden">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navcol-1">
                <ul class="navbar-nav">
                    <li class="nav-item"><a class="nav-link active" href="#">First Item</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">Second Item</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">Third Item</a></li>
                </ul>
            </div>
        </div>
    </nav>
</section>
<section>
    <div class="table-responsive" style="margin-top: 78.375px;border-top-style: ridge;">
        <table class="table">
            <thead style="/*position: fixed;*/width: 100%;border-top-style: ridge;">
            <tr>
                <th style="border-width: 3px;border-style: outset;border-bottom-width: 5px;">Column 1</th>
                <th style="border-width: 3px;border-style: outset;border-bottom-width: 5px;">Column 2</th>
            </tr>
            </thead>
            <tbody style="border-top-style: ridge;">
            <tr style="border-top-style: ridge;">
                <td style="border-width: 3px;border-style: outset;">Cell 1</td>
                <td style="border-width: 3px;border-style: outset;">Cell 2</td>
            </tr>
            <tr>
                <td style="border-width: 3px;border-style: outset;">Cell 3</td>
                <td style="border-width: 3px;border-style: outset;">Cell 4</td>
            </tr>
            </tbody>
        </table>
    </div>











<%@include file="parts/footer.jsp" %>
