<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:import url="parts/header.jsp"/>
<body>
<div class="container">
    <form action="create-quest" method="post">

        <div class="mb-3">
            <label for="quest-name" class="form-label">Название квеста</label>
            <input name="name" type="text" class="form-control" id="quest-name" placeholder="Тут укажите имя квеста">
        </div>

        <div class="mb-3">
            <label for="questTextareaDomId" class="form-label">Содержимое квеста</label>
            <textarea name="text" class="form-control" id="questTextareaDomId" rows="10"
                      placeholder="<%@include file="./parts/quest-demo.jsp" %>"></textarea>
        </div>

        <div class=" form-group">
            <label class="col-md-4 control-label" for="submit"></label>
            <div class="col-md-4">
                <button id="submit" name="create"
                        class="btn btn-success">Создать квест
                </button>
                <button id="jruDemo" name="create"
                        class="btn btn-danger">Хочу этот пример!
                </button>
            </div>
        </div>
    </form>
</div>

<script>
    //это чисто для отладки фрагмент
    document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('jruDemo').addEventListener('click', function(event) {
            event.preventDefault(); // Предотвращаем отправку формы и заполняем форму примерными данными
            document.getElementById('quest-name').value = 'Проверим твои знания арифметики';
            document.getElementById('questTextareaDomId').value =`<%@include file="./parts/quest-math.txt"%>`;
        });
    });
</script>
<c:import url="parts/footer.jsp"/>

