<div class="panel panel-warning top-padding">
    <div class="panel-heading">Search for users</div>
        <div class="panel-body">
            <form:form cssClass="form-horizontal" method="post">
                <t:input path="user"></t:input>
                <div class="form-group">
                    <button type="submit" class="btn btn-warning">
                        <fmt:message key="general.search" bundle="${lang}"/>
                    </button>
                </div>
            </form:form>
        </div>
    <div class="panel-heading">List of searched users</div>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table">

                </table>
            </div>
        </div>
</div>
