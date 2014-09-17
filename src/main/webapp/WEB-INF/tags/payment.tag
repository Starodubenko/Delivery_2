<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel='stylesheet' href='<c:url value="/style/payment.css"/>'>

<div class="eWalet panel panel-default">
    <label class="balance"> Your balance: </label>
    <label> ${user.getVirtualBalance()} </label>
    <br>
    <button class="logoutbtn btn btn-primary" data-toggle="modal" data-target="#Payment">
        Top-up
    </button>
</div>

<div class="modal fade" id="Payment" tabindex="-1" role="dialog" aria-labelledby="PaymentLabel"
     aria-hidden="true">
    <form action="${pageContext.request.contextPath}/do/payment" method="post">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span
                            aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="PaymentLabel">Payment</h4>
                </div>
                <div class="paymentform">
                    <div class="form-group">
                        <div class="center">
                            <p for="SecretCode">Input the secret code of your payment card</p>
                        </div>
                        <div class="center">
                            <input type="text" name="SecretCode" placeholder="Enter the secret code"
                                   class=" center input-field form-control" id="SecretCode">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Pay</button>
                </div>
            </div>
        </div>
    </form>
</div>
