<form id="findForm">
    <div id="searcheFields">
        <div class="form-group edit">
            <div class="center"><label for="order-id" class="edit-field">ID</label>
            </div>
            <input type="text" class="form-control" name="order-id" id="order-id"
                   value="39">
        </div>
        <div class="form-group edit">
            <div class="center"><label for="order-date" class="edit-field">Date</label>
            </div>
            <input type="text" class="form-control searchDate" name="order-date"
                   id="order-date"
                   value="2014-09-14">
        </div>
        <div class="form-group edit">
            <div class="center"><label for="order-goods-name"
                                       class="edit-field">Goods</label></div>
            <input type="text" class="form-control" name="order-goods-name"
                   id="order-goods-name"
                   value="Water 20L">
        </div>
        <div class="form-group edit">
            <div class="center"><label for="delivery-time" class="edit-field">Goods
                count</label></div>
            <input type="text" class="form-control" name="order-goods-count"
                   id="order-goods-count"
                   value="">
        </div>
        <div class="form-group edit">
            <div class="center"><label for="delivery-time" class="edit-field">Order
                cost</label></div>
            <input type="text" class="form-control" name="order-cost" id="order-cost"
                   value="">
        </div>
        <div class="form-group edit">
            <div class="center"><label for="delivery-time" class="edit-field">Delivery
                date</label></div>
            <input type="text" class="form-control searchDate" name="delivery-date"
                   id="delivery-date"
                   value="">
        </div>
        <div class="form-group edit">
            <div class="center"><label for="delivery-time" class="edit-field">Delivery
                time</label></div>
            <input type="text" class="form-control" name="delivery-time"
                   id="delivery-time"
                   value="">
        </div>
        <div class="form-group edit">
            <div class="center"><label for="delivery-time" class="edit-field">Additional
                info</label></div>
            <input type="text" class="form-control" name="order-addInfo"
                   id="order-addInfo"
                   value="">
        </div>
        <div class="form-group edit">
            <div class="center"><label for="delivery-time"
                                       class="edit-field">status</label></div>
            <input type="text" class="form-control" name="order-status"
                   id="order-status"
                   value="">
        </div>
        <br>
        <input type="hidden" id="entityName" value="Order">
        <input type="button" class="btn btn-primary" id="search" value="Search">
    </div>
</form>