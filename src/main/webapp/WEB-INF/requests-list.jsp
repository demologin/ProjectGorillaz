<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="parts/header.jsp" %>

${requestScope.contragentBarTop}
${requestScope.getContragentBarData}
${requestScope.tableTop}
${requestScope.tableHeadData}
<%-- ${requestScope.tableData} --%>
${requestScope.tableLow}

<script>
    $(document).ready( function updateTable() {
        const tab = document.getElementById("reqTable");
        console.info(tab);

        $.ajax({
            type: "GET",
            url: `/rest/db?requestsToShow=${requestsToShow}`,
            success: function (message) {
                console.info(message);

                const tab = document.getElementById("reqTable");



                for (let i = 0; i < message.length; i++) {
                    let row = document.createElement("tr");
                    console.info(message[i].requestNumber);

                    row.innerHTML =
                        "<td>" + message[i].requestNumber + "</td>"+
                        "<td>" + message[i].customer + "</td>"+
                        "<td>" + message[i].phoneNumber + "</td>"+
                        "<td>" + message[i].address + "</td>" +
                        "<td>" + message[i].equipmentsTransferred[0].model + "</td>"+
                        "<td>" + message[i].equipmentsTransferred[0].serialNumber + "</td>"+
                        "<td>" + message[i].equipmentsReceived[0].model + "</td>"+
                        "<td>" + message[i].equipmentsReceived[0].serialNumber + "</td>"
                    ;




                    tab.appendChild(row);
                }

            }
        });
    })

        </script>


    <%@include file="parts/footer.jsp" %>
