<div class="form-group rows-count floatRight">
    <label class="labelCount" for="clientsrows">Rows count</label>

    <form action="${pageContext.request.contextPath}/do/dispatcher">
        <div class="input-group rows-count-height">
            <input type="text" name="clientsrows" id="clientsrows" value="${clientsRowsCount}"
                   class="form-control textCount">
                        <span class="input-group-btn">
                            <button class="btn btn-default rows-count-height" type="submit">apply</button>
                        </span>
        </div>
    </form>
</div>